package securityservices.infrastructure.db.postgredapters;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import securityservices.core.component.equipment.appservices.JsonEquipmentSerializer;
import securityservices.products.Equipment;
import securityservices.core.component.equipment.domain.serializers.EquipmentDTO;
import securityservices.core.component.equipment.infrastructure.EquipmentRepository;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.infrastructure.db.connectors.JdbcConnector;
import securityservices.infrastructure.db.connectors.PersistenceConnector;
import securityservices.shared.responses.ResultRequest;

public class EquipmentPostgreRepository implements EquipmentRepository {

    private PersistenceConnector connection;
    private ResultRequest<Json> result;
    private Boolean exist = true;
    JsonEquipmentSerializer jSerializer = new JsonEquipmentSerializer();
    //constructor on rebem les dades de la connexió, per defecte fem servir un connector jdbc

    public EquipmentPostgreRepository(Json dataconnex) {
        this.connection = new JdbcConnector(dataconnex);
    }
    //constructor on rebem l’objecte per la connexió fent servir Inversió de Dependències

    public EquipmentPostgreRepository(PersistenceConnector connection) {
        this.connection = connection;
    }

    //espai lliure per implementar els serveis de la interfície i els interns necessaris
    @Override
    public ResultRequest<List<EquipmentDTO>> getAll() {
       try {
            result = this.connection.readQuery("SELECT * FROM equipments;");
            Json equipment = result.getValue();
            ArrayList<EquipmentDTO> equipments = new ArrayList();
            for (int i = 0; i < result.getValue().getArraySize("Contenido del Array de la clase"); i++) {

                String newJsonEquipment = (equipment.getArrayObj("Contenido del Array de la clase", i)).toString();
                String physic = (equipment.getArrayObj("Contenido del Array de la clase", i)).get("physicdata");
                Json jphysic = JsonObjectFactory.getInstance();
                jphysic.set(physic);
                Json jobj = JsonObjectFactory.getInstance();
                jobj.set(newJsonEquipment);
                String all = "{\"code\":\"" + jobj.get("code") + "\",\"name\":\"" + jobj.get("name") + "\",\"type\":\""
                        + jobj.get("type") + "\",\"maker\":\"" + jobj.get("maker") + "\",\"description\":\"" + jobj.get("description")
                        + "\",\"price\":\"" + jobj.get("price") + "\",\"high\":\"" + jphysic.get("high") + "\",\"wide\":\"" + jphysic.get("wide")
                        + "\",\"deep\":\"" + jphysic.get("deep") + "\",\"weight\":\"" + jphysic.get("weigth") + "\",\"fragile\":\"" + jphysic.get("fragile")
                        + "\",\"function\":\"" + jobj.get("function") + "\",\"components\":\"" + jobj.get("components") + "\",\"power\":\"" + jobj.get("power")
                        + "\",\"equipmentid\":\"" + jobj.get("equipmentid") + "\"}";
                ResultRequest<EquipmentDTO> equipmentUnserialized = jSerializer.unserialize(all);
                System.out.println(equipmentUnserialized.getValue());
                equipments.add(equipmentUnserialized.getValue());
            }
            return ResultRequest.done(equipments);
        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<EquipmentDTO> getByID(String id) {
        try {
            result = this.connection.readQuery("SELECT * FROM equipments WHERE equipmentId = '" + id + "'");
            Json equipment = result.getValue();
            System.out.println("Equipment: " + equipment);
            Json res = equipment.getJResult("Contenido del Array de la clase");
            System.out.println("Array: " + res);
            String ress = equipment.get("Contenido del Array de la clase");
            ResultRequest<EquipmentDTO> equipmentUnserialized = jSerializer.unserialize(ress);
            return ResultRequest.done(equipmentUnserialized.getValue());

        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<String> add(EquipmentDTO equipment) {
        try {
            String values = "INSERT INTO equipments VALUES ('" + equipment.getCode() + "','" + equipment.getName()
                    + "','" + equipment.getType() + "','" + equipment.getMaker() + "','" + equipment.getDescription()
                    + "'," + equipment.getPrice() + ",'" + equipment.getEquipmentId() + "','" + equipment.getFunction()
                    + "','" + equipment.getComponents() + "'," + equipment.getPower() + ",'{\"high\":" + equipment.getHigh()
                    + ",\"wide\":" + equipment.getWide() + ",\"deep\":" + equipment.getDeep()
                    + ",\"weigth\":" + equipment.getWeight() + ",\"fragile\":" + equipment.isFragile() + "}')";

            System.out.println(values);
            this.result = this.connection.writeQuery(values);

            if (this.result.failed()) {
                return ResultRequest.fails("\"Error\":" + result.getError());
            } else {
                return ResultRequest.done("Insertado correctamente");
            }

        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<String> update(EquipmentDTO equipment) {
        try {
            String up = "UPDATE equipments SET code='" + equipment.getCode() + "', name='" + equipment.getName() + "', type='"
                    + equipment.getType() + "', maker='" + equipment.getMaker() + "', description='" + equipment.getDescription()
                    + "', price=" + equipment.getPrice() + ", equipmentid='" + equipment.getEquipmentId() + "', function='"
                    + equipment.getFunction() + "', components='" + equipment.getComponents() + "', power="
                    + equipment.getPower() + ", physicdata='{\"high\":\"" + equipment.getHigh() + "\",\"wide\":\"" + equipment.getWide()
                    + "\",\"deep\":\"" + equipment.getDeep() + "\",\"weigth\":\"" + equipment.getWeight() + "\",\"fragile\":\"" + equipment.isFragile() + "\"}' WHERE code='" + equipment.getCode() + "'";
            System.out.println(up);
            ResultRequest<Json> result = this.connection.writeQuery(up);
            return ResultRequest.done("\"Actualzado\"");
        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\"");
        }
    }

    @Override
    public ResultRequest<String> deleteByID(String id) {
        try {
            this.result = this.connection.writeQuery("DELETE FROM equipments WHERE equipmentId='" + id + "'");

            if (this.result.failed()) {
                return ResultRequest.fails("\"Error\"");
            } else {
                return ResultRequest.done("\"Eliminado\"");
            }

        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + result.getError());
        }
    }

    @Override
    public Boolean exists(String id) {
        return exist;
    }
}
