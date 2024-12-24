import ApiClient.ClientMain;
import ApiServerModule.ApiServer;
import ApiServerModule.StartServer;

import java.io.Console;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        // get started server api on new thread
        Thread server_thread = new Thread(new StartServer());
        server_thread.start();
        // get started server api on new thread
        SomeInterface();
        if (server_thread.isAlive()) {
            server_thread.interrupt();
            System.out.println("server shutdown");
        }
        //unused
//        ClientMain ac = new ClientMain();
//        StringBuilder content = ac.SendRequest("Test");
//        System.out.println(content);
        //unused
    }

    public static void SomeInterface(){

        while (true){
            System.out.println("1 - Test; 2- TestDB");
            try {
                int some = System.in.read();
                if (some == 49){
                    ClientMain ac = new ClientMain();
                    StringBuilder content = ac.SendRequest("Test");
                    System.out.println(content);
                } else if (some==50) {
                    ClientMain ac = new ClientMain();
                    StringBuilder content = ac.SendRequest("DBConnectionTest");
                    System.out.println(content);
                }
                else {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}