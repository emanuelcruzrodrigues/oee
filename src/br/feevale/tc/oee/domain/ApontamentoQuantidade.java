package br.feevale.tc.oee.domain;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.feevale.tc.oee.enums.QualidadeProducao;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class ApontamentoQuantidade implements Serializable{
	
	private Integer id;
	
	private OrdemProducao ordemProducao;
	
	private LocalDateTime dtHr;
	
	@NumberFormat(style=Style.NUMBER,pattern="###,###.###")
	private Double quantidade;
	
	private QualidadeProducao dmQualidade;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	private Date dtCriacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	private Date dtUltimaAlteracao;
	
	/**
	 * atributos nao mapeados
	 */
	private Equipamento equipamento;
	private LocalDate dtInicial;
	private LocalDate dtFinal;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public OrdemProducao getOrdemProducao() {
		return ordemProducao;
	}
	public void setOrdemProducao(OrdemProducao ordemProducao) {
		this.ordemProducao = ordemProducao;
	}

	public LocalDateTime getDtHr() {
		return dtHr;
	}
	public void setDtHr(LocalDateTime dtHr) {
		this.dtHr = dtHr;
	}

	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public QualidadeProducao getDmQualidade() {
		return dmQualidade;
	}
	public void setDmQualidade(QualidadeProducao dmQualidade) {
		this.dmQualidade = dmQualidade;
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
	
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public LocalDate getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(LocalDate dtInicial) {
		this.dtInicial = dtInicial;
	}
	
	public LocalDate getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(LocalDate dtFinal) {
		this.dtFinal = dtFinal;
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
		if (!(o instanceof ApontamentoQuantidade)) {
			return false;
		}
		ApontamentoQuantidade castedObj = (ApontamentoQuantidade) o;
		if (this.getId() == null || castedObj.getId() == null) {
			return false;
		} else {
			return this.getId().equals(castedObj.getId());
		}
	}

}
