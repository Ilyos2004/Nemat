package clientMng;

import com.diogonunes.jcolor.Attribute;
import commands.ExecuteScriptCommand;
import commands.OrganizationAddCommand;
import commands.UpdateByIdCommand;
import objectResAns.ObjectResAns;
import org.gradle.internal.impldep.com.google.api.services.storage.Storage;
import statics.Static;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

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

                    ObjectResAns serverResponse = (ObjectResAns) in.readObject();
                    System.out.println(serverResponse.getResTesxt());

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
                    System.out.print(">>> ");
                    String line = snr.nextLine();
                    ObjectResAns response = null;

                    if(line.split(" ")[0].equals("add")){
                        OrganizationAddCommand ex = new OrganizationAddCommand();
                        response = ex.doo(null, line);
                    }else if(line.split(" ")[0].equals("execute_script")){
                        Static.execute = null;
                        Static.execute.add(line.split(" ")[1]);
                        ExecuteScriptCommand ex = new ExecuteScriptCommand();
                        response = ex.doo(null, line);
                    }else if(line.split(" ")[0].equals("update")){
                        UpdateByIdCommand up = new UpdateByIdCommand();
                        response = up.doo(null, line, socket, out, in);
                    }else {
                        // Создаем и отправляем объект Res на сервер
                        response = new ObjectResAns(line, true);
                    }
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

                    if(line.equals("exit")){
                        System.exit(0);
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