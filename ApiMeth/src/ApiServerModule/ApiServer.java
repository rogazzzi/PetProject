package ApiServerModule;


import ApiMethods.ApiMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ApiServer {
    public void StartServer(){
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started!");

            while (true) {
                // ожидаем подключения
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                // для подключившегося клиента открываем потоки
                // чтения и записи
                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter output = new PrintWriter(socket.getOutputStream())) {

                    // ждем первой строки запроса
                    while (!input.ready()) ;

                    // считываем и печатаем все что было отправлено клиентом
                    System.out.println();
                    while (input.ready()) {
                        System.out.println(input.readLine());
                    }
                    ApiMethods am = new ApiMethods();
                    // отправляем ответ
                    try {

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    output.println("HTTP/1.1 200 OK");
                    output.println("Content-Type: application/json; charset=utf-8");
                    output.println();
                    output.println("Подключение успешно!");
                    //output.flush();

                    // по окончанию выполнения блока try-with-resources потоки,
                    // а вместе с ними и соединение будут закрыты
                    //System.out.println("Client disconnected!");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
