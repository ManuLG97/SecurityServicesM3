package securityservices.infrastructure.db.postgredapters;

import java.util.ArrayList;
import java.util.List;
import securityservices.core.component.service.appservices.JsonServiceSerializer;
import securityservices.core.component.service.domain.serializers.ServiceDTO;
import securityservices.core.component.service.infrastructure.ServiceRepository;
import securityservices.core.shared.services.serializers.Json;
import securityservices.infrastructure.db.connectors.JdbcConnector;
import securityservices.infrastructure.db.connectors.PersistenceConnector;
import securityservices.shared.responses.ResultRequest;

public class ServicePostgreRepository implements ServiceRepository {

    private PersistenceConnector connection;
    private ResultRequest<Json> result;
    private Boolean exist = true;
    JsonServiceSerializer jSerializer = new JsonServiceSerializer();
    //constructor on rebem les dades de la connexió, per defecte fem servir un connector jdbc

    public ServicePostgreRepository(Json dataconnex) {
        this.connection = new JdbcConnector(dataconnex);
    }
    //constructor on rebem l’objecte per la connexió fent servir Inversió de Dependències

    public ServicePostgreRepository(PersistenceConnector connection) {
        this.connection = connection;
    }

    //espai lliure per implementar els serveis de la interfície i els interns necessaris
    @Override
    public ResultRequest<List<ServiceDTO>> getAll() {
        try {
            result = this.connection.readQuery("SELECT * FROM services");
            Json service = result.getValue();

            ArrayList<ServiceDTO> services = new ArrayList();
            for (int i = 0; i < result.getValue().getArraySize("Contenido del Array de la clase"); i++) {
                String newJsonService = (service.getArrayObj("Contenido del Array de la clase", i)).toString();
                System.out.println(newJsonService);
                ResultRequest<ServiceDTO> serviceUnserialized = jSerializer.unserialize(newJsonService);
                services.add(serviceUnserialized.getValue());
            }
            return ResultRequest.done(services);
        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<ServiceDTO> getByID(String id) {
        try {
            result = this.connection.readQuery("SELECT * FROM services WHERE serviceId = '" + id + "'");
            Json service = result.getValue();
            String res = service.get("Contenido del Array de la clase");
            ResultRequest<ServiceDTO> serviceUnserialized = jSerializer.unserialize(res);
            return ResultRequest.done(serviceUnserialized.getValue());

        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<String> add(ServiceDTO service) {
        try {
            String values = "INSERT INTO services VALUES ('" + service.getName() + "','" + service.getCode()
                    + "','" + service.getType() + "','" + service.getDescription() + "','"
                    + service.getMaker() + "'," + service.getPrice() + ", '" + service.getServiceId() + "', '"
                    + service.getPeriodicity() + "','" + service.getConditions()
                    + "','" + service.getBeginDate() + "', '" + service.getFinishDate() + "')";

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
    public ResultRequest<String> update(ServiceDTO service) {
        try {
          
            String update = "UPDATE services SET code='" + service.getName() + "', name='" + service.getCode() + "', type='"
                    + service.getType() + "', maker='" + service.getDescription() + "', description='" + service.getMaker() + "', price="
                    + service.getPrice() + ", serviceid='" + service.getServiceId() + "', periodicity='" + service.getPeriodicity()
                    + "', conditions='" + service.getConditions() + "', startDate='" + service.getBeginDate()
                    + "', finishDate='" + service.getFinishDate() + "' WHERE code='" + service.getName() + "'";
            System.out.println(update);
            ResultRequest<Json> result = this.connection.writeQuery(update);
            return ResultRequest.done("\"Actualizado\"");
        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\"");
        }
    }

    @Override
    public ResultRequest<String> deleteByID(String id) {
        try {
            this.result = this.connection.writeQuery("DELETE FROM services WHERE serviceId='" + id + "'");

            if (this.result.failed()) {
                return ResultRequest.fails("\"Error\"");
            } else {
                return ResultRequest.done("\"Eliminar\"");
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
