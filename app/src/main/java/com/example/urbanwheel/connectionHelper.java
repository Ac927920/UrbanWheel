package com.example.urbanwheel;
import  android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectionHelper {
    Connection connection;
    String ip, port, db, un, pass;

    @SuppressLint("NewApi")
    public Connection conclass(){
        ip="127.0.0.1";
        port ="8";
        db ="urbanwheel_vehicle_details";
        un ="";
        pass ="";
        StrictMode.ThreadPolicy tpolicy =  new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tpolicy);
        Connection con = null;
        String ConnectionURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databaseName="+db+";user="+un+"password="+pass+";";
            con = DriverManager.getConnection(ConnectionURL);

        }
        catch (Exception e){
            Log.e("Error: ", e.getMessage());
        }
        return con;
    }
}
