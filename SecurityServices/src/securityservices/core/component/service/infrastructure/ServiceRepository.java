package securityservices.core.component.service.infrastructure;

import securityservices.core.component.service.domain.serializers.ServiceDTO;
import java.util.List;
import securityservices.shared.responses.ResultRequest;

public interface ServiceRepository {
 public ResultRequest<List<ServiceDTO>> getAll();
 public ResultRequest<ServiceDTO> getByID (String id);
 public ResultRequest<String> add(ServiceDTO service);
 public ResultRequest<String> update(ServiceDTO service);
 public ResultRequest<String> deleteByID(String id);
 public Boolean exists(String id);
}
