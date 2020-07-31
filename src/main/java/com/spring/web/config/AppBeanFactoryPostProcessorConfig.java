package com.spring.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AppBeanFactoryPostProcessorConfig {

    @Autowired
    private Parent parent;

    public AppBeanFactoryPostProcessorConfig() {
        System.out.println("AppBeanFactoryPostProcessorConfig init...");
    }

    @PostConstruct
    public void init(){
        System.out.println("AppBeanFactoryPostProcessorConfig PostConstruct init..." + parent);
    }

    //加上 static 关键字提升初始化顺序
    @Bean
    static BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return new BeanFactoryPostProcessorHandler();
    }
}

// BeanFactoryPostProcessorHandler 作为后置处理器应该先被初始化, AppBeanFactoryPostProcessorConfig 仅是普通的 bean , 初始化理应靠后
class BeanFactoryPostProcessorHandler implements BeanFactoryPostProcessor{

    public BeanFactoryPostProcessorHandler() {
        System.out.println("BeanFactoryPostProcessorHandler init...");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
