package web.oee.framework.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.feevale.tc.oee.framework.exceptions.OEEException;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 10/08/2015
 * @param <T>
 */
public abstract class CRUDControllerImpl<T extends Serializable> implements CRUDController<T> {
	
	private String beanName;
	
	@RequestMapping("/")
	public String acaoDefault(@Valid T example, BindingResult result, Model model, HttpServletRequest request){
		return acaoListar(example, result,  model, request);
	}
	
	@Override
	@RequestMapping("/listar")
	public String acaoListar(@Valid T example, BindingResult bindingResult, Model model, HttpServletRequest request){
		if (request.getSession().getAttribute("usuarioLogado") == null) return "redirect:/login/logar";
		
		updateExampleBean(example, request);
		
		List<T> result = getService().getByExample(example);
		model.addAttribute("list", result);
		model.addAttribute("example", example);
		
		updateRequestBeforeGoToList(request);
		
		return getListView();
	}

	protected void updateRequestBeforeGoToList(HttpServletRequest request) {}

	protected abstract void updateExampleBean(T example, HttpServletRequest request);

	@Override
	@RequestMapping("/novo")
	public String acaoNovo(Model model, HttpServletRequest request){
		if (request.getSession().getAttribute("usuarioLogado") == null) return "redirect:/login/logar";
		
		T bean = getNewInstance(request);
		model.addAttribute("bean", bean);
		
		updateRequestBeforeGoToForm(request);
		
		return getFormView();
	}

	protected abstract T getNewInstance(HttpServletRequest request);
	
	@Override
	@RequestMapping("/editar")
	public String acaoEdicao(@RequestParam(value = "id") int id, Model model, HttpServletRequest request){
		if (request.getSession().getAttribute("usuarioLogado") == null) return "redirect:/login/logar";
		
		T bean = getService().get(id);
		model.addAttribute("bean", bean);
		
		updateRequestBeforeGoToForm(request);
		
		return getFormView();
	}
	
	protected abstract CRUDServiceTemplate<T> getService();
	
	@Override
	@RequestMapping("/salvar")
	public String acaoSalvar(@Valid T bean, BindingResult result, Model model, HttpServletRequest request){
		if (request.getSession().getAttribute("usuarioLogado") == null) return "redirect:/login/logar";
		try {
			getService().save(bean);
			
		} catch (OEEException e) {
			model.addAttribute("errors", e.getValidationResult());
			model.addAttribute("bean", bean);
			
			updateRequestBeforeGoToForm(request);
			
			return getFormView();
		}
		
		return redirectToRoot();	
	}
	
	protected void updateRequestBeforeGoToForm(HttpServletRequest request) {}

	@Override
	@RequestMapping("/excluir")
	public String acaoExcluir(@RequestParam(value = "id") int id, Model model, HttpServletRequest request){
		if (request.getSession().getAttribute("usuarioLogado") == null) return "redirect:/login/logar";
		
		T bean = getService().get(id);
		
		if (bean != null) {
			try {
				getService().delete(bean);
				
			} catch (OEEException e) {
				model.addAttribute("errors", e.getValidationResult());
				model.addAttribute("bean", bean);
				
				updateRequestBeforeGoToForm(request);
				
				return getFormView();
			}
		}
		
		return redirectToRoot();
	}
	
	protected String redirectToRoot() {
		String beanName = getBeanName();
		return String.format("redirect:/%s/", beanName);
	}
	
	protected String getListView() {
		String beanName = getBeanName();
		return String.format("/%s/%s_list", beanName, beanName);
	}
	
	private String getFormView() {
		String beanName = getBeanName();
		return String.format("/%s/%s_form", beanName, beanName);
	}
	
	protected String getBeanName(){
		if (beanName == null){
			String result = getClass().getSimpleName().replace("Controller", "");
			result = result.substring(0,1).toLowerCase() + result.substring(1);
			beanName = result;
		}
		return beanName;
	}

}
