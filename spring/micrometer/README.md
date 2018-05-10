# Prometheus

https://prometheus.io/download

setup prometheus pull endpoint

${prometheus_home}/prometheus.ymn
```yml
scrape_configs:
    - job_name: 'spring-boot-actuator'
      metrics_path: '/actuator/prometheus'
      static_configs:
        - targets: ['localhost:8080']
```
