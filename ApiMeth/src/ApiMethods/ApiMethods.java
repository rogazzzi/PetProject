package ApiMethods;

import DBAPIConnect.BDAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiMethods {
    String conString = "";
    StringBuilder content = new StringBuilder();
    HttpURLConnection con ;
    final int TIME_OUT_TIME = 30;
    public StringBuilder GetRequest (String Method){
        if (Method == "Test"){
            conString = GetConnectData();
            return TestMeth();
        }
        else if (Method == "DBConnectionTest"){
            conString = GetConnectData();
            return DBConTest(Method);
        }
        else {
            return new StringBuilder("Method not found");
        }
    }






    ///Обработка методов
    private StringBuilder TestMeth(){
        System.out.println("You are here!");
        conString = GetConnectData();
        try{
            con = TryConnect();
            JSONCreate();
            final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            return ReadJson(in);

        } catch (Exception e) {
            System.out.println("Bad connect!!!");
            throw new RuntimeException(e);
        }
    }

    private StringBuilder DBConTest(String Method) {

        System.out.println("You DBConTest now!");
        try{
            System.out.println("Try to connect");
            BDAPI db = new BDAPI();
            db.DBAPIConnect(Method);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new StringBuilder();
    }


    ///Дополнительные функции
    private String GetConnectData(){
        connectData cd = new connectData();
        return cd.GetConnectionData();
    }
    private StringBuilder ReadJson(BufferedReader in){


        String inputLine;
        final StringBuilder content = new StringBuilder();
        while (true){
            try {
                if (!((inputLine = in.readLine()) !=null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            content.append(inputLine);
        }

        return content;
    }
    private HttpURLConnection TryConnect(){
        System.out.println("Try to connect");
        try {
            final URL url = new URL("http://"+conString);
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            System.out.println("Nice connect!");
            return con;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void JSONCreate (){
        try {
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-type","application/json");
            con.setConnectTimeout(TIME_OUT_TIME);
            con.setReadTimeout(TIME_OUT_TIME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
