package com.example.redis;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@Log
@EnableCaching
@EnableRedisHttpSession
@SpringBootApplication
public class RedisApplication {

    static final Logger log = LoggerFactory.getLogger(RedisApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    private ApplicationRunner titleRunner(String title, ApplicationRunner rr) {
        return args -> {
            log.info(title.toUpperCase() + ":");
            rr.run(args);
        };
    }

    // redis config
    // @Bean
    // RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    // RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    // redisTemplate.setConnectionFactory(redisConnectionFactory);
    // redisTemplate.setKeySerializer(serializer);
    // redisTemplate.setValueSerializer(serializer);
    // redisTemplate.setHashKeySerializer(hashKeySerializer);
    // redisTemplate.setHashValueSerializer(hashValueSerializer);
    // return redisTemplate;
    // }

    // redis test

    @Bean
    ApplicationRunner runner(RedisTemplate<String, String> rt) {
        return titleRunner("test redis", args -> {
            rt.keys("*").forEach(k -> {
                log.info("got key: " + k);
            });
        });
    }

    // repository cache

    @Bean
    ApplicationRunner repositories(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        return titleRunner("repositories", args -> {
            // init
            Long orderId = generateId();
            List<OrderItem> items = Arrays.asList(//
                    new OrderItem(generateId(), orderId, "first-item"), //
                    new OrderItem(generateId(), orderId, "second-item"), //
                    new OrderItem(generateId(), orderId, "third-item")//
            );
            items.stream().map(orderItemRepository::save).forEach(e -> log.info("save order item: " + e));

            Order order = orderRepository.save(new Order(orderId, new Date(), items));

            Collection<Order> found = orderRepository.findByWhen(order.getWhen());
            found.forEach(e -> log.info("found " + order));
        });
    }

    // redis pub/sub
    private static final String CHANNEL = "chat";

    @Bean
    RedisMessageListenerContainer listener(RedisConnectionFactory cf) {
        RedisMessageListenerContainer mlc = new RedisMessageListenerContainer();
        mlc.setConnectionFactory(cf);
        mlc.addMessageListener((m, b) -> {
            log.info("receive message " + new String(m.getBody()));
        }, new PatternTopic(CHANNEL));
        return mlc;
    }

    @Bean
    ApplicationRunner pubSub(RedisTemplate<String, String> rt) {
        return titleRunner("redis pub/sub", args -> {
            rt.convertAndSend(CHANNEL, "hello world at " + Instant.now());
        });
    }

    // caching
    // @EnableCaching
    @Bean
    CacheManager redisCacheManager(RedisConnectionFactory cf) {
        // use case OrderService
        return RedisCacheManager.builder(cf).build();
    }

    @Bean
    ApplicationRunner caching(OrderService service) {
        return titleRunner("caching", args -> {
            Long id = generateId();
            Order order = new Order(id, new Date(), Collections.emptyList());
            service.update(order);

            log.info("first time find by id use: " + use(service, id));
            log.info("second time find by id use: " + use(service, id));

            order.setWhen(new Date(System.currentTimeMillis() + 9999));
            service.update(order);

            log.info("thrid time find by id use: " + use(service, id));
        });
    }

    private long use(OrderService service, Long id) {
        long start = System.currentTimeMillis();
        service.byId(id);
        return System.currentTimeMillis() - start;
    }

    private Long generateId() {
        return Math.abs(new Random().nextLong());
    }

    // caching http session

}

// 将应用在多端口启动，访问对应端口的应用，在同一个user-agent中将返回同样的sessionId(即实现了session共享)
@RestController
class EchoController {

    @GetMapping("/echo")
    public String echo(HttpSession session) {
        return "sessionId: " + session.getId();
    }

}

@Service
class OrderService {

    @Autowired
    private OrderRepository orderReposoitory;

    @CacheEvict(value = "order-by-id", key = "#order.getId()")
    public Order update(Order order) {
        return orderReposoitory.save(order);
    }

    @Cacheable("order-by-id")
    public Order byId(Long id) {
        RedisApplication.log.info("order service find order by id(" + id + ") from db");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Optional<Order> order = orderReposoitory.findById(id);
        return order.isPresent() ? order.get() : null;
    }

}

interface OrderRepository extends CrudRepository<Order, Long> {

    Collection<Order> findByWhen(Date when);
}

interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}

@RedisHash("orders")
class Order implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private Date when;

    @Reference
    private List<OrderItem> items;

    public Order() {
    }

    public Order(Long id, Date when, List<OrderItem> items) {
        this.id = id;
        this.when = when;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order {id:" + id + ", when:" + when + ", items:" + items + "}";
    }
}

@RedisHash("orderItems")
class OrderItem implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    @Indexed
    private Long orderId;
    private String description;

    public OrderItem(Long id, Long orderId, String description) {
        this.id = id;
        this.orderId = orderId;
        this.description = description;
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderItem {id:" + id + ", orderId:" + orderId + ", description:" + description + "}";
    }

}