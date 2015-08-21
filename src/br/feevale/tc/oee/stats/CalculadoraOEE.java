package br.feevale.tc.oee.stats;

import com.bullcontrol.util.Calculadora;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 20/08/2015
 * @see CalculadoraOEETest
 */
public class CalculadoraOEE {
	
	public static final int ARREDONDAMENTO_DECIMAL = 20;
	
	/**
	 * OEE = Disponibilidade * Desempenho * Qualidade
	 */
	public void calcularOEE(UnidadeIndiceOEE unidade) {
		calcularDisponibilidade(unidade);
		calcularDesempenho(unidade);
		calcularQualidade(unidade);
		
		try {
			Double oee = 1D;
			oee = Calculadora.multiplicar(oee, unidade.getDisponibilidade(), ARREDONDAMENTO_DECIMAL);
			oee = Calculadora.multiplicar(oee, unidade.getDesempenho(), ARREDONDAMENTO_DECIMAL);
			oee = Calculadora.multiplicar(oee, unidade.getQualidade(), ARREDONDAMENTO_DECIMAL);
			unidade.setOee(oee);
		} catch (Exception e) {
			unidade.setOee(null);
		}
	}


	/**
	 * Disponibilidade = (Tempo de carga – DT) / Tempo de carga
	 */
	public void calcularDisponibilidade(UnidadeIndiceOEE unidade) {
		calcularDtTotal(unidade);
		
		try {
			Double disponibilidade = Calculadora.subtrair(unidade.getTempoCargaMinutos(), unidade.getDtTotalMinutos(), 0);
			disponibilidade = Calculadora.dividir(disponibilidade, unidade.getTempoCargaMinutos(), ARREDONDAMENTO_DECIMAL);
			unidade.setDisponibilidade(disponibilidade);
		} catch (Exception e) {
			unidade.setDisponibilidade(null);
		}
	}

	/**
	 * DT = DT Operacional + DT Qualidade + DT Tecnica + ST Induzido + ST Operacional
	 */
	private void calcularDtTotal(UnidadeIndiceOEE unidade) {
		Double result = 0D;
		result = Calculadora.somar(result, unidade.getDtOperacionalMinutos(), 0);
		result = Calculadora.somar(result, unidade.getDtQualidadeMinutos(), 0);
		result = Calculadora.somar(result, unidade.getDtTecnicaMinutos(), 0);
		result = Calculadora.somar(result, unidade.getStInduzidoMinutos(), 0);
		result = Calculadora.somar(result, unidade.getStOperacionalMinutos(), 0);
		
		unidade.setDtTotalMinutos(result.intValue());
	}

	/**
	 * Desempenho = (1 / Tempo de ciclo teorico) / Tempo de ciclo real
	 */
	public void calcularDesempenho(UnidadeIndiceOEE unidade) {
		try {
			calcularTempoCicloReal(unidade);
			Double desempenho = Calculadora.dividir(1, unidade.getTempoCicloTeoricoUnidadesPorMinuto(), ARREDONDAMENTO_DECIMAL);
			desempenho = Calculadora.dividir(desempenho, unidade.getTempoCicloRealUnidadesPorMinuto(), ARREDONDAMENTO_DECIMAL);
			unidade.setDesempenho(desempenho);
		} catch (Exception e) {
			unidade.setDesempenho(null);
		}
	}

	/**
	 * Tempo de ciclo real = RT / Volume total produzido
	 */
	private void calcularTempoCicloReal(UnidadeIndiceOEE unidade) {
		try {
			Double result = Calculadora.dividir(unidade.getRuntimeMinutos(), unidade.getVolumeTotalProduzido(), ARREDONDAMENTO_DECIMAL);
			unidade.setTempoCicloRealUnidadesPorMinuto(result);
		} catch (Exception e) {
			unidade.setTempoCicloRealUnidadesPorMinuto(null);
		}
	}

	/**
	 * Qualidade = Unidades boas produzidas / Volume total produzido
	 */
	public void calcularQualidade(UnidadeIndiceOEE unidade) {
		try {
			Double qualidade = Calculadora.dividir(unidade.getQuantidadeUnidadesBoasProduzidas(), unidade.getVolumeTotalProduzido(), ARREDONDAMENTO_DECIMAL);
			unidade.setQualidade(qualidade);
		} catch (Exception e) {
			unidade.setQualidade(null);
		}
	}


}
