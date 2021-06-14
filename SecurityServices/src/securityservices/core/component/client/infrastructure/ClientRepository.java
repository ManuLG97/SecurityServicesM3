package securityservices.core.component.client.infrastructure;

import java.util.List;
import securityservices.core.component.client.domain.services.ClientDTO;
import securityservices.shared.responses.ResultRequest;

public interface ClientRepository {
 public ResultRequest<List<ClientDTO>> getAll();
 public ResultRequest<ClientDTO> getByID (String id);
 public ResultRequest<String> add(ClientDTO client);
 public ResultRequest<String> update(ClientDTO client);
 public ResultRequest<String> deleteByID(String id);
 public Boolean exists(String id);
}
