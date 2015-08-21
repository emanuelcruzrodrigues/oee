package br.feevale.tc.oee.framework.utils;

import java.util.Calendar;

import org.joda.time.LocalDateTime;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.bullcontrol.util.Calculadora;

public class DateUtils {
	
	public static LocalDateTime newLocalDateTimeWithoutSeconds(){
		return removeSeconds(new LocalDateTime());
	}
	
	public static LocalDateTime removeSeconds(LocalDateTime dtHr){
		if (dtHr == null) return null;
		
		int year = dtHr.getYear();
		int monthOfYear = dtHr.getMonthOfYear();
		int dayOfMonth = dtHr.getDayOfMonth();
		int hourOfDay = dtHr.getHourOfDay();
		int minuteOfHour = dtHr.getMinuteOfHour();
		return new LocalDateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0, 0);
	}
	
	public static Long getDiferencaEmSegundos(LocalDateTime dtInicial, LocalDateTime dtFinal) {
		Long result = getDiferencaEmMilisegundos(dtInicial, dtFinal);
		return Calculadora.dividir(result, 1_000, 0).longValue();
	}
	
	public static Integer getDiferencaEmMinutos(LocalDateTime dtInicial, LocalDateTime dtFinal) {
		if (dtInicial == null || dtFinal == null) return null;
		Long result = getDiferencaEmMilisegundos(dtInicial, dtFinal);
		return Calculadora.dividir(result, 60_000, 0).intValue();
	}
	
	public static Long getDiferencaEmMilisegundos(LocalDateTime dtInicial, LocalDateTime dtFinal) {
		long m1 = convertToCalendar(dtInicial).getTimeInMillis();
		long m2 = convertToCalendar(dtFinal).getTimeInMillis();
		return m2 - m1;
	}
	
	public static Calendar convertToCalendar(LocalDateTime localDateTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, localDateTime.getYear());
		calendar.set(Calendar.MONTH, localDateTime.getMonthOfYear());
		calendar.set(Calendar.DAY_OF_MONTH, localDateTime.getDayOfMonth());
		calendar.set(Calendar.HOUR_OF_DAY, localDateTime.getHourOfDay());
		calendar.set(Calendar.MINUTE, localDateTime.getMinuteOfHour());
		calendar.set(Calendar.SECOND, localDateTime.getSecondOfMinute());
		calendar.set(Calendar.MILLISECOND, localDateTime.getMillisOfSecond());
		
		return calendar;
	}
	
	public static String printFormatted(ReadablePartial date, String pattern) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
		return dateTimeFormatter.print(date);
	}

}
