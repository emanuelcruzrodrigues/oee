package br.feevale.tc.oee.framework.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.feevale.tc.oee.framework.dao.CRUDDAOTemplate;
import br.feevale.tc.oee.framework.dao.UsuarioDAO;
import br.feevale.tc.oee.framework.domain.Usuario;

@Service
public class UsuarioService extends CRUDServiceTemplateImpl<Usuario>{
	
	@Resource
	protected UsuarioDAO usuarioDAO;

	@Override
	protected CRUDDAOTemplate<Usuario> getCRUDDAO() {
		return usuarioDAO;
	}

	public Usuario getUsuario(String nomeUsuario) {
		return usuarioDAO.getUsuario(nomeUsuario);
	}

}
