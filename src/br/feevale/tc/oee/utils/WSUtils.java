package br.feevale.tc.oee.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import br.feevale.tc.oee.framework.exceptions.OEEException;
import br.feevale.tc.oee.framework.i18n.DefaultMessages;
import br.feevale.tc.oee.framework.validation.OEEValidationMessage;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class WSUtils {

	public static void updateErrors(Object result, Throwable exception) {
		if (exception instanceof OEEException){
			updateErrors(result, (OEEException)exception);
			
		}else{
			exception = getOriginalException(exception);
			
			StringBuilder stackTrace = new StringBuilder();
			stackTrace.append(exception.getMessage()).append("\n\n");
			for (StackTraceElement stackElement : exception.getStackTrace()) {
				stackTrace.append(stackElement.toString()).append("\n");
			}
			
//			result.setErrors(stackTrace.toString());
		}
	}
	
	public static void updateErrors(Object result, OEEException e) {
		OEEValidationResult validationResult = e.getValidationResult();
		if (validationResult == null || !validationResult.hasErrors()) return;
		
		StringBuilder errors = new StringBuilder();
		for (OEEValidationMessage error : validationResult.getErrors()){
			errors.append(DefaultMessages.get(Locale.getDefault(), error)).append("\n");
		}
		
//		result.setErrors(errors.toString());
	}
	
	public static Throwable getOriginalException(Throwable exception){
		
		boolean found = false;
		
		while(!found){
			
			found = true;
			
			if (exception instanceof InvocationTargetException){
				exception = ((InvocationTargetException)exception).getTargetException();
				found = false;
			}
			
			if(exception.getCause() != null && exception.getCause() != exception){
				exception = exception.getCause();
				found = false;
			}
		}

		return exception;
	}

}
