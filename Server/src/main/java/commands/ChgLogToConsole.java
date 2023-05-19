package commands;

import com.diogonunes.jcolor.Attribute;
import objectResAns.ObjectResAns;
import statics.Static;
import сlasses.Organization;

import java.util.HashSet;

public class ChgLogToConsole implements Command{
    private String name = "change_print_logic_console";
    @Override
    public ObjectResAns doo(HashSet<Organization> mySet, String s) {
        Static.isPrint = 1;
        return new ObjectResAns(Static.txt("Changed!\n"), true);
    }

    @Override
    public String des() {
        return "change_print_logic_console : печатаеть вывод в консол";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
