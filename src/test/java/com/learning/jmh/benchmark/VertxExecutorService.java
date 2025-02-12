package com.learning.jmh.benchmark;

import io.vertx.core.spi.VertxThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class VertxExecutorService extends ThreadPoolExecutor {

    public VertxExecutorService(int maxThreads, String prefix) {
        super(maxThreads, maxThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                r -> VertxThreadFactory.INSTANCE.newVertxThread(r, prefix, false, 10000, TimeUnit.NANOSECONDS));
    }
}
