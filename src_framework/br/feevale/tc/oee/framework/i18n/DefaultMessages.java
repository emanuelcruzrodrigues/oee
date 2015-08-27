package br.feevale.tc.oee.framework.i18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import br.feevale.tc.oee.framework.validation.OEEValidationMessage;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public class DefaultMessages {
	
	public static final String BUNDLE_LOCATION = "br.feevale.tc.oee.framework.i18n.i18n";
	
	public static ResourceBundle getResourceBundle(Locale locale) {
		return ResourceBundle.getBundle(BUNDLE_LOCATION, locale);
	}
	
	public static String get(HttpServletRequest request, OEEValidationMessage message){
		List<String> parameters = new ArrayList<>(message.getParameters().length);
		for (String parameter : message.getParameters()) {
			parameters.add(get(request, parameter));
		}
		return get(request, message.getMessage(), parameters.toArray());
	}
	
	public static String get(HttpServletRequest request, String meaningKey){
		return get(request, meaningKey, new Object[]{});
	}
	
	public static String get(HttpServletRequest request, String meaningKey, Object[] arguments){
		try {
			WebApplicationContext context = RequestContextUtils.getWebApplicationContext(request);
			String result = String.format(context.getMessage(meaningKey, null, RequestContextUtils.getLocale(request)), arguments);
			return result;
		} catch (Throwable e) {
			return meaningKey;
		}
	}
	
	public static String get(Locale locale, OEEValidationMessage message){
		List<String> parameters = new ArrayList<>(message.getParameters().length);
		for (String parameter : message.getParameters()) {
			parameters.add(get(locale, parameter));
		}
		return get(locale, message.getMessage(), parameters.toArray());
	}
	
	public static String get(Locale locale, String meaningKey) {
		return get(locale, meaningKey, new Object[]{});
	}
	
	public static String get(Locale locale, String meaningKey, Object[] parameters) {
		try {
			ResourceBundle bundle = getResourceBundle(locale);
			String message = bundle.getString(meaningKey);
			if (parameters != null && parameters.length > 0){
				message = String.format(message, (Object[])parameters);
			}
			
			if (message.contains("\\n")){
				message = message.replace("\\n", "\n");
			}
			
			return message;
		} catch (RuntimeException e) {
			return meaningKey;
		}
	}
	

}
