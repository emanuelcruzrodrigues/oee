package ws.oee.result;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDateTime;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ws.oee.result.types.GetIndiceOEETempoRealRequest;
import ws.oee.result.types.GetIndiceOEETempoRealResponse;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEETempoRealFilter;
import br.feevale.tc.oee.stats.periodo.service.IndiceOEETempoRealService;
import br.feevale.tc.oee.utils.WSUtils;

@Endpoint
public class IndiceOEETempoRealWSEndpoint {
	
	@Resource
	private IndiceOEETempoRealService indiceOEETempoRealService;
	
	@Resource
	private EquipamentoService equipamentoService;
	
	@PayloadRoot(localPart="getIndiceOEETempoRealRequest", namespace="indice.ws.oee.tc.feevale.br")
    public @ResponsePayload GetIndiceOEETempoRealResponse inserirOuAlterar(@RequestPayload GetIndiceOEETempoRealRequest request) {
		GetIndiceOEETempoRealResponse result = new GetIndiceOEETempoRealResponse();
		try {
			Equipamento equipamento = equipamentoService.getByCodigo(request.getCodigoEquipamento());
			
			IndiceOEETempoRealFilter filter = new IndiceOEETempoRealFilter();
			filter.setEquipamento(equipamento);
			filter.setDtHrFinal(new LocalDateTime());
			filter.setDtHrInicial(new LocalDateTime().minusMinutes(request.getPeriodoMinutos()));
			List<UnidadeIndiceOEE> indices = indiceOEETempoRealService.listIndicesOEE(filter);
			if (CollectionUtils.isNotEmpty(indices)){
				UnidadeIndiceOEE indice = indices.get(0);
				result.setDesempenho(indice.getDesempenho() != null ? indice.getDesempenho() : 0);
				result.setDisponibilidade(indice.getDisponibilidade() != null ? indice.getDisponibilidade() : 0);
				result.setQualidade(indice.getQualidade() != null ? indice.getQualidade() : 0);
				result.setOee(indice.getOee() != null ? indice.getOee() : 0);
			}
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
    }


}
