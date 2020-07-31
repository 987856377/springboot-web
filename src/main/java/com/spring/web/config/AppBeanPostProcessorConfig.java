package com.spring.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;

@Configuration
public class AppBeanPostProcessorConfig {

    public AppBeanPostProcessorConfig() {
        System.out.println("AppBeanPostProcessorConfig init...");
    }

    @PostConstruct
    public void init(){
        System.out.println("AppBeanPostProcessorConfig PostConstruct init...");
    }

    //加上 static 关键字提升初始化顺序
    @Lazy
    @DependsOn("beanFactoryPostProcessor")
    @Bean
    static BeanPostProcessor beanPostProcessor(){
        return new BeanPostProcessorHandler();
    }

}

// BeanPostProcessorHandler 作为后置处理器应该先被初始化, AppBeanPostProcessorConfig 仅是普通的 bean , 初始化理应靠后
class BeanPostProcessorHandler implements BeanPostProcessor{
    public BeanPostProcessorHandler() {
        System.out.println("BeanPostProcessorHandler init...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
