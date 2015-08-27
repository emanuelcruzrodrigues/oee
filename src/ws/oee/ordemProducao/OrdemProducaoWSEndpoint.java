package ws.oee.ordemProducao;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ws.oee.ordemProducao.types.InserirOuAlterarOrdemProducaoRequest;
import ws.oee.ordemProducao.types.InserirOuAlterarOrdemProducaoResponse;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.enums.SituacaoOrdemProducao;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.service.OrdemProducaoService;
import br.feevale.tc.oee.utils.WSUtils;

@Endpoint
public class OrdemProducaoWSEndpoint {
	
	@Resource
	protected OrdemProducaoService ordemProducaoService;
	
	@Resource
	protected EquipamentoService equipamentoService;
	
	@PayloadRoot(localPart="inserirOuAlterarOrdemProducaoRequest", namespace="ordemProducao.ws.oee.tc.feevale.br")
    public @ResponsePayload InserirOuAlterarOrdemProducaoResponse inserirOuAlterar(@RequestPayload InserirOuAlterarOrdemProducaoRequest request) {
		InserirOuAlterarOrdemProducaoResponse result = new InserirOuAlterarOrdemProducaoResponse();
		try {
			OrdemProducao ordemProducao = ordemProducaoService.getByCodigo(request.getCodigo());
			if (ordemProducao == null){
				ordemProducao = new OrdemProducao();
				ordemProducao.setCodigo(request.getCodigo());
			}
			ordemProducao.setDescricao(request.getDescricao());
			
			Equipamento equipamento = equipamentoService.getByCodigo(request.getCodigoEquipamento());
			ordemProducao.setEquipamento(equipamento);
			
			ordemProducao.setUnidadesPorMinuto(request.getUnidadesPorMinuto());
			
			ordemProducao.setDmSituacao(SituacaoOrdemProducao.getFromValue(request.getSituacao()));
			ordemProducaoService.save(ordemProducao);
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
    }

}
