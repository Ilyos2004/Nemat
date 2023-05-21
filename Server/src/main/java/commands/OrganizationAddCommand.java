package commands;

import com.diogonunes.jcolor.Attribute;
import objectResAns.ObjectResAns;
import statics.Static;
import сlasses.Address;
import сlasses.Coordinates;
import сlasses.Organization;
import сlasses.OrganizationType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrganizationAddCommand implements Command {
    private String name = "add";

    @Override
    public ObjectResAns doo(HashSet<Organization> mySet, String s) {
        String nm;
        Long crX = 0L;
        float crY = 0f;
        Float annualTurnover = 0F;
        String street = "", zipCode = "";
        boolean isPr = false;
        if (Static.isPrint == 0) {
            isPr = true;
            Static.isPrint = 1;
        } else {
            isPr = false;
        }

        Pattern pt = Pattern.compile("\"([^\"]*)\"");
        Matcher mt = pt.matcher(s);
        List<String> dt = new ArrayList<>();
        while(mt.find()){
            dt.add(mt.group(1));
        }

        if (dt.size() >= 7) {
            String nameNew = dt.get(0);
            Coordinates cr = new Coordinates(Long.parseLong(dt.get(1)), Float.parseFloat(dt.get(2)));
            /*OrganizationType o_type = ortp.getTypeById(Integer.parseInt(dt.get(6)));*/
            Float anT = Float.parseFloat(dt.get(3));
            OrganizationType ortp = OrganizationType.PUBLIC;
            OrganizationType o_type = ortp.getTypeByName(dt.get(4).toUpperCase());
            Address ad = new Address(dt.get(5), dt.get(6));
            Organization data = null;
            try {
                data = new Organization(nameNew, cr, anT, o_type, ad);
                mySet.add(data);
                if (isPr) {
                    Static.isPrint = 0;
                }
                return new ObjectResAns("Организация " + nameNew + " добавлена!\n", true);
            } catch (IOException e) {
                return new ObjectResAns("Данные Организации Некорректно!\n", false);
            }
        } else {
            if (isPr) {
                Static.isPrint = 0;
            }
            return new ObjectResAns("Название Организации Некорректно!\n", false);
        }
    }

    @Override
    public String des() {
        return "add element_name : добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
