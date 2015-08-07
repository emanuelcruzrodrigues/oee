package br.feevale.tc.oee.i18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public class DefaultMessages {
	
	public static final String BUNDLE_LOCATION = "br.feevale.tc.oee.i18n.i18n";
	
	public static String get(String meaningKey) {
		return get(meaningKey, Locale.getDefault(), false, (String[])null);	
	}
	
	public static String get(String meaningKey, String... parameters) {
		return get(meaningKey, Locale.getDefault(), false, parameters);	
	}
	
	public static String get(String meaningKey, boolean parametrosTraduzidos, String... parameters) {
		return get(meaningKey, Locale.getDefault(), parametrosTraduzidos, parameters);	
	}

	public static String get(String meaningKey, Locale locale, boolean parametrosTraduzidos, String... parameters) {
		try {
			ResourceBundle bundle = getResourceBundle(locale);
			String message = bundle.getString(meaningKey);
			if (parameters != null && parameters.length > 0){
				if (!parametrosTraduzidos){
					parameters = translateParameters(parameters);
				}
				message = String.format(message, (Object[])parameters);
			}
			
			if (message.contains("\\n")){
				message = message.replace("\\n", "\n");
			}
			
			return message;
		} catch (RuntimeException e) {
			Logger.getLogger(DefaultMessages.class).warn("### Mensagem sem traducao => " + meaningKey);
			return meaningKey;
		}
	}
	
	private static String[] translateParameters(String[] parameters) {
		List<String> result = new ArrayList<>();
		for (String parameter : parameters) {
			result.add(get(parameter));
		}
		return result.toArray(new String[]{});
	}

	public static ResourceBundle getResourceBundle() {
		return getResourceBundle(Locale.getDefault());
	}
	
	public static ResourceBundle getResourceBundle(Locale locale) {
		return ResourceBundle.getBundle(BUNDLE_LOCATION, locale);
	}

}
