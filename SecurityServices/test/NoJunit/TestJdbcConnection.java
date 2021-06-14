package noJUnit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.shared.responses.ResultRequest;
import securityservices.infrastructure.db.connectors.JdbcConnector;

public class TestJdbcConnection {

    public static void main(String[] args) {
        Json jconnect = JsonObjectFactory.getInstance();
        JdbcConnector connection = new JdbcConnector();

        jconnect.set("jdbc", "postgresql");
        jconnect.set("serv", "localhost");
        jconnect.set("port", "5432");
        jconnect.set("dbname", "securityservices");
        jconnect.set("user", "ManuDemo");
        jconnect.set("pass", "linuxlinux");
 
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("COMPROVANDO CONEXION");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
  
        ResultRequest<Json> request = connection.connect(jconnect);
        if (request.failed()) {
            System.out.println("Fallo al conectar: " + request.getError());
        } else {
            System.out.println("Se ha conectado: " + request.getValue());

        }
     //COMPROVAR CONEXION
     System.out.println(connection.isConnect());  
     
        System.out.println("----------------------");
        if (connection.isConnect() == true) {
            System.out.println("Conexion:  Conectado");
        } else {
            System.out.println("Conexion:  Desconectado");
        }

   /*  
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
         System.out.println("CLIENTES");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   
        //SELECT CLIENTS
         System.out.println("----------------------");
        System.out.println("SELECT");
        ResultRequest<Json> resultSelectClients = connection.readQuery("SELECT * FROM clients");

        if (resultSelectClients.failed()) {
            System.out.println("Select error client " + resultSelectClients.getError());

        } else {
            System.out.println(resultSelectClients.getValue());
        }

        //INSERT CLIENTS
         System.out.println("----------------------");
        System.out.println("INSERT");
        ResultRequest<Json> resultInsertClients = connection.writeQuery("INSERT INTO clients VALUES ( 'Manuel', '45129088T', 'Calle Mayor', '607080999', 'manueel@gmail.com',false,'1979-01-01','003587683111','micpmtra545',1)");

        if (resultInsertClients.failed()) {
            System.out.println("Insert error " + resultInsertClients.getError());

        } else {
            System.out.println(resultInsertClients.getValue());
        }

        //UPDATE CLIENTS
         System.out.println("----------------------");
        System.out.println("UPDATE");
        ResultRequest<Json> resultUpdateClients = connection.writeQuery("UPDATE clients SET address='Calle Menor 66' WHERE code='45129088T'");

        if (resultUpdateClients.failed()) {
            System.out.println("Update error " + resultUpdateClients.getError());

        } else {
            System.out.println(resultUpdateClients.getValue());
        }

        //DELETE CLIENTS
         System.out.println("----------------------");
        System.out.println("DELETE");
        ResultRequest<Json> resultDeleteClients = connection.writeQuery("DELETE FROM clients WHERE code='566128900P'");

        if (resultDeleteClients.failed()) {
            System.out.println("Delete error " + resultDeleteClients.getError());

        } else {
            System.out.println(resultDeleteClients.getValue());
        }
        
       System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
         System.out.println("SERVICES");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   
        //SELECT SERVICES
         System.out.println("----------------------");
        System.out.println("SELECT");
        ResultRequest<Json> resultSelectServices = connection.readQuery("SELECT * FROM services");

        if (resultSelectServices.failed()) {
            System.out.println("Select error " + resultSelectServices.getError());

        } else {
            System.out.println(resultSelectServices.getValue());
        }

        //INSERT SERVICES
         System.out.println("----------------------");
        System.out.println("INSERT");
        ResultRequest<Json> resultInsertServices = connection.writeQuery("INSERT INTO services VALUES ('PAN67', 'Pantalla', 'Periférico', 'HP', 'Pantalla HP HD', 769.99, '99122445', 'Periodicidad', 'Condiciones', '2020-03-27 17:00:00', '2020-03-25 19:30:00');");

        if (resultInsertServices.failed()) {
            System.out.println("Insert error " + resultInsertServices.getError());

        } else {
            System.out.println(resultInsertServices.getValue());
        }
        
        //UPDATE SERVICES
         System.out.println("----------------------");
        System.out.println("UPDATE");
        ResultRequest<Json> resultUpdateServices= connection.writeQuery("UPDATE services SET description='Teclado Razer Azul Cambio' where  code='TEC67';");
        
        if (resultUpdateServices.failed()) {
            System.out.println("Update error " + resultUpdateServices.getError());

        } else {
            System.out.println(resultUpdateServices.getValue());
        }
        
        //DELETE SERVICES
         System.out.println("----------------------");
        System.out.println("DELETE");
        ResultRequest<Json> resultDeleteServices = connection.writeQuery("DELETE FROM services WHERE serviceid='21632666'");

        if (resultDeleteServices.failed()) {
            System.out.println("Delete error " + resultDeleteServices.getError());

        } else {
            System.out.println(resultDeleteServices.getValue());
        }
        
        
        */
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("EQUIPMENT");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        //SELECT EQUIPMENTS
        System.out.println("----------------------");
        System.out.println("SELECT");
        ResultRequest<Json> resultSelectEquipment = connection.readQuery("SELECT * FROM equipments");

        if (resultSelectEquipment.failed()) {
            System.out.println("Select error " + resultSelectEquipment.getError());

        } else {
            System.out.println(resultSelectEquipment.getValue());
        }
        
        //INSERT EQUIPMENTS
         System.out.println("----------------------");
        System.out.println("INSERT");
        ResultRequest<Json> resultInsertEquipment = connection.writeQuery("INSERT INTO equipments VALUES ('MAC10', 'MAC 10', 'Tablet', 'Apple', 'MAC 10 Pantala Grande', 1050.99, '23482', 'Tablet', 'Componentes', 3, "
                + "'{\"high\":2, \"wide\":1, \"deep\":1, \"weigth\":1}')");

        if (resultInsertEquipment.failed()) {
            System.out.println("Insert error " + resultInsertEquipment.getError());

        } else {
            System.out.println(resultInsertEquipment.getValue());
        }
           
        //UPDATE EQUIPMENTS
         System.out.println("----------------------");
        System.out.println("UPDATE");
        ResultRequest<Json> resultUpdateEquipment = connection.writeQuery("UPDATE equipments SET description ='Samgun Galaxy 11 Pantalla 3D' WHERE code='SAM11'");
        
        if (resultUpdateEquipment.failed()) {
            System.out.println("Update error " + resultUpdateEquipment.getError());

        } else {
            System.out.println(resultUpdateEquipment.getValue());
        }
      
        //DELETE EQUIPMENTS
         System.out.println("----------------------");
        System.out.println("DELETE");
        ResultRequest<Json> resultDeleteEquipment = connection.writeQuery("DELETE FROM equipments WHERE equipmentid='23482'");

        if (resultDeleteEquipment.failed()) {
            System.out.println("Delete error " + resultDeleteEquipment.getError());

        } else {
            System.out.println(resultDeleteEquipment.getValue());
        }
       
         /*
         
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("ORDERS");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        //SELECT ORDERS
         System.out.println("----------------------");
        System.out.println("SELECT");
        ResultRequest<Json> resultSelectOrders = connection.readQuery("SELECT * FROM orders");

        if (resultSelectOrders.failed()) {
            System.out.println("Select error " + resultSelectOrders.getError());

        } else {
            System.out.println(resultSelectOrders.getValue());
        }

        //INSERT ORDERS
         System.out.println("----------------------");
        System.out.println("INSERT");
        ResultRequest<Json> resultInsertOrders = connection.writeQuery("INSERT INTO orders VALUES ('ORD664', 2, 35.00, 34.0, 'Type 1', 'Enviado', 'Ninguna', '2021-05-23 16:33:20', '2021-03-23 10:44:44', '13447569', 'Portatil', '2021-03-23 10:45:35', '{\"amount\":1, \"price\":1222.66, \"ref\":8833}');");

        if (resultInsertOrders.failed()) {
            System.out.println("Insert error " + resultInsertOrders.getError());

        } else {
            System.out.println(resultInsertOrders.getValue());
        }

        //UPDATE ORDERS
         System.out.println("----------------------");
        System.out.println("UPDATE");
        ResultRequest<Json> resultUpdateOrders = connection.writeQuery("UPDATE orders SET value = 48.0 WHERE code = 'ORD564'");
        if (resultUpdateOrders.failed()) {
            System.out.println("Update error " + resultUpdateOrders.getError());

        } else {
            System.out.println(resultUpdateOrders.getValue());
        }
        
        //DELETE ORDERS
         System.out.println("----------------------");
        System.out.println("DELETE");
        ResultRequest<Json> resultDeleteOrders = connection.writeQuery("DELETE FROM orders WHERE orderid='10032999'");

        if (resultDeleteOrders.failed()) {
            System.out.println("Delete error " + resultDeleteOrders.getError());

        } else {
            System.out.println(resultDeleteOrders.getValue());
        }
         
    //DESCONECTAR BD
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------"); 
      System.out.println("DESCONECTAR DE DB:");
      System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
             ResultRequest dis = connection.unconnect();      
        if (dis.failed()) {
            System.out.println(dis.getValue());
        } else {
            System.out.println(dis.getValue());
        }

  
        */
        
    }
}
