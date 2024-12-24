import ApiClient.ClientMain;

import java.io.Console;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        //ApiServer as = new ApiServer();
        //as.StartServer();
        SomeInterface();
        ClientMain ac = new ClientMain();
        StringBuilder content = ac.SendRequest("Test");
        System.out.println(content);
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
                    throw new RuntimeException();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}