<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="table-responsive">
	<table class="table table-bordered table-hover stat">
		<thead>
			<tr>
				<th width="75px"><spring:message code="INICIO_PERIODO" /></th>
				<th width="75px"><spring:message code="FIM_PERIODO" /></th>
				
				<th width="100px"><spring:message code="TEMPO_DE_CARGA" /></th>
				<th width="100px"><spring:message code="TEMPO_DAS_PARADAS" /></th>
				<th width="100px"><spring:message code="DISPONIBILIDADE" /></th>
				
				<th width="100px"><spring:message code="VOLUME_TOTAL_PRODUZIDO" /></th>
				<th width="100px"><spring:message code="UNIDADES_BOAS_PRODUZIDAS" /></th>
				<th width="100px"><spring:message code="QUALIDADE" /></th>
				
				<th width="100px"><spring:message code="RUNTIME" /></th>
				<th width="100px"><spring:message code="TEMPO_CICLO_TEORICO" /></th>
				<th width="100px"><spring:message code="TEMPO_CICLO_REAL" /></th>
				<th width="100px"><spring:message code="DESEMPENHO" /></th>
				
				<th width="100px"><spring:message code="OEE" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${indices}" var="indice">
				<c:set var="detalhes" value="${indice.detalhes}"/>
				<c:set var="rowspan" value="${isAnalitico ? (fn:length(detalhes)+1) : 1}"/>
				<tr>
					<td class="numeric" rowspan="${rowspan}"><joda:format value="${indice.inicio}" style="-S" /></td>
					<td class="numeric" rowspan="${rowspan}"><joda:format value="${indice.fim}" style="-S" /></td>
					<td class="numeric"><fmt:formatNumber type="number" value="${indice.tempoCargaMinutos}" minFractionDigits="0" maxFractionDigits="0"/></td>
					<td class="numeric"><fmt:formatNumber type="number" value="${indice.dtTotalMinutos}" minFractionDigits="0" maxFractionDigits="0"/></td>
					<td class="numeric"><strong><fmt:formatNumber type="number" value="${indice.disponibilidade}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
					
					<td class="numeric"><fmt:formatNumber type="number" value="${indice.volumeTotalProduzido}" minFractionDigits="0" maxFractionDigits="0"/></td>
					<td class="numeric"><fmt:formatNumber type="number" value="${indice.quantidadeUnidadesBoasProduzidas}" minFractionDigits="0" maxFractionDigits="0"/></td>
					<td class="numeric"><strong><fmt:formatNumber type="number" value="${indice.qualidade}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
					
					<td class="numeric"><fmt:formatNumber type="number" value="${indice.runtimeMinutos}" minFractionDigits="3" maxFractionDigits="3"/></td>
					<td class="numeric"><fmt:formatNumber type="number" value="${indice.tempoCicloTeoricoUnidadesPorMinuto}" minFractionDigits="3" maxFractionDigits="3"/></td>
					<td class="numeric"><fmt:formatNumber type="number" value="${indice.tempoCicloRealUnidadesPorMinuto}" minFractionDigits="3" maxFractionDigits="3"/></td>
					<td class="numeric"><strong><fmt:formatNumber type="number" value="${indice.desempenho}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
					
					<td class="numeric"><strong><fmt:formatNumber type="number" value="${indice.oee}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
				</tr>
				<c:if test="${isAnalitico}">
					<c:forEach items="${detalhes}" var="detalhe">
						<tr>
							<td colspan="3" class="detalhe">${detalhe.ordemProducao.descricao}</td>
							
							<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.volumeTotalProduzido}" minFractionDigits="0" maxFractionDigits="0"/></td>
							<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.quantidadeUnidadesBoasProduzidas}" minFractionDigits="0" maxFractionDigits="0"/></td>
							<td class="detalhe numeric"><strong><fmt:formatNumber type="number" value="${detalhe.qualidade}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
							
							<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.runtimeMinutos}" minFractionDigits="3" maxFractionDigits="3"/></td>
							<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.tempoCicloTeoricoUnidadesPorMinuto}" minFractionDigits="3" maxFractionDigits="3"/></td>
							<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.tempoCicloRealUnidadesPorMinuto}" minFractionDigits="3" maxFractionDigits="3"/></td>
							<td class="detalhe numeric"><strong><fmt:formatNumber type="number" value="${detalhe.desempenho}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
						</tr>
					</c:forEach>
				</c:if>
				
			</c:forEach>
		</tbody>
	</table>
</div>
		
		