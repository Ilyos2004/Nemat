package commands;

import com.diogonunes.jcolor.Attribute;
import objectResAns.ObjectResAns;
import statics.Static;
import сlasses.Organization;

import java.util.HashSet;

public class NewCommand implements Command{

    private String name;
    public NewCommand(String name){
        this.name = name;
    }

    @Override
    public ObjectResAns doo(HashSet<Organization> mySet, String s) {
        return new ObjectResAns(Static.txt(this.des() + "\n"), true);
    }

    @Override
    public String des() {
        return (this.name + " : simple command");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
