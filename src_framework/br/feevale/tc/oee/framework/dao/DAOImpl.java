package br.feevale.tc.oee.framework.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.transform.Transformers;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.type.AssociationType;
import org.hibernate.type.ComponentType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.framework.utils.HQLUtils;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@Repository(value="dao")
public class DAOImpl implements DAO {

	@Resource
	private HibernateTemplate hibernateTemplate;

	/*create*/

	@Override
	public void save(Object obj) {
		hibernateTemplate.saveOrUpdate(obj);
	}


	/*read*/

	@Override
	public <T> T load(Class<T> clazz, Serializable id) {
		T obj = hibernateTemplate.load(clazz, id);
		if (obj != null) {
			Hibernate.initialize(obj);
		}
		return obj;
	}

	@Override
	public <T> T get(Class<T> clazz, Serializable id) {
		T obj = hibernateTemplate.get(clazz, id);
		if (obj != null) {
			initialize(obj);
		}
		return obj;
	}

	@Override
	public <T> List<T> query(String hql) {
		return query(null, hql, (Object[])null);
	}
	
	@Override
	public <T> List<T> query(String hql, Object... params) {
		return query(null, hql, params);
	}


	@Override
	public <T> List<T> query(Class<T> classRetorno, String hql, Object... params) {
		return query(classRetorno, hql, 0, params);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> query(final Class<?> classRetorno, final String hql, final int maxResult, final Object... params) {
		return hibernateTemplate.executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				addResultTransformer(classRetorno, query);
				if (maxResult > 0) {
					query.setMaxResults(maxResult);
				}
				if (params != null){
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();
			}
		});
	}

	@Override
	public <T> T uniqueResult(String hql, Object... params) {
		return uniqueResult(null, hql, params);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T uniqueResult(final Class<?> classRetorno, final String hql, final Object... params) {
		return (T) hibernateTemplate.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				addResultTransformer(classRetorno, query);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
				Object result = query.uniqueResult();
				Hibernate.initialize(result);
				return result;
			}
		});
	}

	@Override
	public <T> List<T> namedQuery(String hql, Map<String,?> parameters) {
		return namedQuery(null, hql, parameters);
	}

	@Override
	public <T> List<T> namedQuery(final Class<T> classRetorno, final String hql, final Map<String,?> parameters) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<T> result = hibernateTemplate.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				addResultTransformer(classRetorno, q);
				String[] namedParameters = q.getNamedParameters();
				if (namedParameters != null) {
					for (int i = 0; i < namedParameters.length; i++) {
						Object value = parameters.get(namedParameters[i]);
						if (value instanceof Object[]) {
							value = Arrays.asList((Object[])value);
						}
						if (value instanceof Collection<?>) {
							q.setParameterList(namedParameters[i], (Collection<?>) value);
						} else {
							q.setParameter(namedParameters[i], value);
						}
					}
				}
				return q.list();
			}
		});
		return result;
	}

	@Override
	public <T> T uniqueNamedQuery(String hql, Map<String, Object> parameters) {
		return uniqueNamedQuery(null, hql, parameters);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T uniqueNamedQuery(final Class<T> classRetorno, final String hql, final Map<String, Object> parameters) {
		return (T) hibernateTemplate.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				addResultTransformer(classRetorno, query);
				String[] namedParameters = query.getNamedParameters();
				if (namedParameters != null) {
					for (int i = 0; i < namedParameters.length; i++) {
						Object value = parameters.get(namedParameters[i]);
						if (value instanceof Collection) {
							query.setParameterList(namedParameters[i], (Collection) value);
						} else {
							query.setParameter(namedParameters[i], value);
						}
					}
				}
				Object result = query.uniqueResult();
				Hibernate.initialize(result);
				return result;
			}
		});
	}

	@Override
	public <T> List<T> queryByExample(final Class<T>clazz, final T exampleBean){
		return queryByExample(clazz, exampleBean, null, null);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> queryByExample(final Class<T>clazz, final T exampleBean, final List<Criterion> filtrosAdicionais, final List<Order> ordenacoes){
		HQLUtils.updateStringsToLike(exampleBean);
		
		return hibernateTemplate.executeFind(new HibernateCallback() {
			@SuppressWarnings("deprecation")
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(clazz);
				if (possuiId(clazz, exampleBean, meta)) {
					List result = new ArrayList();
					Criteria criteria = session.createCriteria(clazz);
					criteria.add(Restrictions.eq("id", meta.getIdentifier(exampleBean, EntityMode.POJO)));
					Object objResult = criteria.uniqueResult();
					if (objResult != null) {
						result.add(objResult);
					}
					return result;
				} else {
					clearValues(meta, exampleBean);
					Example example = Example.create(exampleBean);
					example.enableLike();
					example.ignoreCase();
					Criteria criteria = session.createCriteria(clazz);
					criteria = criteria.add(example);
					try {
						addPropertyExamples(exampleBean, criteria, meta);
					} catch (Exception e) {
						e.printStackTrace();
					}
					addIdentifierExamples(exampleBean, criteria, meta);
					
					if (CollectionUtils.isNotEmpty(filtrosAdicionais)){
						for (Criterion criterion : filtrosAdicionais) {
							criteria.add(criterion);
						}
					}
					
					if (CollectionUtils.isNotEmpty(ordenacoes)){
						for (Order order : ordenacoes) {
							criteria.addOrder(order);
						}
					}
					
					return criteria.list();
				}
			}
		});
	}


	@SuppressWarnings("deprecation")
	private boolean possuiId(Class<?> clazz, Object obj, ClassMetadata persister) throws HibernateException {
		if (persister.getIdentifier(obj, EntityMode.POJO) != null) {
			if (persister.getIdentifier(obj, EntityMode.POJO).getClass().isAssignableFrom(clazz)) {
				if (persister.getIdentifierType().isComponentType()) {
					ComponentType type = (ComponentType) persister.getIdentifierType();
					Object[] propertyValues = type.getPropertyValues(obj, EntityMode.POJO);
					for (int i = 0; i < propertyValues.length; i++) {
						if (propertyValues[i] == null) {
							return false;
						}
					}
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}

	private void clearValues(ClassMetadata meta, Object obj) throws HibernateException {
		String[] propertyNames = meta.getPropertyNames();
		Type[] propertyTypes = meta.getPropertyTypes();
		Object[] propertyValues = meta.getPropertyValues(obj, EntityMode.POJO);
		for (int i = 0; i < propertyNames.length; i++) {
			Object propertyValue = propertyValues[i];
			String propertyName = propertyNames[i];
			Type propertyType = propertyTypes[i];
			if (propertyValue != null && propertyType instanceof StringType) {
				if ("".equals(propertyValue.toString().trim())) {
					meta.setPropertyValue(obj, propertyName, null, EntityMode.POJO);
				}
			}
		}
	}
	
	private void addIdentifierExamples(Object obj, Criteria criteria, ClassMetadata meta){
		if (meta.getIdentifierType().isComponentType()) {
			ComponentType type = (ComponentType) meta.getIdentifierType();
			String[] propertyNames = type.getPropertyNames();
			Type[] propertyTypes = type.getSubtypes();
			Object[] propertyValues = type.getPropertyValues(obj, EntityMode.POJO);
			for (int i = 0; i < propertyNames.length; i++) {
				Object propertyValue = propertyValues[i];
				String propertyName = propertyNames[i];
				if (propertyTypes[i].isAssociationType() && !propertyTypes[i].isCollectionType() && propertyValue != null) {
					Example subExample = Example.create(propertyValue);
					subExample.enableLike();
					subExample.ignoreCase();
					criteria.createCriteria(propertyName).add(subExample);
				} else if (!propertyTypes[i].isCollectionType() && propertyValue != null) {
					if (propertyTypes[i] instanceof StringType) {
						criteria.add(Restrictions.eq(propertyName, propertyValue).ignoreCase());
					} else {
						criteria.add(Restrictions.eq(propertyName, propertyValue));
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void addPropertyExamples(Object obj, Criteria criteria, ClassMetadata metadata) throws HibernateException {
		String[] propertyNames = metadata.getPropertyNames();
		Type[] propertyTypes = metadata.getPropertyTypes();
		Object[] propertyValues = metadata.getPropertyValues(obj, EntityMode.POJO);
		for (int i = 0; i < propertyNames.length; i++) {
			Object propertyValue = propertyValues[i];
			String propertyName = propertyNames[i];
			if (propertyTypes[i].isAssociationType() && !propertyTypes[i].isCollectionType() && propertyValue != null) {
				String entityName = ((AssociationType) propertyTypes[i]).getAssociatedEntityName((SessionFactoryImplementor) hibernateTemplate.getSessionFactory());
				ClassMetadata metaAssociation = hibernateTemplate.getSessionFactory().getClassMetadata(entityName);
				Class<?> clazz = metaAssociation.getMappedClass(EntityMode.POJO);
				Criteria subCriteria = criteria.createCriteria(propertyName);
				if (possuiId(clazz, propertyValue, metaAssociation)) {
					subCriteria.add(Restrictions.eq(metaAssociation.getIdentifierPropertyName(), metaAssociation.getIdentifier(propertyValue, EntityMode.POJO)));
				} else {
					Example subExample = Example.create(propertyValue);
					subExample.enableLike();
					subExample.ignoreCase();
					subCriteria.add(subExample);
				}
			}
		}
	}


	private void addResultTransformer(Class<?> classRetorno, Query query) {
		if (classRetorno != null){
			query.setResultTransformer(Transformers.aliasToBean(classRetorno));
		}
	}


	/*update*/

	@Override
	public void update(String hql) {
		hibernateTemplate.bulkUpdate(hql);
	}

	@Override
	public void update(String hql, Object[] values) {
		hibernateTemplate.bulkUpdate(hql, values);
	}

	/*delete*/

	@Override
	public void delete(Object obj) {
		hibernateTemplate.delete(obj);
	}

	@Override
	public void delete(String query, Object[] params) {
		hibernateTemplate.bulkUpdate(query, params);
	}


	/*metodos utilitarios*/

	@Override
	public void flush() {
		hibernateTemplate.flush();
	}

	@Override
	public void clearSession() {
		hibernateTemplate.clear();
	}

	@Override
	public Object merge(Object obj) {
		return hibernateTemplate.merge(obj);
	}

	@Override
	public void initialize(Object obj) {
		Hibernate.initialize(obj);
	}


	@Override
	public void evict(Object obj) {
		hibernateTemplate.evict(obj);
	}

	@Override
	public String createGroupByForBean(Class<?> clazz, String alias){
		ClassMetadata metaData = hibernateTemplate.getSessionFactory().getClassMetadata(clazz);

		String ident = metaData.getIdentifierPropertyName();
		StringBuilder groupBy = new StringBuilder();
		groupBy.append(alias).append(".").append(ident);

		for (String name : metaData.getPropertyNames()) {
			Type type = metaData.getPropertyType(name);

			if (!type.isCollectionType()){
				groupBy = groupBy.append(" 	,").append(alias).append(".").append(name);
			}
		}

		if (metaData.hasSubclasses()){
			EntityPersister entityPersister = ((SessionFactoryImpl)hibernateTemplate.getSessionFactory()).getEntityPersister(clazz.getName());
			EntityMetamodel entityMetamodel = entityPersister.getEntityMetamodel();

			@SuppressWarnings("unchecked")
			Set<Object> subClasses = entityMetamodel.getSubclassEntityNames();
			for(Object subClass : subClasses){
				try {
					Class<?> subClazz = Class.forName(subClass.toString());

					if (!subClazz.equals(clazz)){
						ClassMetadata subClassMetaData = hibernateTemplate.getSessionFactory().getClassMetadata(subClazz);
						for (String name : subClassMetaData.getPropertyNames()) {
							Type type = metaData.getPropertyType(name);

							if (!type.isCollectionType() && !groupBy.toString().contains( alias + "." + name )){
								groupBy = groupBy.append(" 	,").append(alias).append(".").append(name);
							}
						}
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

			if (entityMetamodel.isPolymorphic()){
				groupBy = groupBy.append(" 	,").append(alias).append(".").append("class");
			}
		}

		return groupBy.append(" ").toString();
	}

	@SuppressWarnings("deprecation")
	public String createWhere(Class<?> clazz, Object obj, String alias, List<Object> params, String... atributosIgnorados) {
		HQLUtils.updateStringsToLike(obj);
		
		StringBuilder hql = new StringBuilder();
		ClassMetadata metaData = hibernateTemplate.getSessionFactory().getClassMetadata(clazz);
		
		List<String> ignorados = new ArrayList<>();
		if (atributosIgnorados != null){
			ignorados = Arrays.asList(atributosIgnorados);
		}
		
		if (possuiId(clazz, obj, metaData)) {
			hql.append(" and " + alias + ".id = ? ");
			params.add(metaData.getIdentifier(obj, EntityMode.POJO));
		} else {
			clearValues(metaData, obj);
			String[] propertyNames = metaData.getPropertyNames();
			Object[] propertyValues = metaData.getPropertyValues(obj, EntityMode.POJO);
			Type[] propertyTypes = metaData.getPropertyTypes();
			for (int i = 0; i < propertyNames.length; i++) {
				Object propertyValue = propertyValues[i];
				String propertyName = propertyNames[i];
				if (!(ignorados.contains(propertyName)) && propertyValue != null && !(propertyTypes[i].isCollectionType())) {
					if (propertyTypes[i] instanceof StringType) {
						hql.append(" and upper(").append(alias).append(".").append(propertyName).append(")").append(" like ? ");
					} else {
						hql.append(" and ").append(alias).append(".").append(propertyName).append(" = ? ");
					}
					params.add(propertyValue);
				}
			}
		}
		return hql.toString();
	}
	

}
