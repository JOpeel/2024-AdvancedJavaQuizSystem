package uk.ac.ncl.csc8404quizsystem.classes;

public class System {
    private static final System SYSTEM = new System();

    private System(){}

    public static System getInstance(){
        return SYSTEM;
    }

}
