package ws.oee.equipamento;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ws.oee.equipamento.exclusao.types.ExcluirEquipamentoRequest;
import ws.oee.equipamento.exclusao.types.ExcluirEquipamentoResponse;
import ws.oee.equipamento.inclusao.types.InserirOuAlterarEquipamentoRequest;
import ws.oee.equipamento.inclusao.types.InserirOuAlterarEquipamentoResponse;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.enums.AtivoInativo;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.utils.WSUtils;

@Endpoint
public class EquipamentoWSEndpoint {
	
	@Resource
	private EquipamentoService equipamentoService;
	
	@PayloadRoot(localPart="inserirOuAlterarEquipamentoRequest", namespace="equipamento.ws.oee.tc.feevale.br")
    public @ResponsePayload InserirOuAlterarEquipamentoResponse inserirOuAlterar(@RequestPayload InserirOuAlterarEquipamentoRequest request) {
		InserirOuAlterarEquipamentoResponse result = new InserirOuAlterarEquipamentoResponse();
		try {
			Equipamento equipamento = equipamentoService.getByCodigo(request.getCodigo());
			if (equipamento == null){
				equipamento = new Equipamento();
				equipamento.setCodigo(request.getCodigo());
			}
			equipamento.setNome(request.getNome());
			equipamento.setDmSituacao(AtivoInativo.getFromValue(request.getSituacao()));
			equipamentoService.save(equipamento);
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
    }
	
	@PayloadRoot(localPart="excluirEquipamentoRequest", namespace="equipamento.ws.oee.tc.feevale.br")
    public @ResponsePayload ExcluirEquipamentoResponse excluir(@RequestPayload ExcluirEquipamentoRequest request) {
		ExcluirEquipamentoResponse result = new ExcluirEquipamentoResponse();
		try {
			Equipamento equipamento = equipamentoService.getByCodigo(request.getCodigo());
			if (equipamento != null){
				equipamentoService.delete(equipamento);
			}
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
    }

}
