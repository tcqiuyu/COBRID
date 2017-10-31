package edu.colostate.ember.util;

public class LogUtil {

    public static void printErr(String msg) {
        System.out.println(StaticFields.ANSI_RED + msg + StaticFields.ANSI_RESET);
    }
}
