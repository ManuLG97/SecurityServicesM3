package NoJunit.RepositoryTest;

import java.util.List;
import securityservices.core.component.equipment.appservices.JsonEquipmentSerializer;
import securityservices.core.component.equipment.appservices.XmlEquipmentSerializer;
import securityservices.products.Equipment;
import securityservices.core.component.equipment.domain.serializers.EquipmentDTO;
import securityservices.core.component.equipment.domain.serializers.EquipmentMapper;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.shared.services.serializers.Xml;
import securityservices.core.shared.services.serializers.xmlapis.Dom;
import securityservices.infrastructure.db.postgredapters.EquipmentPostgreRepository;
import securityservices.shared.responses.ResultRequest;
import securityservices.infrastructure.db.connectors.JdbcConnector;

public class TestEquipmentRepository {

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
            EquipmentPostgreRepository data = new EquipmentPostgreRepository(connection);
              
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
         System.out.println("EQUIPMENTS");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
      JsonEquipmentSerializer jcSerializer = new JsonEquipmentSerializer();
            
            
             System.out.println("----------------------");
            System.out.println("GET ALL");
            ResultRequest<List<EquipmentDTO>> result = data.getAll();
            if (result.failed()) {
                System.out.println("No a sido posible ejecutar " + result.getError());
            } else {
                System.out.println("Ejecutado");
                    
                int res = result.getValue().size();
System.out.println(res);
                for (int i = 0; i < res; i++) {
                   
                    EquipmentDTO Equipment = result.getValue().get(i);

                    ResultRequest<String> equipmentSerialized = jcSerializer.serialize(Equipment);

                    Xml xmlConverter = new Dom();
                    Serializer xmlEquipmentSerializer = new XmlEquipmentSerializer(xmlConverter);

                    equipmentSerialized = xmlEquipmentSerializer.serialize(Equipment);

                    String xmlEquipment = equipmentSerialized.getValue();
                    System.out.println(xmlEquipment);
                }
            }
            

           
         
           
            System.out.println("----------------------");
            System.out.println("ADD");
            ResultRequest<Equipment> equipmentRequest = Equipment.getInstance("PAR53", "Bq", "Smartphone", "Bq", "Bq Pro Color Verde", 549.00, 
                                                                                31.30, 31.30, 31.30, 31.30, true, "Movil", "Componentes", 1);
            Equipment equipment = equipmentRequest.getValue();
            EquipmentDTO edto = EquipmentMapper.dtoFromComponent(equipment);
            ResultRequest<String> add = data.add(edto);
            if (add.failed()) {
                System.out.println("Error" + add.getError());
            } else {
                System.out.println(add.getValue());
                 
            } System.out.println("----------------------");
            System.out.println("DELETE");
              ResultRequest<String> del = data.deleteByID("68a70103-8574-45a1-85cf-13ef9efc7d7d");
            if (del.failed()) {
                System.out.println("Error" + del.getError());
            } else {
                 System.out.println(del.getValue());
            }
            System.out.println(); System.out.println("----------------------");
            System.out.println("UPDATE");
             equipmentRequest = Equipment.getInstance("HP002", "Portatil", "HP", "HP", "Portatil HP Color Rojo", 
                                                                                999.99, 3.3, 2.5, 3.3, 6.6, false, "Function", "Portatil", 9);
            equipment = equipmentRequest.getValue();
            edto = EquipmentMapper.dtoFromComponent(equipment);
            ResultRequest<String> upeqip = data.update(edto);
            if (upeqip.failed()) {
                System.out.println("Error" + upeqip.getError());
            } else {
                 System.out.println(upeqip.getValue());
            }

        }

    }
}
