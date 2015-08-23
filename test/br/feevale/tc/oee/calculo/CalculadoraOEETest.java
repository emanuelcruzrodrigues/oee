package br.feevale.tc.oee.calculo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.feevale.tc.oee.stats.CalculadoraOEE;
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
		
		assertEquals(0.286300, unidade.getMinutosPorUnidade(), 0.0001D);
		assertEquals(3.492537, unidade.getTempoCicloRealUnidadesPorMinuto(), 0.0001D);
		assertEquals(0.873134, unidade.getDesempenho(), 0.0001D);
	}
	
	@Test
	public void test_Calcular_Desempenho_Acima_De_100_por_cento() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		unidade.setVolumeTotalProduzido(unidade.getVolumeTotalProduzido() * 2);
		calculadora.calcularDesempenho(unidade);
		
		assertEquals(1D, unidade.getDesempenho(), 0D);
	}
	
	@Test
	public void test_Calcular_Desempenho_Sem_Volume_Total_Produzido() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		unidade.setVolumeTotalProduzido(0);
		
		calculadora.calcularDesempenho(unidade);
		
		assertNull(unidade.getMinutosPorUnidade());
		assertEquals(0D, unidade.getTempoCicloRealUnidadesPorMinuto(), 0D);
		assertNull(unidade.getDesempenho());
	}
	
	@Test
	public void test_Calcular_Desempenho_Sem_Runtime() {
		UnidadeIndiceOEE unidade = createUnidadeCasoTesteConformeHansen();
		unidade.setRuntimeMinutos(0);
		
		calculadora.calcularDesempenho(unidade);
		
		assertEquals(0D, unidade.getMinutosPorUnidade(), 0D);
		assertNull(unidade.getTempoCicloRealUnidadesPorMinuto());
		assertNull(unidade.getDesempenho());
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
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setDtOperacionalMinutos(30);
		unidade.setDtQualidadeMinutos(80);
		unidade.setDtTecnicaMinutos(150);
		unidade.setStInduzidoMinutos(60);
		unidade.setStOperacionalMinutos(170);
		unidade.setRuntimeMinutos(1340);
		unidade.setTempoCargaMinutos(1830);
		unidade.setTempoCicloTeoricoUnidadesPorMinuto(4.0);
		unidade.setQuantidadeUnidadesBoasProduzidas(4362);
		unidade.setVolumeTotalProduzido(4680);
		return unidade;
	}

}
