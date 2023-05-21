package commands;

import objectResAns.ObjectResAns;
import сlasses.Organization;

import java.io.IOException;
import java.util.HashSet;

public interface Command {
    ObjectResAns doo(HashSet<Organization> mySet, String s) throws IOException;
    String des();
    String getName();
}
