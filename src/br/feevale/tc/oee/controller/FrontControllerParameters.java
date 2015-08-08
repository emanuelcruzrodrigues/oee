package br.feevale.tc.oee.controller;

public class FrontControllerParameters {
	
	private String controller;
	private String action;
	
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("controller=");
		builder.append(getController());
		builder.append(" action=");
		builder.append(getAction());
		return builder.toString();
	}
	
	
	
	

}
