package NoJunit.RepositoryTest;

import java.util.List;
import securityservices.core.component.service.appservices.JsonServiceSerializer;
import securityservices.core.component.service.appservices.XmlServiceSerializer;
import securityservices.products.Service;
import securityservices.core.component.service.domain.serializers.ServiceDTO;
import securityservices.core.component.service.domain.serializers.ServiceMapper;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.shared.services.serializers.Xml;
import securityservices.core.shared.services.serializers.xmlapis.Dom;
import securityservices.infrastructure.db.postgredapters.ServicePostgreRepository;
import securityservices.shared.responses.ResultRequest;
import securityservices.infrastructure.db.connectors.JdbcConnector;

public class TestServiceRepository {

    public static void main(String[] args) {
        Json jconnect = JsonObjectFactory.getInstance();
        JdbcConnector connection = new JdbcConnector();
        Boolean existe;
   
        jconnect.set("jdbc", "postgresql");
        jconnect.set("serv", "localhost");
        jconnect.set("port", "5432");
        jconnect.set("dbname", "securityservices");
        jconnect.set("user", "ManuDemo");
        jconnect.set("pass", "linuxlinux");

        ResultRequest<Json> request = connection.connect(jconnect);

        if (request.failed()) {
            System.out.println("Fallo al conectar: " + request.getError());
        } else {
            System.out.println("Se ha conectado");
            ServicePostgreRepository data = new ServicePostgreRepository(connection);
            System.out.println();
               System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
         System.out.println("SERVICE");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   
 /*
       System.out.println("----------------------");    
        System.out.println("GETALL");
            JsonServiceSerializer jcSerializer = new JsonServiceSerializer();
            ResultRequest<List<ServiceDTO>> result = data.getAll();
            if (result.failed()) {
                System.out.println("Error" + result.getError());
            } else {
                int res = result.getValue().size();

                for (int i = 0; i < res; i++) {
                    ServiceDTO Service = result.getValue().get(i);

                    ResultRequest<String> serviceSerialized = jcSerializer.serialize(Service);

                    Xml xmlConverter = new Dom();
                    Serializer xmlServiceSerializer = new XmlServiceSerializer(xmlConverter);

                    serviceSerialized = xmlServiceSerializer.serialize(Service);

                    String xmlService = serviceSerialized.getValue();
                    System.out.println(xmlService);
                }
            }  
*/
  System.out.println("----------------------");
            System.out.println("GETBYID");
            JsonServiceSerializer jcSerializer = new JsonServiceSerializer();
            ResultRequest<ServiceDTO> res = data.getByID("06122222");
            System.out.println(res);

            ServiceDTO Service = res.getValue();
            System.out.println("Service " + Service);
            ResultRequest<String> serviceSerialized = jcSerializer.serialize(Service);
            String jsonService = serviceSerialized.getValue();
            System.out.println(jsonService);
            if (jsonService != null) {
                existe = true;
            } else {
                existe = false;
                System.out.println("Sin contenido");
            }
  System.out.println("----------------------");
            System.out.println("EXISTS");
            if (existe == true) {
                System.out.println("Existe");
            } else {
                System.out.println("No existe");
            }  /*
          System.out.println("----------------------");
          System.out.println("ADD"); 
            ResultRequest<Service> serviceRequest = Service.getInstance("PC13", "Arreglo de PC", "Type 3: Grafica", "Maker 1", "Tarjeta Grafica Cambio", 50.89, 
                                                                            "Periodicidad", "Ninguna", "2021-03-24", "2021-03-24");
            
            Service service = serviceRequest.getValue();
            ServiceDTO sdto = ServiceMapper.dtoFromComponent(service);
            ResultRequest<String> add = data.add(sdto);
            if (add.failed()) {
                System.out.println("Error" + add.getError());
            } else {
                 System.out.println(add.getValue());
            }  
          System.out.println("----------------------");
            System.out.println("DELETE"); 
              ResultRequest<String> del = data.deleteByID("cb4577c3-ee32-462e-851c-f1bc4f928e0a");
            if (del.failed()) {
                System.out.println("Error" + del.getError());
            } else {
                 System.out.println(del.getValue());
            }
              System.out.println("----------------------"); */ /*
            System.out.println("UPDATE");
            ResultRequest<Service> serviceRequest = Service.getInstance("PAN67", "Pantalla", "Type 5", "HP", "Cambio de Pantalla HP con 45 leds rotos"
                                                                            , 30.99, "Periodicidad", "Condiciones", 
                                                                            "2020-03-14", "2020-03-25");
            Service service = serviceRequest.getValue();
            ServiceDTO sdto = ServiceMapper.dtoFromComponent(service);
            ResultRequest<String> upser = data.update(sdto);
            if (upser.failed()) {
                System.out.println("Error" + upser.getError());
            } else {
                 System.out.println(upser.getValue());
            } 
            */
        } 
  
    }
}
