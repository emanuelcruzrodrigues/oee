package ws.oee.apontamento;

import javax.annotation.Resource;

import org.joda.time.LocalDateTime;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ws.oee.apontamento.encerrarApontamentoEquipamentoService.types.EncerrarApontamentoEquipamentoRequest;
import ws.oee.apontamento.encerrarApontamentoEquipamentoService.types.EncerrarApontamentoEquipamentoResponse;
import ws.oee.apontamento.iniciarApontamentoParadaService.types.IniciarApontamentoParadaRequest;
import ws.oee.apontamento.iniciarApontamentoParadaService.types.IniciarApontamentoParadaResponse;
import ws.oee.apontamento.iniciarApontamentoProducaoService.types.IniciarApontamentoProducaoRequest;
import ws.oee.apontamento.iniciarApontamentoProducaoService.types.IniciarApontamentoProducaoResponse;
import ws.oee.apontamento.inserirApontamentoQuantidadeRefugoService.types.InserirApontamentoQuantidadeRefugoRequest;
import ws.oee.apontamento.inserirApontamentoQuantidadeRefugoService.types.InserirApontamentoQuantidadeRefugoResponse;
import ws.oee.apontamento.inserirApontamentoQuantidadeService.types.InserirApontamentoQuantidadeRequest;
import ws.oee.apontamento.inserirApontamentoQuantidadeService.types.InserirApontamentoQuantidadeResponse;
import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.enums.QualidadeProducao;
import br.feevale.tc.oee.service.ApontamentoQuantidadeService;
import br.feevale.tc.oee.service.ApontamentoTempoParadaService;
import br.feevale.tc.oee.service.ApontamentoTempoProducaoService;
import br.feevale.tc.oee.service.ApontamentoTempoService;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.service.MotivoParadaService;
import br.feevale.tc.oee.service.OrdemProducaoService;
import br.feevale.tc.oee.utils.WSUtils;

@Endpoint
public class ApontamentoWSEndpoint {

	@Resource
	protected OrdemProducaoService ordemProducaoService;

	@Resource
	protected MotivoParadaService motivoParadaService;

	@Resource
	protected EquipamentoService equipamentoService;

	@Resource
	protected ApontamentoTempoProducaoService apontamentoTempoProducaoService;

	@Resource
	protected ApontamentoTempoParadaService apontamentoTempoParadaService;

	@Resource
	protected ApontamentoTempoService apontamentoTempoService;

	@Resource
	protected ApontamentoQuantidadeService apontamentoQuantidadeService;

	@PayloadRoot(localPart="iniciarApontamentoProducaoRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload IniciarApontamentoProducaoResponse iniciarApontamentoProducao(
			@RequestPayload IniciarApontamentoProducaoRequest request){
		
		IniciarApontamentoProducaoResponse result = new IniciarApontamentoProducaoResponse();
		try {
			ApontamentoTempoProducao apontamento = new ApontamentoTempoProducao();

			OrdemProducao ordemProducao = ordemProducaoService.getByCodigo(request.getCodigoOrdemProducao());
			apontamento.setOrdemProducao(ordemProducao);

			apontamento = apontamentoTempoProducaoService.save(apontamento);

		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
	}

	@PayloadRoot(localPart="iniciarApontamentoParadaRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload IniciarApontamentoParadaResponse iniciarApontamentoParada(
			@RequestPayload IniciarApontamentoParadaRequest request){
		
		IniciarApontamentoParadaResponse result = new IniciarApontamentoParadaResponse();
		try {
			ApontamentoTempoParada apontamento = new ApontamentoTempoParada();

			Equipamento equipamento = equipamentoService.getByCodigo(request.getCodigoEquipamento());
			apontamento.setEquipamento(equipamento);

			MotivoParada motivoParada = motivoParadaService.getByCodigo(request.getCodigoMotivoParada());
			apontamento.setMotivoParada(motivoParada);

			apontamentoTempoParadaService.save(apontamento);
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
	}

	@PayloadRoot(localPart="inserirApontamentoQuantidadeRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload InserirApontamentoQuantidadeResponse inserirApontamentoQuantidade(
			@RequestPayload InserirApontamentoQuantidadeRequest request){
		
		InserirApontamentoQuantidadeResponse response = new InserirApontamentoQuantidadeResponse();
		try {
			inserirApontamentoQuantidade(request.getCodigoOrdemProducao(), request.getQuantidade(), QualidadeProducao.DENTRO_DOS_PADROES);
		} catch (Throwable e) {
			WSUtils.updateErrors(response, e);
		}
		return response;
	}

	@PayloadRoot(localPart="inserirApontamentoQuantidadeRefugoRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload InserirApontamentoQuantidadeRefugoResponse inserirApontamentoQuantidadeRefugo(
			@RequestPayload InserirApontamentoQuantidadeRefugoRequest request){
		
		InserirApontamentoQuantidadeRefugoResponse response = new InserirApontamentoQuantidadeRefugoResponse();
		try {
			inserirApontamentoQuantidade(request.getCodigoOrdemProducao(), request.getQuantidade(), QualidadeProducao.REFUGO);
		} catch (Throwable e) {
			WSUtils.updateErrors(response, e);
		}
		return response;
	}

	private void inserirApontamentoQuantidade(Integer cdOrdemProducao, Double quantidade, QualidadeProducao dmQualidade){
		OrdemProducao ordemProducao = ordemProducaoService.getByCodigo(cdOrdemProducao);

		ApontamentoQuantidade apontamento = new ApontamentoQuantidade();
		apontamento.setDtHr(new LocalDateTime());
		apontamento.setOrdemProducao(ordemProducao);
		apontamento.setQuantidade(quantidade);
		apontamento.setDmQualidade(dmQualidade);
		
		apontamento = apontamentoQuantidadeService.save(apontamento);
	}

	@PayloadRoot(localPart="encerrarApontamentoEquipamentoRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload EncerrarApontamentoEquipamentoResponse encerrarApontamentoAtual(
			@RequestPayload EncerrarApontamentoEquipamentoRequest request){
		
		EncerrarApontamentoEquipamentoResponse result = new EncerrarApontamentoEquipamentoResponse();
		try {
			Equipamento equipamento = equipamentoService.getByCodigo(request.getCodigoEquipamento());
			apontamentoTempoService.encerrarApontamentosAbertos(equipamento);
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
	}

}
