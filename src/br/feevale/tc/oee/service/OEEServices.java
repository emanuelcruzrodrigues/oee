package br.feevale.tc.oee.service;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class OEEServices implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@SuppressWarnings("unchecked")
	public static <T> T getService(String beanName){
		return (T) applicationContext.getBean(beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		OEEServices.applicationContext = applicationContext;
	}
	
	public static <T> Map<String, T>  getBeansOfType(Class<T> type){
		return applicationContext.getBeansOfType(type);
	}

}
