package br.feevale.tc.oee.framework.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.framework.domain.Usuario;

@Repository
public class UsuarioDAO extends CRUDDAOTemplateImpl<Usuario>{

	@Override
	protected Class<Usuario> getBeanClazz() {
		return Usuario.class;
	}

	@Override
	protected void initialize(Usuario t) {}

	public Usuario getUsuario(String nomeUsuario) {
		if (StringUtils.isBlank(nomeUsuario)) return null;
		return dao.uniqueResult("select usua from Usuario usua where usua.nome = ? ", nomeUsuario);
	}

}
