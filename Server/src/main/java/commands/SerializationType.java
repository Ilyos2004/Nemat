package commands;

import com.diogonunes.jcolor.Attribute;
import objectResAns.ObjectResAns;
import statics.Static;
import сlasses.Organization;

import java.util.HashSet;

public class SerializationType implements Command{

    private String name = "Serialization_type";
    @Override
    public ObjectResAns doo(HashSet<Organization> mySet, String s) {
        if(Static.isCsv == 1){
            return new ObjectResAns(Static.txt("CSV\n"),true);
        } else{
            return new ObjectResAns(Static.txt("JSON\n"),true);
        }
    }

    @Override
    public String des() {
        return "Serialization_type: Current Serialization_type";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
