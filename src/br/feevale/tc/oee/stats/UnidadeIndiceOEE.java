package br.feevale.tc.oee.stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.enums.QualidadeProducao;
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
	
	private List<DetalheUnidadeIndiceOEE> detalhes;
	private List<ParadaUnidadeIndiceOEE> paradas;
	
	public UnidadeIndiceOEE() {
		super();
		this.detalhes = new ArrayList<>();
		this.paradas = new ArrayList<>();
		this.dtTecnicaMinutos = 0;
		this.dtOperacionalMinutos = 0;
		this.dtQualidadeMinutos = 0;
		this.dtTotalMinutos = 0;
		this.stOperacionalMinutos = 0;
		this.stInduzidoMinutos = 0;
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

	public Integer getDtOperacionalMinutos() {
		return dtOperacionalMinutos;
	}
	public void setDtOperacionalMinutos(Integer dtOperacionalMinutos) {
		this.dtOperacionalMinutos = dtOperacionalMinutos;
	}

	public Integer getDtQualidadeMinutos() {
		return dtQualidadeMinutos;
	}
	public void setDtQualidadeMinutos(Integer dtQualidadeMinutos) {
		this.dtQualidadeMinutos = dtQualidadeMinutos;
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

	public Integer getStInduzidoMinutos() {
		return stInduzidoMinutos;
	}
	public void setStInduzidoMinutos(Integer stInduzidoMinutos) {
		this.stInduzidoMinutos = stInduzidoMinutos;
	}

	public Integer getTempoCargaMinutos() {
		return tempoCargaMinutos;
	}
	public void setTempoCargaMinutos(Integer tempoCargaMinutos) {
		this.tempoCargaMinutos = tempoCargaMinutos;
	}
	public void addTempoCargaMinutos(Integer tempoCarga) {
		tempoCarga = Calculadora.somar(getTempoCargaMinutos(), tempoCarga, 0).intValue();
		setTempoCargaMinutos(tempoCarga);
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
	public void setTempoCicloRealUnidadesPorMinuto(
			Double tempoCicloRealUnidadesPorMinuto) {
		this.tempoCicloRealUnidadesPorMinuto = tempoCicloRealUnidadesPorMinuto;
	}

	public Integer getRuntimeMinutos() {
		return runtimeMinutos;
	}
	public void setRuntimeMinutos(Integer runtimeMinutos) {
		this.runtimeMinutos = runtimeMinutos;
	}
	public void addRuntime(Integer runtime) {
		setRuntimeMinutos(Calculadora.somar(getRuntimeMinutos(), runtime, 0).intValue());
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

	public Double getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(Double disponibilidade) {
		if (disponibilidade != null && disponibilidade > 1D){
			disponibilidade = 1D;
		}
		this.disponibilidade = disponibilidade;
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
		if (qualidade != null && qualidade > 1D){
			qualidade = 1D;
		}
		this.qualidade = qualidade;
	}

	public Double getOee() {
		return oee;
	}
	public void setOee(Double oee) {
		this.oee = oee;
	}

	public List<DetalheUnidadeIndiceOEE> getDetalhes() {
		return detalhes;
	}
	public void addDetalhe(UnidadeIndiceOEE unidade, OrdemProducao ordemProducao) {
		DetalheUnidadeIndiceOEE detalhe = new DetalheUnidadeIndiceOEE(ordemProducao);
		detalhe.setQuantidadeUnidadesBoasProduzidas(unidade.getQuantidadeUnidadesBoasProduzidas());
		detalhe.setRuntimeMinutos(unidade.getRuntimeMinutos());
		detalhe.setTempoCicloTeoricoUnidadesPorMinuto(ordemProducao.getUnidadesPorMinuto());
		detalhe.setTempoUtilMinutos(unidade.getTempoUtilMinutos());
		detalhe.setVolumeTotalProduzido(unidade.getVolumeTotalProduzido());
		addDetalhe(detalhe);
	}
	public void addDetalhe(DetalheUnidadeIndiceOEE novoDetalhe) {
		novoDetalhe.setUnidadeIndiceOEE(this);
		int index = getDetalhes().indexOf(novoDetalhe);
		if (index < 0){
			getDetalhes().add(novoDetalhe);
		}else{
			DetalheUnidadeIndiceOEE detalhe = getDetalhes().get(index);
			detalhe.addValues(novoDetalhe);
		}
	}
	
	public List<ParadaUnidadeIndiceOEE> getParadas() {
		return paradas;
	}
	public void setParadas(List<ParadaUnidadeIndiceOEE> paradas) {
		this.paradas = paradas;
	}
	public void addParada(ApontamentoTempoParada apontamento, Integer tempo){
		ParadaUnidadeIndiceOEE parada = new ParadaUnidadeIndiceOEE(apontamento, tempo);
		int index = getParadas().indexOf(parada);
		if(index >= 0){
			ParadaUnidadeIndiceOEE paradaExistente = getParadas().get(index);
			paradaExistente.addTempo(tempo);
		}else{
			getParadas().add(parada);
		}
	}

	public void setDetalhes(List<DetalheUnidadeIndiceOEE> detalhes) {
		this.detalhes = detalhes;
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

	public void addQuantidadeProduzida(Double quantidade, QualidadeProducao dmQualidade) {
		setVolumeTotalProduzido(Calculadora.somar(getVolumeTotalProduzido(), quantidade, CalculadoraOEE.ARREDONDAMENTO_DECIMAL).intValue());
		if (QualidadeProducao.DENTRO_DOS_PADROES == dmQualidade){
			setQuantidadeUnidadesBoasProduzidas(Calculadora.somar(getQuantidadeUnidadesBoasProduzidas(), quantidade, CalculadoraOEE.ARREDONDAMENTO_DECIMAL).intValue());
		}else{
			setQuantidadeUnidadesBoasProduzidas(Calculadora.somar(getQuantidadeUnidadesBoasProduzidas(), 0, 0).intValue());
		}
	}

	public void refreshTempoCicloTeoricoConformeDetalhes() {
		Double tempoCicloTeorico = getTempoCicloTeoricoConformeDetalhes();
		setTempoCicloTeoricoUnidadesPorMinuto(tempoCicloTeorico);
	}

	private Double getTempoCicloTeoricoConformeDetalhes() {
		if (getDetalhes().size() == 0) return null;
		Double tempoCicloTeorico = getDetalhes().get(0).getTempoCicloTeoricoUnidadesPorMinuto();
		for (DetalheUnidadeIndiceOEE detalhe : detalhes) {
			if (!detalhe.getTempoCicloTeoricoUnidadesPorMinuto().equals(tempoCicloTeorico)) return null;
		}
		return tempoCicloTeorico;
	}

}
