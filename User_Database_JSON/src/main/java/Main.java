import java.util.*;
import java.io.*;
import java.text.*;

public class Main {

    public static void main(String[] args) throws IOException, ThrowException {
        Scanner sc = new Scanner(System.in);
        UserData userData = new UserData();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> surname = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        ArrayList<String> date_of_birth = new ArrayList<>();
        String file = "home/cmdq1/User Database";
        int choice;

        //Ask the user to add, update, delete and list users until they exit the program
        //Loops until the user terminates the program
        while (true){
            int option = getMenu();
            if (option == 1){
                userData.addUser(name,surname,email,date_of_birth,file);
            }
            else if (option == 2){
                userData.updateUserData(name,surname,email,date_of_birth,file);
            }
            else if (option == 3){
                userData.deleteUserData(name,surname,email,date_of_birth,file);
            }
            else if (option == 4){
                userData.sizeOfList(name);
            }
            else if (option == 5){
                break;
            }
            else {
                System.out.println("Invalid option selected");
            }
        }
    }

    public static int getMenu(){
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true){
            System.out.println("Select option from menu:\n" +
                    "1. Add User\n" +
                    "2. Update User\n" +
                    "3. Delete User\n" +
                    "4. List Users\n" +
                    "5. Exit");
            try {
                choice = sc.nextInt();
                break;
            }
            catch (Exception e){
                System.out.println("Selected option not valid.");
                sc.next();
            }
        }
        return choice;
    }
}
