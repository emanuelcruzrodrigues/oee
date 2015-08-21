package br.feevale.tc.oee.stats;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.enums.TipoParada;

import com.bullcontrol.util.Calculadora;
/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 20/08/2015
 * 
 * @see UnidadeIndiceOEETest
 */
@SuppressWarnings("serial")
public class UnidadeIndiceOEE implements Serializable, Comparable<UnidadeIndiceOEE>{
	
	private String id;
	private Equipamento equipamento;
	private OrdemProducao ordemProducao;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	private Integer tempoUtilMinutos;
	private Integer dtTecnicaMinutos;
	private Integer dtOperacionalMinutos;
	private Integer dtQualidadeMinutos;
	private Integer dtTotalMinutos;
	private Integer stOperacionalMinutos;
	private Integer stInduzidoMinutos;
	private Integer tempoCargaMinutos;
	private Double tempoCicloTeoricoUnidadesPorMinuto;
	private Double tempoCicloRealUnidadesPorMinuto;
	private Integer runtimeMinutos;
	private Integer quantidadeUnidadesBoasProduzidas;
	private Integer volumeTotalProduzido;
	private Double disponibilidade;
	private Double desempenho;
	private Double qualidade;
	private Double oee;
	
	public UnidadeIndiceOEE() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public OrdemProducao getOrdemProducao() {
		return ordemProducao;
	}
	public void setOrdemProducao(OrdemProducao ordemProducao) {
		this.ordemProducao = ordemProducao;
	}
	
	public Double getOee() {
		return oee;
	}
	public void setOee(Double oee) {
		this.oee = oee;
	}
	
	public LocalDateTime getInicio() {
		return inicio;
	}
	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}
	
	public LocalDateTime getFim() {
		return fim;
	}
	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}
	
	public Integer getTempoUtilMinutos() {
		return tempoUtilMinutos;
	}
	public void setTempoUtilMinutos(Integer tempoUtilMinutos) {
		this.tempoUtilMinutos = tempoUtilMinutos;
	}

	public Integer getDtTecnicaMinutos() {
		return dtTecnicaMinutos;
	}
	public void setDtTecnicaMinutos(Integer dtTecnicaMinutos) {
		this.dtTecnicaMinutos = dtTecnicaMinutos;
	}
	public void addDtTecnicaMinutos(Integer add) {
		Double soma = Calculadora.somar(getDtTecnicaMinutos(), add, 0);
		setDtTecnicaMinutos(soma.intValue());
	}
	
	public Integer getDtOperacionalMinutos() {
		return dtOperacionalMinutos;
	}
	public void setDtOperacionalMinutos(Integer dtOperacionalMinutos) {
		this.dtOperacionalMinutos = dtOperacionalMinutos;
	}
	public void addDtOperacionalMinutos(Integer add) {
		Double soma = Calculadora.somar(getDtOperacionalMinutos(), add, 0);
		setDtOperacionalMinutos(soma.intValue());
	}
	
	public Integer getDtQualidadeMinutos() {
		return dtQualidadeMinutos;
	}
	public void setDtQualidadeMinutos(Integer dtQualidadeMinutos) {
		this.dtQualidadeMinutos = dtQualidadeMinutos;
	}
	public void addDtQualidadeMinutos(Integer add) {
		Double soma = Calculadora.somar(getDtQualidadeMinutos(), add, 0);
		setDtQualidadeMinutos(soma.intValue());
	}
	
	public Integer getDtTotalMinutos() {
		return dtTotalMinutos;
	}
	public void setDtTotalMinutos(Integer dtTotalMinutos) {
		this.dtTotalMinutos = dtTotalMinutos;
	}
	
	public Integer getStOperacionalMinutos() {
		return stOperacionalMinutos;
	}
	public void setStOperacionalMinutos(Integer stOperacionalMinutos) {
		this.stOperacionalMinutos = stOperacionalMinutos;
	}
	public void addStOperacionalMinutos(Integer add) {
		Double soma = Calculadora.somar(getStOperacionalMinutos(), add, 0);
		setStOperacionalMinutos(soma.intValue());
	}
	
	public Integer getStInduzidoMinutos() {
		return stInduzidoMinutos;
	}
	public void setStInduzidoMinutos(Integer stInduzidoMinutos) {
		this.stInduzidoMinutos = stInduzidoMinutos;
	}
	public void addStInduzidoMinutos(Integer add) {
		Double soma = Calculadora.somar(getStInduzidoMinutos(), add, 0);
		setStInduzidoMinutos(soma.intValue());
	}
	
	public Integer getTempoCargaMinutos() {
		return tempoCargaMinutos;
	}
	public void setTempoCargaMinutos(Integer tempoCargaMinutos) {
		this.tempoCargaMinutos = tempoCargaMinutos;
	}
	
	public Double getTempoCicloTeoricoUnidadesPorMinuto() {
		return tempoCicloTeoricoUnidadesPorMinuto;
	}
	public void setTempoCicloTeoricoUnidadesPorMinuto(Double tempoCicloTeoricoUnidadesPorMinuto) {
		this.tempoCicloTeoricoUnidadesPorMinuto = tempoCicloTeoricoUnidadesPorMinuto;
	}
	
	public Double getTempoCicloRealUnidadesPorMinuto() {
		return tempoCicloRealUnidadesPorMinuto;
	}
	public void setTempoCicloRealUnidadesPorMinuto(Double tempoCicloRealUnidadesPorMinuto) {
		this.tempoCicloRealUnidadesPorMinuto = tempoCicloRealUnidadesPorMinuto;
	}
	public Integer getRuntimeMinutos() {
		return runtimeMinutos;
	}
	public void setRuntimeMinutos(Integer runtimeMinutos) {
		this.runtimeMinutos = runtimeMinutos;
	}
	public void addRuntimeMinutos(Integer runtime) {
		Double runtimeMinutos = Calculadora.somar(getRuntimeMinutos(), runtime, 0);
		setRuntimeMinutos(runtimeMinutos.intValue());
	}

	
	public Integer getQuantidadeUnidadesBoasProduzidas() {
		return quantidadeUnidadesBoasProduzidas;
	}
	public void setQuantidadeUnidadesBoasProduzidas(
			Integer quantidadeUnidadesBoasProduzidas) {
		this.quantidadeUnidadesBoasProduzidas = quantidadeUnidadesBoasProduzidas;
	}
	
	public Integer getVolumeTotalProduzido() {
		return volumeTotalProduzido;
	}
	public void setVolumeTotalProduzido(Integer volumeTotalProduzido) {
		this.volumeTotalProduzido = volumeTotalProduzido;
	}
	
	public Double getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(Double disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	public Double getDesempenho() {
		return desempenho;
	}
	public void setDesempenho(Double desempenho) {
		this.desempenho = desempenho;
	}
	
	public Double getQualidade() {
		return qualidade;
	}
	public void setQualidade(Double qualidade) {
		this.qualidade = qualidade;
	}
	
	@Override
	public int compareTo(UnidadeIndiceOEE other) {
		int result = getInicio().compareTo(other.getInicio());
		return result;
	}

	public void addTempoParada(TipoParada dmTipoParada, Integer tempo) {
		if (dmTipoParada == null) return;
		
		if (TipoParada.DT_OPERACIONAL == dmTipoParada){
			setDtOperacionalMinutos(Calculadora.somar(getDtOperacionalMinutos(), tempo, 0).intValue());
			
		}else if (TipoParada.DT_QUALIDADE == dmTipoParada){
			setDtQualidadeMinutos(Calculadora.somar(getDtQualidadeMinutos(), tempo, 0).intValue());
			
		}else if (TipoParada.DT_TECNICA == dmTipoParada){
			setDtTecnicaMinutos(Calculadora.somar(getDtTecnicaMinutos(), tempo, 0).intValue());
			
		}else if (TipoParada.ST_INDUZIDO == dmTipoParada){
			setStInduzidoMinutos(Calculadora.somar(getStInduzidoMinutos(), tempo, 0).intValue());
			
		}else if (TipoParada.ST_OPERACIONAL == dmTipoParada){
			setStOperacionalMinutos(Calculadora.somar(getStOperacionalMinutos(), tempo, 0).intValue());
			
		}
		
	}

	public Integer getTempoParadaMinutos(TipoParada dmTipoParada) {
		
		if (TipoParada.DT_OPERACIONAL == dmTipoParada){
			return getDtOperacionalMinutos();
			
		}else if (TipoParada.DT_QUALIDADE == dmTipoParada){
			return getDtQualidadeMinutos();
			
		}else if (TipoParada.DT_TECNICA == dmTipoParada){
			return getDtTecnicaMinutos();
			
		}else if (TipoParada.ST_INDUZIDO == dmTipoParada){
			return getStInduzidoMinutos();
			
		}else if (TipoParada.ST_OPERACIONAL == dmTipoParada){
			return getStOperacionalMinutos();
			
		}
		
		return null;
	}

	
	

}
