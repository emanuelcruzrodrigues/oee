package br.feevale.tc.oee.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Equipment implements Serializable{
	
	private Integer id;
	private String name;
	private Date dtCreation;
	private Date dtUpdate;
	
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
	
	public Date getDtUpdate() {
		return dtUpdate;
	}
	public void setDtUpdate(Date dtUpdate) {
		this.dtUpdate = dtUpdate;
	}
	

}
