package br.feevale.tc.oee.framework.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class Usuario implements Serializable{
	
	private Integer id;
	
	@NotBlank
	@Size (max=30)
	private String nome;
	
	private Integer senha;
	
	private Date dtCriacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	private Date dtUltimaAlteracao;
	
	private String ipUltimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getSenha() {
		return senha;
	}
	public void setSenha(Integer senha) {
		this.senha = senha;
	}
	
	public Date getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}
	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public String getIpUltimaAlteracao() {
		return ipUltimaAlteracao;
	}
	public void setIpUltimaAlteracao(String ipUltimaAlteracao) {
		this.ipUltimaAlteracao = ipUltimaAlteracao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Usuario)) {
			return false;
		}
		Usuario castedObj = (Usuario) o;
		if (this.getId() == null || castedObj.getId() == null) {
			return false;
		} else {
			return this.getId().equals(castedObj.getId());
		}
	}

}