package br.feevale.tc.oee.framework.validation;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class OEEValidationResult implements Serializable{
	
	private List<OEEValidationMessage> errors;
	
	public OEEValidationResult(Throwable e) {
		this();
		if (e instanceof DataIntegrityViolationException){
			DataIntegrityViolationException dataIntegrityViolationException = (DataIntegrityViolationException)e;
			if (dataIntegrityViolationException.getLocalizedMessage().contains("could not delete")){
				addError(new OEEValidationMessage("REGISTRO_EM_USO_NAO_PODE_SER_EXCLUIDO"));
			}else{
				addError(new OEEValidationMessage("ERRO_INESPERADO"));
			}
			
		}else if (e instanceof org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException){
			addError(new OEEValidationMessage("REGISTRO_ALTERADO_POR_OUTRO_USUARIO"));
		}else{
			addError(new OEEValidationMessage("ERRO_INESPERADO"));
		}
	}
	
	public OEEValidationResult() {
		errors = new ArrayList<>();
	}

	public void addError(String error) {
		addError(new OEEValidationMessage(error));
	}
	
	public void addFieldNotNullError(String field){
		addError(new OEEValidationMessage("CAMPO_X_OBRIGATORIO", new String[]{field}));
	}
	
	public void addFieldNotNullError(String field, String id){
		addError(new OEEValidationMessage("CAMPO_X_OBRIGATORIO", new String[]{field}, id));
	}
	
	public void addFieldCharacterLimitError(String field, String limit, String id) {
		addError(new OEEValidationMessage("CAMPO_X_ULTRAPASSOU_LIMITE_DE_Y_CARACTERES", new String[]{field, limit}, id));
	}
	
	public void addFieldLimitError(String field, String limit, String id) {
		addError(new OEEValidationMessage("CAMPO_X_ULTRAPASSOU_TAMANHO_MAXIMO_DE_Y", new String[]{field, limit}, id));
	}

	public void validateStringField(String value, String field, Integer limit, String id){
		if (StringUtils.isBlank(value)){
			addFieldNotNullError(field, id);
		}else if (value.length() > limit){
			addFieldCharacterLimitError(field, limit.toString(), id);
		}
	}
	
	public void validateNumberField(Number value, String field, Number limit, String id){
		if (value == null){
			addFieldNotNullError(field, id);
		}else if (value.doubleValue() > limit.doubleValue()){
			addFieldLimitError(field, new DecimalFormat("###,###.000000").format(limit.doubleValue()), id);
		}
	}
	
	public void addError(OEEValidationMessage error){
		errors.add(error);
	}
	
	public void addErrors(List<OEEValidationMessage> messages) {
		errors.addAll(messages);
	}

	public boolean hasErrors() {
		return errors.size() > 0;
	}

	public List<OEEValidationMessage> getErrors() {
		return errors;
	}
	
	public boolean hasError(String id){
		for (OEEValidationMessage oeeValidationMessage : getErrors()) {
			if (id.equals(oeeValidationMessage.getComponentId())) return true;
		}
		return false;
	}


}
