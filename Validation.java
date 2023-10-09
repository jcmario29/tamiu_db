import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public Validation(){

    }

    public boolean validatePassword(String sPassword){
        //System.out.println(Pattern.matches("[a-zA-Z0-9]{8}", sPassword));
        boolean matchFound = Pattern.matches("[a-zA-Z0-9]{8}", sPassword);
        return matchFound;
    }

}
