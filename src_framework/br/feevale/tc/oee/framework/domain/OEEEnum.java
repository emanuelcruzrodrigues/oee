package br.feevale.tc.oee.framework.domain;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public interface OEEEnum {

	public String getValue();
	
	public static final OEEEnum NULL = new OEEEnum() {
		@Override
		public String getValue() {
			return null;
		}
		
		@Override
		public String toString() {
			return "";
		}
	};

}
