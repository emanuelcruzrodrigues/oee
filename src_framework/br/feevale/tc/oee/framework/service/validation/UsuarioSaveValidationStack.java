package br.feevale.tc.oee.framework.service.validation;

import java.util.ArrayList;
import java.util.List;

import br.feevale.tc.oee.framework.domain.Usuario;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationHandler;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;

public class UsuarioSaveValidationStack extends OEEValidationStack {

	private Usuario usuario;
	private OEEValidationHandler handler;

	public UsuarioSaveValidationStack(Usuario usuario, OEEValidationHandler handler) {
		this.usuario = usuario;
		this.handler = handler;
	}

	@Override
	public OEEValidationHandler getHandler() {
		return handler;
	}

	@Override
	public List<OEEValidation> getValidations() {
		List<OEEValidation> validations = new ArrayList<>();
		validations.add(new UsuarioCamposObrigatoriosValidation(usuario));
		return validations;
	}

}
