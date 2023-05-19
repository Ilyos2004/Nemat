package commands;

import com.diogonunes.jcolor.Attribute;
import objectResAns.ObjectResAns;
import org.apache.tools.ant.taskdefs.condition.Or;
import statics.Static;
import сlasses.Organization;

import java.util.HashSet;
import java.util.OptionalDouble;

public class AverageCommand implements Command{

    private String name = "average_of_annual_turnover";

    @Override
    public ObjectResAns doo(HashSet<Organization> mySet, String s) {
        OptionalDouble av = OptionalDouble.of(0);
        if(mySet.size() > 0) {
            av = mySet.stream().mapToDouble(Organization::getAnnualTurnover).average();
            return new ObjectResAns(Static.txt("average of annual turnover: " + av.toString() + "\n"), true);
        }else {
            return new ObjectResAns(Static.txt("Коллекция пуста!\n"), true);
        }
    }

    @Override
    public String des() {
        return "average_of_annual_turnover : среднее значение поля annualTurnover для всех элементов коллекции";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
