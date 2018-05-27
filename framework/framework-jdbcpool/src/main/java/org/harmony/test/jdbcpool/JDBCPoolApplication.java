package org.harmony.test.jdbcpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.Servlet;
import javax.sql.DataSource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @author wuxii@foxmail.com
 */
@SpringBootApplication
public class JDBCPoolApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JDBCPoolApplication.class, args);
	}

	@Bean
	DataSource dataSource() throws SQLException {
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl("jdbc:h2:file:~/.harmony/test");
		ds.setUsername("root");
		ds.setPassword(null);
		ds.getProxyFilters().add(new StatFilter());
		return ds;
	}

	@Bean
	FilterRegistrationBean webStatFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		WebStatFilter filter = new WebStatFilter();
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/*");
		registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return registrationBean;
	}

	@Bean
	ServletRegistrationBean<Servlet> statViewServlet() {
		ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>();
		registrationBean.setUrlMappings(Arrays.asList("/druid/*"));
		registrationBean.setName("druidStatViewServlet");
		registrationBean.setServlet(new StatViewServlet());
		return registrationBean;
	}

	@Bean
	CommandLineRunner runner(DataSource ds) {
		return args -> {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"create table if not exists users(username varchar(20) primary key, password varchar(20));");
			ps.execute();
			ps.close();
			conn.close();
		};
	}

	@RestController
	@RequestMapping("/")
	class IndexController {

		@Autowired
		private DataSource ds;

		@RequestMapping
		public ResponseEntity<String> index() throws Exception {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("select count(*) from users");
			ResultSet resultSet = ps.executeQuery();
			int count = -1;
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			resultSet.close();
			ps.close();
			conn.close();
			return ResponseEntity.ok().body("user count " + count);
		}

		@RequestMapping("/create")
		public ResponseEntity<String> create(@RequestParam String username) throws Exception {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into users(username, password) values(?1, ?2)");
			ps.setString(1, username);
			ps.setString(2, RandomStringUtils.randomAlphabetic(6));
			ps.execute();
			ps.close();
			conn.close();
			return ResponseEntity.ok().body("create user " + username);
		}

	}

}
