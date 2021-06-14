package securityservices.operations;

public interface Billable {
    
public String getCode();
public String getType();
public int getClient();
public String getIniDate();
public String getFinishDate();
public double getValue();


 
public int getNumDetails ();
public int setDetail (String detail);
public String getDetail (int n);
public String getDetail (String n);
public int updateDetail (int n, String newdetail);
public int updateDetail (String n, String newdetail);
public int deleteDetail (int n);
public int deleteDetail (String n); 

}
