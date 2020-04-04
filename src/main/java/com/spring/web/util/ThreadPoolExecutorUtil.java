package com.spring.web.util;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Description
 * @Project springboot-web
 * @Package com.spring.web.util
 * @Author xuzhenkui
 * @Date 2020/4/4 10:59
 */
public class ThreadPoolExecutorUtil {

    private final static int CORE_POOL_SIZE = 3;

    private final static int MAXIMUM_POOL_SIZE = 6;

    private final static long KEEP_ALIVE_TIME = 3 * 60;

    private final static TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private final static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

    private final static ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNamePrefix("Thread-pool-%d")
            .build();

    private static ExecutorService threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME, TIME_UNIT,
            workQueue, nameThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static ExecutorService getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
}
