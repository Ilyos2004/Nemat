package clientMng;

import objectResAns.ObjectResAns;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    private static int port = 3909;
    public static void main(String[] args) throws Exception {
        boolean b = false;
        Socket socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Scanner snr = null;
        int tmp = 0;
        System.out.println("Подключение к серверу...");

        while (true) {
            if(!b) {
                try {
                    // Устанавливаем соединение с сервером
                    socket = new Socket("localhost", port);
                    System.out.println("Я подключилься к серверу " + socket.getInetAddress());

                    // Получаем потоки ввода-вывода для обмена данными с сервером
                    out = new ObjectOutputStream(socket.getOutputStream());
                    in = new ObjectInputStream(socket.getInputStream());
                    snr = new Scanner(System.in);
                    b = true;
                }catch (Exception e){
                    tmp = tmp + 1;
                    if(tmp >= 99999){
                        System.err.println("Ошибка Сервера! Повторное подключение...");
                        tmp = 0;
                    }
                }
            }

            while (b) {
                try {
                    String line = snr.nextLine();

                    // Создаем и отправляем объект Res на сервер
                    ObjectResAns response = new ObjectResAns(line, true);
                    out.writeObject(response);

                    ObjectResAns serverResponse = null;
                    try {
                        // Читаем ответ от сервера
                        serverResponse = (ObjectResAns) in.readObject();
                        //System.out.println("Ответ от сервера: " + serverResponse.getResTesxt());
                        System.out.println(serverResponse.getResTesxt());
                    } catch (ClassNotFoundException e) {
                        System.err.println("Ошибка при чтении объекта: " + e.getMessage());
                    }
                }catch (Exception e){
                    System.err.println("Ошибка Сервера! Повторное подключение...");
                    socket.close();
                    b = false;
                }
            }
        }
    }
}