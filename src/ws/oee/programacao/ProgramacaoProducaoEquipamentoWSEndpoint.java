package ws.oee.programacao;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ws.oee.programacao.exclusao.types.Data;
import ws.oee.programacao.exclusao.types.ExcluirProgramacaoProducaoEquipamentoRequest;
import ws.oee.programacao.exclusao.types.ExcluirProgramacaoProducaoEquipamentoResponse;
import ws.oee.programacao.inclusao.types.DataHoraFinal;
import ws.oee.programacao.inclusao.types.DataHoraInicial;
import ws.oee.programacao.inclusao.types.InserirProgramacaoProducaoEquipamentoRequest;
import ws.oee.programacao.inclusao.types.InserirProgramacaoProducaoEquipamentoResponse;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.service.ProgramacaoProducaoEquipamentoService;
import br.feevale.tc.oee.utils.WSUtils;

@Endpoint
public class ProgramacaoProducaoEquipamentoWSEndpoint {
	
	@Resource
	protected EquipamentoService equipamentoService;
	
	@Resource
	protected ProgramacaoProducaoEquipamentoService programacaoProducaoEquipamentoService;
	
	@PayloadRoot(localPart="excluirProgramacaoProducaoEquipamentoRequest", namespace="programacao.ws.oee.tc.feevale.br")
    public @ResponsePayload ExcluirProgramacaoProducaoEquipamentoResponse excluir(@RequestPayload ExcluirProgramacaoProducaoEquipamentoRequest request) {
		ExcluirProgramacaoProducaoEquipamentoResponse result = new ExcluirProgramacaoProducaoEquipamentoResponse();
		try {
			Equipamento equipamento = equipamentoService.getByCodigo(request.getCodigoEquipamento());
			if (equipamento != null){
				programacaoProducaoEquipamentoService.excluirProgramacao(equipamento, toLocalDate(request.getData()));
			}
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
    }

	
	private LocalDate toLocalDate(Data dt) {
		return new LocalDate(dt.getAno(), dt.getMes(), dt.getDia());
	}

	@PayloadRoot(localPart="inserirProgramacaoProducaoEquipamentoRequest", namespace="programacao.ws.oee.tc.feevale.br")
    public @ResponsePayload InserirProgramacaoProducaoEquipamentoResponse inserir(@RequestPayload InserirProgramacaoProducaoEquipamentoRequest request) {
		InserirProgramacaoProducaoEquipamentoResponse result = new InserirProgramacaoProducaoEquipamentoResponse();
		try {
			Equipamento equipamento = equipamentoService.getByCodigo(request.getCodigoEquipamento());
			ProgramacaoProducaoEquipamento programacao = new ProgramacaoProducaoEquipamento();
			programacao.setEquipamento(equipamento);
			programacao.setDtHrInicio(toLocalDateTime(request.getDataHoraInicial()));
			programacao.setDtHrFim(toLocalDateTime(request.getDataHoraFinal()));
			programacaoProducaoEquipamentoService.save(programacao);
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
    }

	private LocalDateTime toLocalDateTime(DataHoraFinal dtHr) {
		return new LocalDateTime(dtHr.getAno(), dtHr.getMes(), dtHr.getDia(), dtHr.getHora(), dtHr.getMinuto(), 0, 0);
	}

	private LocalDateTime toLocalDateTime(DataHoraInicial dtHr) {
		return new LocalDateTime(dtHr.getAno(), dtHr.getMes(), dtHr.getDia(), dtHr.getHora(), dtHr.getMinuto(), 0, 0);
	}


}
