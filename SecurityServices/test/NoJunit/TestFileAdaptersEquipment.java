package NoJunit;

import securityservices.core.component.equipment.domain.serializers.EquipmentDTO;
import securityservices.core.ports.infrastructure.FilePort;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.infrastructure.filesystemadapters.FileAdapter;
import securityservices.infrastructure.filesystemadapters.FileManager;
import securityservices.managment.catalogs.serializers.SerializerCatalog;
import securityservices.managment.catalogs.serializers.SerializerType;
import securityservices.shared.responses.ResultRequest;



public class TestFileAdaptersEquipment {
    
     public static void main(String[] args) {

        //OBJETOS DTO PARA LA DEMO, MAPPERS Y METODO FACTORIA DE EQUIPMENT YA ESTA PROBADO                       
        EquipmentDTO edto1 = new EquipmentDTO("CODEAX32", "Name", "Type", "Maker", "Description", 30.5, 
                10.1, 11.1, 12.1, 13.1, false, "Function", "Components", 3, "IdEquipment");
        EquipmentDTO edto2, edto3, edto4;

        //OBJETO ENCARGADO DE LEER Y ESCRIBIR FICHEROS
        FilePort fileAdapter = new FileAdapter();
        //OBJETO QUE GESTIONA LOS NOMBRES Y RUTAS A UTILIZAR
        FileManager fileManager = new FileManager(fileAdapter);

        //SERIALITZACIO JSON OBTENIR EL OBJECTE A TRAVES DEL CATALOG/DICCIONARI QUE ENS PROPORCIONA L'OBJECTE ADIENT
        Serializer equipmentSerializer = SerializerCatalog.getInstance(SerializerType.JsonEquipment);

        ResultRequest<String> equipmentSerialized = equipmentSerializer.serialize(edto1);
        String dataEquipment = equipmentSerialized.getValue();
        System.out.println(dataEquipment);

        //ESCRITURA DEL FICHERO
        fileManager.write(dataEquipment, "equipment", "json");

        //LECTURA DEL FICHERO ("UTILIZAD CADA UNO EL NOMBRE DE FICHERO NECESARIO")
        ResultRequest<String> dataEquipmentFile = fileManager.read("c:\\files\\equipment_03_03_2021_20_26_46.json");
        if (dataEquipmentFile.failed()) {
            System.out.println(dataEquipmentFile.getError());
        } else {
            dataEquipment = dataEquipmentFile.getValue();
            ResultRequest<EquipmentDTO> equipmentUnserialized = equipmentSerializer.unserialize(dataEquipment);

            if (equipmentUnserialized.failed()) {
                System.out.println(equipmentUnserialized.getError());
            } else {
                edto2 = equipmentUnserialized.getValue();
                //SERIALITZACIO XML AMB LA LLIBRERIA DOM SENSE QUE EL EQUIPMENT TINGUI CONSTANCIA GRACIES AL CATALOG
                equipmentSerializer = SerializerCatalog.getInstance(SerializerType.XmlEquipment);

                equipmentSerialized = equipmentSerializer.serialize(edto2);
                dataEquipment = equipmentSerialized.getValue();
                System.out.println(dataEquipment);
                fileManager.write(dataEquipment, "equipment", "xml");

                dataEquipmentFile = fileManager.read("c:\\files\\equipment_03_03_2021_20_27_19.xml");
                if (dataEquipmentFile.failed()) {
                    System.out.println(dataEquipmentFile.getError());
                } else {
                    dataEquipment = dataEquipmentFile.getValue();
                    equipmentUnserialized = equipmentSerializer.unserialize(dataEquipment);

                    if (equipmentUnserialized.failed()) {
                        System.out.println(equipmentUnserialized.getError());
                    } else {
                        edto3 = equipmentUnserialized.getValue();
                        
                        }
                    }
                }
            }
        }
     }
       
    
    

