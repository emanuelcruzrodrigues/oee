package br.feevale.tc.oee.framework.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public interface DAO {

	/**
	 * Salva um objeto no banco de dados 
	 */
	public abstract void save(Object obj);

	/**
	 * Carrega um objeto do banco de dados a partir do seu identificador. 
	 * Caso o objeto nao exista, uma exception eh lancada. 
	 */
	public abstract <T> T load(Class<T> clazz, Serializable id);

	/**
	 * Carrega um objeto do banco de dados a partir do seu identificador. 
	 * Caso o objeto nao exista, retorna null. 
	 */
	public abstract <T> T get(Class<T> clazz, Serializable id);

	/**
	 * Retorna a lista de objetos mapeados que resultarem da consulta 
	 * HQL sem utilizar parametros
	 */
	public abstract <T> List<T> query(String hql);

	/**
	 * Retorna a lista de objetos mapeados que resultarem da consulta
	 * HQL com uma quantidade indefinida de parametros
	 */
	public abstract <T> List<T> query(String hql, Object... params);

	/**
	 * Retorna a lista de objetos de uma classe especifica (nao necessariamente mapeada) 
	 * que resultarem da consulta HQL com uma quantidade 
	 * indefinida de parametros
	 */
	public abstract <T> List<T> query(Class<T> classRetorno, String hql, Object... params);

	/**
	 * Retorna a lista de objetos de uma classe especifica (nao necessariamente mapeada) 
	 * que resultarem da consulta HQL com as caracteristicas: 
	 *  - Limitando a quantidade maxima de resultados
	 *  - Quantidade indefinida de parametros
	 */
	public abstract <T> List<T> query(final Class<?> classRetorno, final String hql, final int maxResult, final Object... params);

	/**
	 * Retorna um unico objeto mapeado que resultar da consulta HQL 
	 * com uma quantidade indefinida de parametros
	 * Caso exista mais de um registro correspondente a consulta, uma
	 * exception eh lancada
	 */
	public abstract <T> T uniqueResult(String hql, Object... params);

	/**
	 * Retorna um unico objeto de uma classe especifica (nao necessariamente mapeada)
	 * que resultar da consulta HQL com uma quantidade 
	 * indefinida de parametros.
	 * Caso exista mais de um registro correspondente a consulta, uma
	 * exception eh lancada
	 */
	public abstract <T> T uniqueResult(final Class<?> classRetorno, final String hql, final Object... params);

	/**
	 * Retorna a lista de objetos de uma classe especifica (nao necessariamente mapeada) 
	 * que resultarem da consulta HQL com uma quantidade 
	 * indefinida de parametros constantes em um Map
	 */
	public abstract <T> List<T> namedQuery(String hql, Map<String, ?> parameters);

	/**
	 * Retorna a lista de objetos mapeados que resultarem da consulta HQL
	 * com uma quantidade indefinida de parametros 
	 * constantes em um Map
	 */
	public abstract <T> List<T> namedQuery(final Class<T> classRetorno, final String hql, final Map<String, ?> parameters);

	/**
	 * Retorna um unico objeto de uma classe mapeada que resultar da 
	 * consulta HQL com uma quantidade 
	 * indefinida de parametros constantes em um Map
	 * Caso exista mais de um registro correspondente a consulta, uma
	 * exception eh lancada
	 */
	public abstract <T> T uniqueNamedQuery(String hql, Map<String, Object> parameters);

	/**
	 * Retorna um unico objeto de uma classe especifica 
	 * (nao necessariamente mapeada) resultante da consulta HQL 
	 * com uma quantidade 
	 * indefinida de parametros constantes em um Map
	 * Caso exista mais de um registro correspondente a consulta, uma
	 * exception eh lancada
	 */
	public abstract <T> T uniqueNamedQuery(final Class<T> classRetorno, final String hql, final Map<String, Object> parameters);

	/**
	 * Retorna a lista de objetos de uma classe especifica (nao necessariamente mapeada) 
	 * que resultarem da consulta HQL que serah criada
	 * automaticamente a partir do objeto passado como exemplo
	 */
	public abstract <T> List<T> queryByExample(final Class<T> clazz, final T exampleBean);

	/**
	 * Retorna a lista de objetos de uma classe especifica (nao necessariamente mapeada) 
	 * que resultarem da consulta HQL que serah criada
	 * automaticamente a partir do objeto passado como exemplo.
	 * Possibilita passar filtros adicionais e regras de ordenacao especificas
	 */
	public abstract <T> List<T> queryByExample(final Class<T> clazz, final T exampleBean, final List<Criterion> filtrosAdicionais, final List<Order> ordenacoes);

	/**
	 * Executa um update a partir de um sript hql sem parametros
	 */
	public abstract void update(String hql);

	/**
	 * Executa um update a partir de um sript hql com uma quantidade indefinida de parametros
	 */
	public abstract void update(String hql, Object[] values);

	/**
	 * Executa um delete a partir de um sript hql sem parametros
	 */
	public abstract void delete(Object obj);

	/**
	 * Executa um delete a partir de um sript hql com uma quantidade indefinida de parametros
	 */
	public abstract void delete(String hql, Object[] params);

	/**
	 * Envia para o banco de dados todos as alteracoes realizadas nos objetos existentes
	 * na sessao do hibernate
	 */
	public abstract void flush();

	/**
	 * Exclui todos os objetos existentes na sessao do hibernate.
	 */
	public abstract void clearSession();

	/**
	 * Caso exista mais de um objeto com o mesmo identificador na sessao,
	 * realiza a juncao entre estes objetos.
	 */
	public abstract Object merge(Object obj);

	/**
	 * Carrega o objeto da sessao do hibernate. 
	 * Se necessario, realiza sua busca no banco de dados e o inclui na sessao. 
	 */
	public abstract void initialize(Object obj);

	/**
	 * Remove um objeto da sessao do hibernate.
	 */
	public abstract void evict(Object obj);

	/**
	 * Gera a expressao necessaria para agrupar um objeto conforme seus atributos mapeados
	 */
	public abstract String createGroupByForBean(Class<?> clazz, String alias);

	/**
	 * Gera a expressao para buscar objetos a partir de um objeto exemplo, 
	 * para uma classe especifica e um alias
	 */
	public abstract String createWhere(Class<?> clazz, Object example, String alias, List<Object> params, String... atributosIgnorados);
}