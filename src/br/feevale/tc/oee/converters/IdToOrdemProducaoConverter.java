package br.feevale.tc.oee.converters;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.service.OrdemProducaoService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class IdToOrdemProducaoConverter implements Converter<String, OrdemProducao>{
	
    @Resource 
    private OrdemProducaoService ordemProducaoService;
    
    public OrdemProducao convert(String id) {
        try {
        	return ordemProducaoService.get(new Integer(id));
		} catch (Exception e) {
			return null;
		}
    }

}
