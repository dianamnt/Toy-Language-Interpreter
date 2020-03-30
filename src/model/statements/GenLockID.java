package model.statements;

public class GenLockID {
    private static int number=-1;
    public static  int getID(){
        return number++;
    }
}
