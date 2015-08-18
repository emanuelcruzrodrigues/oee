package br.feevale.tc.oee.converters;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.service.MotivoParadaService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 18/08/2015
 */
public class IdToMotivoParadaConverter implements Converter<String, MotivoParada>{
	
    @Resource 
    private MotivoParadaService motivoParadaService;
    
    public MotivoParada convert(String id) {
        try {
        	return motivoParadaService.get(new Integer(id));
		} catch (Exception e) {
			return null;
		}
    }

}
