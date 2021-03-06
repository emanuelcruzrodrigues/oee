package web.oee.framework.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.feevale.tc.oee.framework.domain.Usuario;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.framework.service.UsuarioService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController extends CRUDControllerImpl<Usuario>{
	
	@Resource
	protected UsuarioService usuarioService;

	@Override
	protected void updateExampleBean(Usuario example, HttpServletRequest request) {}

	@Override
	protected Usuario getNewInstance(HttpServletRequest request) {
		return new Usuario();
	}

	@Override
	protected CRUDServiceTemplate<Usuario> getService() {
		return usuarioService;
	}

}
