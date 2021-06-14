package NoJUnit;

import securityservices.infrastructure.filesystemadapters.FileManager;
import securityservices.core.component.client.domain.services.ClientDTO;
import securityservices.core.ports.infrastructure.FilePort;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.infrastructure.filesystemadapters.FileAdapter;
import securityservices.managment.catalogs.serializers.SerializerCatalog;
import securityservices.managment.catalogs.serializers.SerializerType;
import securityservices.shared.responses.ResultRequest;

public class TestFileAdaptersClient {

    public static void main(String[] args) {

        //OBJETOS DTO PARA LA DEMO, MAPPERS Y METODO FACTORIA DE CLIENT YA ESTA PROBADO                       
        ClientDTO cdto1 = new ClientDTO("jose", "001", "carrer kalea 1", "666555444", "josem@gmail.cat",
                false, "20-febrero-1997", "ccccc1", 3, "***");
        ClientDTO cdto2, cdto3, cdto4;

        //OBJETO ENCARGADO DE LEER Y ESCRIBIR FICHEROS
        FilePort fileAdapter = new FileAdapter();
        //OBJETO QUE GESTIONA LOS NOMBRES Y RUTAS A UTILIZAR
        FileManager fileManager = new FileManager(fileAdapter);

        //SERIALITZACIO JSON OBTENIR EL OBJECTE A TRAVES DEL CATALOG/DICCIONARI QUE ENS PROPORCIONA L'OBJECTE ADIENT
        Serializer clientSerializer = SerializerCatalog.getInstance(SerializerType.JsonClient);

        ResultRequest<String> clientSerialized = clientSerializer.serialize(cdto1);
        String dataClient = clientSerialized.getValue();
        System.out.println(dataClient);

        //ESCRITURA DEL FICHERO
        fileManager.write(dataClient, "client", "json");

        //LECTURA DEL FICHERO ("UTILIZAD CADA UNO EL NOMBRE DE FICHERO NECESARIO")
        ResultRequest<String> dataClientFile = fileManager.read("c:\\files\\client_25_02_2021_18_52_14.json");
        if (dataClientFile.failed()) {
            System.out.println(dataClientFile.getError());
        } else {
            dataClient = dataClientFile.getValue();
            ResultRequest<ClientDTO> clientUnserialized = clientSerializer.unserialize(dataClient);

            if (clientUnserialized.failed()) {
                System.out.println(clientUnserialized.getError());
            } else {
                cdto2 = clientUnserialized.getValue();
                //SERIALITZACIO XML AMB LA LLIBRERIA DOM SENSE QUE EL CLIENT TINGUI CONSTANCIA GRACIES AL CATALOG
                clientSerializer = SerializerCatalog.getInstance(SerializerType.XmlClient);

                clientSerialized = clientSerializer.serialize(cdto2);
                dataClient = clientSerialized.getValue();
                System.out.println(dataClient);
                fileManager.write(dataClient, "client", "xml");

                dataClientFile = fileManager.read("c:\\files\\client_25_02_2021_19_08_13.xml");
                if (dataClientFile.failed()) {
                    System.out.println(dataClientFile.getError());
                } else {
                    dataClient = dataClientFile.getValue();
                    clientUnserialized = clientSerializer.unserialize(dataClient);

                    if (clientUnserialized.failed()) {
                        System.out.println(clientUnserialized.getError());
                    } else {
                        cdto3 = clientUnserialized.getValue();
                        //SERIALITZACIO XML AMB JAXB (revisseu que aprofitem els components adients de la serialitzacio anterior)
                        clientSerializer = SerializerCatalog.getInstance(SerializerType.JaxbClient);
                        clientSerialized = clientSerializer.serialize(cdto3);
                        dataClient = clientSerialized.getValue();
                        System.out.println(dataClient);
                        fileManager.write(dataClient, "client", "xml");

                        dataClientFile = fileManager.read("c:\\files\\client_25_02_2021_19_09_11.xml");
                        if (dataClientFile.failed()) {
                            System.out.println(dataClientFile.getError());
                        } else {
                            dataClient = dataClientFile.getValue();

                            clientUnserialized = clientSerializer.unserialize(dataClient);

                            if (clientUnserialized.failed()) {
                                System.out.println(clientUnserialized.getError());
                            } else {
                                cdto4 = clientUnserialized.getValue();
                                System.out.println(cdto4);
                            }
                        }
                    }
                }
            }
        }
        
         //OBJETOS DTO PARA LA DEMO, MAPPERS Y METODO FACTORIA DE SERVICE 
        
    }
}
