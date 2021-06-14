package NoJUnit;

import securityservices.core.component.client.appservices.JsonClientSerializer;
import securityservices.core.component.client.appservices.XmlClientSerializer;
import securityservices.core.component.client.domain.model.Client;
import securityservices.core.component.client.domain.services.ClientDTO;
import securityservices.core.component.client.domain.services.ClientMapper;
import securityservices.core.component.equipment.appservices.JsonEquipmentSerializer;
import securityservices.core.component.equipment.appservices.XmlEquipmentSerializer;
import securityservices.core.component.equipment.domain.serializers.EquipmentDTO;
import securityservices.core.component.equipment.domain.serializers.EquipmentMapper;
import securityservices.core.component.order.domain.serializers.JsonOrderSerializer;
import securityservices.core.component.order.domain.services.OrderDTO;
import securityservices.core.component.order.domain.services.OrderMapper;
import securityservices.core.component.service.appservices.JsonServiceSerializer;
import securityservices.core.component.service.appservices.XmlServiceSerializer;
import securityservices.core.component.service.domain.serializers.ServiceDTO;
import securityservices.core.component.service.domain.serializers.ServiceMapper;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.shared.services.serializers.Xml;
import securityservices.core.shared.services.serializers.xmlapis.Dom;
import securityservices.core.shared.services.serializers.xmlapis.Jdom;
import securityservices.operations.Order;
import securityservices.products.Equipment;
import securityservices.shared.responses.ResultRequest;
import securityservices.products.Service;

public class TestSerializers {
/* PARTE JSON */
    /*
    public static void main(String[] args) {
        //OBJETOS CLIENT PARA LA DEMO     
        Client client1 = null, client2 = null, client3 = null, auxClient = null;

        ResultRequest<Client> clientRequest = Client.getInstance("jose", "001", "carrer kalea 1", "666555444", "josem@gmail.cat",
                false, "20-febrero-1997", 3, "***");
        if (clientRequest.failed()) {
            System.out.println(clientRequest.getError());
        } else {
            client1 = clientRequest.getValue();
        }

        clientRequest = Client.getInstance("juan", "002", "carrer kalea 3", "666555555", "junam@gmail.cat",
                false, "28-febrero-1987", 1, "****");
        if (clientRequest.failed()) {
            System.out.println(clientRequest.getError());
        } else {
            client2 = clientRequest.getValue();
        }

        clientRequest = Client.getInstance("jordi", "003", "carrer kalea 5", "666555666", "jordi@gmail.cat",
                false, "10-febrero-1990", 5, "****");
        if (clientRequest.failed()) {
            System.out.println(clientRequest.getError());
        } else {
            client3 = clientRequest.getValue();
        }

   /*     if (client1 != null && client2 != null && client3 != null) {
            
            ClientDTO cdto1 = ClientMapper.dtoFromComponent(client1);
            ClientDTO cdto2 = ClientMapper.dtoFromComponent(client2);
            ClientDTO cdto3 = ClientMapper.dtoFromComponent(client3);
            ClientDTO auxCdto;

            JsonClientSerializer jcSerializer = new JsonClientSerializer();
            
            ResultRequest<String> clientSerialized = jcSerializer.serialize(cdto2);
            
            String jsonClient = clientSerialized.getValue();
            System.out.println("LOS JSON DE CLIENT, EQUIPMENT, SERVICE Y ORDER (SIN ORDERDETAIL)");
            System.out.println("CLIENT:");
            System.out.println(jsonClient);
            System.out.println("----------------------------------------------------------------");

            String newJsonClient = "{"
                    + "  \"birthday\": \"22-febrero-1987\","
                    + "  \"code\": \"023\","
                    + "  \"clientid\": \"1d335c53-f5c4-48da-9a6a-e6f766c67a76\","
                    + "  \"address\": \"carrer kalea 13\","
                    + "  \"phone\": \"666555888\","
                    + "  \"name\": \"alex\","
                    + "  \"numequipments\": \"4\","
                    + "  \"password\": \"xela\","
                    + "  \"iscompany\": \"false\","
                    + "  \"email\": \"alexm@gmail.cat\""
                    + "}";

            ResultRequest<ClientDTO> clientUnserialized = jcSerializer.unserialize(newJsonClient);
            
            if (clientUnserialized.failed()) {
                System.out.println(clientUnserialized.getError());
                
            } else {
                auxCdto = clientUnserialized.getValue();
                ResultRequest<Client> clientUnMapped = ClientMapper.componentFromDTO(auxCdto);
                
                if (clientUnMapped.failed()) {
                    System.out.println(clientUnMapped.getError());
                } else {
                    auxClient = clientUnMapped.getValue();
                }
            }
            
            
        }
        
       /* 
        //OBJETOS SERVICE PARA LA DEMO 
        Service service1= null;
        
        ResultRequest<Service> serviceRequest = Service.getInstance("001", "Servicio 10", "Type", "Manu S.L", "Venta de un PC", 8.88, "30 dias", "Ninguna", "16/09/2013-19:45:03",  "16/09/2013-19:45:03");
        if (serviceRequest.failed()) {
            System.out.println(serviceRequest.getError());
        } else {
            service1 = serviceRequest.getValue();
        }
        
        if (service1 != null) {
            ServiceDTO sdto1 = ServiceMapper.dtoFromComponent(service1);
            
            JsonServiceSerializer jsSerializer = new JsonServiceSerializer();
            
            ResultRequest<String> serviceSerialized = jsSerializer.serialize(sdto1);
            
            String jsonService = serviceSerialized.getValue();
            System.out.println("SERVICE:");
            System.out.println(jsonService);
            System.out.println("----------------------------------------------------------------");

        } 
        
         //OBJETOS EQUIPMETN PARA LA DEMO
         Equipment equipment1=null;
         
         ResultRequest<Equipment> equipmentRequest = Equipment.getInstance("001", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        if (equipmentRequest.failed()) {
              System.out.println(equipmentRequest.getError());
        } else {
            equipment1 = equipmentRequest.getValue();
        }
        
        if(equipment1 != null) {
            EquipmentDTO edto1 = EquipmentMapper.dtoFromComponent(equipment1);
            
            JsonEquipmentSerializer jeSerializer = new JsonEquipmentSerializer();
            
            ResultRequest<String> equipmentSerialized = jeSerializer.serialize(edto1);
            
            String jsonEquipment = equipmentSerialized.getValue();
            System.out.println("EQUIPMENT:");
            System.out.println(jsonEquipment);
            System.out.println("----------------------------------------------------------------");
        }
        
        
         //OBJETOS EQUIPMETN PARA LA DEMO
         Order order1 = null;
         
         ResultRequest<Order> orderRequest = Order.getInstace("2",1, 22.22, 22.22, "type2", "status2", "additionalInformacion","16/09/2013-19:45:03", "16/09/2013-19:55:03", "tarjeta", "16/10/2013-19:45:03");
         if (orderRequest.failed()) {
              System.out.println(orderRequest.getError());
        } else {
            order1 = orderRequest.getValue();
         }
         
          if(order1 != null) {
            OrderDTO odto1 = OrderMapper.dtoFromComponent(order1);
            
            JsonOrderSerializer joSerializer = new JsonOrderSerializer();
                    
            ResultRequest<String> orderSerialized = joSerializer.serialize(odto1);
            
            String jsonOrder = orderSerialized.getValue();
            System.out.println("ORDER(SIN ORDERDETAIL):");
            System.out.println(jsonOrder);
            System.out.println("----------------------------------------------------------------");
    
        } */
   public static void main(String[] args) {
        //OBJETOS CLIENT PARA LA DEMO     
        System.out.println("JSON");
        
        Client client1 = null, client2 = null, client3 = null, auxClient = null;

        ResultRequest<Client> clientRequest = Client.getInstance("jose", "001", "carrer kalea 1", "666555444", "josem@gmail.cat",
                false, "20-febrero-1997", 3, "passwordSecret99");
        if (clientRequest.failed()) {
            System.out.println(clientRequest.getError());
        } else {
            client1 = clientRequest.getValue();
        }

        clientRequest = Client.getInstance(null, "002", "carrer kalea 3", "666555555", "     ",
                false, "28-febrero-1987", 1, "****");
        if (clientRequest.failed()) {
            System.out.println(clientRequest.getError());
        } else {
            client2 = clientRequest.getValue();
        }

        clientRequest = Client.getInstance("juan", "002", "carrer kalea 3", "666555555", "junam@gmail.cat",
                false, "28-febrero-1987", 1, "****");
        if (clientRequest.failed()) {
            System.out.println(clientRequest.getError());
        } else {
            client2 = clientRequest.getValue();
        }

        clientRequest = Client.getInstance("jordi", "003", "carrer kalea 5", "666555666", "jordi@gmail.cat",
                false, "10-febrero-1990", 5, "****");
        if (clientRequest.failed()) {
            System.out.println(clientRequest.getError());
        } else {
            client3 = clientRequest.getValue();
        }

        if (client1 != null && client2 != null && client3 != null) {

            ClientDTO cdto1 = ClientMapper.dtoFromComponent(client1);
            ClientDTO cdto2 = ClientMapper.dtoFromComponent(client2);
            ClientDTO cdto3 = ClientMapper.dtoFromComponent(client3);
            ClientDTO auxCdto;

            //SERIALITZACIO JSON AMB UNA LLIBRERIA ESTABLERTA PER DEFECTE
            JsonClientSerializer jcSerializer = new JsonClientSerializer();

            ResultRequest<String> clientSerialized = jcSerializer.serialize(cdto2);

           // String jsonClient = clientSerialized.getValue();
           // System.out.println(jsonClient);

            String newJsonClient = "{"
                    + "  \"birthday\": \"22-febrero-1987\","
                    + "  \"code\": \"023\","
                    + "  \"clientid\": \"1d335c53-f5c4-48da-9a6a-e6f766c67a76\","
                    + "  \"address\": \"carrer kalea 13\","
                    + "  \"phone\": \"666555888\","
                    + "  \"name\": \"alex\","
                    + "  \"numequipments\": \"4\","
                    + "  \"password\": \"xela\","
                    + "  \"iscompany\": \"false\","
                    + "  \"email\": \"alexm@gmail.cat\""
                    + "}";

            ResultRequest<ClientDTO> clientUnserialized = jcSerializer.unserialize(newJsonClient);

            if (clientUnserialized.failed()) {
                System.out.println(clientUnserialized.getError());

            } else {
                auxCdto = clientUnserialized.getValue();
                ResultRequest<Client> clientUnMapped = ClientMapper.componentFromDTO(auxCdto);

                if (clientUnMapped.failed()) {
                    System.out.println(clientUnMapped.getError());
                } else {
                    auxClient = clientUnMapped.getValue();
                }
            }

            //SERIALITZACIO XML AMB LA LLIBRERIA DOM (revisseu que aprofitem els components adients de la serialitzacio anterior)
           System.out.println("XML");
           Xml xmlConverter = new Dom();
           Serializer xmlClientSerializer = new XmlClientSerializer(xmlConverter);

           clientSerialized = xmlClientSerializer.serialize(cdto1);

            String xmlClient = clientSerialized.getValue();
            System.out.println(xmlClient);

            //APROFITEM EL MATEIX STRING AMB EL XML PER SIMULAR LA ENTRADA D'UN DOCUMENT XML AL SISTEMA
            clientUnserialized = xmlClientSerializer.unserialize(xmlClient);

            if (clientUnserialized.failed()) {
                System.out.println(clientUnserialized.getError());

            } else {
                auxCdto = clientUnserialized.getValue();
                ResultRequest<Client> clientUnMapped = ClientMapper.componentFromDTO(auxCdto);

                if (clientUnMapped.failed()) {
                    System.out.println(clientUnMapped.getError());
                } else {
                    auxClient = clientUnMapped.getValue();
                }
            }
        }
        
        //XML SERVICE
          //OBJETOS SERVICE PARA LA DEMO 
        Service service1= null;
        
        ResultRequest<Service> serviceRequest = Service.getInstance("001", "Servicio 10", "Type", "Manu S.L", "Venta de un PC", 8.88, "30 dias", "Ninguna", "16/09/2013-19:45:03",  "16/09/2013-19:45:03");
        if (serviceRequest.failed()) {
            System.out.println(serviceRequest.getError());
        } else {
            service1 = serviceRequest.getValue();
        }
        
        if (service1 != null) {
             
//SERIALITZACIO XML AMB LA LLIBRERIA DOM (revisseu que aprofitem els components adients de la serialitzacio anterior)
           

            ServiceDTO sdto1 = ServiceMapper.dtoFromComponent(service1);
            System.out.println("SERVICE:");
            Xml xmlConverter = new Dom();
            Serializer xmlServiceSerializer = new XmlServiceSerializer(xmlConverter);
            ResultRequest ServiceSerialized = xmlServiceSerializer.serialize(sdto1);
            String xmlService = (String) ServiceSerialized.getValue();
            System.out.println(xmlService);
            System.out.println("----------------------------------------------------------------");

        }
     
   //XML EQUIPMENT
   //OBJETO EQUIPMENT
   
     Equipment equipment1=null;
         
         ResultRequest<Equipment> equipmentRequest = Equipment.getInstance("001", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        if (equipmentRequest.failed()) {
              System.out.println(equipmentRequest.getError());
        } else {
            equipment1 = equipmentRequest.getValue();
        }
        
        if(equipment1 != null) {
            EquipmentDTO edto1 = EquipmentMapper.dtoFromComponent(equipment1);
            System.out.println("EQUIPMENT:");
            Xml xmlConverter = new Jdom();
            Serializer xmlEquipmentSerializer = new XmlEquipmentSerializer(xmlConverter);
            ResultRequest ServiceSerialized = xmlEquipmentSerializer.serialize(edto1);
            String xmlService = (String) ServiceSerialized.getValue();
            System.out.println(xmlService);
            System.out.println("----------------------------------------------------------------");

}
      
   }   
}
   
