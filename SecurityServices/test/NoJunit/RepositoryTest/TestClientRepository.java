package NoJunit.RepositoryTest;

import java.util.List;
import securityservices.core.component.client.appservices.JsonClientSerializer;
import securityservices.core.component.client.appservices.XmlClientSerializer;
import securityservices.core.component.client.domain.model.Client;
import securityservices.core.component.client.domain.services.ClientDTO;
import securityservices.core.component.client.domain.services.ClientMapper;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.shared.services.serializers.Xml;
import securityservices.core.shared.services.serializers.xmlapis.Dom;
import securityservices.shared.responses.ResultRequest;
import securityservices.infrastructure.db.connectors.JdbcConnector;
import securityservices.infrastructure.db.postgredapters.ClientPostgreRepository;

public class TestClientRepository {

    public static void main(String[] args) {
        Json jconnect = JsonObjectFactory.getInstance();
        JdbcConnector connection = new JdbcConnector();

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
            ClientPostgreRepository data = new ClientPostgreRepository(connection);
            System.out.println();
         
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
         System.out.println("CLIENTES");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   
        //GET ALL CLIENTS
         System.out.println("----------------------");
        System.out.println("GET ALL");
            JsonClientSerializer jcSerializer = new JsonClientSerializer();
            ResultRequest<List<ClientDTO>> result = data.getAll();
            if (result.failed()) {
                System.out.println("Error" + result.getError());
            } else {
                int res = result.getValue().size();

                for (int i = 0; i < res; i++) {
                    ClientDTO Client = result.getValue().get(i);
                    ResultRequest<String> clientSerialized = jcSerializer.serialize(Client);
                    Xml xmlConverter = new Dom();
                    Serializer xmlClientSerializer = new XmlClientSerializer(xmlConverter);
                    clientSerialized = xmlClientSerializer.serialize(Client);

                    String xmlClient = clientSerialized.getValue();
                    System.out.println(xmlClient);
                }
            } 

            

           System.out.println("----------------------");
            System.out.println("ADD"); 
            ResultRequest<Client> clientRequest = Client.getInstance("Cristobal Garcia", "12345678P", "C/ Iglesia", "666444888", 
                                                                        "cristobalgarcia@escolesnuria.com", false, "1993-03-03", 3, "1234XR54");
            Client client = clientRequest.getValue();
            ClientDTO cdto = ClientMapper.dtoFromComponent(client);
            ResultRequest<String> add = data.add(cdto);
            if (add.failed()) {
                System.out.println("Error" + add.getError());
            } else {
                 System.out.println(add.getValue());
            }  
             System.out.println("----------------------");
            System.out.println("DELETE"); 
              ResultRequest<String> del = data.deleteByID("0003244400044432");
            if (del.failed()) {
                System.out.println("Error" + del.getError());
            } else {
                 System.out.println(del.getValue());
            } 
            
            System.out.println("----------------------");
            System.out.println("UPDATE");
            clientRequest = Client.getInstance("Cristobal Garcia González", "12345678P", "C/ Iglesia de Moserrat", "666909413", "cristobal98@gmail.com", false, "1998-11-13", 3, "1234XR54");

            ResultRequest<String> updc = data.update(cdto);
            if (updc.failed()) {
                System.out.println("Error" + updc.getError());
            } else {
                 System.out.println(updc.getValue());
            } 
          
        }

    }
}
