import ApiClient.ClientMain;
import ApiServerModule.StartServer;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        // get started server api on new thread
        Thread server_thread = new Thread(new StartServer());
        // get started server api on new thread
        while (true) {
            SomeInterface(server_thread);
        }
        /*if (server_thread.isAlive()) {
            server_thread.interrupt();
            System.out.println("server shutdown");
        }*/
        //unused
//        ClientMain ac = new ClientMain();
//        StringBuilder content = ac.SendRequest("Test");
//        System.out.println(content);
        //unused
    }

    public static void SomeInterface(Thread server_thread){

        if (server_thread.isAlive()) {
            server_thread.interrupt();
            System.out.println("server shutdown");
        }
        else{
            server_thread.start();
            if (server_thread.isAlive()) {
                System.out.println("server start");
            }
        }
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