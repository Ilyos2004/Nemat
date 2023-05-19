package commands;

import dataParseIng.CsvJson;
import dataParseIng.ParseIng;
import objectResAns.ObjectResAns;
import statics.Static;
import сlasses.Organization;

import java.io.IOException;
import java.util.HashSet;

public class CngSertpCSV implements Command{

    private String name = "change_serialization_type_CSV";
    @Override
    public ObjectResAns doo(HashSet<Organization> mySet, String s) {
        if(Static.isCsv != 1){
            Static.isCsv = 1;
            ParseIng parseCol = new ParseIng();
            CsvJson csvJson = new CsvJson();
            SaveCommand svCmd = new SaveCommand();
            try {
                HashSet<Organization> tmp = parseCol.getOrganizationFromJson();
                svCmd.doo(tmp, s);
                csvJson.saveIsCsv(Static.isCsv);
                return new ObjectResAns(Static.txt("Converted Serialization Type to CSV!\n"), true);
            } catch (IOException e) {
                return new ObjectResAns(Static.txt("Error!\n"), false);
            }
        }else {
            return new ObjectResAns(Static.txt("Serialization Type is CSV!\n"), true);
        }
    }

    @Override
    public String des() {
        return "change_serialization_type_CSV : Convert Serialization Type to CSV";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
