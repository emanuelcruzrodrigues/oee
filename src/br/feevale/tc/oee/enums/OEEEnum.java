package br.feevale.tc.oee.enums;

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
