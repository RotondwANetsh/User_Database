import java.util.ArrayList;
import java.util.regex.Pattern;

public class Validate {
    public void nameValid(String name) throws ThrowException {
        if(!Pattern.matches("[a-zA-Z]+$", name)){
            throw new ThrowException("");
        }
    }

    public void surnameValid(String surname) throws ThrowException {
        if(!Pattern.matches("[a-zA-Z]+$", surname)){
            throw new ThrowException("");
        }
    }

    public void emailValid(String email) throws ThrowException {
        if(!Pattern.matches("[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\""
                + "[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$", email)){
            throw new ThrowException("");
        }
    }
    public void listOfUsers(ArrayList<String> arrayList) throws ThrowException{
        ArrayList<String> userNames = arrayList;
        if (userNames.size() == 0){
            throw new ThrowException("");
        }
    }
}
