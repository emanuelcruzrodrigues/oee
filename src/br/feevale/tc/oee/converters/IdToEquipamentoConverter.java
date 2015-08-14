package br.feevale.tc.oee.converters;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.service.EquipamentoService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class IdToEquipamentoConverter implements Converter<String, Equipamento>{
	
    @Resource 
    private EquipamentoService equipamentoService;
    
    public Equipamento convert(String id) {
        try {
        	return equipamentoService.get(new Integer(id));
		} catch (Exception e) {
			return null;
		}
    }

}
