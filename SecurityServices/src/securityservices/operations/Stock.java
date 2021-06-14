package securityservices.operations;
import java.util.HashMap;


public class Stock {
 protected HashMap <String, Integer> stock = new HashMap <String, Integer>();
 protected String storePlace;
 
 public String[] getLines() {
    String valor = stock.entrySet().toString();
     valor.replace("[", "");
     valor.replace("]", "");
    
    return valor.split(", ");
 } 

 public int setStock(String name, int amount) {
   
    if (amount <= 0) { 
       return -2;
   }
    if (stock.containsKey(name)) {
       int amountwo = stock.get(name) + amount;
       if(amountwo >=0 ) {
       stock.put(name, amountwo);
       }
   } else {
    stock.put(name, amount); 
    }
   
    
    return 0;
 }
 
 public int getAmount(String name) {
     if (stock.containsKey(name)) {
         return stock.get(name);
     }
     return -1;
 } 
 
 public int delStock (String name) {
     int result = 0;
     
     if (stock.containsKey(name)){
         stock.remove(name);
     } else { result = -1;}
     return result;
 }
 
 public int updateStock ( String name, int amount ) { 
 
 if(amount <= 0) return -2;
 
 if(stock.containsKey(name)) {
     stock.put(name, amount);
 }  
     return -1;
 }
 
 public int getNumLines() { 
 return stock.size();
 }


 }