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

public class UpdateByIdCommand implements Command {

    private String name = "update";

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

        if (dt.size() >= 8) {
            String nameNew = dt.get(1);
            Coordinates cr = new Coordinates(Long.parseLong(dt.get(2)), Float.parseFloat(dt.get(3)));
            /*OrganizationType o_type = ortp.getTypeById(Integer.parseInt(dt.get(6)));*/
            Float anT = Float.parseFloat(dt.get(4));
            OrganizationType ortp = OrganizationType.PUBLIC;
            OrganizationType o_type = ortp.getTypeByName(dt.get(5).toUpperCase());
            Address ad = new Address(dt.get(6), dt.get(7));
            for(Organization o: mySet){
                if(o.getId() == Integer.parseInt(dt.get(0))){
                    o.setName(nameNew);
                    o.setCoordinates(cr);
                    o.setAnnualTurnover(anT);
                    o.setType(o_type);
                    o.setPostalAddress(ad);
                    return new ObjectResAns("Организация " + nameNew + " изменена!\n", true);
                }
            }
            return new ObjectResAns("Нету такого элемента!\n", true);
        } else {
            if (isPr) {
                Static.isPrint = 0;
            }
            return new ObjectResAns("Данные Организации Некорректно!\n", false);
        }
    }

    @Override
    public String des() {
        return "update id new_element : обновить значение элемента коллекции";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
