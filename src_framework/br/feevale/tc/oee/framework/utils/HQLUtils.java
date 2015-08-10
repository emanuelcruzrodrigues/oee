package br.feevale.tc.oee.framework.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import br.feevale.tc.oee.framework.domain.OEEEnum;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public class HQLUtils {

	public static String createNotInCommand(String fieldName, Collection<?> collection) {
		return createInCommand(fieldName, collection, false);
	}
	
	public static String createInCommand(String fieldName, Collection<?> collection) {
		return createInCommand(fieldName, collection, true);
	}
	
	private static String createInCommand(String fieldName, Collection<?> collection, boolean isIn) {
		if (CollectionUtils.isEmpty(collection)) return (" (1=1) ");
		
		StringBuilder result = new StringBuilder();
		result.append(" ").append(fieldName).append(isIn ? "" : " not").append(" in ( ");
		List<?> list = new ArrayList<>(collection);
		for (int i = 0; i < list.size(); i++) {
			if (i > 0){
				result.append(", ");
			}
			Object obj = list.get(i);
			if (obj instanceof String){
				result.append("'").append(obj).append("'");
			}else if (obj instanceof OEEEnum){
				result.append("'").append(((OEEEnum)obj).getValue()).append("'");
			}else{
				result.append(obj);
			}
		}
		result.append(" ) ");
		return result.toString();
	}

	public static String createInCommand(String fieldName, String identificadoresSeparadosPorVirgulas, boolean numeric) {
		String[] identificadores = identificadoresSeparadosPorVirgulas.split(",");
		if (!numeric){
			return createInCommand(fieldName, Arrays.asList(identificadores));
		}
		
		List<Long> list = new ArrayList<>();
		for (String identificador : identificadores) {
			try {
				list.add(new Long(identificador));
			} catch (Exception e) {}
		}
		return createInCommand(fieldName, list);
	}
	
	public static void updateStringsToLike(Object object) {
		updateStringsToLike(object, object.getClass());
	}
	
	@SuppressWarnings("rawtypes")
	public static void updateStringsToLike(Object object, Class clazz) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getType().isAssignableFrom(String.class)) {
				String value = ReflectionUtils.invokeMethodGet(field, object);
				value = updateStringToLike(value);
				ReflectionUtils.invokeMethodSet(field, value, object);
			}
		}
		if (clazz.getSuperclass() != null){
			updateStringsToLike(object, clazz.getSuperclass());
		}
	}

	public static String updateStringToLike(String value) {
		if (StringUtils.isBlank(value)) return value;
		value = value.toUpperCase();
		value = value.replaceAll("\\*", "%");
		
		if (!value.contains("%")){
			return "%" + value + "%";
		}
		
		return value;
	}

	

}
