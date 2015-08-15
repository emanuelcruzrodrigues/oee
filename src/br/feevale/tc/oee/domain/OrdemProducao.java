package br.feevale.tc.oee.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.feevale.tc.oee.enums.SituacaoOrdemProducao;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class OrdemProducao implements Serializable{

	private Integer id;
	
	@NumberFormat(style=Style.NUMBER,pattern="###,###")
	private Integer codigo;
	
	@NotBlank
	@Length(max = 100)
	private String descricao;
	
	private Equipamento equipamento;
	
	@NumberFormat(style=Style.NUMBER,pattern="###,###.###")
	private Double unidadesPorMinuto;
	
	private SituacaoOrdemProducao dmSituacao;
	
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
	
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public Double getUnidadesPorMinuto() {
		return unidadesPorMinuto;
	}
	public void setUnidadesPorMinuto(Double unidadesPorMinuto) {
		this.unidadesPorMinuto = unidadesPorMinuto;
	}
	
	public SituacaoOrdemProducao getDmSituacao() {
		return dmSituacao;
	}
	public void setDmSituacao(SituacaoOrdemProducao dmSituacao) {
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
		if (!(o instanceof OrdemProducao)) {
			return false;
		}
		OrdemProducao castedObj = (OrdemProducao) o;
		if (this.getId() == null || castedObj.getId() == null) {
			return false;
		} else {
			return this.getId().equals(castedObj.getId());
		}
	}
	
	
	
}
