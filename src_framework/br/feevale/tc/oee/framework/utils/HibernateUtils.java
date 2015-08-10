package br.feevale.tc.oee.framework.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.collection.PersistentBag;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public class HibernateUtils {
	
	public static <T> List<T> getDeletedItens(List<T> list){
		List<T> deleted = new ArrayList<>();
		
		List<T> storedSnapshot = getStoredItens(list);
		if (storedSnapshot == null) return deleted;
		
		for (T storedBean : storedSnapshot) {
			if (!list.contains(storedBean)){
				deleted.add(storedBean);
			}
		}
		
		return deleted;
	}

	public static <T> List<T> getStoredItens(List<T> list) {
		if (!(list instanceof PersistentBag)) return new ArrayList<>();
		
		PersistentBag bag = (PersistentBag) list;
		
		@SuppressWarnings("unchecked")
		List<T> storedSnapshot = (List<T>) bag.getStoredSnapshot();
		
		return storedSnapshot;
	}

}
