package securityservices.core.component.order.domain.services;

public class OrderDTO {
private final String code, type, status, additonalInfo, beginDate, finishDate, paymentType, paymentDate;
private final int creator;
private final double value, surcharges;
    
    public OrderDTO(String code, String type, String status, String additonalInfo, String beginDate, String finishDate, String paymentType, String paymentDate, Integer creator, double value, double surcharges) {
        this.code = code;
        this.type = type;
        this.status = status;
        this.additonalInfo = additonalInfo;
        this.beginDate = beginDate;
        this.finishDate = finishDate;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.creator = creator;
        this.value = value;
        this.surcharges = surcharges;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getAdditonalInfo() {
        return additonalInfo;
    }


    public String getBeginDate() {
        return beginDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public Integer getCreator() {
        return creator;
    }

    public double getValue() {
        return value;
    }

    public double getSurcharges() {
        return surcharges;
    }
    
    
}
