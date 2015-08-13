package br.feevale.tc.oee.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.feevale.tc.oee.dao.MotivoParadaDAO;
import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplate;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplateImpl;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;
import br.feevale.tc.oee.service.validation.MotivoParadaSaveValidationStack;

@Service
public class MotivoParadaService extends CRUDServiceTemplateImpl<MotivoParada>{
	
	@Resource
	protected MotivoParadaDAO motivoParadaDAO;

	@Override
	protected CRUDDAOTemplate<MotivoParada> getCRUDDAO() {
		return motivoParadaDAO;
	}
	
	@Override
	protected OEEValidationStack getBeforeSaveValidationStack(MotivoParada motivoParada) {
		return new MotivoParadaSaveValidationStack(motivoParada, handler);
	}

}
