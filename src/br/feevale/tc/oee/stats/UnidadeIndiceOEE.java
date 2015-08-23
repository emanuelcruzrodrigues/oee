package br.feevale.tc.oee.stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;

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
	private Double minutosPorUnidade;
	private Integer runtimeMinutos;
	private Integer quantidadeUnidadesBoasProduzidas;
	private Integer volumeTotalProduzido;
	private Double disponibilidade;
	private Double desempenho;
	private Double qualidade;
	private Double oee;
	
	private UnidadeIndiceOEE grupoUnidade;
	private Map<Integer, UnidadeIndiceOEE> unidadePorIdOrdemProducao;
	
	public UnidadeIndiceOEE() {
		super();
		unidadePorIdOrdemProducao = new LinkedHashMap<>();
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
	
	public Double getTempoCicloTeoricoUnidadesPorMinuto() {
		return tempoCicloTeoricoUnidadesPorMinuto;
	}
	public void setTempoCicloTeoricoUnidadesPorMinuto(Double tempoCicloTeoricoUnidadesPorMinuto) {
		this.tempoCicloTeoricoUnidadesPorMinuto = tempoCicloTeoricoUnidadesPorMinuto;
	}
	public void refreshTempoCicloTeoricoConformeDetalhes() {
		Double tempoUtilTotal = 0D;
		Double ponderacaoTotal = 0D;
		for (UnidadeIndiceOEE unidade : unidadePorIdOrdemProducao.values()) {
			tempoUtilTotal = Calculadora.somar(tempoUtilTotal, unidade.getTempoUtilMinutos(), 0);
			
			OrdemProducao ordemProducao = unidade.getOrdemProducao();
			Double ponderacao = Calculadora.multiplicar(ordemProducao.getUnidadesPorMinuto(), unidade.getTempoUtilMinutos(), CalculadoraOEE.ARREDONDAMENTO_DECIMAL);
			ponderacaoTotal = Calculadora.somar(ponderacaoTotal, ponderacao, CalculadoraOEE.ARREDONDAMENTO_DECIMAL);
		}
		if (tempoUtilTotal > 0D){
			Double tempoCicloTeoricoPonderado = Calculadora.dividir(ponderacaoTotal, tempoUtilTotal, CalculadoraOEE.ARREDONDAMENTO_DECIMAL);
			setTempoCicloTeoricoUnidadesPorMinuto(tempoCicloTeoricoPonderado);
		}else{
			setTempoCicloTeoricoUnidadesPorMinuto(null);
		}
	}
	
	public Double getTempoCicloRealUnidadesPorMinuto() {
		return tempoCicloRealUnidadesPorMinuto;
	}
	public void setTempoCicloRealUnidadesPorMinuto(
			Double tempoCicloRealUnidadesPorMinuto) {
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

	public Double getOee() {
		return oee;
	}
	public void setOee(Double oee) {
		this.oee = oee;
	}

	public UnidadeIndiceOEE getGrupoUnidade() {
		return grupoUnidade;
	}
	public void setGrupoUnidade(UnidadeIndiceOEE grupoUnidade) {
		this.grupoUnidade = grupoUnidade;
	}

	public List<UnidadeIndiceOEE> getDetalhes() {
		return new ArrayList<UnidadeIndiceOEE>(unidadePorIdOrdemProducao.values());
	}
	public void addDetalhe(UnidadeIndiceOEE novoDetalhe) {
		novoDetalhe.setGrupoUnidade(this);
		UnidadeIndiceOEE detalhe = unidadePorIdOrdemProducao.get(novoDetalhe.getOrdemProducao().getId());
		if (detalhe == null){
			unidadePorIdOrdemProducao.put(novoDetalhe.getOrdemProducao().getId(), novoDetalhe);
		}else{
			detalhe.addValues(novoDetalhe);
		}
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
		setVolumeTotalProduzido(Calculadora.somar(getVolumeTotalProduzido(), quantidade, 0).intValue());
		if (QualidadeProducao.DENTRO_DOS_PADROES == dmQualidade){
			setQuantidadeUnidadesBoasProduzidas(Calculadora.somar(getQuantidadeUnidadesBoasProduzidas(), quantidade, 0).intValue());
		}else{
			setQuantidadeUnidadesBoasProduzidas(Calculadora.somar(getQuantidadeUnidadesBoasProduzidas(), 0, 0).intValue());
		}
	}
	
	public void addValues(UnidadeIndiceOEE other) {
		setTempoUtilMinutos(Calculadora.somar(getTempoUtilMinutos(), other.getTempoUtilMinutos(), 0).intValue());
		setDtTecnicaMinutos(Calculadora.somar(getDtTecnicaMinutos(), other.getDtTecnicaMinutos(), 0).intValue());
		setDtOperacionalMinutos(Calculadora.somar(getDtOperacionalMinutos(), other.getDtOperacionalMinutos(), 0).intValue());
		setDtQualidadeMinutos(Calculadora.somar(getDtQualidadeMinutos(), other.getDtQualidadeMinutos(), 0).intValue());
		setStOperacionalMinutos(Calculadora.somar(getStOperacionalMinutos(), other.getStOperacionalMinutos(), 0).intValue());
		setStInduzidoMinutos(Calculadora.somar(getStInduzidoMinutos(), other.getStInduzidoMinutos(), 0).intValue());
		setTempoCargaMinutos(Calculadora.somar(getTempoCargaMinutos(), other.getTempoCargaMinutos(), 0).intValue());
		setRuntimeMinutos(Calculadora.somar(getRuntimeMinutos(), other.getRuntimeMinutos(), 0).intValue());
		setQuantidadeUnidadesBoasProduzidas(Calculadora.somar(getQuantidadeUnidadesBoasProduzidas(), other.getQuantidadeUnidadesBoasProduzidas(), 0).intValue());
		setVolumeTotalProduzido(Calculadora.somar(getVolumeTotalProduzido(), other.getVolumeTotalProduzido(), 0).intValue());
		setTempoCicloTeoricoUnidadesPorMinuto(other.getTempoCicloTeoricoUnidadesPorMinuto());
	}

}
