package br.gov.mt.controladoria.scsp.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class Functions {
	
	private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
	
	public Functions() {
	}

	public int daysBetween(Date before, Date after) {
		return Days.daysBetween(new DateTime(before.getTime()), new DateTime(after.getTime())).getDays();
	}

	public static int daysUntilToday(Date date) {
		return Days.daysBetween(new DateTime(date.getTime()), new DateTime()).getDays();
	}
	
	public static int getDiffHour(String value) {
		Date first = null;
		int hoursBetween = 0;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		
		try {
			first = (Date) format.parse(value);
			hoursBetween = Hours.hoursBetween(new LocalDate(first), new LocalDate()).getHours();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
        return hoursBetween;
    }
	
	public long subTrairDatas(Date dtini, Date dtfin){
		long diff = dtfin.getTime() - dtini.getTime();		
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}	
	
    public static String diferencaDias(String d1){
    	DateTimeZone timeZone = DateTimeZone.forID( "America/Cuiaba" );
    	Locale locale_ptBR = new Locale( "pt" , "BR" );
    	
    	DateTime d2 = new DateTime();	    	
    	
    	DateTimeFormatter formatter = DateTimeFormat.forPattern( "dd/MM/yyyy HH:mm:ss" ).withLocale( locale_ptBR ).withZone( timeZone ); 

    	DateTime dateTimeStart = formatter.parseDateTime( d1 );
    	DateTime dateTimeStop  = d2;
    	Period period = new Period( dateTimeStart, dateTimeStop );

    	PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
    			.appendMonths()
		        .appendSuffix(" mês", " meses")
    		    .appendSeparator(" , ")	    			
    			.appendWeeks()
		        .appendSuffix(" semana", " semanas")
    		    .appendSeparator(" , ") 			
    			.appendDays()
		        .appendSuffix(" dia", " dias")
    		    .appendSeparator(" , ")   			
    			.appendHours()
		        .appendSuffix(" hora", " horas")
    		    .appendSeparator(" e ")    			
    			.appendMinutes()
		        .appendSuffix(" minuto", " minutos")	    		    
    			.toFormatter();
    	
    	String output = periodFormatter.withLocale( locale_ptBR ).print( period );
    	
    	return output;
  	
    }
    
	public static String removeTags(String string) {
	    if (string == null || string.length() == 0) {
	        return string;
	    }

	    Matcher m = REMOVE_TAGS.matcher(string);
	    return m.replaceAll("");
	}
	
	
	public static String retornaTamanho(String size) {
		
		long tamanho = Long.parseLong(size);
		
	    if(tamanho <= 0) return "0";
	    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(tamanho)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(tamanho/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
	
    
    public byte[] convertBase64ToImage(String base64) {
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(base64);
        return data;
    }
    
	public int getDaysBetween (Timestamp start, Timestamp end)   {

        boolean negative = false;
        if (end.before(start))  {
            negative = true;
            Timestamp temp = start;
            start = end;
            end = temp;
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        GregorianCalendar calEnd = new GregorianCalendar();
        calEnd.setTime(end);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);


        if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR))   {
            if (negative)
                 return (calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR)) * -1;
            return calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
        }

        int days = 0;
        while (calEnd.after(cal))    {
            cal.add (Calendar.DAY_OF_YEAR, 1);
            days++;
        }
        if (negative)
            return days * -1;
        return days;
    }
	
	public int age(Date birthday, Date date) {
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    int d1 = Integer.parseInt(formatter.format(birthday));
	    int d2 = Integer.parseInt(formatter.format(date));
	    int age = (d2-d1)/10000;
	    return age;
	}	

}
