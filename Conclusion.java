package Mad6;
import java.io.*;
import java.util.Scanner;
public class Conclusion {
    static Scanner input = new Scanner(System.in);
    static final String USERS_FILE = "users.txt";
    public static void main(String[] args) {
        System.out.print(
                "Welcome to Secure File Protection System");
        System.out.print("\n*************************************************\n");
        int choice;
        do {
            Scanner input = new Scanner(System.in);
            //System.out.print(
            // "1. for register enter 1\n2. for login enter 2\n3. for forgot password enter 3\n4. for exit enter 4\n");
            System.out.print(
                    "1. Register \n2. Login \n3. Forgot password \n4. Access Secure File System \n5. Exit \n");

            System.out.println("Enter Your Choice between 1-4");
            choice = input.nextInt();
            //choice=Integer.parseInt(input.nextLine().trim());
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    forgotPassword();
                    break;
                case 4:
                    accessSecureSystem();
                    break;
                case 5:
                    System.out.println("Thank you For Using our Program I hope you will liked it Please Rate us 50/50");
                    break;
                default:
                    System.out.println("Please Enter input Between 1 to 4");
            }
        } while (choice != 5);
    }
    public static void registerUser() {
        Scanner input = new Scanner(System.in);
        String username = "";
        String favouriteAnimal = "";
        try {
            do {
                System.out.println("Enter Username:");
                username = input.nextLine();
                if (username.length() < 5) {
                    System.out.println("Enter proper username greater than length of 5 characters");
                }
            } while (username.length() < 5);

            System.out.println("Enter your favourite animal name");
            favouriteAnimal = input.nextLine();

            while (favouriteAnimal.length() < 4) {
                System.out.println("please enter animal name of characters greater than 5 for security purpose");
                favouriteAnimal = input.nextLine();
            }

            String password;
            while (true) {
                System.out.println("Enter Password:");
                password = input.nextLine();
                if (password.length() >= 8) {
                    break;
                } else {
                    System.out.println("Enter a Password of length 8 or greater");
                }
            }
           ensureUsersFileExists();
             //BufferedReader br = new BufferedReader(new FileReader(
           // "D:\\Mubashir files\\CSC103 Programming Fundamentals\\programming java learning\\AREAoverloading\\src\\users.txt"));
            BufferedReader br = new BufferedReader(new FileReader(USERS_FILE));
            //BufferedReader br = new BufferedReader(new FileReader("d://users.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String data[] = line.split(",");
                String oldUsername = data[0];
                String oldPassword = data[1];
                if (username.equals(oldUsername)) {
                    System.out.println(
                            "Sorry this Username is not available , try to use another one or something like mubashir123456");
                    br.close();
                    return;
                }
                if (password.equals(oldPassword)) {
                    System.out.println("Password already used");
                    br.close();
                    return;
                }
            }
            br.close();
            //FileWriter fw = new FileWriter(
             //"D:\\Mubashir files\\CSC103 Programming Fundamentals\\programming java learning\\AREAoverloading\\src\\users.txt",
           // true);
            FileWriter fw = new FileWriter(USERS_FILE, true);
           // FileWriter fw = new FileWriter("d://users.txt");
            fw.write(username + "," + password + "," + favouriteAnimal + "\n");
            fw.close();
            System.out.println("You are successfully registered in our System");
        } catch (Exception e) {
            System.out.println("Something went wrong.Please be patient And restart the Program"+e.getMessage());
        }
    }

    public static void loginUser() {
        try {
            String username;
            String password;
            int count = 0;
            do {
                count++;
                Scanner input = new Scanner(System.in);
                System.out.println("Enter Username:");
                username = input.nextLine();
                System.out.println("Enter Password:");
                password = input.nextLine();
                ensureUsersFileExists();
              //  BufferedReader br = new BufferedReader(new FileReader(
                // "D:\\Mubashir files\\CSC103 Programming Fundamentals\\programming java learning\\AREAoverloading\\src\\users.txt"));
                BufferedReader br = new BufferedReader(new FileReader(USERS_FILE));
                //BufferedReader br = new BufferedReader(new FileReader("d://users.txt"));
                String line;
                while ((line = br.readLine()) != null) {

                    String data[] = line.split(",");
                    String oldusername = data[0];
                    String oldpassword = data[1];
                    if (username.equals(oldusername) && password.equals(oldpassword)) {
                        System.out.println("You are successfully logged in");
                        br.close();
                        int choice2;
                        do {
                            System.out.println(
                                    "1. Encrypt Text\n2. Decrypt Text\n3. Save Encrypted Text\n4.Read Save Encrypted Data\n5. Exit");
                            System.out.println("Enter Your Choice between 1-4");
                            choice2 = input.nextInt();
                            switch (choice2) {
                                case 1:
                                    System.out.println("Enter Text to Encrypt");
                                    input.nextLine();
                                    String text = input.nextLine();
                                    String result = makeSecret(text);
                                    System.out.println("YOUR ENCRYPTED DATA IS!");
                                    System.out.println(
                                            "===========================================================================");
                                    System.out.println(result);
                                    System.out.println(
                                            "===========================================================================");
                                    System.out.println("!YOU WANNA TO SAVE THE DATA! PRESS 1");
                                    int choice = input.nextInt();
                                    if (choice == 1) {
                                        writeData(result, username);
                                    } else {
                                        System.out.println("! INVALID CHOICE !");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter Text to Decrypt");
                                    System.out.println("ENTER THE ENCRYPTED MESSAGE FOR DECRYPTION");
                                    input.nextLine();
                                    String encryptedMessage = input.nextLine();
                                    String decrypted = openSecret(encryptedMessage);
                                    System.out.println("YOUR DECRYPTED DATA IS!");
                                    System.out.println(
                                            "===========================================================================");
                                    System.out.println(decrypted);
                                    System.out.println(
                                            "===========================================================================");
                                    break;
                                case 3:
                                    System.out.println("Enter Encrypted Text to Save");
                                    System.out.println("Enter the encrypted data");
                                    input.nextLine();
                                    String EncryptedText = input.nextLine();
                                    writeData(EncryptedText, username);
                                    break;
                                case 4:
                                    String output = getData(username);
                                    System.out.println("YOUR Encrypted  DATA IS!");
                                    System.out.println(
                                            "===========================================================================");
                                    System.out.println(output);
                                    System.out.println(
                                            "===========================================================================");
                                    break;
                                case 5:
                                    System.out.println("our slogan : your Privacy is our Priority");
                                    break;
                                default:
                                    System.out.println("Please Enter Choice Between 1 to 4");
                            }
                        } while (choice2 != 5);
                        return;
                    }
                }
                System.out.println("Invalid Username or Password");
                br.close();
            } while (count < 3);
            System.out.println("=============================================================================");
            System.out.println(
                    "Sorry to say that your account is permanently bloked due to security reasons\nyou can't login anymore!");
            System.out.println("=============================================================================");
            delete(username, password);

        } catch (Exception e) {
            System.out.println("Something went wrong.Please be patient And restart the Program"+e.getMessage());
        }
    }
    public static void ensureUsersFileExists() throws IOException {
        File f = new File(USERS_FILE);
        if (!f.exists()) {
            f.createNewFile();
        }
    }
    public static void forgotPassword() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Username:");
            String username = sc.nextLine();
            ensureUsersFileExists();
            //BufferedReader br = new BufferedReader(new FileReader(
            // "D:\\Mubashir files\\CSC103 Programming Fundamentals\\programming java learning\\AREAoverloading\\src\\users.txt"));
            BufferedReader br = new BufferedReader(new FileReader(USERS_FILE));
            //BufferedReader br = new BufferedReader(new FileReader("d://users.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String data[] = line.split(",");
                String oldusername = data[0];
                String oldpassword = data[1];
                String favoriteAnimal = data[2];
                if (username.equals(oldusername)) {
                    System.out.println("Enter your favorite animal name that you enter during registeration process");
                    String favouriteInput = sc.nextLine();

                    if (favouriteInput.equals(favoriteAnimal)) {
                        System.out.println("Your Password is: " + oldpassword);
                        br.close();
                        return;

                    } else {
                        System.out.println("your input is incorrect");
                    }

                }
            }
            System.out.println("Invalid Username");
            br.close();
        } catch (Exception e) {
            System.out.println("Something went wrong.Please be patient And restart the Program"+e.getMessage());
        }
    }

    public static void accessSecureSystem() {
        int choice = 0;

        while (choice != 10) {
            System.out.println("\n===== Secure File and Text Protection System.=====");
            System.out.println("1. Save Encrypted Text");
            System.out.println("2. Read File");
            System.out.println("3. Decrypt File content");
            System.out.println("4. Search text in file");
            System.out.println("5. Delete File Contents");
            System.out.println("6. View Activity logs");
            System.out.println("7. Backup File");
            System.out.println("8. File Statics Report");
            System.out.println("9. Export to custom File");
            System.out.println("10. Exit");
            System.out.println("Enter your choice");
            try {
                choice = input.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number!");
                continue;
            }
            switch (choice){
                case 1:
                    saveFile();
                    break;
                case 2:
                    readFile();
                    break;
                case 3:
                    decryptFile();
                    break;
                case 4:
                    searchText();
                    break;
                case 5:
                    deleteFileContent();
                    break;
                case 6:
                    viewActivity();
                    break;
                case 7:
                    backupFile();
                    break;
                case 8:
                    fileStatics();
                    break;
                case 9:
                    exportToCustomFile();
                    break;
                case 10:
                    System.out.println("Thanks for your trust. Be Careful!");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }

    }

    // SaveFile
    public static void saveFile() {
        try {
            System.out.println("Enter encrypted text: ");
            String text = input.nextLine();
            FileWriter file = new FileWriter("data.txt", true);
            BufferedWriter writer = new BufferedWriter(file);
            writer.write(text);
            writer.newLine();
            writer.close();
            System.out.println("File saved successfully.");
            writeLog("Saved encrypted text to: d://data.txt\n");
        } catch (Exception e) {
            System.out.println("Error saving file!" + e.getMessage());
        }
    }

    //ReadFile
    public static void readFile() {
        try {
            File se = new File("data.txt");
            if (!se.exists()) {
                System.out.println("Error:File not found!");
                return;
            }
            Scanner input = new Scanner(se);
            System.out.println("\n==== File Data ====");
            boolean isEmpty = true;
            while (input.hasNextLine()) {
                String text = input.nextLine();
                System.out.println(text + " ");
                isEmpty = false;
            }
            if (isEmpty) {
                System.out.println("File is Empty");
            }
            input.close();
            writeLog("Read file: d://data.txt\n");
        } catch (Exception e) {
            System.out.println("Error: Can't read data!" + e.getMessage());
        }
    }

    // Decrypt File
    public static void decryptFile() {
        File file = new File("data.txt");
        if (!file.exists()) {
            System.out.println("File not Found");
            return;
        }
        try {
            Scanner input = new Scanner(file);
            System.out.println("\n====== Decrypt Data ======");
            while (input.hasNextLine()) {
                String text = input.nextLine();
                String decrypted = "";
                for (int i = 0; i < text.length(); i++) {
                    char ch = text.charAt(i);
                    ch = (char) (ch - 3);
                    decrypted += ch;
                }
                System.out.println("The decrypted text is: " + decrypted);
            }

            System.out.println("Data Decrypted Successfully..");

            input.close();
            writeLog("Decrypted file: d://data.txt\n");
        } catch (Exception e) {
            System.out.println("Error: Decryption Failed!"+e.getMessage());
        }
    }

    // Search File
    public static void searchText() {
        try {
            File file = new File("data.txt");
            if (!file.exists()) {
                System.out.println("Error File not Found!");
            }
            System.out.println("Enter text to  search: ");
            String search = input.nextLine();
            Scanner fileInput = new Scanner(file);
            boolean found = false;
            while (fileInput.hasNextLine()) {
                String text = fileInput.nextLine();
                if (text.contains(search)) {
                    found = true;
                    break;
                }
            }
            fileInput.close();
            if (found) {
                System.out.println("Text Found.");
            } else {
                System.out.println("Text not Found.");
            }
            writeLog("Searched Text In: d://data.txt\n");
        } catch (Exception e) {
            System.out.println("Error: File can't be read!"+e.getMessage());
        }
    }

    //deleteFile
    public static void deleteFileContent() {
        try {
            FileOutputStream writer = new FileOutputStream("data.txt");
            writer.write("".getBytes());
            writer.close();
            System.out.println("File Content Deleted");
            writeLog("Deleted content of: d://data.txt");
        } catch (Exception e) {
            System.out.println("Error: Failed to Delete File.");
        }
    }

    //view activity
    public static void viewActivity() {
        try {
            File file = new File("log.txt");

            if (!file.exists()) {
                System.out.println("No activity logs found yet.");
                return;
            }

            Scanner input = new Scanner(file);
            boolean isEmpty = true;
            System.out.println("\n===== Activity logs =====");
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
                isEmpty = false;
            }
            if (isEmpty) {
                System.out.println("No logs yet.");
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error: File can't be viewed."+e.getMessage());
        }
    }

    //backup file
    public static void backupFile() {
        try {
            File ot = new File("data.txt");
            if (!ot.exists()) {
                System.out.println("File does not exists. There is nothing to store as backup!");
                return;
            }
            Scanner fileInput = new Scanner(ot);
            FileWriter bkp = new FileWriter("backup.txt", false);
            BufferedWriter bw = new BufferedWriter(bkp);
            boolean isEmpty = true;
            while (fileInput.hasNext()) {
                bw.write(fileInput.nextLine());
                bw.newLine();
                isEmpty = false;
            }
            fileInput.close();
            bw.close();
            if (isEmpty) {
                System.out.println("Warning: data.txt file is empty. Backup is created but is empty");
            } else {
                System.out.println("Backup created successfully");
            }
            writeLog("Backed up data.txt file to: backup.txt ");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Source file not found " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error during backup! " + e.getMessage());
        }
    }

    //File statics report
    public static void fileStatics() {
        try {
            File fl = new File("data.txt");
            if (!fl.exists()) {
                System.out.println("Error: File not found!");
                return;
            }
            Scanner fileInput = new Scanner(fl);
            int totalLines = 0;
            int totalWords = 0;
            int totalChars = 0;
            int totalSpecial = 0;
            int totalDigits = 0;
            while (fileInput.hasNext()) {
                String line = fileInput.nextLine();
                totalLines++;
                totalChars += line.length();
                if (!line.trim().isEmpty()) {
                    String[] words = line.trim().split("\\s+");
                    totalWords += words.length;
                }
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (Character.isDigit(ch)) {
                        totalDigits++;
                    } else if (!Character.isLetterOrDigit(ch) && ch != ' ') {
                        totalSpecial++;
                    }
                }
            }
            fileInput.close();
            System.out.println("\n====== File statistics Report ======");
            System.out.println("TotalLines : " + totalLines);
            System.out.println("TotalChars : " + totalChars);
            System.out.println("TotalWords : " + totalWords);
            System.out.println("TotalDigits : " + totalDigits);
            System.out.println("TotalSpecial : " + totalSpecial);
            System.out.println("==================================== ");
            writeLog("Viewed statics of : data.txt");

        } catch (FileNotFoundException e) {
            System.out.println("File not found! " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error Reading File For Statics! " + e.getMessage());
        }
    }

    //Export to custom file
    public static void exportToCustomFile() {
        try {
            File os = new File("data.txt");
            if (!os.exists()) {
                System.out.println("Error: File not found. There is nothing to Export");
                return;
            }
            System.out.println("Enter a name for Export File");
            String n = input.nextLine().trim();
            if (n.isEmpty()) {
                System.out.println("File name cannot be Empty!");
                return;
            }
            if (!n.endsWith(".txt")) {
                n = n + ".txt";
            }
            Scanner fileInput = new Scanner(os);
            FileWriter fw = new FileWriter(n, false);
            BufferedWriter bw = new BufferedWriter(fw);
            boolean isEmpty = true;
            while (fileInput.hasNext()) {
                bw.write(fileInput.nextLine());
                bw.newLine();
                isEmpty = false;
            }
            fileInput.close();
            bw.close();
            if (isEmpty) {
                System.out.println("Error: data.txt is Empty " + n + " is created but empty");
            } else {
                System.out.println("Data Exported successfully to " + n);
            }
            writeLog("Exported data from data.txt to custom File " + n);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error in Exporting File " + e.getMessage());
        }
    }

    public static void writeLog(String message) throws IOException {
        FileWriter log = new FileWriter("log.txt", true);
        log.write(message + "\n");
        log.close();
    }
    // Method for encryption
    static String makeSecret(String message) {

        String result = "";

        for (int i = 0; i < message.length(); i++) {

            char letter = message.charAt(i);

            letter = (char) (letter + 3);

            result = result + letter;
        }

        return result;
    }

    // Method for decryption
    static String openSecret(String message) {

        String result = "";

        for (int i = 0; i < message.length(); i++) {

            char letter = message.charAt(i);

            letter = (char) (letter - 3);

            result = result + letter;
        }

        return result;
    }

    // Save encrypted data in file
    static void writeData(String text, String username) throws IOException {

        FileWriter writer = new FileWriter(username + ".txt");

        writer.write(text);

        writer.close();

        System.out.println("Data stored in file.");
    }

    // Read encrypted data from file
    static String getData(String username) throws IOException {

        File file = new File(username + ".txt");

        FileReader reader = new FileReader(file);

        Scanner fileInput = new Scanner(reader);

        String line = "";

        while (fileInput.hasNextLine()) {

            line = fileInput.nextLine();
        }

        fileInput.close();

        reader.close();

        return line;
    }

    public static void delete(String username, String password) {
        try {
            File temp = new File("temporary.txt");
            File users = new File("D:\\Mubashir files\\CSC103 Programming Fundamentals\\programming java learning\\AREAoverloading\\src\\users.txt");
            PrintWriter writer = new PrintWriter(temp);
            Scanner reader = new Scanner(users);
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] data = line.split(",");
                String thisusername = data[0];
                String thispassword = data[1];
                String favouriteAnimal = data[2];
                if (!thisusername.equals(username)) {
                    writer.println(thisusername + "," + thispassword + "," + favouriteAnimal);
                }
            }
            writer.close();
            reader.close();
            if (users.delete()) {
                temp.renameTo(users);
            } else {
                System.out.println("Could not delete original file");
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}

