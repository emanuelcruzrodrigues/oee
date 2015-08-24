package br.feevale.tc.oee.calculo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.stats.CalculadoraOEE;
import br.feevale.tc.oee.stats.DetalheUnidadeIndiceOEE;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 20/08/2015
 */
public class CalculadoraOEETest {

	private CalculadoraOEE calculadora;
	
	@Before
	public void setUp(){
		calculadora = new CalculadoraOEE();
	}
	
	@Test
	public void test_Calcular_Disponibilidade() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		
		calculadora.calcularDisponibilidade(unidade);
		
		assertEquals(490, unidade.getDtTotalMinutos(), 0);
		assertEquals(0.73224, unidade.getDisponibilidade(), 0.0001D);
	}
	
	@Test
	public void test_Calcular_Disponibilidade_Sem_Tempo_De_Carga() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		unidade.setTempoCargaMinutos(0);
		
		calculadora.calcularDisponibilidade(unidade);
		
		assertEquals(490, unidade.getDtTotalMinutos(), 0);
		assertNull(unidade.getDisponibilidade());
	}

	@Test
	public void test_Calcular_Desempenho() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		
		calculadora.calcularDesempenho(unidade);
		
		assertEquals(0.286300, unidade.getDetalhes().get(0).getMinutosPorUnidade(), 0.0001D);
		assertEquals(3.492537, unidade.getTempoCicloRealUnidadesPorMinuto(), 0.0001D);
		assertEquals(0.873134, unidade.getDesempenho(), 0.0001D);
	}
	
	@Test
	public void test_Calcular_Desempenho_Acima_De_100_por_cento() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		
		int volumeTotalProduzido = unidade.getVolumeTotalProduzido() * 2;
		
		unidade.setVolumeTotalProduzido(volumeTotalProduzido);
		unidade.getDetalhes().get(0).setVolumeTotalProduzido(volumeTotalProduzido);
		
		calculadora.calcularDesempenho(unidade);
		
		assertEquals(1D, unidade.getDesempenho(), 0D);
	}
	
	@Test
	public void test_Calcular_Desempenho_Sem_Volume_Total_Produzido() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		unidade.setVolumeTotalProduzido(0);
		unidade.getDetalhes().get(0).setVolumeTotalProduzido(0);
		
		calculadora.calcularDesempenho(unidade);
		
		assertNull(unidade.getDetalhes().get(0).getMinutosPorUnidade());
		assertNull(unidade.getTempoCicloRealUnidadesPorMinuto());
		assertNull(unidade.getDesempenho());
	}
	
	@Test
	public void test_Calcular_Desempenho_Sem_Runtime() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		unidade.setRuntimeMinutos(0);
		unidade.getDetalhes().get(0).setRuntimeMinutos(0);
		
		calculadora.calcularDesempenho(unidade);
		
		assertEquals(0D, unidade.getDetalhes().get(0).getMinutosPorUnidade(), 0D);
		assertNull(unidade.getTempoCicloRealUnidadesPorMinuto());
		assertNull(unidade.getDesempenho());
	}
	
	@Test
	public void test_Calcular_Desempenho_Ponderado_Conforme_Detalhes() {
		
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		
		DetalheUnidadeIndiceOEE detalhe1 = unidade.getDetalhes().get(0);
		detalhe1.setTempoCicloTeoricoUnidadesPorMinuto(5D);
		detalhe1.setRuntimeMinutos(600);
		detalhe1.setVolumeTotalProduzido(2000);
		
		OrdemProducao ordemProducao2 = createOrdemProducao(2, 4D);
		DetalheUnidadeIndiceOEE detalhe2 = createDetalheConformeHansen(ordemProducao2);
		detalhe2.setRuntimeMinutos(740);
		detalhe2.setVolumeTotalProduzido(2680);
		unidade.addDetalhe(detalhe2);
		
		calculadora.calcularDesempenho(unidade);
		
		assertEquals(0.300000, detalhe1.getMinutosPorUnidade(), 0.0001D);
		assertEquals(3.333333, detalhe1.getTempoCicloRealUnidadesPorMinuto(), 0.0001D);
		assertEquals(0.666667, detalhe1.getDesempenho(), 0.0001D);
		
		assertEquals(0.276119, detalhe2.getMinutosPorUnidade(), 0.0001D);
		assertEquals(3.621622, detalhe2.getTempoCicloRealUnidadesPorMinuto(), 0.0001D);
		assertEquals(0.905405, detalhe2.getDesempenho(), 0.0001D);
		
		assertNull(unidade.getTempoCicloTeoricoUnidadesPorMinuto());
		assertEquals(3.492537313, unidade.getTempoCicloRealUnidadesPorMinuto(), 0.0001D);
		assertEquals(0.798507, unidade.getDesempenho(), 0.0001D);
	}
	
	private OrdemProducao createOrdemProducao(Integer id, Double unidadesPorMinuto) {
		OrdemProducao ordemProducao = new OrdemProducao();
		ordemProducao.setId(id);
		ordemProducao.setUnidadesPorMinuto(unidadesPorMinuto);
		return ordemProducao ;
	}

	@Test
	public void test_Calcular_Qualidade() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		
		calculadora.calcularQualidade(unidade);

		assertEquals(0.932051, unidade.getQualidade(), 0.0001D);
	}
	
	@Test
	public void test_Calcular_Qualidade_Sem_Volume_Total_Produzido() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		unidade.setVolumeTotalProduzido(0);
		
		calculadora.calcularQualidade(unidade);
		
		assertNull(unidade.getQualidade());
	}
	
	@Test
	public void test_Calcular_OEE() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		
		calculadora.calcularOEE(unidade);

		assertEquals(0.59590164, unidade.getOee(), 0.0001D);
	}
	
	@Test
	public void test_Calcular_OEE_Sem_Tempo_Carga() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		unidade.setTempoCargaMinutos(0);
		
		calculadora.calcularOEE(unidade);
		
		assertNull(unidade.getOee());
	}
	
	@Test
	public void test_Calcular_OEE_Sem_Volume_Total_Produzido() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		unidade.setVolumeTotalProduzido(0);
		
		calculadora.calcularOEE(unidade);
		
		assertNull(unidade.getOee());
	}
	
	private UnidadeIndiceOEE createUnidadeCasoTesteConformeHansen() {
		OrdemProducao ordemProducao = new OrdemProducao();
		ordemProducao.setId(1);
		ordemProducao.setUnidadesPorMinuto(4D);
		
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setDtOperacionalMinutos(30);
		unidade.setDtQualidadeMinutos(80);
		unidade.setDtTecnicaMinutos(150);
		unidade.setStInduzidoMinutos(60);
		unidade.setStOperacionalMinutos(170);
		unidade.setTempoCargaMinutos(1830);
		unidade.setQuantidadeUnidadesBoasProduzidas(4362);
		unidade.setRuntimeMinutos(1340);
		unidade.setVolumeTotalProduzido(4680);
		
		DetalheUnidadeIndiceOEE detalhe = createDetalheConformeHansen(ordemProducao);
		unidade.addDetalhe(detalhe);
		
		return unidade;
	}

	private DetalheUnidadeIndiceOEE createDetalheConformeHansen(OrdemProducao ordemProducao) {
		DetalheUnidadeIndiceOEE detalhe = new DetalheUnidadeIndiceOEE(ordemProducao);
		detalhe.setTempoCicloTeoricoUnidadesPorMinuto(4D);
		detalhe.setRuntimeMinutos(1340);
		detalhe.setVolumeTotalProduzido(4680);
		return detalhe;
	}

}
