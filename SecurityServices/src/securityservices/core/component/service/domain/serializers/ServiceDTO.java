package securityservices.core.component.service.domain.serializers;

public class ServiceDTO {

    private final String code, name, type, maker, description, periodicity, conditions, startDate, finishDate, serviceId;
    private final double price;
    
    public ServiceDTO(String code, String name, String type, String maker, String description, double price,
            String periodicity, String conditions, String startDate, String finishDate, String serviceId) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.maker = maker;
        this.description = description;
        this.price = price;
        this.periodicity = periodicity;
        this.conditions = conditions;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.serviceId = serviceId;
        
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getMaker() {
        return maker;
    }
    
    public String getPeriodicity() {
        return periodicity;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getConditions() {
        return conditions;
    }

    public double getPrice() {
        return price;
    }
    
    public String getBeginDate(){
        return startDate;
    }
    public String getFinishDate() {
        return finishDate;
    }
}
