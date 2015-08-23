package br.feevale.tc.oee.stats;

import static org.junit.Assert.*;

import org.junit.Test;

import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.enums.TipoParada;

public class UnidadeIndiceOEETest {

	@Test
	public void test_Add_Tempo_Parada() {
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		
		unidade.addTempoParada(TipoParada.DT_OPERACIONAL, 10);
		assertEquals(10, unidade.getDtOperacionalMinutos(), 0);
		
		unidade.addTempoParada(TipoParada.DT_OPERACIONAL, 15);
		assertEquals(25, unidade.getDtOperacionalMinutos(), 0);
		
		unidade.addTempoParada(TipoParada.DT_QUALIDADE, 15);
		assertEquals(15, unidade.getDtQualidadeMinutos(), 0);
		
		unidade.addTempoParada(TipoParada.DT_TECNICA, 5);
		assertEquals(5, unidade.getDtTecnicaMinutos(), 0);
		
		unidade.addTempoParada(TipoParada.ST_INDUZIDO, 1);
		assertEquals(1, unidade.getStInduzidoMinutos(), 0);
		
		unidade.addTempoParada(TipoParada.ST_OPERACIONAL, 2);
		assertEquals(2, unidade.getStOperacionalMinutos(), 0);
		
	}
	
	@Test
	public void test_Add_Detalhe(){
		OrdemProducao ordemProducao1 = createOrdemProducao(1);
		OrdemProducao ordemProducao2 = createOrdemProducao(2);
		
		UnidadeIndiceOEE unidade1 = new UnidadeIndiceOEE();
		unidade1.setOrdemProducao(ordemProducao1);
		
		UnidadeIndiceOEE unidade2 = new UnidadeIndiceOEE();
		unidade2.setOrdemProducao(ordemProducao2);
		
		
		unidade1.addDetalhe(unidade1);
		assertEquals(1, unidade1.getDetalhes().size(), 0);
		
		unidade1.addDetalhe(unidade1);
		assertEquals(1, unidade1.getDetalhes().size(), 0);
		
		unidade1.addDetalhe(unidade2);
		assertEquals(2, unidade1.getDetalhes().size(), 0);
		
		unidade1.addDetalhe(unidade2);
		assertEquals(2, unidade1.getDetalhes().size(), 0);
		
	}
	
	@Test
	public void test_Add_Volues(){
		UnidadeIndiceOEE unidade1 = new UnidadeIndiceOEE();
		UnidadeIndiceOEE unidade2 = new UnidadeIndiceOEE();
				
		unidade1.setDtOperacionalMinutos(10);
		unidade1.setDtQualidadeMinutos(20);
		unidade1.setDtTecnicaMinutos(30);
		unidade1.setStInduzidoMinutos(40);
		unidade1.setStOperacionalMinutos(50);
		unidade1.setQuantidadeUnidadesBoasProduzidas(60);
		unidade1.setVolumeTotalProduzido(70);
		unidade1.setTempoCargaMinutos(80);
		unidade1.setRuntimeMinutos(90);
		
		unidade2.setDtOperacionalMinutos(1);
		unidade2.setDtQualidadeMinutos(2);
		unidade2.setDtTecnicaMinutos(3);
		unidade2.setStInduzidoMinutos(4);
		unidade2.setStOperacionalMinutos(5);
		unidade2.setQuantidadeUnidadesBoasProduzidas(6);
		unidade2.setVolumeTotalProduzido(7);
		unidade2.setTempoCargaMinutos(8);
		unidade2.setRuntimeMinutos(9);
		
		unidade1.addValues(unidade2);
		assertEquals(11, unidade1.getDtOperacionalMinutos(), 0);
		assertEquals(22, unidade1.getDtQualidadeMinutos(), 0);
		assertEquals(33, unidade1.getDtTecnicaMinutos(), 0);
		assertEquals(44, unidade1.getStInduzidoMinutos(), 0);
		assertEquals(55, unidade1.getStOperacionalMinutos(), 0);
		assertEquals(66, unidade1.getQuantidadeUnidadesBoasProduzidas(), 0);
		assertEquals(77, unidade1.getVolumeTotalProduzido(), 0);
		assertEquals(88, unidade1.getTempoCargaMinutos(), 0);
		assertEquals(99, unidade1.getRuntimeMinutos(), 0);

	}
	
	@Test
	public void test_Refresh_Tempo_Cliclo_Teorico_Conforme_Detalhes(){
		OrdemProducao ordemProducao1 = createOrdemProducao(1, 4.0);
		OrdemProducao ordemProducao2 = createOrdemProducao(2, 3.5);
		OrdemProducao ordemProducao3 = createOrdemProducao(3, 2.0);
		OrdemProducao ordemProducao4 = createOrdemProducao(4, 1.5);
		
		UnidadeIndiceOEE unidade1 = createUnidade(ordemProducao1, 10);
		UnidadeIndiceOEE unidade2 = createUnidade(ordemProducao2, 20);
		UnidadeIndiceOEE unidade3 = createUnidade(ordemProducao3, 15);
		UnidadeIndiceOEE unidade4 = createUnidade(ordemProducao4, 30);
		UnidadeIndiceOEE unidade5 = createUnidade(ordemProducao4, 10);
		unidade1.addDetalhe(unidade1);
		unidade1.addDetalhe(unidade2);
		unidade1.addDetalhe(unidade3);
		unidade1.addDetalhe(unidade4);
		unidade1.addDetalhe(unidade5);
		
		unidade1.refreshTempoCicloTeoricoConformeDetalhes();
		assertEquals(2.352941, unidade1.getTempoCicloTeoricoUnidadesPorMinuto(), 0.000001);
	}
	
	private OrdemProducao createOrdemProducao(Integer id) {
		return createOrdemProducao(id, 0D);
	}
	
	private OrdemProducao createOrdemProducao(Integer id, Double unidadesPorMinuto) {
		OrdemProducao ordemProducao = new OrdemProducao();
		ordemProducao.setId(id);
		ordemProducao.setUnidadesPorMinuto(unidadesPorMinuto);
		return ordemProducao;
	}

	private UnidadeIndiceOEE createUnidade(OrdemProducao ordemProducao, Integer tempoUtilMinutos) {
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setOrdemProducao(ordemProducao);
		unidade.setTempoUtilMinutos(tempoUtilMinutos);
		return unidade;
	}


}
