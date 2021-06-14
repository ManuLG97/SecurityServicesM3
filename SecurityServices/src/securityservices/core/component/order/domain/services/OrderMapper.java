package securityservices.core.component.order.domain.services;

import securityservices.operations.Order;
import securityservices.shared.responses.ResultRequest;


public class OrderMapper {
    
    public static ResultRequest<Order> componentFromDTO (OrderDTO odto) {
       return Order.getInstace(
       odto.getCode(),
       odto.getCreator(),
       odto.getValue(),
       odto.getSurcharges(),
       odto.getType(),
       odto.getStatus(),
       odto.getAdditonalInfo(),
       odto.getBeginDate(),
       odto.getFinishDate(),
       odto.getPaymentType(),
       odto.getPaymentDate()           
       ); 
    }
    
    public static OrderDTO dtoFromComponent (Order o) {
      return new OrderDTO (
       o.getCode(),
       o.getType(),
       o.getStatus(),
       o.getAdditonalInfo(),
       o.getBeginDate(),
       o.getFinishDate(),
       o.getPaymentType(),
       o.getPaymentDate(),
       o.getCreator(),
       o.getValue(),
       o.getSurcharges()
       ); 
    }
    
}
