package com.bullcontrol.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

	public static Double multiplicar(Number multiplicando, Number multiplicador, int scale) {
        BigDecimal bdMultiplicando = new BigDecimal(multiplicando.toString());
        BigDecimal bdMultiplicador = new BigDecimal(multiplicador.toString());
        return new Double(multiplicar(bdMultiplicando, bdMultiplicador, scale).doubleValue());
    }

    private static BigDecimal multiplicar(BigDecimal multiplicando, BigDecimal multiplicador, int scale) {
        BigDecimal bdMultiplicando = new BigDecimal(multiplicando.toString());
        BigDecimal bdMultiplicador = new BigDecimal(multiplicador.toString());
        BigDecimal retorno = bdMultiplicando.multiply(bdMultiplicador);
        return retorno.setScale(scale, ROUNDING_MODE);
    }
    
    public static Double dividir(Number dividendo, Number divisor, int scale) {
        BigDecimal bdDivisor = new BigDecimal(divisor.toString()).setScale(15, ROUNDING_MODE);
        BigDecimal bdDividendo = new BigDecimal(dividendo.toString()).setScale(15, ROUNDING_MODE);
        return new Double(dividir(bdDividendo, bdDivisor, scale).doubleValue());
    }
 
    private static BigDecimal dividir(BigDecimal dividendo, BigDecimal divisor, int scale) {
        BigDecimal bdDivisor = new BigDecimal(divisor.toString()).setScale(15, ROUNDING_MODE);
        BigDecimal bdDividendo = new BigDecimal(dividendo.toString()).setScale(15, ROUNDING_MODE);
        return bdDividendo.divide(bdDivisor, ROUNDING_MODE).setScale(scale, ROUNDING_MODE);
    }

    public static Double calcularVolume(Double compr, Double larg, Double alt, int scale) {
    	Double area = calcularArea(compr, larg, scale);
    	return calcularVolumePelaArea(area, alt, scale);
    }
    
	public static Double calcularVolumePelaArea(Double area, Double alt, int scale) {
		return multiplicar(area, alt, scale);
	}

	public static Double calcularArea(Double compr, Double larg, int scale) {
		return multiplicar(compr, larg, scale);
	}
	
	public static Double calcularAreaPeloVolume(Double volume, Double altura, int scale) {
		return dividir(volume, altura, scale);
	}


	public static Double calcularPeso(Double compr, Double larg, Double alt, Double densidade, int scale) {
		Double volume = calcularVolume(compr, larg, alt, 10);
		return calcularPeso(volume, densidade, scale);
	}
	
	public static Double calcularPeso(Double volume, Double densidade, int scale) {
		Double result = multiplicar(volume, densidade, scale);
		return result;
	}

	public static Double calcularDensidade(Double compr, Double larg, Double alt, Double peso, int scale) {
		Double volume = calcularVolume(compr, larg, alt, 10);
		return calcularDensidade(volume, peso, scale);
	}
	
	public static Double calcularDensidade(Double volume, Double peso, int scale) {
		return dividir(peso, volume, scale);
	}
	
    public static Double somar(Number valor1, Number valor2, int scale) {
        BigDecimal bdValor1 = new BigDecimal(getDouble(valor1).toString()).setScale(15, ROUNDING_MODE);
        BigDecimal bdValor2 = new BigDecimal(getDouble(valor2).toString()).setScale(15, ROUNDING_MODE);
        return new Double(somar(bdValor1, bdValor2, scale).doubleValue());
    }
    
    private static BigDecimal somar(BigDecimal valor1, BigDecimal valor2, int scale) {
        BigDecimal bdValor1 = new BigDecimal(getDouble(valor1).toString()).setScale(15, ROUNDING_MODE);
        BigDecimal bdValor2 = new BigDecimal(getDouble(valor2).toString()).setScale(15, ROUNDING_MODE);
        BigDecimal resultado = bdValor1.add(bdValor2);
        return resultado.setScale(scale, ROUNDING_MODE);
    }
    
    public static Double subtrair(Number valor1, Number valor2, int scale) {
        BigDecimal bdValor1 = new BigDecimal(getDouble(valor1).toString()).setScale(15, ROUNDING_MODE);
        BigDecimal bdValor2 = new BigDecimal(getDouble(valor2).toString()).setScale(15, ROUNDING_MODE);
        return new Double(subtrair(bdValor1, bdValor2, scale).doubleValue());
    }
    
    private static BigDecimal subtrair(BigDecimal valor1, BigDecimal valor2, int scale) {
        BigDecimal bdValor1 = new BigDecimal(getDouble(valor1).toString()).setScale(15, ROUNDING_MODE);
        BigDecimal bdValor2 = new BigDecimal(getDouble(valor2).toString()).setScale(15, ROUNDING_MODE);
        BigDecimal resultado = bdValor1.subtract(bdValor2);
        return resultado.setScale(scale, ROUNDING_MODE);
    }

    private static Double getDouble(Number number) {
		return number == null ? 0D : number.doubleValue(); 
	}

	public static Double potencia(Double valor, int potencia, int scale) {
        BigDecimal bdValor = new BigDecimal(getDouble(valor).toString());
        BigDecimal retorno = bdValor.pow(potencia);
        return retorno.setScale(scale, ROUNDING_MODE).doubleValue();
	}

	public static Double arredondar(Double valor, int scale) {
		BigDecimal bdValor = new BigDecimal(getDouble(valor).toString());
		return bdValor.setScale(scale, ROUNDING_MODE).doubleValue();
	}
	
	public static Double arredondar(Double valor, Double multiplo, int scale) {
		Double result = dividir(valor, multiplo, 0);
		return multiplicar(result, multiplo, scale);
	}


}
