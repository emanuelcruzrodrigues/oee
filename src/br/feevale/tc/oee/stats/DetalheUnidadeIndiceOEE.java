package br.feevale.tc.oee.stats;

import java.io.Serializable;

import com.bullcontrol.util.Calculadora;

import br.feevale.tc.oee.domain.OrdemProducao;

@SuppressWarnings("serial")
public class DetalheUnidadeIndiceOEE implements Serializable{

	private final Integer id;
	private final OrdemProducao ordemProducao;
	private Integer tempoUtilMinutos;
	private UnidadeIndiceOEE unidadeIndiceOEE;
	private Double tempoCicloTeoricoUnidadesPorMinuto;
	private Double tempoCicloRealUnidadesPorMinuto;
	private Double minutosPorUnidade;
	private Integer runtimeMinutos;
	private Integer quantidadeUnidadesBoasProduzidas;
	private Integer volumeTotalProduzido;
	private Double desempenho;
	private Double qualidade;
	
	public DetalheUnidadeIndiceOEE(OrdemProducao ordemProducao) {
		super();
		this.ordemProducao = ordemProducao;
		this.id = ordemProducao.getId();
	}
	
	public Integer getId() {
		return id;
	}
	
	public OrdemProducao getOrdemProducao() {
		return ordemProducao;
	}
	public Integer getTempoUtilMinutos() {
		return tempoUtilMinutos;
	}

	public void setTempoUtilMinutos(Integer tempoUtilMinutos) {
		this.tempoUtilMinutos = tempoUtilMinutos;
	}

	public UnidadeIndiceOEE getUnidadeIndiceOEE() {
		return unidadeIndiceOEE;
	}
	public void setUnidadeIndiceOEE(UnidadeIndiceOEE unidadeIndiceOEE) {
		this.unidadeIndiceOEE = unidadeIndiceOEE;
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
	
	public Double getMinutosPorUnidade() {
		return minutosPorUnidade;
	}
	public void setMinutosPorUnidade(Double minutosPorUnidade) {
		this.minutosPorUnidade = minutosPorUnidade;
	}
	
	public Integer getRuntimeMinutos() {
		return runtimeMinutos;
	}
	public void setRuntimeMinutos(Integer runtimeMinutos) {
		this.runtimeMinutos = runtimeMinutos;
	}
	
	public Integer getQuantidadeUnidadesBoasProduzidas() {
		return quantidadeUnidadesBoasProduzidas;
	}
	public void setQuantidadeUnidadesBoasProduzidas(Integer quantidadeUnidadesBoasProduzidas) {
		this.quantidadeUnidadesBoasProduzidas = quantidadeUnidadesBoasProduzidas;
	}
	
	public Integer getVolumeTotalProduzido() {
		return volumeTotalProduzido;
	}
	public void setVolumeTotalProduzido(Integer volumeTotalProduzido) {
		this.volumeTotalProduzido = volumeTotalProduzido;
	}
	
	public Double getDesempenho() {
		return desempenho;
	}
	public void setDesempenho(Double desempenho) {
		if (desempenho != null && desempenho > 1D){
			desempenho = 1D;
		}
		this.desempenho = desempenho;
	}

	public Double getQualidade() {
		return qualidade;
	}
	public void setQualidade(Double qualidade) {
		this.qualidade = qualidade;
	}

	public void addValues(DetalheUnidadeIndiceOEE other) {
		setTempoUtilMinutos(Calculadora.somar(getTempoUtilMinutos(), other.getTempoUtilMinutos(), 0).intValue());
		setRuntimeMinutos(Calculadora.somar(getRuntimeMinutos(), other.getRuntimeMinutos(), 0).intValue());
		setQuantidadeUnidadesBoasProduzidas(Calculadora.somar(getQuantidadeUnidadesBoasProduzidas(), other.getQuantidadeUnidadesBoasProduzidas(), 0).intValue());
		setVolumeTotalProduzido(Calculadora.somar(getVolumeTotalProduzido(), other.getVolumeTotalProduzido(), 0).intValue());
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
		if (!(o instanceof DetalheUnidadeIndiceOEE)) {
			return false;
		}
		DetalheUnidadeIndiceOEE castedObj = (DetalheUnidadeIndiceOEE) o;
		if (this.getId() == null || castedObj.getId() == null) {
			return false;
		} else {
			return this.getId().equals(castedObj.getId());
		}
	}

	
}
