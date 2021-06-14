package securityservices.infrastructure.db.postgredapters;

import java.util.ArrayList;
import java.util.List;
import securityservices.core.component.client.appservices.JsonClientSerializer;
import securityservices.core.component.client.domain.services.ClientDTO;
import securityservices.core.component.client.infrastructure.ClientRepository;
import securityservices.core.shared.services.serializers.Json;
import securityservices.infrastructure.db.connectors.JdbcConnector;
import securityservices.infrastructure.db.connectors.PersistenceConnector;
import securityservices.shared.responses.ResultRequest;

public class ClientPostgreRepository implements ClientRepository {

    private PersistenceConnector connection;
    private ResultRequest<Json> result;
    private Boolean exist = true;
    JsonClientSerializer jSerializer = new JsonClientSerializer();
    //constructor on rebem les dades de la connexió, per defecte fem servir un connector jdbc

    public ClientPostgreRepository(Json dataconnex) {
        this.connection = new JdbcConnector(dataconnex);
    }
    //constructor on rebem l’objecte per la connexió fent servir Inversió de Dependències

    public ClientPostgreRepository(PersistenceConnector connection) {
        this.connection = connection;
    }

    //espai lliure per implementar els serveis de la interfície i els interns necessaris
    @Override
    public ResultRequest<List<ClientDTO>> getAll() {
        try {
            result = this.connection.readQuery("SELECT * FROM clients");
            Json client = result.getValue();

            ArrayList<ClientDTO> clients = new ArrayList();
            for (int i = 0; i < result.getValue().getArraySize("Contenido del Array de la clase"); i++) {
                String newJsonClient = (client.getArrayObj("Contenido del Array de la clase", i)).toString();
                ResultRequest<ClientDTO> clientUnserialized = jSerializer.unserialize(newJsonClient);
                clients.add(clientUnserialized.getValue());
            }
            return ResultRequest.done(clients);
        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<ClientDTO> getByID(String id) {
        try {
            result = this.connection.readQuery("SELECT * FROM clients WHERE clientId = '" + id + "'");
            Json client = result.getValue();
            String res = client.get("Contenido del Array de la clase");
            ResultRequest<ClientDTO> clientUnserialized = jSerializer.unserialize(res);
            return ResultRequest.done(clientUnserialized.getValue());

        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<String> add(ClientDTO client) {
        try {
            String values = "INSERT INTO clients VALUES ('" + client.getName() + "','" + client.getCode()
                    + "','" + client.getAddress() + "','" + client.getPhone() + "','" + client.getEmail()
                    + "'," + client.isCompany() + ",'" + client.getBirthday() + "','" + client.getClientId()
                    + "','" + client.getPassword() + "'," + client.getNumEquipments() + ")";

            System.out.println(values);
            this.result = this.connection.writeQuery(values);

            if (this.result.failed()) {
                return ResultRequest.fails("\"Error\":" + result.getError());
            } else {
                return ResultRequest.done("Se inserto correctamente"
                        + "");
            }

        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<String> update(ClientDTO client) {
        try {
            String up = "UPDATE clients SET name='" + client.getName() + "', code='" + client.getCode() + "', address='"
                    + client.getAddress() + "', phone='" + client.getPhone() + "', email='" + client.getEmail() + "', company="
                    + client.isCompany() + ", birthday='" + client.getBirthday() + "', clientid='" + client.getClientId()
                    + "', password='" + client.getPassword() + "', numequipments=" + client.getNumEquipments()
                    + " WHERE code='" + client.getCode() + "'";
            System.out.println(up);
            ResultRequest<Json> result = this.connection.writeQuery(up);
            return ResultRequest.done("\"Actualizado\"");
        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\"");
        }
    }

    @Override
    public ResultRequest<String> deleteByID(String id) {
        try {
            this.result = this.connection.writeQuery("DELETE FROM clients WHERE clientId='" + id + "'");

            if (this.result.failed()) {
                return ResultRequest.fails("\"Error: No se puedo eliminar\"");
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
