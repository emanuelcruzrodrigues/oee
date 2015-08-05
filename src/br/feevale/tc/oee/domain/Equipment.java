package br.feevale.tc.oee.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Equipment implements Serializable{
	
	private Integer id;
	private String name;
	private Date dtCreation;
	private Date dtLastUpdate;
	private String ipLastUpdate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDtCreation() {
		return dtCreation;
	}
	public void setDtCreation(Date dtCreation) {
		this.dtCreation = dtCreation;
	}
	
	public Date getDtLastUpdate() {
		return dtLastUpdate;
	}
	public void setDtLastUpdate(Date dtLastUpdate) {
		this.dtLastUpdate = dtLastUpdate;
	}
	
	public String getIpLastUpdate() {
		return ipLastUpdate;
	}
	public void setIpLastUpdate(String ipLastUpdate) {
		this.ipLastUpdate = ipLastUpdate;
	}

}
