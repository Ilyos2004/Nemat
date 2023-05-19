package commands;

import com.diogonunes.jcolor.Attribute;
import objectResAns.ObjectResAns;
import statics.Static;
import сlasses.Organization;

import java.util.HashSet;

public class RemoveByIdCommand implements Command{

    private String name = "remove_by_id";

    @Override
    public ObjectResAns doo(HashSet<Organization> mySet, String s) {
        String[] xY = s.split(" ");
        Object bb = null;
        boolean b = false;
        boolean isPr = false;
        String allres = "";
        if(Static.isPrint == 0){
            isPr = true;
            Static.isPrint = 1;
        }else {
            isPr = false;
        }
        try {
            if (Integer.parseInt(xY[1]) >= 0) {
                if (mySet.stream().map(p -> p.getId() == Integer.parseInt(xY[1])).count() >= 1) {
                    b = true;
                }
            }
            if(b == true){
                mySet.stream().map(p -> p.getId() == Integer.parseInt(xY[1])).forEach(mySet::remove);
                if(isPr){
                    Static.isPrint = 0;
                }
                return new ObjectResAns(Static.txt("Объект удалено!\n"), true);
            }else{
                if(isPr){
                    Static.isPrint = 0;
                }
                return new ObjectResAns(Static.txt("Нету такого Объекта!\n"), true);
            }
        } catch (Exception e) {
            if(isPr){
                Static.isPrint = 0;
            }
            return new ObjectResAns("Ошибка формата!\n", true);
        }
    }

    @Override
    public String des() {
        return "remove_by_id id: удалить элемент из коллекции по его id";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
