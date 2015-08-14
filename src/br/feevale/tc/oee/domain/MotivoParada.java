package br.feevale.tc.oee.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.feevale.tc.oee.enums.AtivoInativo;
import br.feevale.tc.oee.enums.TipoParada;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class MotivoParada implements Serializable{

	private Integer id;
	
	@NumberFormat(style=Style.NUMBER,pattern="###,###")
	private Integer codigo;
	
	@NotBlank
	@Length(max = 100)
	private String descricao;
	
	private TipoParada dmTipoParada;
	
	private AtivoInativo dmSituacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
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
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public TipoParada getDmTipoParada() {
		return dmTipoParada;
	}
	public void setDmTipoParada(TipoParada dmTipoParada) {
		this.dmTipoParada = dmTipoParada;
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
		if (!(o instanceof MotivoParada)) {
			return false;
		}
		MotivoParada castedObj = (MotivoParada) o;
		if (this.getId() == null || castedObj.getId() == null) {
			return false;
		} else {
			return this.getId().equals(castedObj.getId());
		}
	}
	
	
}
