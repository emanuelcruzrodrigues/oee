package br.feevale.tc.oee.framework.dao;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class OEEHibernateInterceptor extends EmptyInterceptor{

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		updateAutomaticValues(state, propertyNames);
		return false;
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		updateAutomaticValues(currentState, propertyNames);
		return false;
	}

	private void updateAutomaticValues(Object[] state, String[] propertyNames) {
		try {
			
			for (int i = 0; i < propertyNames.length; i++) {
				
				if("dtCriacao".equals(propertyNames[i])){
					if (state[i] == null){
						state[i] = new Date();
					}
				}
			}
		} catch (Throwable e) {}
		
		return;
	}
	
	
	
}
