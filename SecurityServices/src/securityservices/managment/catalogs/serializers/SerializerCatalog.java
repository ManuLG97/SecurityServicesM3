package securityservices.managment.catalogs.serializers;

import java.util.TreeMap;
import securityservices.core.component.client.appservices.JaxbClientSerializer;
import securityservices.core.component.client.appservices.JsonClientSerializer;
import securityservices.core.component.client.appservices.XmlClientSerializer;
import securityservices.core.component.equipment.appservices.JsonEquipmentSerializer;
import securityservices.core.component.equipment.appservices.XmlEquipmentSerializer;
import securityservices.core.component.service.appservices.JsonServiceSerializer;
import securityservices.core.component.service.appservices.XmlServiceSerializer;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.shared.services.serializers.xmlapis.Dom;

public class SerializerCatalog {
    
    private static TreeMap<SerializerType, Serializer> catalog = new TreeMap<>();
    
    private static void loadCatalog(){
        catalog.put(SerializerType.XmlClient, new XmlClientSerializer(new Dom()));
        catalog.put(SerializerType.JaxbClient, JaxbClientSerializer.getInstance().getValue());
        catalog.put(SerializerType.JsonClient, new JsonClientSerializer());
        catalog.put(SerializerType.XmlService, new XmlServiceSerializer(new Dom()));
        catalog.put(SerializerType.JsonService, new JsonServiceSerializer());
        catalog.put(SerializerType.XmlEquipment, new XmlEquipmentSerializer(new Dom()));
        catalog.put(SerializerType.JsonEquipment, new JsonEquipmentSerializer());
    }
    
    public static Serializer getInstance(SerializerType type){
        if (catalog.isEmpty()){
            loadCatalog();
        }
        return catalog.get(type);
    }
}
