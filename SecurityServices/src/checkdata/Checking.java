package checkdata;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Manu
 */
public class Checking {
    
/**
 * @return 0 correcte
 * @return -1 no acompleix amb el patro
 * @return -2 el resultat no es correcte
 */


        public static boolean checkDni (String nif) { 
        boolean correcto = false;
        Pattern pat = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");      
        Matcher matcher = pat.matcher(nif);
       
         if(matcher.matches()){
            String letra=matcher.group(2);
            String letras="TRWAGMYFPDXBNJZSQVHLCKE";
            int index=Integer.parseInt(matcher.group(1));
            index=index%23;
            String reference=letras.substring(index,index+1);
            if(reference.equalsIgnoreCase(letra)){ //EqualsIgnoreCase comprova si son iguals sense valorar mayusculas y minusculas
                correcto=true;
            }else{
                correcto=false;
            }
        }else{
            correcto=false;
        }
        return correcto;
    }    
    public static boolean checkEmail (String correo) {  
        boolean correct = false;  
        Pattern pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$");
         Matcher matcher = pat.matcher(correo);          
  
        if(matcher.matches()){
            correct = true;
        } else {
                correct = false;
            }
         return correct;
  }
        
        public static int checkDate (String fecha) { 
        int anyo;
        int mes; // 1 a 12
        int dia; // 1 a 31
        String signo, signodos;
        int correcte = 0; 
        Pattern p = Pattern.compile("(\\d{2})([/])(\\d{2})([/])(\\d{4})$");
        Matcher matcher = p.matcher(fecha);
        if (matcher.matches()) {
            dia = Integer.valueOf(matcher.group(1));
            signo = matcher.group(2);
            mes = Integer.valueOf(matcher.group(3));
            signodos = matcher.group(4);
            anyo = Integer.valueOf(matcher.group(5));
        
            if(dia > 31 || dia==0) {correcte = -1;}
            
        switch (mes) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                if (dia>31) {
                    correcte = -1;
                }
                break;
            case 4: case 6: case 9: case 11:
                if (dia>30) {
                    correcte = -1;
                }
                break;
            case 2:
                if (dia<=29) { 
                if (anyo % 4 == 0 && anyo % 100!=0 || anyo % 400 == 0 ) {
                    
                    correcte = 0;
                }
                else{
                    correcte = -1;
                }
                
                } else {correcte = -1;}
            
                break;
        }
   
    } else {correcte = -1;}
        return correcte; 
 }
        
      
//CHECKEAR DIFERENCIA ENTRE DOS FECHAS
        public static int diffDates (String dateMin, String dateMax) {
        int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int daysMin = 0, daysMax = 0, i, monthMin, monthMax, yearMin, yearMax;
        Pattern p = Pattern.compile("(\\d{1,2})[-/](\\d{1,2})[-/](\\d{4})$");
        Matcher matcherDateMin, matcherDateMax;
       
        if (checkDate(dateMin) != 0 || checkDate(dateMax) != 0) {
            return -1;
        }      
        matcherDateMin = p.matcher(dateMin);
        matcherDateMax = p.matcher(dateMax);      
        matcherDateMin.matches();
        matcherDateMax.matches();                        
        monthMin = Integer.valueOf(matcherDateMin.group(2));
        monthMax = Integer.valueOf(matcherDateMax.group(2));
        yearMin = Integer.valueOf(matcherDateMin.group(3));
        yearMax = Integer.valueOf(matcherDateMax.group(3));      
        daysMin += Integer.valueOf(matcherDateMin.group(1));
        daysMax += Integer.valueOf(matcherDateMax.group(1));
       
        for (i = 0; i < monthMin - 1; i++) {
            daysMin += daysPerMonth[i];
        }      
        if (monthMin > 2 && isLeap(yearMin)) {
            daysMin += 1;
        }
        for (i = 0; i < monthMax - 1; i++) {
            daysMax += daysPerMonth[i];
        }
        if (monthMax > 2 && isLeap(yearMax)) {
            daysMax += 1;
        }      
        for (i = yearMin; i < yearMax; i++) {
            daysMax += 365;          
            if (isLeap(i)) {
                daysMax += 1;
            }
        }      
        return daysMax - daysMin;
    }
     private static boolean isLeap(int year) {
        return (year%4 == 0 && year%100 != 0) || (year%400 == 0);
    }
     
}  
        

       



