package securityservices.operations;

import securityservices.products.Storable;

public interface Transportable extends Storable {
    public String getDeliveryAddress();
    public String getReceiverName();
    public String getTransporter();
}
