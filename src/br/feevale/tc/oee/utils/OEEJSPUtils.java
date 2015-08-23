package br.feevale.tc.oee.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;

import com.bullcontrol.util.Calculadora;

import br.feevale.tc.oee.framework.i18n.DefaultMessages;
import br.feevale.tc.oee.framework.utils.DateUtils;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;

public class OEEJSPUtils {
	
	public static String printGrafico(HttpServletRequest request, String datePattern){
		@SuppressWarnings("unchecked")
		List<UnidadeIndiceOEE> indices = (List<UnidadeIndiceOEE>)request.getAttribute("indices");
		if (CollectionUtils.isEmpty(indices)) return "";
		
		
		StringBuilder labels = new StringBuilder();
		StringBuilder disponibiliade = new StringBuilder();
		StringBuilder desempenho = new StringBuilder();
		StringBuilder qualidade = new StringBuilder();
		StringBuilder oee = new StringBuilder();
		for (UnidadeIndiceOEE unidade : indices) {
			if (labels.length() > 0){
				labels.append(", ");
				disponibiliade.append(", ");
				desempenho.append(", ");
				qualidade.append(", ");
				oee.append(", ");
			}
			labels.append("\"").append(DateUtils.printFormatted(unidade.getInicio(), datePattern)).append("\"");
			disponibiliade.append("\"").append(unidade.getDisponibilidade() != null ? Calculadora.multiplicar(unidade.getDisponibilidade(), 100, 0).intValue() : "0").append("\"");
			desempenho.append("\"").append(unidade.getDesempenho() != null ? Calculadora.multiplicar(unidade.getDesempenho(), 100, 0).intValue() : "0").append("\"");
			qualidade.append("\"").append(unidade.getQualidade() != null ? Calculadora.multiplicar(unidade.getQualidade(), 100, 0).intValue() : "0").append("\"");
			oee.append("\"").append(unidade.getOee() != null ? Calculadora.multiplicar(unidade.getOee(), 100, 0).intValue() : "0").append("\"");
		}
		
		StringBuilder result = new StringBuilder();
		
		result.append("\n<div class=\"box-chart\">");
		result.append("\n<canvas id=\"grafico\"></canvas>");
		result.append("\n<div id=\"placeholder\"></div>");
		result.append("\n</div>");
		
		result.append("\n<script type=\"text/javascript\">  ");
		result.append("\n     var options = {   ");
		result.append("\n         responsive:true    ");
		result.append("\n     };    ");
		result.append("\n     var data = {  ");
		result.append("\n         labels: [ ");
		result.append(labels);
		result.append("\n         ],    ");
		
		result.append("\n         datasets: [   ");
		result.append("\n             { ");
		result.append("\n                 label: \"").append(DefaultMessages.get(request, "DISPONIBILIDADE")).append("\",   ");
		result.append("\n                 fillColor: \"rgba(220,120,20,0.2)\", ");
		result.append("\n                 strokeColor: \"rgba(220,120,20,1)\", ");
		result.append("\n                 pointColor: \"rgba(220,120,20,1)\",  ");
		result.append("\n                 pointStrokeColor: \"#fff\",   ");
		result.append("\n                 pointHighlightFill: \"#fff\", ");
		result.append("\n                 pointHighlightStroke: \"rgba(220,120,20,1)\",    ");
		result.append("\n                 data: [").append(disponibiliade).append("]");
		result.append("\n             } ");
		result.append("\n            ,{ ");
		result.append("\n                 label: \"").append(DefaultMessages.get(request, "DESEMPENHO")).append("\",   ");
		result.append("\n                 fillColor: \"rgba(151,187,205,0.2)\", ");
		result.append("\n                 strokeColor: \"rgba(151,187,205,1)\", ");
		result.append("\n                 pointColor: \"rgba(151,187,205,1)\",  ");
		result.append("\n                 pointStrokeColor: \"#fff\",   ");
		result.append("\n                 pointHighlightFill: \"#fff\", ");
		result.append("\n                 pointHighlightStroke: \"rgba(151,187,205,1)\",    ");
		result.append("\n                 data: [").append(desempenho).append("]");
		result.append("\n             } ");
		result.append("\n            ,{ ");
		result.append("\n                 label: \"").append(DefaultMessages.get(request, "QUALIDADE")).append("\",   ");
		result.append("\n                 fillColor: \"rgba(101,187,151,0.2)\", ");
		result.append("\n                 strokeColor: \"rgba(101,187,151,1)\", ");
		result.append("\n                 pointColor: \"rgba(101,187,151,1)\",  ");
		result.append("\n                 pointStrokeColor: \"#fff\",   ");
		result.append("\n                 pointHighlightFill: \"#fff\", ");
		result.append("\n                 pointHighlightStroke: \"rgba(101,187,151,1)\",    ");
		result.append("\n                 data: [").append(qualidade).append("]");
		result.append("\n             } ");
		result.append("\n            ,{ ");
		result.append("\n                 label: \"").append(DefaultMessages.get(request, "OEE")).append("\",   ");
		result.append("\n                 fillColor: \"rgba(200,187,101,0.2)\", ");
		result.append("\n                 strokeColor: \"rgba(200,187,101,1)\", ");
		result.append("\n                 pointColor: \"rgba(200,187,101,1)\",  ");
		result.append("\n                 pointStrokeColor: \"#fff\",   ");
		result.append("\n                 pointHighlightFill: \"#fff\", ");
		result.append("\n                 pointHighlightStroke: \"rgba(200,187,101,1)\",    ");
		result.append("\n                 data: [").append(oee).append("]");
		result.append("\n             } ");
		result.append("\n         ] ");
		result.append("\n     };    ");
		result.append("\n     window.onload = function(){   ");
		result.append("\n         var ctx = document.getElementById(\"grafico\").getContext(\"2d\");    ");
		result.append("\n         var LineChart = new Chart(ctx).Bar(data, options);   ");
		result.append("\n         legend(document.getElementById('placeholder'), data);   ");
		result.append("\n     } ");
		result.append("\n     </script> ");
		result.append("\n</div> ");
   	
   		return result.toString();
	}

}
