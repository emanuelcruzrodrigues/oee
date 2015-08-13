package web.oee.framework.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 10/08/2015
 * @param <T>
 */
public interface CRUDController<T extends Serializable> {

	public abstract String acaoListar(@Valid T example, BindingResult result, Model model, HttpServletRequest request);

	public abstract String acaoNovo(Model model, HttpServletRequest request);

	public abstract String acaoEdicao(int id, Model model, HttpServletRequest request);

	public abstract String acaoSalvar(@Valid T bean, BindingResult result, Model model, HttpServletRequest request);

	public abstract String acaoExcluir(int id, Model model, HttpServletRequest request);

}