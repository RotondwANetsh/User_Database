import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> firstName = new ArrayList<>();
        ArrayList<String> lastName = new ArrayList<>();
        ArrayList<String> emailAddress = new ArrayList<>();
        ArrayList<String> dateOfBirth = new ArrayList<>();
        String file = "\\User_Database\\User_Database_Serialization\\src\\main\\java";

        // While loop that keeps the program running until the user terminates
        // Menu options for the user on what to do
        while (true){
            int option = getMenu();

            if(option == 1){        // Adds user
                addUser(firstName,lastName,emailAddress,dateOfBirth,file);
            }
            else if (option == 2){  // Update user
                updateUser(firstName,lastName,emailAddress,dateOfBirth,file);
            }
            else if (option == 3){  // Delete user
                deleteUser(firstName,lastName,emailAddress,dateOfBirth,file);
            }
            else if (option == 4){  // List all users
                listAllUsers(firstName,lastName,emailAddress,dateOfBirth,file);
            }
            else if (option == 5){  // Terminate the program
                break;
            }
        }
    }

    // Display menu to the user that promts the user for what to perform
    public static int getMenu() {
        int option;
        // Display menu to user
        while (true){
            System.out.println("Select Option\n" +
                    "1. Add User\n" +
                    "2. Update User\n" +
                    "3. Delete User\n" +
                    "4. List Users\n" +
                    "5. Exit");
            try {
                option = sc.nextInt();
                break;
            }
            catch (Exception e){
                System.out.println("Option not valid");
                sc.next();
            }
        }

        return 0;
    }

    // Add user to database
    public static void addUser(ArrayList<String> firstName, ArrayList<String> lastName, ArrayList<String> emailAddress, ArrayList<String> dateOfBirth, String file) throws IOException, ClassNotFoundException{
        ArrayList<ArrayList<String>> userDatabase;
        userDatabase = (ArrayList<ArrayList<String>>) SerializationHelper.deserializeFromFile(file);
        if (userDatabase.get(0).size() == 0){
            userDatabase.add(firstName);
            userDatabase.add(lastName);
            userDatabase.add(emailAddress);
            userDatabase.add(dateOfBirth);
        }
        else {
            firstName = userDatabase.get(0);
            lastName = userDatabase.get(1);
            emailAddress = userDatabase.get(2);
            dateOfBirth = userDatabase.get(3);
        }

        String fname = GetName();
        String lname = GetSurname();
        String email = GetEmail();
        String dob = GetDoB();

        firstName.add(fname);
        lastName.add(lname);
        emailAddress.add(email);
        dateOfBirth.add(dob);

        // User
        SerializationHelper.serialiseToFile(userDatabase,file);
        int year = Integer.parseInt(dob.split("/")[2]);
        int age = 2022 - year;
        System.out.println("Hello " + fname + " " + lname + ", details saved to database.");
        System.out.println("You are " + age + " years old");

    }

    // Prompts the user to choose option of what detail to update
    public static int updateUser(ArrayList<String> firstName, ArrayList<String> lastName, ArrayList<String> emailAddress, ArrayList<String> dateOfBirth, String file) {
        int option;

        while (true){
            // Choose from menu what to update
            System.out.println("Choose Data To Update\n" +
                    "1. First Name\n" +
                    "2. Last Name\n" +
                    "3. Email Address\n" +
                    "4. Date of Birth\n" +
                    "5. All Details");

            try {
                option = sc.nextInt();
                if (option >= 1 && option <= 5){
                    break;
                }
            }
            catch (Exception e){
                System.out.println("Invalid Option Selected");
                sc.next();
            }
        }

        return option;
    }

    // Delete user from database
    public static void deleteUser(ArrayList<String> firstName, ArrayList<String> lastName, ArrayList<String> emailAddress, ArrayList<String> dateOfBirth, String file) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<String>> userDatabase;
        userDatabase = (ArrayList<ArrayList<String>>) SerializationHelper.deserializeFromFile(file);
        try {
            firstName = userDatabase.get(0);
            lastName = userDatabase.get(1);
            emailAddress = userDatabase.get(2);
            dateOfBirth = userDatabase.get(3);
            sizeOfList(emailAddress);

            System.out.print("Enter email address: ");
            String email = sc.nextLine();
            int temp = emailAddress.size();

            for (int i = 0; i < emailAddress.size(); i++) {
                if (email.equalsIgnoreCase(emailAddress.get(i))){
                    String tmpName = firstName.get(i);
                    String tmpSurname = lastName.get(i);
                    firstName.remove(i);
                    lastName.remove(i);
                    emailAddress.remove(i);
                    dateOfBirth.remove(i);
                    System.out.println("User " + tmpName + " " + tmpSurname + " has been removed.");
                }
            }
        }
        catch (Exception e){
            System.out.println("No Users Available");
        }
    }

    // List all the users in the database
    public static void listAllUsers(ArrayList<String> firstName, ArrayList<String> lastName, ArrayList<String> emailAddress, ArrayList<String> dateOfBirth, String file) throws IOException, ClassNotFoundException{
        ArrayList<ArrayList<String>> userDatabase;
        try {
            userDatabase = (ArrayList<ArrayList<String>>) SerializationHelper.deserializeFromFile(file);
            firstName = userDatabase.get(0);
            lastName = userDatabase.get(1);
            emailAddress = userDatabase.get(2);
            dateOfBirth = userDatabase.get(3);

            for (int i = 0; i < firstName.size(); i++) {
                System.out.println(firstName.get(i) + " " + lastName.get(i) + " " + emailAddress.get(i) + " " + dateOfBirth.get(i));
            }
        }
        catch (Exception e){
            System.out.println("No Users Available");
        }
    }

    // Get name and check if its valid then return it
    public static String GetName() {
        while (true){
            System.out.print("Enter your name: ");
            String fname = sc.nextLine();
            try {
                isNameValid(fname);
                return fname;
            }
            catch (Exception e){
                System.out.println("Invalid Name");
            }
        }
    }

    // Gets the last name from user input and checks if its valid and returns it if its valid
    public static String GetSurname() {
        while (true){
            System.out.print("Enter your last name: ");
            String lname = sc.nextLine();
            try {
                isSurnameValid(lname);
                return lname;
            }
            catch (Exception e){
                System.out.println("Invalid Surname");
            }
        }
    }

    // Get email from user and check if its valid then return it
    public static String GetEmail() {
        while (true){
            System.out.print("Enter Email Address: ");
            String emailAddr = sc.nextLine();

            try {
                isEmailValid(emailAddr);
                return emailAddr;
            }
            catch (Exception e){
                System.out.println("Invalid Email Address");
            }
        }
    }

    // Get date of birth and check if its valid and return it
    public static String GetDoB() {
        String dob;
        int year;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        while (true){
            System.out.print("Enter date of birth e.g dd/MM/yyyy: ");
            dob = sc.nextLine();

            try {
                Date date = dateFormat.parse(dob);

                if (dob.length() == 10){
                    year = Integer.parseInt(dob.split("/")[2]);
                    if (year < 2022){
                        return dob;
                    }
                    else {
                        System.out.println("Date cannot be future date");
                    }
                }
                else {
                    System.out.println("Date format not valid");
                }
            } catch (Exception e) {
                System.out.println("Date of birth format not valid e.g 11/11/2011");
            }
        }
    }

    // Throws UserException if last name format is not valid
    public static void isNameValid(String surnameCheck) throws UserException {
        if (!Pattern.matches("^[a-zA-Z]+$",surnameCheck)){
            throw new UserException("");
        }
    }

    // Throws UserException if surname is not valid
    public static void isSurnameValid(String lname) throws UserException {
        if (!Pattern.matches("^[a-zA-Z]+$",lname)){
            throw new UserException("");
        }
    }
    // Throw UserException if email address is not valid.
    public static void isEmailValid(String emailCheck) throws UserException{
        if(!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
                emailCheck)){
            throw new UserException("");
        }
    }

    // Throw UserException if there are no users
    public static void sizeOfList(ArrayList<String> arrayList) throws UserException {
        ArrayList<String> userName = arrayList;
        if (userName.size()  == 0){
            throw new UserException("");
        }
    }
}
