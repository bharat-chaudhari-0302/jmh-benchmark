package com.learning.jmh.benchmark;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      if (req.method().name().equals("GET") && req.path().equals("/hello")) {
        req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello from Vert.x!");
      } else {
        req.response().setStatusCode(404).end();
      }
    }).listen(8888).onComplete(http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  public static void main(String[] args) {
    // Deploy the MainVerticle
    var vertx = io.vertx.core.Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }
}