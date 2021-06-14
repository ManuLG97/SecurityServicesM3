package securityservices;

import java.util.Arrays;
import securityservices.checkdata.Check;
import securityservices.operations.Order;
import securityservices.operations.Stock;

public class SecurityServices {

    public static void main(String[] args) {
        /*EXPRESIONES REGULARES
        int error = Check.checkExample("4500-2570=1930");
        switch (error) {
            case 0:
                System.out.println("Operación Correcta");
                break;
            case -1:
                System.out.println("Cadena Mal Formada");
                break;
            case -2:
                System.out.println("Operación Incorrecta");
                break;
        }

        error = Check.checkExample("45/5=9");
        switch (error) {
            case 0:
                System.out.println("Operación Correcta");
                break;
            case -1:
                System.out.println("Cadena Mal Formada");
                break;
            case -2:
                System.out.println("Operación Incorrecta");
                break;
        }*/
        
        /*ACTIVIDAD 5*/
            System.out.println("Actividad 5 UF4");
            Order o;
            o = new Order();
            
            System.out.println("--------------------------------------------------");
            System.out.println("ACTIVIDAD 5 (Parte Order)");
            System.out.println("--------------------------------------------------");
            System.out.println("Vemos el numero de detalles creados");
            o.setDetail("13;3;10.95");
            o.setDetail("14;3;10.95");
            o.setDetail("15;5;10.95");
            System.out.println(o.getNumDetails());
             System.out.println("--------------------------------------------------"); 
            System.out.println("Vemos el getvalue");
            System.out.println(o.getValue());
            System.out.println("--------------------------------------------------"); 
            System.out.println("Eliminamos detalle por posicion");
            System.out.println(o.getDetail("15"));
            o.deleteDetail(2);
            System.out.println(o.getDetail("15"));
             System.out.println("--------------------------------------------------"); 
            System.out.println("Eliminamos detalle por numero");
            System.out.println(o.getDetail("14"));
            o.deleteDetail("14");
            System.out.println(o.getDetail("14"));
             System.out.println("--------------------------------------------------"); 
            System.out.println("Actualizamos detalle por numero");
            System.out.println(o.getDetail("13"));
            o.updateDetail("13", "977");
            System.out.println(o.getDetail("13")); 
             
           
            Stock s1 = new Stock();
            
            s1.setStock("Windows 10", 7);
            s1.setStock("AntiSpyware" , 15);
            s1.setStock("Antivirus" , 5);
            
            System.out.println("--------------------------------------------------");
            System.out.println("ACTIVIDAD 5 (Parte Stock");
            System.out.println("--------------------------------------------------");
            
           System.out.println("Printamos el numero de  lineas que recibe por el set");
           System.out.println(s1.getNumLines());
           System.out.println("--------------------------------------------------"); 
             
             System.out.println("Printamos el array"); 
            System.out.println(Arrays.toString(s1.getLines()));
             System.out.println("--------------------------------------------------"); 
            
            System.out.println("Printamos el amount del nombre indicado");
            System.out.println(s1.getAmount("Windows 10"));
             System.out.println("--------------------------------------------------"); 
            
            
            System.out.println("Actualizamos por name su amount");
            System.out.println(s1.getAmount("Antivirus"));
            s1.updateStock("Antivirus", 9);
            System.out.println(Arrays.toString(s1.getLines()));
            System.out.println("--------------------------------------------------");
                     
            System.out.println("Elimanos un fila");
            System.out.println(s1.getAmount("Windows 10"));
            s1.delStock("Windows 10");
            System.out.println(Arrays.toString(s1.getLines()));
            System.out.println("--------------------------------------------------");
            
             System.out.println("set por name su amount");
            System.out.println(s1.getAmount("Antivirus"));
            s1.setStock("Antivirus" , 4);
            System.out.println(Arrays.toString(s1.getLines())); 
            System.out.println("--------------------------------------------------");
         
    }

}
