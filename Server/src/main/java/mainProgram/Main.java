package mainProgram;

import com.diogonunes.jcolor.Attribute;
import commands.*;
import commandsEditor.CommandNew;
import dataParseIng.CsvJson;
import dataParseIng.ParseIng;
import startMain.Stating;
import statics.Static;
import сlasses.Organization;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * Программа
 */
public class Main {
    public HashMap<String, HashSet<Organization>> allCmd() throws IOException {
        HashSet<Organization> mySet = new HashSet<>();
        ParseIng parseCol = new ParseIng();
        Commands cmd = new Commands();
        SaveCommand svCmd = new SaveCommand();
        ExecuteScriptCommand exSrCmd = new ExecuteScriptCommand();
        CsvJson csvJson = new CsvJson();
        String res = "";

        CommandNew cmdEd = new CommandNew();
        cmdEd.newCommadsReader();

        Stating st = new Stating();
        st.saveDate();

        /*Scanner scr = new Scanner(System.in);
        while (mySet.isEmpty())  {
            System.out.println("Укажите название файла! (data.csv)");
            ParseIng.fileName = ("Datas/" + scr.nextLine());
            try {
                mySet = ParseIng.getOrganizationFromCsv();
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка в файле или неправильный путь!");
            }
        }*/
        Static.fileName = ("Datas/" + "data");
        String outputFileName = Static.fileName;
        boolean isScript = false;

        if(Static.isCsv == 1) {
            try {
                mySet = parseCol.getOrganizationFromCsv();
                res = res + "Готова!\n";
            } catch (FileNotFoundException e) {
                res = res + "Ошибка в файле или неправильный путь!\n";
            }
        }else if(Static.isCsv == 0) {
            try {
                mySet = parseCol.getOrganizationFromJson();
                res = res + "Готова!\n";
            } catch (FileNotFoundException e) {
                res = res + "Ошибка в файле или неправильный путь!\n";
            }
        }
        HashMap<String, HashSet<Organization>> ker = new HashMap<>();
        ker.put(res, mySet);
        return ker;
    }
}