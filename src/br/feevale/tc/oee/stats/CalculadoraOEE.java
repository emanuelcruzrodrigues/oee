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

	public void calcularDesempenho(UnidadeIndiceOEE unidade) {
		try {
			unidade.refreshTempoCicloTeoricoConformeDetalhes();
			Double desempenhoPonderadoTotal = 0D;
			Double somatorioRuntime = 0D;
			for (DetalheUnidadeIndiceOEE detalhe : unidade.getDetalhes()) {
				calcularDesempenho(detalhe);
				Double desempenhoPonderado = Calculadora.multiplicar(detalhe.getDesempenho(), detalhe.getRuntimeMinutos(), ARREDONDAMENTO_DECIMAL);
				desempenhoPonderadoTotal = Calculadora.somar(desempenhoPonderadoTotal, desempenhoPonderado, ARREDONDAMENTO_DECIMAL);
				somatorioRuntime = Calculadora.somar(somatorioRuntime, detalhe.getRuntimeMinutos(), ARREDONDAMENTO_DECIMAL);
			}
			Double desempenho = Calculadora.dividir(desempenhoPonderadoTotal, somatorioRuntime, ARREDONDAMENTO_DECIMAL);
			unidade.setDesempenho(desempenho);
			calcularTempoCicloReal(unidade);
		} catch (Exception e) {
			unidade.setDesempenho(null);
			unidade.setTempoCicloRealUnidadesPorMinuto(null);
		}	
	}

	/**
	 * Desempenho = (1 / Tempo de ciclo teorico) / Minutos por unidade
	 */
	private void calcularDesempenho(DetalheUnidadeIndiceOEE detalhe) {
		try {
			calcularTempoCicloReal(detalhe);
			calcularMinutosPorUnidade(detalhe);
			Double desempenho = Calculadora.dividir(1, detalhe.getTempoCicloTeoricoUnidadesPorMinuto(), ARREDONDAMENTO_DECIMAL);
			desempenho = Calculadora.dividir(desempenho, detalhe.getMinutosPorUnidade(), ARREDONDAMENTO_DECIMAL);
			detalhe.setDesempenho(desempenho);
		} catch (Exception e) {
			detalhe.setDesempenho(null);
		}
	}

	/**
	 * Minutos por unidade = RT / Volume total produzido
	 */
	private void calcularMinutosPorUnidade(DetalheUnidadeIndiceOEE detalhe) {
		try {
			Double result = Calculadora.dividir(detalhe.getRuntimeMinutos(), detalhe.getVolumeTotalProduzido(), ARREDONDAMENTO_DECIMAL);
			detalhe.setMinutosPorUnidade(result);
		} catch (Exception e) {
			detalhe.setMinutosPorUnidade(null);
		}
	}
	
	private void calcularTempoCicloReal(UnidadeIndiceOEE unidade) {
		Double result = calcularTempoCicloReal(unidade.getVolumeTotalProduzido(), unidade.getRuntimeMinutos());
		unidade.setTempoCicloRealUnidadesPorMinuto(result);
	}
	
	private void calcularTempoCicloReal(DetalheUnidadeIndiceOEE detalhe) {
		Double result = calcularTempoCicloReal(detalhe.getVolumeTotalProduzido(), detalhe.getRuntimeMinutos());
		detalhe.setTempoCicloRealUnidadesPorMinuto(result);
	}
	
	/**
	 * Tempo de ciclo real = Volume total produzido / RT
	 */
	private Double calcularTempoCicloReal(Integer volumeTotalProduzido, Integer runtimeMinutos) {
		try {
			return Calculadora.dividir(volumeTotalProduzido, runtimeMinutos, ARREDONDAMENTO_DECIMAL);
		} catch (Exception e) {
			return null;
		}
	}

	public void calcularQualidade(UnidadeIndiceOEE unidade) {
		Double qualidade = calcularQualidade(unidade.getQuantidadeUnidadesBoasProduzidas(), unidade.getVolumeTotalProduzido());
		unidade.setQualidade(qualidade);
	}
	
	public void calcularQualidade(DetalheUnidadeIndiceOEE detalhe) {
		Double qualidade = calcularQualidade(detalhe.getQuantidadeUnidadesBoasProduzidas(), detalhe.getVolumeTotalProduzido());
		detalhe.setQualidade(qualidade);
	}
	
	/**
	 * Qualidade = Unidades boas produzidas / Volume total produzido
	 */
	public Double calcularQualidade(Integer unidadesBoas, Integer volumeTotal) {
		try {
			return Calculadora.dividir(unidadesBoas, volumeTotal, ARREDONDAMENTO_DECIMAL);
		} catch (Exception e) {
			return null;
		}
	}


}
