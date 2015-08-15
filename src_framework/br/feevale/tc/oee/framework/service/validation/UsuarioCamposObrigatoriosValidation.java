package br.feevale.tc.oee.framework.service.validation;

import br.feevale.tc.oee.framework.domain.Usuario;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class UsuarioCamposObrigatoriosValidation implements OEEValidation {

	private Usuario usuario;

	public UsuarioCamposObrigatoriosValidation(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public void validate(OEEValidationResult result) {
		
		result.validateStringField(usuario.getNome(), "NOME", 100, "nome");
		
	}

}
