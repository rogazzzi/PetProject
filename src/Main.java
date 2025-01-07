import ApiClient.ClientMain;
import ApiServerModule.StartServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static boolean stop_server = false;
    public static void main(String[] args) {
        // get started server api on new thread
        Thread server_thread = new Thread(new StartServer());
        do {
            if (!server_thread.isAlive()) {
                server_thread.start();
            }
            SomeInterface(server_thread);
        }
        while (!stop_server);

    }

    public static void SomeInterface(Thread server_thread) {

        if (server_thread.isAlive()) {
            System.out.println("1 - Test; 2 - TestDB; 3 - stop server");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int some = Integer.parseInt(reader.readLine());
                if (some == 1) {
                    ClientMain ac = new ClientMain();
                    StringBuilder content = ac.SendRequest("Test");
                    System.out.println(content);
                } else if (some == 2) {
                    ClientMain ac = new ClientMain();
                    StringBuilder content = ac.SendRequest("DBConnectionTest");
                    System.out.println(content);
                } else if (some == 3) {
                    server_thread.interrupt();
                    stop_server = true;
                    System.out.println("Server stopped, bb!");
                } else {
                    System.out.println("404 method " + some);
                }
            } catch (IOException e) {
                System.out.println("why i here??");
                //throw new RuntimeException(e);
            }
        }
    }

}