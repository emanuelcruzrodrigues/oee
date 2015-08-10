package br.feevale.tc.oee.framework.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public class ReflectionUtils {

	public static Method getMethod(String methodName, Class<?> clazz) throws Exception {
		try {
			Method method = clazz.getDeclaredMethod(methodName);
			if (!method.isAccessible()){
				method.setAccessible(true);
			}
			return method;
		} catch (Exception e) {
			if (!clazz.getSuperclass().equals(Object.class)){
				return getMethod(methodName, clazz.getSuperclass());
			}
			throw e;
		}
	}
	
	public static Field getField(Class<?> clazz, String fieldName){
		try {
			Field field = clazz.getDeclaredField(fieldName);
			if (!field.isAccessible()){
				field.setAccessible(true);
			}
			return field;
		} catch (Exception e) {
			if (!clazz.getSuperclass().equals(Object.class)){
				return getField(clazz.getSuperclass(), fieldName);
			}
			return null;
		}
	}
	
	public static <T> T invokeField(String fieldName, Object object){
		try {
			Field field = getField(object.getClass(), fieldName);
			return invokeField(field, object);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static <T> T invokeField(Field field, Object object){
		try {
			@SuppressWarnings("unchecked")
			T t = (T) field.get(object);
			return t;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Field getFieldWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClazz){
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(annotationClazz)) return field;
		}
		if (!clazz.getSuperclass().equals(Object.class)){
			return getFieldWithAnnotation(clazz.getSuperclass(), annotationClazz);
		}
		return null;
	}
	
	public static List<Field> getFieldsWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClazz){
		List<Field> fields = new ArrayList<>();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(annotationClazz)) {
				fields.add(field);
			}
		}
		return fields;
	}

	public static Method getMethodSet(Field field, Class<?> clazz) {
		String nmMethod = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		try {
			return clazz.getDeclaredMethod(nmMethod, field.getType());
		} catch (Exception e) {
			if (!clazz.getSuperclass().equals(Object.class)){
				return getMethodSet(field, clazz.getSuperclass());
			}
			return null;
		}
	}
	
	public static Method getMethodGet(String fieldName, Class<?> clazz) {
		String nmMethod = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		try {
			Method declaredMethod = clazz.getDeclaredMethod(nmMethod);
			return declaredMethod;
		} catch (Exception e) {
			if (!clazz.getSuperclass().equals(Object.class)){
				return getMethodGet(fieldName, clazz.getSuperclass());
			}
			return null;
		}
	}

	public static <T> T invokeMethodGet(Field field, Object object){
		return invokeMethodGet(field.getName(), object);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T invokeMethodGet(String fieldName, Object object){
		if (fieldName.contains(".")){
			for (String fieldNamePart : fieldName.split("\\.")) {
				object = invokeMethodGet(fieldNamePart, object);
			}
			return (T) object;
		}
		
		if (object == null) return null;
		
		Method methodGet = getMethodGet(fieldName, object.getClass());
		if (methodGet == null) return null;
		try {
			return (T) methodGet.invoke(object);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	}
	
	public static void invokeMethodSet(String fieldName, Object value, Object object) {
		Class<? extends Object> clazz = object.getClass();
		Field field = getField(clazz, fieldName);
		invokeMethodSet(field, value, object);
	}

	public static void invokeMethodSet(Field field, Object value, Object object) {
		Class<? extends Object> clazz = object.getClass();
		try {
			Method methodSet = getMethodSet(field, clazz);
			if (methodSet != null){
				methodSet.invoke(object, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
