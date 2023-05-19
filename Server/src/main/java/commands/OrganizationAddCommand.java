package commands;

import com.diogonunes.jcolor.Attribute;
import objectResAns.ObjectResAns;
import statics.Static;
import сlasses.Address;
import сlasses.Coordinates;
import сlasses.Organization;
import сlasses.OrganizationType;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

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

        String[] dt = s.split(" ");
        if (dt.length >= 8) {
            String nameNew = dt[1];
            Coordinates cr = new Coordinates(Long.parseLong(dt[2]), Float.parseFloat(dt[3]));
            /*OrganizationType o_type = ortp.getTypeById(Integer.parseInt(dt.get(6)));*/
            Float anT = Float.parseFloat(dt[4]);
            OrganizationType ortp = OrganizationType.PUBLIC;
            OrganizationType o_type = ortp.getTypeByName(dt[5].toUpperCase());
            Address ad = new Address(dt[6], dt[7]);
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
            return new ObjectResAns("Название Организации Некорректно!", false);
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
