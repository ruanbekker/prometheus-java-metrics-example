# prometheus-java-metrics-example
Demonstration for instrumentation of a Java app with Prometheus

## Usage


### Start the application

Build and run:

```
$ docker-compose build
$ docker-compose run
```

Test the application:

```
$ curl -i http://localhost:8080/
HTTP/1.1 200
X-Application-Context: prometheus-java-metrics-example
Content-Type: text/plain;charset=UTF-8
Content-Length: 8
Date: Wed, 02 Sep 2020 15:24:27 GMT

Heeeey!
```

## Make Requests

Make 10 GET requests:

```
$ for x in {1..10}; do curl -i http://localhost:8080/; sleep 0.2; done
```

Make 2 GET requests to endpoints that dont exist:

```
$ curl http://localhost:8080/foobar
$ curl http://localhost:8080/foobar2
```

## Prometheus Endpoint

Make a request against the `/prometheus` endpoint, this will be the endpoint that prometheus scrapes and ingests the metrics into it's timeseries database:

```
$ curl http://localhost:8080/prometheus

# HELP requests_total Total number of requests
# TYPE requests_total counter
requests_total 10.0
# HELP httpsessions_max httpsessions_max
# TYPE httpsessions_max gauge
httpsessions_max -1.0
# HELP httpsessions_active httpsessions_active
# TYPE httpsessions_active gauge
httpsessions_active 0.0
# HELP mem mem
# TYPE mem gauge
mem 218782.0
# HELP mem_free mem_free
# TYPE mem_free gauge
mem_free 63361.0
# HELP processors processors
# TYPE processors gauge
processors 6.0
# HELP instance_uptime instance_uptime
# TYPE instance_uptime gauge
instance_uptime 159385.0
# HELP uptime uptime
# TYPE uptime gauge
uptime 163632.0
# HELP systemload_average systemload_average
# TYPE systemload_average gauge
systemload_average 0.39
# HELP heap_committed heap_committed
# TYPE heap_committed gauge
heap_committed 168960.0
# HELP heap_init heap_init
# TYPE heap_init gauge
heap_init 32768.0
# HELP heap_used heap_used
# TYPE heap_used gauge
heap_used 105598.0
# HELP heap heap
# TYPE heap gauge
heap 453632.0
# HELP nonheap_committed nonheap_committed
# TYPE nonheap_committed gauge
nonheap_committed 52896.0
# HELP nonheap_init nonheap_init
# TYPE nonheap_init gauge
nonheap_init 2496.0
# HELP nonheap_used nonheap_used
# TYPE nonheap_used gauge
nonheap_used 49823.0
# HELP nonheap nonheap
# TYPE nonheap gauge
nonheap 0.0
# HELP threads_peak threads_peak
# TYPE threads_peak gauge
threads_peak 23.0
# HELP threads_daemon threads_daemon
# TYPE threads_daemon gauge
threads_daemon 19.0
# HELP threads_totalStarted threads_totalStarted
# TYPE threads_totalStarted gauge
threads_totalStarted 26.0
# HELP threads threads
# TYPE threads gauge
threads 21.0
# HELP classes classes
# TYPE classes gauge
classes 6264.0
# HELP classes_loaded classes_loaded
# TYPE classes_loaded gauge
classes_loaded 6264.0
# HELP classes_unloaded classes_unloaded
# TYPE classes_unloaded gauge
classes_unloaded 0.0
# HELP gc_ps_scavenge_count gc_ps_scavenge_count
# TYPE gc_ps_scavenge_count gauge
gc_ps_scavenge_count 12.0
# HELP gc_ps_scavenge_time gc_ps_scavenge_time
# TYPE gc_ps_scavenge_time gauge
gc_ps_scavenge_time 75.0
# HELP gc_ps_marksweep_count gc_ps_marksweep_count
# TYPE gc_ps_marksweep_count gauge
gc_ps_marksweep_count 2.0
# HELP gc_ps_marksweep_time gc_ps_marksweep_time
# TYPE gc_ps_marksweep_time gauge
gc_ps_marksweep_time 130.0
# HELP gauge_response_root gauge_response_root
# TYPE gauge_response_root gauge
gauge_response_root 2.0
# HELP gauge_response_star_star gauge_response_star_star
# TYPE gauge_response_star_star gauge
gauge_response_star_star 2.0
# HELP counter_status_200_root counter_status_200_root
# TYPE counter_status_200_root gauge
counter_status_200_root 10.0
# HELP counter_status_404_star_star counter_status_404_star_star
# TYPE counter_status_404_star_star gauge
counter_status_404_star_star 2.0
# HELP requests_latency_seconds Request latency in seconds
# TYPE requests_latency_seconds histogram
requests_latency_seconds_bucket{le="0.005",} 10.0
requests_latency_seconds_bucket{le="0.01",} 10.0
requests_latency_seconds_bucket{le="0.025",} 10.0
requests_latency_seconds_bucket{le="0.05",} 10.0
requests_latency_seconds_bucket{le="0.075",} 10.0
requests_latency_seconds_bucket{le="0.1",} 10.0
requests_latency_seconds_bucket{le="0.25",} 10.0
requests_latency_seconds_bucket{le="0.5",} 10.0
requests_latency_seconds_bucket{le="0.75",} 10.0
requests_latency_seconds_bucket{le="1.0",} 10.0
requests_latency_seconds_bucket{le="2.5",} 10.0
requests_latency_seconds_bucket{le="5.0",} 10.0
requests_latency_seconds_bucket{le="7.5",} 10.0
requests_latency_seconds_bucket{le="10.0",} 10.0
requests_latency_seconds_bucket{le="+Inf",} 10.0
requests_latency_seconds_count 10.0
requests_latency_seconds_sum 1.0300000000000001E-4
```

### Dashboarding

[TODO]

## More

We can also scrape metrics from micrometer, which I believe is the default metrics export engine for spring applications:

- [spring-boot-actuator-metrics](https://www.callicoder.com/spring-boot-actuator-metrics-monitoring-dashboard-prometheus-grafana/)

## Resources

- https://www.robustperception.io/instrumenting-java-with-prometheus
- https://blog.kubernauts.io/https-blog-kubernauts-io-monitoring-java-spring-boot-applications-with-prometheus-part-1-c0512f2acd7b
