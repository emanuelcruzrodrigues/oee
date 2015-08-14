package br.feevale.tc.oee.framework.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.feevale.tc.oee.framework.domain.Login;
import br.feevale.tc.oee.framework.domain.Usuario;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Service
public class LoginService {
	
	@Resource
	protected UsuarioService usuarioService;

	@Transactional
	public Usuario executeLogin(Login login) {
		
		Usuario usuario = usuarioService.getUsuario(login.getNomeUsuario());
		if (usuario == null) return null;
		
		if (usuario.getSenha() == null){
			usuario.setSenha(login.getSenha().hashCode());
			usuario = usuarioService.save(usuario);
		
		}else if (!usuario.getSenha().equals(login.getSenha().hashCode())){
			return null;
		}
		
		return usuario;
		
	}
	

}
