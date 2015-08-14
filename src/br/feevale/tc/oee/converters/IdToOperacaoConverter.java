package br.feevale.tc.oee.converters;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import br.feevale.tc.oee.domain.Operacao;
import br.feevale.tc.oee.service.OperacaoService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class IdToOperacaoConverter implements Converter<String, Operacao>{
	
    @Resource 
    private OperacaoService operacaoService;
    
    public Operacao convert(String id) {
        try {
        	return operacaoService.get(new Integer(id));
		} catch (Exception e) {
			return null;
		}
    }

}
