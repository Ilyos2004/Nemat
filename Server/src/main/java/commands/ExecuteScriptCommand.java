package commands;

import com.diogonunes.jcolor.Attribute;
import dataParseIng.ParseIng;
import objectResAns.ObjectResAns;
import statics.Static;
import сlasses.Organization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteScriptCommand implements Command {

    private String name = "execute_script";
    ParseIng parseCol = new ParseIng();
    Commands cmd = new Commands();

    @Override
    public ObjectResAns doo(HashSet<Organization> mySet, String s) {
        String allRes = "";
        Commands cmd = new Commands();
        Pattern pt = Pattern.compile("\"([^\"]*)\"");
        Matcher mt = pt.matcher(s);
        List<String> dt = new ArrayList<>();
        while(mt.find()){
            dt.add(mt.group(1));
        }

        for (String tmp: dt) {
            if(!tmp.equals("Рекурсия!") && !tmp.equals("execute_script")) {
                try {
                    allRes = allRes + cmd.commandsEditor(mySet, tmp).getResTesxt();
                }catch (Exception e){
                    allRes = allRes + "Ошибка команды!\n";
                }
            }
            if(tmp.equals("Рекурсия!")){
                allRes = allRes + "Рекурсия!\n";
            }
        }
        return new ObjectResAns(Static.txt(allRes), true);
    }

    @Override
    public String des() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
