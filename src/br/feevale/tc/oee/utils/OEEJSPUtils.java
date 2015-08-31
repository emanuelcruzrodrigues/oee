package br.feevale.tc.oee.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;

import br.feevale.tc.oee.framework.i18n.DefaultMessages;
import br.feevale.tc.oee.framework.utils.DateUtils;
import br.feevale.tc.oee.stats.ParadaUnidadeIndiceOEE;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;

import com.bullcontrol.util.Calculadora;

public class OEEJSPUtils {
	
	public static String printGrafico(HttpServletRequest request, String datePattern){
		@SuppressWarnings("unchecked")
		List<UnidadeIndiceOEE> indices = (List<UnidadeIndiceOEE>)request.getAttribute("indices");
		if (CollectionUtils.isEmpty(indices) || indices.size() <= 1 ) return "";
		
		
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
			labels.append("\"").append(DateUtils.printFormatted(unidade.getFim(), datePattern)).append("\"");
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
		result.append("\n         bezierCurve : false, responsive:true    ");
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
		result.append("\n         var LineChart = new Chart(ctx).Line(data, options);   ");
		result.append("\n         legend(document.getElementById('placeholder'), data);   ");
		result.append("\n     } ");
		result.append("\n     </script> ");
		result.append("\n</div> ");
   	
   		return result.toString();
	}
	
	public static String[] DEFAULT_COLORS = new String[]{"\"#F7464A\"", "\"#46BFBD\"", "\"#FDB45C\"", "\"#ADB45C\"", "\"#0DB45C\""};
	public static String[] DEFAULT_HIGHLIGHT_COLORS = new String[]{"\"#FF5A5E\"", "\"#5AD3D1\"", "\"#FFC870\"", "\"#AFC870\"", "\"#0FC870\""};
	
	public static String getColor(int idx){
		idx = idx % DEFAULT_COLORS.length;
		return DEFAULT_COLORS[idx];
	}
	
	public static String getHighlightColor(int idx){
		idx = idx % DEFAULT_HIGHLIGHT_COLORS.length;
		return DEFAULT_HIGHLIGHT_COLORS[idx];
	}
	
	public static String printGraficoParadas(HttpServletRequest request){
		UnidadeIndiceOEE unidade = (UnidadeIndiceOEE) request.getAttribute("indice");
		
		StringBuilder result = new StringBuilder();
		if (unidade.getDtTotalMinutos().equals(0)) return result.toString();

		StringBuilder data = new StringBuilder();
		for (int i = 0; i < unidade.getParadas().size(); i++) {
			ParadaUnidadeIndiceOEE parada = unidade.getParadas().get(i);
			if (data.length() > 0) data.append(", ");
			
			data.append("\n 	{ ");
			data.append("\n 		value: " + parada.getTempoTotal() + ", ");
			data.append("\n 		color: ").append(getColor(i)).append(", ");
			data.append("\n 		highlight: ").append(getHighlightColor(i)).append(", ");
			data.append("\n 		label: \"").append(parada.getMotivoParada().getDescricao()).append("\" ");
			data.append("\n 	}");
		}
		
		result.append("\n <script type=\"text/javascript\"> ");
		result.append("\n var options = { ");
		result.append("\n 	responsive:true ");
		result.append("\n }; ");
		result.append("\n var data = [ ");
		result.append(data.toString());
		result.append("\n ] ");
		result.append("\n window.onload = function(){ ");
		result.append("\n 	var ctx = document.getElementById(\"GraficoPizza\").getContext(\"2d\"); ");
		result.append("\n 	var PizzaChart = new Chart(ctx).Pie(data, options); ");
		result.append("\n 	legend(document.getElementById('placeholder'), data);  ");
		result.append("\n }   ");
		result.append("\n </script>  ");
		
		return result.toString();
	}
	
	public static String printProgressBars(UnidadeIndiceOEE unidade){
		StringBuilder result = new StringBuilder();
		
		result.append("\n Disponibilidade: ");
		appendProgressBar(result, unidade.getDisponibilidade(), getDisponibilidadeStyle(unidade.getDisponibilidade()));
		
		result.append("\n Desempenho: ");
		appendProgressBar(result, unidade.getDesempenho(), getDesempenhoStyle(unidade.getDesempenho()));
		
		result.append("\n Qualidade: ");
		appendProgressBar(result, unidade.getQualidade(), getQualidadeStyle(unidade.getQualidade()));
		
		result.append("\n OEE: ");
		appendProgressBar(result, unidade.getOee(), getOEEStyle(unidade.getOee()));

		return result.toString();
	}

	private static void appendProgressBar(StringBuilder result, Double percent, String style) {
		if (percent == null) {
			result.append("<p>-</p>");
			return;
		}
		
		percent = percent * 100;
		
		result.append("\n <div class=\"progress\"> ");
		result.append("\n 	<div class=\"progress-bar progress-bar-" + style + " progress-bar-striped\" ");
		result.append("\n      style=\"min-width: 2em; width: " + percent.intValue() + "%;\"> ");
		result.append("\n 		" + percent.intValue() + "% ");
		result.append("\n 	</div> ");
		result.append("\n </div> ");
	}

	public static String getDisponibilidadeStyle(Double percent) {
		return getStyle(percent, 45, 68, 90);
	}
	
	public static String getDesempenhoStyle(Double percent) {
		return getStyle(percent, 48, 72, 95);
	}
	
	public static String getQualidadeStyle(Double percent) {
		return getStyle(percent, 50, 75, 99);
	}
	
	public static String getOEEStyle(Double percent) {
		return getStyle(percent, 43, 64, 85);
	}
	
	public static String getStyle(Double percent, int minimo, int media, int classeMundial) {
		if (percent == null) return "default";
		
		percent = percent * 100;
		
		String style = null;
		if (percent < minimo){
			style = "danger";
		}else if (percent < media){
			style = "warning";
		}else if (percent < classeMundial){
			style = "info";
		}else{
			style = "success";
		}
		return style;
	}
	

}
