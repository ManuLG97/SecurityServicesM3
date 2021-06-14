package securityservices.operations;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import securityservices.shared.responses.ResultRequest;
import securityservices.shared.responses.ResultResponses;

public class Order extends Operation {

    protected String paymentType;
    protected LocalDateTime paymentDate;
    protected ArrayList<OrderDetail> details = new ArrayList();

    public Order() {
    }

    public static ResultRequest<Order> getInstace(String code, int creator, double value, double surcharges, String type, String status, String additonalInfo, String beginDate, String finishDate, String paymentType, String paymentDate) {
       
       Order order = new Order();
       
       ResultRequest result = order.setCode(code);
    if (result.failed()) {
        return result;
    }
    
        result = order.setCreator(creator);
        if (result.failed()) {
            return result;
        }
       
        result = order.setValue(value);
        if (result.failed()) {
            return result;
    }
        result = order.setSurcharges(surcharges);
        if (result.failed()) {
           return result;
        }
   
   order.setType(type);
   
   order.setStatus(status);
   
   order.setAdditonalInfo(additonalInfo);
   
   result = order.setBeginDate(beginDate);
      if (result.failed()) {
           return result;
        }
   result = order.setFinishDate(finishDate);
      if (result.failed()) {
           return result;
        }
   
      order.setPaymentType(paymentType);
      
      result = order.setPaymentDate(paymentDate);
       if (result.failed()) {
           return result;
        }
       
       return ResultRequest.done(order);
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentDate() {
        if (this.paymentDate != null) {
            return this.paymentDate.format(this.outputDate);
        }
        return "";
    }

    public ResultRequest setPaymentDate(String paymentDate) throws DateTimeParseException {
              try {
            this.paymentDate = LocalDateTime.parse(paymentDate, outputDate);
            return ResultRequest.done(ResultResponses.SUCCESS);
        } catch (Exception e) {
            return ResultRequest.fails("\"Error\":\"invalid birthday: " + e.getMessage() + "\"");
        }
    }

 
    @Override
    public int getClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIniDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumDetails() {
        int getdetail = details.size();
        return getdetail;
    }

    @Override
    public int setDetail(String detail) {
  //coger de seg_service y ponerlo en el array
        String[] array_detail = detail.split(";");
        
        //crear variable para los tres valores y ponerl
        String cod = array_detail[0];
        int cant = Integer.parseInt(array_detail[1]);
        double precio = Double.parseDouble(array_detail[2]);
        
        if(cod.trim().length()<3 && cant <=0 && precio <=0){
            return -1;
            
        } else {
            this.details.add(new OrderDetail(cod, cant, precio));
            return 0;
        }
        
       
    }

    @Override
    public String getDetail(int n) {
         int tamaño = details.size();
        if(tamaño > n){
            String detail = details.get(n).getRef() + ";"
                    + details.get(n).getAmount() + ";"
                    + details.get(n).getPrice();
            return detail;
        } else { 
            String error = "El tamaño del array es incorrecto";
            return error;
        }
    }

    @Override
    public String getDetail(String n) {
        int tamaño = details.size();
        for (int i = 0; i < tamaño; i++){
            if (details.get(i).getRef().equals(n)){
                String detail = details.get(i).getRef() + ";"
                + details.get(i).getAmount() + ";"
                + details.get(i).getPrice();
                return detail;
            }
        }
        String error = "No se encuentra en ninguna lista";
        return error;
    }

    @Override
    public int updateDetail(int n, String newdetail) {
          int amount = Integer.parseInt(newdetail);
        if(amount <= 0) return -2;
        int tamaño = details.size();
        if (tamaño > n) {
            details.get(n).setAmount(amount);
        } else {
            return -1;
        }
        return 0;
    }

    @Override
    public int updateDetail(String n, String newdetail) {
         int amount = Integer.parseInt(newdetail);
            if (amount <= 0) return -2;
         int tamaño = details.size();
         for (int i=0 ;i<tamaño; i++)  { 
             if (details.get(i).getRef().equals(n)) {
                 details.get(i).setAmount(amount);
                  return 0;
             }
         }
         return -1;
    }

    @Override
    public int deleteDetail(int n) {
          int tamaño = details.size();
       if(tamaño>n) {
        details.remove(n);   
       } else {
           return -1;
       }
       return 0;
    }

    @Override
    public int deleteDetail(String n) {
          int tamaño = details.size();
        for (int i=0 ; i<tamaño;i++) {
            if(details.get(i).getRef().equals(n)){
                details.remove(i);
                return 0;
            }
        }
        return -1;
    }
    
        
    public double getValue() {
        double valor = 0;
        int tam = details.size();
        for (int i = 0; i < tam; i++){
            valor = valor + details.get(i).getAmount() * details.get(i).getPrice();
        }
        return valor;
    }
}
