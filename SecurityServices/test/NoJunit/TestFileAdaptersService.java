package NoJunit;

import securityservices.core.component.service.appservices.JsonServiceSerializer;
import securityservices.core.component.service.appservices.XmlServiceSerializer;
import securityservices.core.component.service.domain.serializers.ServiceDTO;
import securityservices.core.component.service.domain.serializers.ServiceMapper;
import securityservices.core.ports.infrastructure.FilePort;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.shared.services.serializers.xmlapis.Dom;
import securityservices.infrastructure.filesystemadapters.FileAdapter;
import securityservices.infrastructure.filesystemadapters.FileManager;
import securityservices.managment.catalogs.serializers.SerializerCatalog;
import securityservices.managment.catalogs.serializers.SerializerType;
import securityservices.products.Service;
import securityservices.shared.responses.ResultRequest;

public class TestFileAdaptersService {
    
 public static void main(String[] args) {

        //OBJETOS DTO PARA LA DEMO, MAPPERS Y METODO FACTORIA DE SERVICE YA ESTA PROBADO                       
        ServiceDTO sdto1 = new ServiceDTO("SA1311", "Name", "Type", "Maker", "Description", 333.33,
                "Periodicity", "Conditions", "03/02/2021-17:33:33", "04/02/2021-13:33:33", "IdService");
        ServiceDTO sdto2, sdto3;

        //OBJETO ENCARGADO DE LEER Y ESCRIBIR FICHEROS
        FilePort fileAdapter = new FileAdapter();
        //OBJETO QUE GESTIONA LOS NOMBRES Y RUTAS A UTILIZAR
        FileManager fileManager = new FileManager(fileAdapter);

        //SERIALITZACIO JSON OBTENIR EL OBJECTE A TRAVES DEL CATALOG/DICCIONARI QUE ENS PROPORCIONA L'OBJECTE ADIENT
        Serializer serviceSerializer = SerializerCatalog.getInstance(SerializerType.JsonService);

        ResultRequest<String> serviceSerialized = serviceSerializer.serialize(sdto1);
        String dataService = serviceSerialized.getValue();
        System.out.println("---JSON---");
        System.out.println(dataService);
        System.out.println();
        System.out.println("---XML DOM---");

        //ESCRITURA DEL FICHERO
        fileManager.write(dataService, "service", "json");

        //LECTURA DEL FICHERO ("UTILIZAD CADA UNO EL NOMBRE DE FICHERO NECESARIO")
        ResultRequest<String> dataServiceFile = fileManager.read("c:\\files\\service_03_03_2021_19_01_49.json");
        if (dataServiceFile.failed()) {
            System.out.println(dataServiceFile.getError());
        } else {
            dataService = dataServiceFile.getValue();
            ResultRequest<ServiceDTO> serviceUnserialized = serviceSerializer.unserialize(dataService);

            if (serviceUnserialized.failed()) {
                System.out.println(serviceUnserialized.getError());
            } else {
                sdto2 = serviceUnserialized.getValue();
                //SERIALITZACIO XML AMB LA LLIBRERIA DOM SENSE QUE EL SERVICE TINGUI CONSTANCIA GRACIES AL CATALOG
                serviceSerializer = SerializerCatalog.getInstance(SerializerType.XmlService);

                serviceSerialized = serviceSerializer.serialize(sdto2);
                dataService = serviceSerialized.getValue();
                System.out.println(dataService);
                fileManager.write(dataService, "service", "xml");

                dataServiceFile = fileManager.read("c:\\files\\service_03_03_2021_19_16_22.xml");
                if (dataServiceFile.failed()) {
                    System.out.println(dataServiceFile.getError());
                } else {
                    dataService = dataServiceFile.getValue();
                    serviceUnserialized = serviceSerializer.unserialize(dataService);

                    if (serviceUnserialized.failed()) {
                        System.out.println(serviceUnserialized.getError());
                    } else {
                        sdto3 = serviceUnserialized.getValue();
                        System.out.println(sdto3);
                    }
                }
            }
        }
    }
}
