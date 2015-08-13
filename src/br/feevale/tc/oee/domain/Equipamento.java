package br.feevale.tc.oee.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import br.feevale.tc.oee.enums.AtivoInativo;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class Equipamento implements Serializable{
	
	private Integer id;
	
	private Integer codigo;
	
	private String nome;
	
	private AtivoInativo dmSituacao;
	
	private Date dtCriacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	private Date dtUltimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public AtivoInativo getDmSituacao() {
		return dmSituacao;
	}
	public void setDmSituacao(AtivoInativo dmSituacao) {
		this.dmSituacao = dmSituacao;
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
		if (!(o instanceof Equipamento)) {
			return false;
		}
		Equipamento castedObj = (Equipamento) o;
		if (this.getId() == null || castedObj.getId() == null) {
			return false;
		} else {
			return this.getId().equals(castedObj.getId());
		}
	}
	
	
}
