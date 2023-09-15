package aclij.pio.waitForAnswer;

import aclij.pio.coordinates.Coordinates;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleResponse implements WaitForResponse{
    Scanner scanner = new Scanner(System.in);
    @Override
    public Coordinates getNextStep() {
        return simpleConsoleResponse();
    }

    private Coordinates simpleConsoleResponse() {
        String str = scanner.nextLine().toUpperCase();
        Matcher matcher = Pattern.compile("^([A-H][1-8])$").matcher(str);
        if(matcher.matches()){
            return Coordinates.valueOf(matcher.group(0));
        }
        return null;
    }
    private Coordinates simpleDoubleConsoleResponse(){
        String str = scanner.nextLine().toUpperCase();
        Matcher matcher = Pattern.compile("^([A-H][1-8]){2}$").matcher(str);
        if(matcher.matches()){
            return Coordinates.valueOf(matcher.group(0));
        }
        return null;
    }
}
