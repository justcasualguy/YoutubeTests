import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("C:\\Users\\jakub.chwesiuk\\Desktop\\vid.mp4".split("\\\\"));
        for(String s:"C:\\Users\\jakub.chwesiuk\\Desktop\\vid.mp4".split("\\\\"))
            System.out.println(s);
    }
}
