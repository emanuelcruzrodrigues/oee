package br.feevale.tc.oee.stats;

import static org.junit.Assert.*;

import org.junit.Test;

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


}
