package com.spring.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AppBeanDefinitionRegistryPostProcessorConfig {

    public AppBeanDefinitionRegistryPostProcessorConfig() {
        System.out.println("AppBeanDefinitionRegistryPostProcessorConfig init...");
    }

    @PostConstruct
    public void init(){
        System.out.println("AppBeanDefinitionRegistryPostProcessorConfig PostConstruct init...");
    }

    //加上 static 关键字提升初始化顺序
    @Bean
    static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor(){
        return new BeanDefinitionRegistryPostProcessorHandler();
    }

    @Bean
    Parent parent(){
        return new Parent();
    }

    @Bean
    Son son(){
        return new Son(parent());
    }
}

// BeanDefinitionRegistryPostProcessorHandler 作为后置处理器应该先被初始化, AppBeanDefinitionRegistryPostProcessorConfig 仅是普通的 bean , 初始化理应靠后
class BeanDefinitionRegistryPostProcessorHandler implements BeanDefinitionRegistryPostProcessor{

    public BeanDefinitionRegistryPostProcessorHandler() {
        System.out.println("BeanDefinitionRegistryPostProcessorHandler init...");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}

class Parent{
    public Parent() {
        System.out.println("Parent init...");
    }
}


class Son extends Parent{
    public Son(Parent parent) {
        System.out.println("Parent and Son init...");
    }
}