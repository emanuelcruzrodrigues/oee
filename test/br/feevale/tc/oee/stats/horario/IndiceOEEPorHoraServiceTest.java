package br.feevale.tc.oee.stats.horario;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.enums.QualidadeProducao;
import br.feevale.tc.oee.enums.TipoParada;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;

public class IndiceOEEPorHoraServiceTest {
	
	private IndiceOEEPorHoraService service;
	
	@Mock
	private IndiceOEEPorHoraDAO indiceOEEPorHoraDAO;
	
	@Before
	public void setUp(){
		service = new IndiceOEEPorHoraService();
		
		MockitoAnnotations.initMocks(this);
		service.indiceOEEPorHoraDAO = indiceOEEPorHoraDAO;
	}

	@Test
	public void test_Update_Tempo_De_Carga() {
		IndiceOEEPorHoraFilter filter = new IndiceOEEPorHoraFilter();
		filter.setDt(new LocalDate(2015,8,21));
		
		List<ProgramacaoProducaoEquipamento> programacoes = new ArrayList<>();
		programacoes.add(createProgramacao(new LocalDateTime(2015,8,20,23, 0, 0), new LocalDateTime(2015,8,21, 2, 0, 0)));
		programacoes.add(createProgramacao(new LocalDateTime(2015,8,21, 7,30, 0), new LocalDateTime(2015,8,21,11,45, 0)));
		programacoes.add(createProgramacao(new LocalDateTime(2015,8,21,12,45, 0), new LocalDateTime(2015,8,21,17,18, 0)));
		programacoes.add(createProgramacao(new LocalDateTime(2015,8,21,23, 0, 0), new LocalDateTime(2015,8,22, 2, 0, 0)));
		when(indiceOEEPorHoraDAO.queryProgramacoes(filter)).thenReturn(programacoes);
		
		Map<String, UnidadeIndiceOEE> unidadesPorHora = new HashMap<>();
		
		service.updateTempoCarga(filter, unidadesPorHora);
		
		List<UnidadeIndiceOEE> result = new ArrayList<>(unidadesPorHora.values());
		Collections.sort(result);
		
		assertTempoCargaEquals(result.get( 0), new LocalDateTime(2015,8,21, 0, 0, 0), new LocalDateTime(2015,8,21, 0,59, 0), 60);
		assertTempoCargaEquals(result.get( 1), new LocalDateTime(2015,8,21, 1, 0, 0), new LocalDateTime(2015,8,21, 1,59, 0), 60);
		assertTempoCargaEquals(result.get( 2), new LocalDateTime(2015,8,21, 7, 0, 0), new LocalDateTime(2015,8,21, 7,59, 0), 30);
		assertTempoCargaEquals(result.get( 3), new LocalDateTime(2015,8,21, 8, 0, 0), new LocalDateTime(2015,8,21, 8,59, 0), 60);
		assertTempoCargaEquals(result.get( 4), new LocalDateTime(2015,8,21, 9, 0, 0), new LocalDateTime(2015,8,21, 9,59, 0), 60);
		assertTempoCargaEquals(result.get( 5), new LocalDateTime(2015,8,21,10, 0, 0), new LocalDateTime(2015,8,21,10,59, 0), 60);
		assertTempoCargaEquals(result.get( 6), new LocalDateTime(2015,8,21,11, 0, 0), new LocalDateTime(2015,8,21,11,59, 0), 45);
		assertTempoCargaEquals(result.get( 7), new LocalDateTime(2015,8,21,12, 0, 0), new LocalDateTime(2015,8,21,12,59, 0), 15);
		assertTempoCargaEquals(result.get( 8), new LocalDateTime(2015,8,21,13, 0, 0), new LocalDateTime(2015,8,21,13,59, 0), 60);
		assertTempoCargaEquals(result.get( 9), new LocalDateTime(2015,8,21,14, 0, 0), new LocalDateTime(2015,8,21,14,59, 0), 60);
		assertTempoCargaEquals(result.get(10), new LocalDateTime(2015,8,21,15, 0, 0), new LocalDateTime(2015,8,21,15,59, 0), 60);
		assertTempoCargaEquals(result.get(11), new LocalDateTime(2015,8,21,16, 0, 0), new LocalDateTime(2015,8,21,16,59, 0), 60);
		assertTempoCargaEquals(result.get(12), new LocalDateTime(2015,8,21,17, 0, 0), new LocalDateTime(2015,8,21,17,59, 0), 18);
		assertTempoCargaEquals(result.get(13), new LocalDateTime(2015,8,21,23, 0, 0), new LocalDateTime(2015,8,21,23,59, 0), 60);
	}

	private void assertTempoCargaEquals(UnidadeIndiceOEE unidade, LocalDateTime inicio, LocalDateTime fim, Integer tempoCarga) {
		assertEquals(inicio, unidade.getInicio());
		assertEquals(fim, unidade.getFim());
		assertEquals(tempoCarga, unidade.getTempoCargaMinutos(), 0);
	}

	private ProgramacaoProducaoEquipamento createProgramacao(LocalDateTime dtHrInicio, LocalDateTime dtHrFim) {
		ProgramacaoProducaoEquipamento programacao = new ProgramacaoProducaoEquipamento();
		programacao.setDtHrInicio(dtHrInicio);
		programacao.setDtHrFim(dtHrFim);
		return programacao;
	}
	
	@Test
	public void test_Update_Paradas() {
		IndiceOEEPorHoraFilter filter = new IndiceOEEPorHoraFilter();
		filter.setDt(new LocalDate(2015,8,21));
		
		List<ApontamentoTempoParada> paradas = new ArrayList<>();
		paradas.add(createApontamentoParada(new LocalDateTime(2015,8,20,23, 0, 0), new LocalDateTime(2015,8,21, 2, 0, 0), TipoParada.DT_QUALIDADE));
		paradas.add(createApontamentoParada(new LocalDateTime(2015,8,21, 7,30, 0), new LocalDateTime(2015,8,21, 8, 0, 0), TipoParada.DT_TECNICA));
		paradas.add(createApontamentoParada(new LocalDateTime(2015,8,21, 8, 0, 0), new LocalDateTime(2015,8,21,11,45, 0), TipoParada.DT_OPERACIONAL));
		paradas.add(createApontamentoParada(new LocalDateTime(2015,8,21,12,45, 0), new LocalDateTime(2015,8,21,17,18, 0), TipoParada.ST_INDUZIDO));
		paradas.add(createApontamentoParada(new LocalDateTime(2015,8,21,23, 0, 0), new LocalDateTime(2015,8,22, 2, 0, 0), TipoParada.ST_OPERACIONAL));
		when(indiceOEEPorHoraDAO.queryParadas(filter)).thenReturn(paradas);
		
		Map<String, UnidadeIndiceOEE> unidadesPorHora = new HashMap<>();
		service.updateParadas(filter, unidadesPorHora);
		
		List<UnidadeIndiceOEE> result = new ArrayList<>(unidadesPorHora.values());
		Collections.sort(result);
		
		assertParadaEquals(result.get( 0), new LocalDateTime(2015,8,21, 0, 0, 0), new LocalDateTime(2015,8,21, 0,59, 0), 60, TipoParada.DT_QUALIDADE);
		assertParadaEquals(result.get( 1), new LocalDateTime(2015,8,21, 1, 0, 0), new LocalDateTime(2015,8,21, 1,59, 0), 60, TipoParada.DT_QUALIDADE);
		assertParadaEquals(result.get( 2), new LocalDateTime(2015,8,21, 7, 0, 0), new LocalDateTime(2015,8,21, 7,59, 0), 30, TipoParada.DT_TECNICA);
		assertParadaEquals(result.get( 3), new LocalDateTime(2015,8,21, 8, 0, 0), new LocalDateTime(2015,8,21, 8,59, 0), 60, TipoParada.DT_OPERACIONAL);
		assertParadaEquals(result.get( 4), new LocalDateTime(2015,8,21, 9, 0, 0), new LocalDateTime(2015,8,21, 9,59, 0), 60, TipoParada.DT_OPERACIONAL);
		assertParadaEquals(result.get( 5), new LocalDateTime(2015,8,21,10, 0, 0), new LocalDateTime(2015,8,21,10,59, 0), 60, TipoParada.DT_OPERACIONAL);
		assertParadaEquals(result.get( 6), new LocalDateTime(2015,8,21,11, 0, 0), new LocalDateTime(2015,8,21,11,59, 0), 45, TipoParada.DT_OPERACIONAL);
		assertParadaEquals(result.get( 7), new LocalDateTime(2015,8,21,12, 0, 0), new LocalDateTime(2015,8,21,12,59, 0), 15, TipoParada.ST_INDUZIDO);
		assertParadaEquals(result.get( 8), new LocalDateTime(2015,8,21,13, 0, 0), new LocalDateTime(2015,8,21,13,59, 0), 60, TipoParada.ST_INDUZIDO);
		assertParadaEquals(result.get( 9), new LocalDateTime(2015,8,21,14, 0, 0), new LocalDateTime(2015,8,21,14,59, 0), 60, TipoParada.ST_INDUZIDO);
		assertParadaEquals(result.get(10), new LocalDateTime(2015,8,21,15, 0, 0), new LocalDateTime(2015,8,21,15,59, 0), 60, TipoParada.ST_INDUZIDO);
		assertParadaEquals(result.get(11), new LocalDateTime(2015,8,21,16, 0, 0), new LocalDateTime(2015,8,21,16,59, 0), 60, TipoParada.ST_INDUZIDO);
		assertParadaEquals(result.get(12), new LocalDateTime(2015,8,21,17, 0, 0), new LocalDateTime(2015,8,21,17,59, 0), 18, TipoParada.ST_INDUZIDO);
		assertParadaEquals(result.get(13), new LocalDateTime(2015,8,21,23, 0, 0), new LocalDateTime(2015,8,21,23,59, 0), 60, TipoParada.ST_OPERACIONAL);
		
	}

	private ApontamentoTempoParada createApontamentoParada(LocalDateTime dtHrEntrada, LocalDateTime dtHrSaida, TipoParada dmTipoParada) {
		ApontamentoTempoParada apontamento = new ApontamentoTempoParada();
		apontamento.setDtHrEntrada(dtHrEntrada);
		apontamento.setDtHrSaida(dtHrSaida);
		
		MotivoParada motivoParada = new MotivoParada();
		motivoParada.setDmTipoParada(dmTipoParada);
		apontamento.setMotivoParada(motivoParada);
		return apontamento;
	}
	
	private void assertParadaEquals(UnidadeIndiceOEE unidade, LocalDateTime inicio, LocalDateTime fim, Integer tempoParada, TipoParada dmTipoParada) {
		assertEquals(inicio, unidade.getInicio());
		assertEquals(fim, unidade.getFim());
		assertEquals(tempoParada, unidade.getTempoParadaMinutos(dmTipoParada), 0);
	}
	
	@Test
	public void test_Update_Quantidades_Produzidas() {
		IndiceOEEPorHoraFilter filter = new IndiceOEEPorHoraFilter();
		filter.setDt(new LocalDate(2015,8,21));
		
		List<ApontamentoQuantidade> quantidades = new ArrayList<>();
		quantidades.add(createApontamentoQuantidade(new LocalDateTime(2015,8,20,23, 0, 0), 10D, QualidadeProducao.DENTRO_DOS_PADROES));
		quantidades.add(createApontamentoQuantidade(new LocalDateTime(2015,8,20,23, 0, 0), 20D, QualidadeProducao.REFUGO));
		quantidades.add(createApontamentoQuantidade(new LocalDateTime(2015,8,21, 0, 0, 0), 30D, QualidadeProducao.DENTRO_DOS_PADROES));
		quantidades.add(createApontamentoQuantidade(new LocalDateTime(2015,8,21, 0, 0, 0), 25D, QualidadeProducao.REFUGO));
		quantidades.add(createApontamentoQuantidade(new LocalDateTime(2015,8,21, 1, 0, 0), 35D, QualidadeProducao.DENTRO_DOS_PADROES));
		quantidades.add(createApontamentoQuantidade(new LocalDateTime(2015,8,21, 2, 0, 0), 15D, QualidadeProducao.REFUGO));
		when(indiceOEEPorHoraDAO.queryUnidadesProduzidas(filter)).thenReturn(quantidades);
		
		Map<String, UnidadeIndiceOEE> unidadesPorHora = new HashMap<>();
		service.updateUnidadesProduzidas(filter, unidadesPorHora);
		
		List<UnidadeIndiceOEE> result = new ArrayList<>(unidadesPorHora.values());
		Collections.sort(result);
		
		assertQuantidadeEquals(result.get(0), new LocalDateTime(2015,8,21, 0, 0, 0), new LocalDateTime(2015,8,21, 0,59, 0), 30D, 55D);
		assertQuantidadeEquals(result.get(1), new LocalDateTime(2015,8,21, 1, 0, 0), new LocalDateTime(2015,8,21, 1,59, 0), 35D, 35D);
		assertQuantidadeEquals(result.get(2), new LocalDateTime(2015,8,21, 2, 0, 0), new LocalDateTime(2015,8,21, 2,59, 0),  0D, 15D);
		
	}


	private ApontamentoQuantidade createApontamentoQuantidade(LocalDateTime dtHr, double quantidade, QualidadeProducao dmQualidade) {
		ApontamentoQuantidade apontamento = new ApontamentoQuantidade();
		apontamento.setDtHr(dtHr);
		apontamento.setQuantidade(quantidade);
		apontamento.setDmQualidade(dmQualidade);
		return apontamento ;
	}
	
	private void assertQuantidadeEquals(UnidadeIndiceOEE unidade, LocalDateTime inicio, LocalDateTime fim, Double unidadesBoas, double volumeTotal) {
		assertEquals(inicio, unidade.getInicio());
		assertEquals(fim, unidade.getFim());
		assertEquals(unidadesBoas, unidade.getQuantidadeUnidadesBoasProduzidas(), 0);
		assertEquals(volumeTotal, unidade.getVolumeTotalProduzido(), 0);
	}


}
