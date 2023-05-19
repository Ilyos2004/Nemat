package commands;

import dataParseIng.CsvJson;
import dataParseIng.ParseIng;
import objectResAns.ObjectResAns;
import statics.Static;
import сlasses.Organization;

import java.io.IOException;
import java.util.HashSet;

public class CngSertpJSON implements Command{

    private String name = "change_serialization_type_JSON";

    @Override
    public ObjectResAns doo(HashSet<Organization> mySet, String s) {
        if(Static.isCsv != 0){
            Static.isCsv = 0;
            ParseIng parseCol = new ParseIng();
            SaveCommand svCmd = new SaveCommand();
            try {
                HashSet<Organization> tmp = parseCol.getOrganizationFromCsv();
                svCmd.doo(tmp, s);
                CsvJson csvJson = new CsvJson();
                csvJson.saveIsCsv(Static.isCsv);
                return new ObjectResAns(Static.txt("Converted Serialization Type to JSON!\n"), true);
            } catch (IOException e) {
                return new ObjectResAns(Static.txt("Error!\n"), false);
            }
        }else {
            return new ObjectResAns(Static.txt("Serialization Type is JSON!\n"), true);
        }
    }

    @Override
    public String des() {
        return "change_serialization_type_JSON : Convert Serialization Type to JSON";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
