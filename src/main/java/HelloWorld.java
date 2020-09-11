package com.github.mamamoney.prometheus_java_example.springboot.basic;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

@SpringBootApplication
@RestController
@EnablePrometheusEndpoint // my default prometheus uses /metrics but already in use by actuator, using /prometheus
@EnableSpringBootMetricsCollector

public class HelloWorld {

    static final Counter requests = Counter.build() // define a counter metric for /prometheus
        .name("requests_total")
        .help("Total number of requests")
        .register();
    static final Histogram requestLatency = Histogram.build() // define a histogram metric for /prometheus
        .name("requests_latency_seconds")
        .help("Request latency in seconds")
        .register();
	@RequestMapping("/")
	String home() {
        requests.inc(); // increase the counter metric
        Histogram.Timer requestTimer = requestLatency.startTimer(); // starts the histogram timer
        try {
		    return "Heeeey!";
        } finally {
            requestTimer.observeDuration(); // stops the histogram timer
        }
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HelloWorld.class, args);
	}

}
