package commands;

import objectResAns.ObjectResAns;
import сlasses.Organization;

import java.util.HashSet;

public interface Command {
    ObjectResAns doo(HashSet<Organization> mySet, String s);
    String des();
    String getName();
}
