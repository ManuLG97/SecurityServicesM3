package securityservices.core.component.order.domain.serializers;

import securityservices.core.component.order.domain.services.OrderDTO;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.shared.responses.ResultRequest;

public class JsonOrderSerializer implements Serializer{
 private Json jOrder = JsonObjectFactory.getInstance();   

    public JsonOrderSerializer() {
    }

 
    @Override
    public ResultRequest<OrderDTO> unserialize(String data) {
        jOrder.set(data);
        try {
            OrderDTO order = new OrderDTO (
               jOrder.get("code"),
               jOrder.get("type"),
               jOrder.get("status"),
               jOrder.get("additonalInfo"),
               jOrder.get("beginDate"),
               jOrder.get("finishDate"),
               jOrder.get("paymentType"),
               jOrder.get("paymentDate"),
               Integer.valueOf(jOrder.get("creator")),
               Double.valueOf(jOrder.get("value")),
               Double.valueOf(jOrder.get("surcharges"))
            );
                    return ResultRequest.done(order);
       } catch (Exception o) {
         return ResultRequest.fails("{\"Error\":\"ClientDTO unserialized Exception\","
                    + "\"Details\":\"" + o.toString() + "\"}");  
       }
    }
    
    
    @Override
    public ResultRequest serialize(Object order) {
        jOrder.removeAll();
        jOrder.set("code", ((OrderDTO) order).getCode());
        jOrder.set("type", ((OrderDTO) order).getType());
        jOrder.set("status", ((OrderDTO) order).getStatus());
        jOrder.set("additonalInfo", ((OrderDTO) order).getAdditonalInfo());    
        jOrder.set("beginDate", ((OrderDTO) order).getBeginDate());            
        jOrder.set("finishDate", ((OrderDTO) order).getFinishDate());
        jOrder.set("paymentType", ((OrderDTO) order).getPaymentType());
        jOrder.set("paymentDate", ((OrderDTO) order).getPaymentDate());  
        jOrder.set("creator", String.valueOf(((OrderDTO) order).getCreator()));
        jOrder.set("value", String.valueOf(((OrderDTO) order).getValue()));
        jOrder.set("surcharges", String.valueOf(((OrderDTO) order).getSurcharges()));
        return ResultRequest.done(jOrder.toString());
            

    }

}
