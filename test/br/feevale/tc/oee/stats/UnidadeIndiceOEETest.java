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
		
		DetalheUnidadeIndiceOEE detalhe1 = new DetalheUnidadeIndiceOEE(ordemProducao1);
		DetalheUnidadeIndiceOEE detalhe2 = new DetalheUnidadeIndiceOEE(ordemProducao2);
		
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		
		unidade.addDetalhe(detalhe1);
		assertEquals(1, unidade.getDetalhes().size(), 0);
		
		unidade.addDetalhe(detalhe1);
		assertEquals(1, unidade.getDetalhes().size(), 0);
		
		unidade.addDetalhe(detalhe2);
		assertEquals(2, unidade.getDetalhes().size(), 0);
		
		unidade.addDetalhe(detalhe2);
		assertEquals(2, unidade.getDetalhes().size(), 0);
		
	}
	
	@Test
	public void test_Add_Values(){
		DetalheUnidadeIndiceOEE detalhe1 = new DetalheUnidadeIndiceOEE(new OrdemProducao());
		DetalheUnidadeIndiceOEE detalhe2 = new DetalheUnidadeIndiceOEE(new OrdemProducao());
				
		detalhe1.setQuantidadeUnidadesBoasProduzidas(60);
		detalhe1.setVolumeTotalProduzido(70);
		detalhe1.setRuntimeMinutos(90);
		
		detalhe2.setQuantidadeUnidadesBoasProduzidas(6);
		detalhe2.setVolumeTotalProduzido(7);
		detalhe2.setRuntimeMinutos(9);
		
		detalhe1.addValues(detalhe2);
		assertEquals(66, detalhe1.getQuantidadeUnidadesBoasProduzidas(), 0);
		assertEquals(77, detalhe1.getVolumeTotalProduzido(), 0);
		assertEquals(99, detalhe1.getRuntimeMinutos(), 0);

	}
	
	@Test
	public void test_refreshTempoCicloTeoricoConformeDetalhes(){
		
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.refreshTempoCicloTeoricoConformeDetalhes();
		assertNull(unidade.getTempoCicloTeoricoUnidadesPorMinuto());
		
		DetalheUnidadeIndiceOEE detalhe1 = new DetalheUnidadeIndiceOEE(new OrdemProducao());
		detalhe1.setTempoCicloTeoricoUnidadesPorMinuto(4D);
		unidade.addDetalhe(detalhe1);
		unidade.refreshTempoCicloTeoricoConformeDetalhes();
		assertEquals(4D, unidade.getTempoCicloTeoricoUnidadesPorMinuto(), 0D);
		
		DetalheUnidadeIndiceOEE detalhe2 = new DetalheUnidadeIndiceOEE(new OrdemProducao());
		detalhe2.setTempoCicloTeoricoUnidadesPorMinuto(4D);
		unidade.addDetalhe(detalhe2);
		unidade.refreshTempoCicloTeoricoConformeDetalhes();
		assertEquals(4D, unidade.getTempoCicloTeoricoUnidadesPorMinuto(), 0D);
		
		DetalheUnidadeIndiceOEE detalhe3 = new DetalheUnidadeIndiceOEE(new OrdemProducao());
		detalhe3.setTempoCicloTeoricoUnidadesPorMinuto(3D);
		unidade.addDetalhe(detalhe3);
		unidade.refreshTempoCicloTeoricoConformeDetalhes();
		assertNull(unidade.getTempoCicloTeoricoUnidadesPorMinuto());
		
	}
	
	@Test
	public void test_Impedir_Disponibilidade_Acima_De_100_Por_Cento(){
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setDisponibilidade(null);
		assertNull(unidade.getDisponibilidade());
		
		for (int i = 0; i < 20; i++) {
			double disponibilidade = 0.1 * i;
			unidade.setDisponibilidade(disponibilidade);
			if (disponibilidade > 1D){
				assertEquals(1D, unidade.getDisponibilidade(), 0D);
			}else{
				assertEquals(disponibilidade, unidade.getDisponibilidade(), 0D);
			}
		}
	}
	
	@Test
	public void test_Impedir_Desempenho_Acima_De_100_Por_Cento(){
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setDesempenho(null);
		assertNull(unidade.getDesempenho());
		
		for (int i = 0; i < 20; i++) {
			double desempenho = 0.1 * i;
			unidade.setDesempenho(desempenho);
			if (desempenho > 1D){
				assertEquals(1D, unidade.getDesempenho(), 0D);
			}else{
				assertEquals(desempenho, unidade.getDesempenho(), 0D);
			}
		}
	}
	
	@Test
	public void test_Impedir_Qualidade_Acima_De_100_Por_Cento(){
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setQualidade(null);
		assertNull(unidade.getQualidade());
		
		for (int i = 0; i < 20; i++) {
			double qualidade = 0.1 * i;
			unidade.setQualidade(qualidade);
			if (qualidade > 1D){
				assertEquals(1D, unidade.getQualidade(), 0D);
			}else{
				assertEquals(qualidade, unidade.getQualidade(), 0D);
			}
		}
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


}
