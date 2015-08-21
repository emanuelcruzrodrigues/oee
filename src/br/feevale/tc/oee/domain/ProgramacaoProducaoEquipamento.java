package br.feevale.tc.oee.domain;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 20/08/2015
 */
@SuppressWarnings("serial")
public class ProgramacaoProducaoEquipamento implements Serializable{
	
	private Integer id;
	
	private Equipamento equipamento;
	
	private LocalDateTime dtHrInicio;
	
	private LocalDateTime dtHrFim;
	
	@NumberFormat(style=Style.NUMBER,pattern="###,###")
	private Integer tempoMinutos;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	private Date dtCriacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	private Date dtUltimaAlteracao;
	
	/**
	 * atributos nao mapeados
	 */
	private LocalDate dtInicial;
	private LocalDate dtFinal;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public LocalDateTime getDtHrInicio() {
		return dtHrInicio;
	}
	public void setDtHrInicio(LocalDateTime dtHrInicio) {
		this.dtHrInicio = dtHrInicio;
	}

	public LocalDateTime getDtHrFim() {
		return dtHrFim;
	}
	public void setDtHrFim(LocalDateTime dtHrFim) {
		this.dtHrFim = dtHrFim;
	}

	public Integer getTempoMinutos() {
		return tempoMinutos;
	}
	public void setTempoMinutos(Integer tempoMinutos) {
		this.tempoMinutos = tempoMinutos;
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
		if (!(o instanceof ProgramacaoProducaoEquipamento)) {
			return false;
		}
		ProgramacaoProducaoEquipamento castedObj = (ProgramacaoProducaoEquipamento) o;
		if (this.getId() == null || castedObj.getId() == null) {
			return false;
		} else {
			return this.getId().equals(castedObj.getId());
		}
	}

}
