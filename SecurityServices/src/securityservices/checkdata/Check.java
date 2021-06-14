package securityservices.checkdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
  Returns
  0 correcte
  -1 no acompleix amb el patro
  -2 el resultat no es correcte
*/
public class Check {

    public static int checkExample(String request) {
        int valor1, valor2, result;
        String signo;
        Pattern pattern = Pattern.compile("(\\d+)([+-/*])(\\d+)\\=(\\d+)$");
        Matcher matcher = pattern.matcher(request);

        if (matcher.matches()) {
            //les 3 línees següents es mostren només a nivell didactic
            System.out.println("Primer valor numeric = " + matcher.group(1));
            System.out.println("Segon valor numeric = " + matcher.group(3));
            System.out.println("Resultat numeric = " + matcher.group(4));

            valor1 = Integer.valueOf(matcher.group(1));
            signo = matcher.group(2);
            valor2 = Integer.valueOf(matcher.group(3));
            result = Integer.valueOf(matcher.group(4));
            switch (signo) {
                case "+":
                    if (valor1 + valor2 == result) {
                        return 0;
                    }
                    break;
                case "-":
                    if (valor1 - valor2 == result) {
                        return 0;
                    }
                    break;
                case "*":
                    if (valor1 * valor2 == result) {
                        return 0;
                    }
                    break;
                case "/":
                    if (valor1 / valor2 == result) {
                        return 0;
                    }
                    break;
            }
        } else {
            return -1;
        }
        return -2;
    }
    
    
    
    
    
}
