package securityservices.infrastructure.db.connectors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.shared.responses.ResultRequest;

public class JdbcConnector implements PersistenceConnector {

    public JdbcConnector() {
    }
    
    private Connection connect = null;
    private Statement stat;
    private Boolean stablished = true;
    private ResultSetMetaData mData;
    private int cols;
    private ResultSet result;
    
  
    Json jArr = JsonObjectFactory.getInstance();
    Json jresp = JsonObjectFactory.getInstance();
    Json jconnect = JsonObjectFactory.getInstance();
    Json jObject = JsonObjectFactory.getInstance();
    
    public JdbcConnector(Json dataconnex) {
        if (this.connect(dataconnex).failed()) {
            this.stablished = false;
        } else {
            this.stablished = true;
        }
    }

    @Override
    public ResultRequest<Json> connect(Json dataConnect) {
        if (dataConnect == null) {
            return ResultRequest.fails("{\"Error\":\"Invalid dataConnect\"}");
        } else {
            try {
             
                String jdbc = dataConnect.get("jdbc");
                String serv = dataConnect.get("serv");
                String port = dataConnect.get("port");
                String dbname = dataConnect.get("dbname");
                String user = dataConnect.get("user");
                String pass = dataConnect.get("pass");
            
                if (jdbc == null || jdbc.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Jdbc Invalido\"}");
                }
                if (serv == null || serv.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Server Invalido\"}");
                }
                if (port == null || port.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Port Invalido\"}");
                }
                if (dbname == null || dbname.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Database Invalido\"}");
                }
                if (user == null || user.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Username Invalido\"}");
                }
                if (pass == null || pass.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Password Invalido\"}");
                }
             
                Class.forName("org." + jdbc + ".Driver");
                this.connect = DriverManager.getConnection("jdbc:" + jdbc + "://" + serv + ":" + port + "/"
                        + dbname + "?" + "user=" + user + "&password=" + pass + "");
                this.stat = connect.createStatement();
                jconnect.set("conexion","Conexion establecida correctamente");
                return ResultRequest.done(jconnect);
            } catch (Exception ex) {
                
                return ResultRequest.fails("{\"Error\":\"Connection Exception\"}" + ex.getMessage());
            }
        }

    }

    @Override
    public Boolean isConnect() {
        return stablished;
    }

    @Override
    public ResultRequest<Json> readQuery(String query) {
        try {
            this.result = this.stat.executeQuery(query);
            this.mData = this.result.getMetaData();
            cols = mData.getColumnCount();

            while (result.next()) {
                jObject.removeAll();
                for (int i = 1; i <= cols; i++) {
                    jObject.set(mData.getColumnName(i), result.getString(i));
                }
                jArr.set("Contenido del Array de la clase", jObject);
            }
        } catch (Exception ex) {
         
            return ResultRequest.fails("{\"Error\":\"Establece la conexion para poder realizar lecturas de queries\"}");
        }

        return ResultRequest.done(jArr);

    }
        public ResultRequest<Json> unconnect() {
        try {
            jconnect.removeAll();
            jconnect.set("{\"Desconnexion\":\"Tu sesion fue cerrada correctamente\"}");
            this.connect.close();
            return ResultRequest.done(jconnect);
        } catch (Exception ex) {
            return ResultRequest.fails("{\"Error\":\"Error en la desconexion\"}");
        }
    }

    @Override
    public ResultRequest<Json> writeQuery(String query) {

        try {
            this.stat.executeUpdate(query);
            jresp.set("{\"Insertado correctamente\":\"Insercion realizada correctamente\"}");

            return ResultRequest.done(jresp);
        } catch (Exception ex) {
            
            return ResultRequest.fails("{\"Error\":\"Establece la conexion para poder insetar queries\"} " + ex.toString());
        }
    }

}
