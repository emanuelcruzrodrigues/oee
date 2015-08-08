package br.feevale.tc.oee.cadastros.domain;

import java.io.Serializable;
import java.util.Date;

import br.feevale.tc.oee.enums.AtivoInativo;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class Equipamento implements Serializable{
	
	private Integer id;
	private String nome;
	private AtivoInativo dmSituacao;
	private Date dtCriacao;
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
	
	public String getIpUltimaAlteracao() {
		return ipUltimaAlteracao;
	}
	public void setIpUltimaAlteracao(String ipUltimaAlteracao) {
		this.ipUltimaAlteracao = ipUltimaAlteracao;
	}
	
	
	
}
