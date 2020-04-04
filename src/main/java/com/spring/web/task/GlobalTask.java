package com.spring.web.task;

import cn.hutool.system.RuntimeInfo;
import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.TimerTask;

/**
 * @Description
 * @Project springboot-web
 * @Package com.spring.web.task
 * @Author xuzhenkui
 * @Date 2020/4/4 9:51
 */
public class GlobalTask extends TimerTask {

    Logger logger = LoggerFactory.getLogger(GlobalTask.class);

    double Mb = 1024 * 1024 * 1.0;
    double Gb = 1024 * 1024 * 1024 * 1.0;

    // jvm
    double jvmMaxMemory = getDoubleValue(Runtime.getRuntime().maxMemory() / Mb);
    double jvmTotalMemory = getDoubleValue(Runtime.getRuntime().totalMemory() / Mb);
    double jvmFreeMemory = getDoubleValue(Runtime.getRuntime().freeMemory() / Mb);
    double jvmUsableMemory = getDoubleValue((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / Mb);

    // os
    OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
            .getOperatingSystemMXBean();
    double osTotalMemory = getDoubleValue(osmxb.getTotalPhysicalMemorySize() / Gb);
    double osFreeMemory = getDoubleValue(osmxb.getFreePhysicalMemorySize() / Gb);
    double osUsableMemory = getDoubleValue((osmxb.getTotalPhysicalMemorySize() - osmxb
            .getFreePhysicalMemorySize()) / Gb);

    @Override
    public void run() {
        logger.info("JVM 运行时信息: " + "[最大内存: " + jvmMaxMemory
                +"Mb] [已分配内存: " + jvmTotalMemory + "Mb] [已分配内存中的剩余空间: "
                + jvmFreeMemory + "Mb] [最大可用内存: " + jvmUsableMemory + "Mb]");

        logger.info("OS 运行时信息: " + "[已分配内存: " + osTotalMemory + "Gb] [已分配内存中的剩余空间: "
                + osFreeMemory + "Gb] [最大可用内存: " + osUsableMemory + "Gb]");

    }

    private static double getDoubleValue(double d){
        return new BigDecimal(d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
