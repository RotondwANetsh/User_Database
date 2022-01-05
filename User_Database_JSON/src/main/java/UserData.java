import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserData {
    Scanner sc = new Scanner(System.in);
    String name, surname, email, DoB;

    public void addUser(ArrayList<String> name, ArrayList<String> surname, ArrayList<String> email, ArrayList<String> DoB, String file) throws IOException {
        ArrayList<ArrayList<String>> userData = new ArrayList<>();
        try {
            userData = JSONSerialization.deserializeFromFile(file,ArrayList.class);
        }catch (Exception e){
            System.out.println("");
        }finally {
            if (userData.size() == 0){
                userData.add(name);
                userData.add(surname);
                userData.add(email);
                userData.add(DoB);
            }
            else {
                name = userData.get(0);
                surname = userData.get(1);
                email = userData.get(2);
                DoB = userData.get(3);
            }
            String userName = getName();
            name.add(userName);
            String userSurname = getSurname();
            surname.add(userName);
            String userEmail = getEmail();
            email.add(userName);
            String userDoB = getDoB();
            DoB.add(userName);
            JSONSerialization.serialiseToFile(userData,file);
            int year = Integer.parseInt(String.valueOf(DoB).split("/")[2]);
            int age = 2022 - year;
            System.out.println("Hello " + name + " " + surname + " your details have been saved.");
            System.out.println("You are " + age + " years old");

        }
    }

    public void updateUserData(ArrayList<String> name, ArrayList<String> surname, ArrayList<String> email, ArrayList<String> DoB, String file) throws IOException{
        int choiceUpdate;
        ArrayList<ArrayList<String>> userDatabase;
        String userName;
        String userSurname;
        String emailAddress;
        String dob;
        try {
            userDatabase = JSONSerialization.deserializeFromFile(file, ArrayList.class);
            name = userDatabase.get(0);
            surname = userDatabase.get(1);
            email = userDatabase.get(2);
            dob = String.valueOf(userDatabase.get(3));

        }
        catch (Exception e){
            System.out.println();
        }
    }

    public void deleteUserData(ArrayList<String> name, ArrayList<String> surname,ArrayList<String> email,ArrayList<String> dateOfBirth, String file) throws ThrowException{
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<String>> userDatabase;
        try{
            userDatabase = JSONSerialization.deserializeFromFile(file,ArrayList.class);
            name = userDatabase.get(0);
            surname = userDatabase.get(1);
            email = userDatabase.get(2);
            dateOfBirth = userDatabase.get(3);
            sizeOfList(email);
            System.out.print("Enter your email: ");
            String emailAddress = sc.nextLine();
            int temp = email.size();
            for (int i = 0; i < email.size(); i++) {
                if (email.equals(email.get(i))){
                    String tempName = name.get(i);
                    String tempSurname = surname.get(i);
                    name.remove(i);
                    surname.remove(i);
                    email.remove(i);
                    dateOfBirth.remove(i);

                    System.out.println(tempName + " " + tempSurname + " has been removed from database.");
                    i--;
                }
                JSONSerialization.serialiseToFile(userDatabase,file);
            }
            if (temp == email.size()){
                System.out.println("User not found");
            }
        }
        catch (Exception e){
            System.out.println("User not found in the database.");
        }
    }

    public void listUsers(ArrayList<String> name, ArrayList<String> surname,ArrayList<String> emails,ArrayList<String> dateOfBirth, String file) throws IOException, ClassNotFoundException{
        ArrayList<ArrayList<String>> userDatabase;
        try{
            userDatabase = JSONSerialization.deserializeFromFile(file, ArrayList.class);
            name = userDatabase.get(0);
            surname = userDatabase.get(1);
            emails = userDatabase.get(2);
            dateOfBirth = userDatabase.get(3);
            UserData userData = new UserData();
            userData.sizeOfList(emails);
            for (int i = 0; i < name.size(); i++) {
                System.out.println(name.get(i) + " " + surname.get(i) + " " + emails.get(i) + " " + dateOfBirth.get(i));

            }
        }
        catch (Exception e){
            System.out.println("No user found.");
        }
    }

    public String getName() {
        while(true){
            System.out.print("Enter your name: ");
            name = sc.nextLine();
            try {
                Validate validate = new Validate();
                validate.nameValid(name);
                return name;
            }
            catch (Exception e){
                System.out.println("Name not valid.");
            }
        }
    }

    public String getSurname() {
        while (true){
            System.out.print("Enter your surname: ");
            name = sc.nextLine();
            try {
                Validate validate = new Validate();
                validate.surnameValid(surname);
                return surname;
            }
            catch (Exception e){
                System.out.println("Surname not valid.");
            }
        }
    }

    public String getEmail() {
        while (true){
            System.out.print("Enter email address: ");
            email = sc.nextLine();
            try {
                Validate validate = new Validate();
                validate.emailValid(email);
                return email;
            }
            catch (Exception e){
                System.out.println("Email address not valid.");
            }
        }
    }

    public String getDoB() {
        String dob;
        int year;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd//MM/yyyy");
        dateFormat.setLenient(false);
        while (true){
            System.out.print("Enter your date of birth eg. dd/MM/yyyy:");
            dob = sc.nextLine();

            try {
                Date date = dateFormat.parse(dob);
                if (dob.length() == 10){
                    year = Integer.parseInt(dob.split("/")[2]);
                    if (year < 2022){
                        return dob;
                    }
                    else {
                        System.out.println("Date cannot be in future.");
                    }
                }
                else {
                    System.out.println("Date entered not valid e.g dd/MM/yyyy");
                }
            }catch (Exception e){
                System.out.println("Date of birth is not valid.");
            }
        }
    }

    public void sizeOfList(ArrayList<String> arrayList) throws ThrowException{
        ArrayList<String> arrList = arrayList;

        if (arrList.size() == 0){
//            throw ThrowException;
        }
    }
}
