package com.spring.web.listener;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.spring.web.filter.GlobalRequestFilter;
import com.spring.web.task.GlobalTask;
import com.spring.web.util.ThreadPoolExecutorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @Description
 * @Project springboot-web
 * @Package com.spring.web.listener
 * @Author xuzhenkui
 * @Date 2020/4/4 9:40
 */
@WebListener
public class GlobalRequestListener implements ServletContextListener {

    Logger logger = LoggerFactory.getLogger(GlobalRequestListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("[ GlobalRequestListener Initialized ]");
        // 当监听开始执行时,设置一个TIME
        Timer timer = new Timer();
        // 设定执行的时间点
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();

        logger.info("-------GlobalTask   Timer 准备执行--------------");
        GlobalTask absenceRecordTask = new GlobalTask();
//        timer.schedule(absenceRecordTask, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
//        timer.schedule(absenceRecordTask, 0, 1000 * 10);    // 测试使用, 每 10s 执行一次
        ThreadPoolExecutorUtil.getThreadPoolExecutor().execute(() -> {
            timer.schedule(absenceRecordTask, 0, 1000 * 60 * 60);
        });
        logger.info("-------GlobalTask   Timer 正在执行--------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("[ GlobalRequestListener Destroyed ]");
    }
}
