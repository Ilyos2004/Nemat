package serverMng;

import objectResAns.ObjectResAns;
import org.apache.groovy.json.internal.IO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    private static int port = 3909;
    public static void main(String[] args) throws IOException {
        boolean b = false;
        Socket socket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        ServerSocket serverSocket = null;

        new Thread(() -> {
            Scanner s = new Scanner(System.in);
            if(s.nextLine().equals("exit")){
                System.exit(0);
            }
        }).start();

        try {
            // Создаем серверный сокет, прослушивающий порт
            serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен.");
            System.out.println("Ожидание подключения клиента...");

        } catch (IOException e) {
            System.err.println("Ошибка при работе сервера: " + e.getMessage());
        }

        while (true) {
            // Ожидаем подключение клиента
            try {
                socket = serverSocket.accept();
                System.out.println("Клиент " + socket.getInetAddress() + " подключился.");
                // Получаем потоки ввода-вывода для обмена данными с клиентом
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                b = true;
            } catch (Exception e) {
                continue;
            }

            while (b) {
                try {
                    ObjectResAns clientRequest = null;

                    // Читаем запрос от клиента
                    socket.setKeepAlive(true);
                    clientRequest = (ObjectResAns) in.readObject();
                    System.out.println("Запрос от клиента: " + clientRequest.getResTesxt());

                    // Создаем и отправляем объект Res в ответ клиенту
                    ObjectResAns response = new ObjectResAns(clientRequest.getResTesxt(), true);
                    out.writeObject(response);
                }catch (Exception e){
                    System.err.println("Клиент: " + socket.getInetAddress() + " отключилься!");
                    b = false;
                }
            }
            socket.close();
        }
    }
}
