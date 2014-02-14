package com.bluecloud.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component("beanFactoryComponent")
public class BeanFactoryComponent implements BeanFactoryAware {

	private static BeanFactory beanFactory; 
	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		BeanFactoryComponent.beanFactory = beanFactory;
	}
	
	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}
}
