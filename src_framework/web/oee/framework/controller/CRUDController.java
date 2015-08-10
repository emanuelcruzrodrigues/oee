package web.oee.framework.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 10/08/2015
 * @param <T>
 */
public interface CRUDController<T extends Serializable> {

	public abstract String acaoListar(T example, Model model, HttpServletRequest request);

	public abstract String acaoNovo(Model model, HttpServletRequest request);

	public abstract String acaoEdicao(int id, Model model, HttpServletRequest request);

	public abstract String acaoSalvar(T bean, Model model, HttpServletRequest request);

	public abstract String acaoExcluir(int id, Model model, HttpServletRequest request);

}