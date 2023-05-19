package serverMng;

import commands.Command;
import commands.Commands;
import commands.InfoCommands;
import commands.SaveCommand;
import mainProgram.Main;
import objectResAns.ObjectResAns;
import org.apache.groovy.json.internal.IO;
import statics.Static;
import сlasses.Organization;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class Server {
    private static int port = 3909;
    public static void main(String[] args) throws IOException {
        Map<String, Command> listCommand = new LinkedHashMap<String, Command>();
        listCommand.put(new InfoCommands().getName(), new InfoCommands());
        Main ker = new Main();

        boolean b = false;
        Socket socket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        ServerSocket serverSocket = null;
        HashSet<Organization> mySet = null;
        String allRes = null;
        HashMap<String, HashSet<Organization>> mainRes = null;
        mainRes = ker.allCmd();
        for(String s: mainRes.keySet()){
            allRes = s;
        }
        mySet = mainRes.get(allRes);
        Commands cmd = new Commands();
        HashSet<Organization> finalMySet = mySet;

        new Thread(() -> {
            Scanner s = new Scanner(System.in);
            if(s.nextLine().equals("exit")){
                System.out.println("Выход!...");
                System.exit(0);
            }
            if(s.nextLine().equals("save")){
                SaveCommand sv = new SaveCommand();
                System.out.println(sv.doo(finalMySet, "save").getResTesxt());
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
            try {
                // Ожидаем подключение клиента
                if (!b) {
                    socket = serverSocket.accept();
                    System.out.println("Клиент " + socket.getInetAddress() + " подключился.");
                    // Получаем потоки ввода-вывода для обмена данными с клиентом
                    in = new ObjectInputStream(socket.getInputStream());
                    out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectResAns response = new ObjectResAns(Static.txt(allRes), true);
                    out.writeObject(response);
                    b = true;
                }
            }catch (IOException e) {
                continue;
            }
            try {
                while (b) {
                    ObjectResAns clientRequest = null;

                    // Читаем запрос от клиента
                    clientRequest = (ObjectResAns) in.readObject();
                    System.out.println("Запрос от клиента: " + clientRequest.getResTesxt());

                    // Создаем и отправляем объект Res в ответ клиенту
                    ObjectResAns response = cmd.commandsEditor(mySet, clientRequest.getResTesxt());
                    //ObjectResAns response = new ObjectResAns(clientRequest.getResTesxt(), true);
                    out.writeObject(response);
                }
            }catch (Exception e){
                System.err.println("Клиент: " + socket.getInetAddress() + " отключилься!");
                socket.close();
                b = false;
            }
        }
    }
}
