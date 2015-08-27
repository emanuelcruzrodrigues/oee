package ws.oee.motivoParada;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ws.oee.motivoParada.types.InserirOuAlterarMotivoParadaRequest;
import ws.oee.motivoParada.types.InserirOuAlterarMotivoParadaResponse;
import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.enums.AtivoInativo;
import br.feevale.tc.oee.enums.TipoParada;
import br.feevale.tc.oee.service.MotivoParadaService;
import br.feevale.tc.oee.utils.WSUtils;

@Endpoint
public class MotivoParadaWSEndpoint {
	
	@Resource
	protected MotivoParadaService motivoParadaService;
	
	@PayloadRoot(localPart="inserirOuAlterarMotivoParadaRequest", namespace="motivoParada.ws.oee.tc.feevale.br")
    public @ResponsePayload InserirOuAlterarMotivoParadaResponse inserirOuAlterar(@RequestPayload InserirOuAlterarMotivoParadaRequest request) {
		InserirOuAlterarMotivoParadaResponse result = new InserirOuAlterarMotivoParadaResponse();
		try {
			MotivoParada motivo = motivoParadaService.getByCodigo(request.getCodigo());
			if (motivo == null){
				motivo = new MotivoParada();
				motivo.setCodigo(request.getCodigo());
			}
			motivo.setDescricao(request.getDescricao());
			motivo.setDmTipoParada(TipoParada.getFromValue(request.getTipoParada()));
			motivo.setDmSituacao(AtivoInativo.getFromValue(request.getSituacao()));
			motivoParadaService.save(motivo);
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
    }

}
