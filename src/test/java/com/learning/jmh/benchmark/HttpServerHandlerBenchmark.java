package com.learning.jmh.benchmark;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Thread)
public class HttpServerHandlerBenchmark extends BenchmarkBase {

    private Vertx vertx;
    private WebClient webClient;

    @Setup
    public void setup() {
        vertx = Vertx.vertx();
        webClient = WebClient.create(vertx);
    }

    @TearDown
    public void tearDown() {
        vertx.close();
    }

    @Benchmark
    public void handleRequestBenchmark() {
        // Simulate an HTTP request using Vert.x Web Client
        webClient.get(8888, "localhost", "/hello").send(ar -> {
            if (ar.succeeded()) {
                System.out.println(ar.result());
            } else {
                ar.cause().printStackTrace();
            }
        });
    }
}