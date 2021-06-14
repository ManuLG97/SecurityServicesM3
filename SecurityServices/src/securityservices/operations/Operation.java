package securityservices.operations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import securityservices.shared.responses.ResultRequest;
import securityservices.shared.responses.ResultResponses;

public abstract class Operation implements Billable {

    protected int creator;
    protected double value, surcharges;
    protected String code, type, status, additonalInfo;
    protected LocalDateTime initDate, finishDate;
    protected DateTimeFormatter inputDate=DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy'-'HH:mm:ss");
     protected DateTimeFormatter outputDate=DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy'-'HH:mm:ss");

    public Operation() {
    }

    public Operation(String code, int creator, double value, double surcharges, String type,
            String status, String additonalInfo, String initDate, String finishDate) {
        this.code = code;
        this.creator = creator;
        this.value = value;
        this.surcharges = surcharges;
        this.type = type;
        this.status = status;
        this.additonalInfo = additonalInfo;
        this.setBeginDate(initDate);
        this.setFinishDate(finishDate);
    }

    @Override
    public String getCode() {
        return code;
    }

    public ResultRequest setCode(String code) {
               if (code == null || code.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid code value\"");
        }
        this.code = code;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public int getCreator() {
        return creator;
    }

    public ResultRequest setCreator(int creator) {
            if (creator <= 0) {
            return ResultRequest.fails("\"Error\":\"invalid creator value\"");
        }
        this.creator = creator;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }
    

    public double getValue() {
        return value;
    }

    public ResultRequest setValue(double value) {
         if (value <= 0) {
            return ResultRequest.fails("\"Error\":\"invalid value value\"");
        }
        this.value = value;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public double getSurcharges() {
        return surcharges;
    }

    public ResultRequest setSurcharges(double surcharges) {
         if (surcharges <= 0) {
            return ResultRequest.fails("\"Error\":\"invalid surcharges value\"");
        }
        this.surcharges =surcharges;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdditonalInfo() {
        return additonalInfo;
    }

    public void setAdditonalInfo(String additonalInfo) {
        this.additonalInfo = additonalInfo;
    }

    
    public String getBeginDate() {
        if (initDate != null)
            return initDate.format(outputDate);
        else return "";
    }

    public ResultRequest setBeginDate(String beginDate) {
        try {
            this.initDate = LocalDateTime.parse(beginDate, inputDate);
            return ResultRequest.done(ResultResponses.SUCCESS);
        } catch (Exception e) {
            return ResultRequest.fails("\"Error\":\"invalid begin date: " + e.getMessage() + "\"");
        }
    }

    public String getFinishDate() {
        if (finishDate != null)
            return finishDate.format(outputDate);
        else return "";
    }

    public ResultRequest  setFinishDate(String finishDate) {
        try {
            this.initDate = LocalDateTime.parse(finishDate, inputDate);
            return ResultRequest.done(ResultResponses.SUCCESS);
        } catch (Exception e) {
            return ResultRequest.fails("\"Error\":\"invalid finsh date: " + e.getMessage() + "\"");
        }
    }
}
