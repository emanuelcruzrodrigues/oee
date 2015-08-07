package br.feevale.tc.oee.utils;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
				
				if("ipLastUpdate".equals(propertyNames[i])){
					HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
					state[i] = request.getLocalAddr();
				}
				
				if("dtCreation".equals(propertyNames[i])){
					if (state[i] == null){
						state[i] = new Date();
					}
				}
			}
		} catch (Throwable e) {}
		
		return;
	}
	
	
	
}
