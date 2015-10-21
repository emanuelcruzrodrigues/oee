package ws.oee.apontamento;

import javax.annotation.Resource;

import org.joda.time.LocalDateTime;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ws.oee.apontamento.encerrarApontamentoEquipamentoService.types.EncerrarApontamentoEquipamentoRequest;
import ws.oee.apontamento.encerrarApontamentoEquipamentoService.types.EncerrarApontamentoEquipamentoResponse;
import ws.oee.apontamento.excluirApontamentoParada.types.ExcluirApontamentoParadaRequest;
import ws.oee.apontamento.excluirApontamentoParada.types.ExcluirApontamentoParadaResponse;
import ws.oee.apontamento.excluirApontamentoProducao.types.ExcluirApontamentoProducaoRequest;
import ws.oee.apontamento.excluirApontamentoProducao.types.ExcluirApontamentoProducaoResponse;
import ws.oee.apontamento.excluirApontamentoQuantidade.types.ExcluirApontamentoQuantidadeRequest;
import ws.oee.apontamento.excluirApontamentoQuantidade.types.ExcluirApontamentoQuantidadeResponse;
import ws.oee.apontamento.iniciarApontamentoParadaService.types.IniciarApontamentoParadaRequest;
import ws.oee.apontamento.iniciarApontamentoParadaService.types.IniciarApontamentoParadaResponse;
import ws.oee.apontamento.iniciarApontamentoProducaoService.types.IniciarApontamentoProducaoRequest;
import ws.oee.apontamento.iniciarApontamentoProducaoService.types.IniciarApontamentoProducaoResponse;
import ws.oee.apontamento.inserirAlterarApontamentoParada.types.InserirOuAlterarApontamentoParadaRequest;
import ws.oee.apontamento.inserirAlterarApontamentoParada.types.InserirOuAlterarApontamentoParadaResponse;
import ws.oee.apontamento.inserirAlterarApontamentoProducao.types.DataHoraFinal;
import ws.oee.apontamento.inserirAlterarApontamentoProducao.types.DataHoraInicial;
import ws.oee.apontamento.inserirAlterarApontamentoProducao.types.InserirOuAlterarApontamentoProducaoRequest;
import ws.oee.apontamento.inserirAlterarApontamentoProducao.types.InserirOuAlterarApontamentoProducaoResponse;
import ws.oee.apontamento.inserirAlterarApontamentoQuantidade.types.DataHora;
import ws.oee.apontamento.inserirAlterarApontamentoQuantidade.types.InserirOuAlterarApontamentoQuantidadeRequest;
import ws.oee.apontamento.inserirAlterarApontamentoQuantidade.types.InserirOuAlterarApontamentoQuantidadeResponse;
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
	
	@PayloadRoot(localPart="inserirOuAlterarApontamentoProducaoRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload InserirOuAlterarApontamentoProducaoResponse inserirOuAlterarApontamentoProducao(
			@RequestPayload InserirOuAlterarApontamentoProducaoRequest request){
		
		InserirOuAlterarApontamentoProducaoResponse result = new InserirOuAlterarApontamentoProducaoResponse();
		
		try {
			
			ApontamentoTempoProducao apontamento = apontamentoTempoProducaoService.getByCodigo(request.getCodigo());
			if (apontamento == null){
				apontamento = new ApontamentoTempoProducao();
			}
			
			apontamento.setCodigo(request.getCodigo());
			
			OrdemProducao ordemProducao = ordemProducaoService.getByCodigo(request.getCodigoOrdemProducao());
			apontamento.setOrdemProducao(ordemProducao);
			
			DataHoraInicial dataHoraInicial = request.getDataHoraInicial();
			if (dataHoraInicial != null){
				LocalDateTime dtHrEntrada = new LocalDateTime(
						  dataHoraInicial.getAno(), dataHoraInicial.getMes(), dataHoraInicial.getDia()
						, dataHoraInicial.getHora(), dataHoraInicial.getMinuto());
				apontamento.setDtHrEntrada(dtHrEntrada);
			}
			
			DataHoraFinal dataHoraFinal = request.getDataHoraFinal();
			if (dataHoraFinal != null){
				LocalDateTime dtHrSaida = new LocalDateTime(dataHoraFinal.getAno(), dataHoraFinal.getMes(), dataHoraFinal.getDia()
														  , dataHoraFinal.getHora(), dataHoraFinal.getMinuto());
				apontamento.setDtHrSaida(dtHrSaida);
			}

			apontamentoTempoProducaoService.save(apontamento);
			
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
	
	@PayloadRoot(localPart="inserirOuAlterarApontamentoParadaRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload InserirOuAlterarApontamentoParadaResponse inserirOuAlterarApontamentoParada(
			@RequestPayload InserirOuAlterarApontamentoParadaRequest request){
		
		InserirOuAlterarApontamentoParadaResponse result = new InserirOuAlterarApontamentoParadaResponse();
		
		try {
			ApontamentoTempoParada apontamento = apontamentoTempoParadaService.getByCodigo(request.getCodigo());
			if (apontamento == null){
				apontamento = new ApontamentoTempoParada();
			}
			
			apontamento.setCodigo(request.getCodigo());
			
			Equipamento equipamento = equipamentoService.getByCodigo(request.getCodigoEquipamento());
			apontamento.setEquipamento(equipamento);

			MotivoParada motivoParada = motivoParadaService.getByCodigo(request.getCodigoMotivoParada());
			apontamento.setMotivoParada(motivoParada);
			
			ws.oee.apontamento.inserirAlterarApontamentoParada.types.DataHoraInicial dataHoraInicial = request.getDataHoraInicial();
			if (dataHoraInicial != null){
				LocalDateTime dtHrEntrada = new LocalDateTime(
						  dataHoraInicial.getAno(), dataHoraInicial.getMes(), dataHoraInicial.getDia()
						, dataHoraInicial.getHora(), dataHoraInicial.getMinuto());
				apontamento.setDtHrEntrada(dtHrEntrada);
			}
			
			ws.oee.apontamento.inserirAlterarApontamentoParada.types.DataHoraFinal dataHoraFinal = request.getDataHoraFinal();
			if (dataHoraFinal != null){
				LocalDateTime dtHrSaida = new LocalDateTime(dataHoraFinal.getAno(), dataHoraFinal.getMes(), dataHoraFinal.getDia()
														  , dataHoraFinal.getHora(), dataHoraFinal.getMinuto());
				apontamento.setDtHrSaida(dtHrSaida);
			}

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

	private ApontamentoQuantidade inserirApontamentoQuantidade(Integer cdOrdemProducao, Double quantidade, QualidadeProducao dmQualidade){
		OrdemProducao ordemProducao = ordemProducaoService.getByCodigo(cdOrdemProducao);

		ApontamentoQuantidade apontamento = new ApontamentoQuantidade();
		apontamento.setDtHr(new LocalDateTime());
		apontamento.setOrdemProducao(ordemProducao);
		apontamento.setQuantidade(quantidade);
		apontamento.setDmQualidade(dmQualidade);
		
		apontamento = apontamentoQuantidadeService.save(apontamento);
		
		return apontamento;
	}
	
	@PayloadRoot(localPart="inserirOuAlterarApontamentoQuantidadeRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload InserirOuAlterarApontamentoQuantidadeResponse inserirOuAlterarApontamentoQuantidade(
			@RequestPayload InserirOuAlterarApontamentoQuantidadeRequest request){
		
		InserirOuAlterarApontamentoQuantidadeResponse result = new InserirOuAlterarApontamentoQuantidadeResponse();
		
		try {
			
			ApontamentoQuantidade apontamento = apontamentoQuantidadeService.getByCodigo(request.getCodigo());
			
			if (apontamento == null){
				apontamento = new ApontamentoQuantidade();
			}
			apontamento.setCodigo(request.getCodigo());
			
			DataHora dataHora = request.getDataHora();
			if(dataHora != null){
				apontamento.setDtHr(new LocalDateTime(dataHora.getAno(), dataHora.getMes(), dataHora.getDia(), dataHora.getHora(), dataHora.getMinuto()));
			}else{
				apontamento.setDtHr(new LocalDateTime());
			}
			
			OrdemProducao ordemProducao = ordemProducaoService.getByCodigo(request.getCodigoOrdemProducao());
			apontamento.setOrdemProducao(ordemProducao);
			
			apontamento.setQuantidade(request.getQuantidade());
			
			apontamento.setDmQualidade(QualidadeProducao.getFromValue(request.getQualidadeProducao()));
			
			apontamento = apontamentoQuantidadeService.save(apontamento);
			
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
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
	
	@PayloadRoot(localPart="excluirApontamentoProducaoRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload ExcluirApontamentoProducaoResponse excluirApontamentoProducao(
			@RequestPayload ExcluirApontamentoProducaoRequest request){
		
		ExcluirApontamentoProducaoResponse result = new ExcluirApontamentoProducaoResponse();
		try {
			ApontamentoTempoProducao apontamento = apontamentoTempoProducaoService.getByCodigo(request.getCodigo());
			if (apontamento != null){
				apontamentoTempoProducaoService.delete(apontamento);
			}
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
	}
	
	@PayloadRoot(localPart="excluirApontamentoParadaRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload ExcluirApontamentoParadaResponse excluirApontamentoParada(
			@RequestPayload ExcluirApontamentoParadaRequest request){
		
		ExcluirApontamentoParadaResponse result = new ExcluirApontamentoParadaResponse();
		try {
			ApontamentoTempoParada apontamento = apontamentoTempoParadaService.getByCodigo(request.getCodigo());
			if (apontamento != null){
				apontamentoTempoParadaService.delete(apontamento);
			}
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
	}
	
	@PayloadRoot(localPart="excluirApontamentoQuantidadeRequest", namespace="apontamento.ws.oee.tc.feevale.br")
	public @ResponsePayload ExcluirApontamentoQuantidadeResponse excluirApontamentoQuantidade(
			@RequestPayload ExcluirApontamentoQuantidadeRequest request){
		
		ExcluirApontamentoQuantidadeResponse result = new ExcluirApontamentoQuantidadeResponse();
		try {
			ApontamentoQuantidade apontamento = apontamentoQuantidadeService.getByCodigo(request.getCodigo());
			if (apontamento != null){
				apontamentoQuantidadeService.delete(apontamento);
			}
		} catch (Throwable e) {
			WSUtils.updateErrors(result, e);
		}
		return result;
	}
	

}
