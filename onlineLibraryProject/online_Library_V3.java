import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
class library_V3 {
    final private ArrayList<ArrayList<String>> database = new ArrayList<>(), issuedDatabase = new ArrayList<>();

    final private String databaseFilePath = "database.csv";
    final private String issuedDatabaseFilePath = "issuedDatabase.csv";

    library_V3(){
        for (int i = 0; i < 5; i++) {
            database.add(new ArrayList<>());
            issuedDatabase.add(new ArrayList<>());
        }

        File availableBooks = new File(databaseFilePath);
        File issuedBooks = new File(issuedDatabaseFilePath);

        BufferedReader totBooksReader;
        BufferedReader issuedBooksReader;

        String line;

        try {
            totBooksReader = new BufferedReader(new FileReader(availableBooks));
            issuedBooksReader = new BufferedReader(new FileReader(issuedBooks));

            totBooksReader.readLine();  // skips the headers in the csv file
            issuedBooksReader.readLine();

            while((line = totBooksReader.readLine()) != null) {
                for(int i = 0; i < 5; i++){
                    database.get(i).add(line.split(",")[i]);
                }
            }

            while((line = issuedBooksReader.readLine()) != null){
                for(int i = 0; i < 5; i++){
                    issuedDatabase.get(i).add(line.split(",")[i]);
                }
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    void saveFiles(String filePath, ArrayList<ArrayList<String>> data){
        ArrayList<String> line = new ArrayList<>();
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("name,author,language,ID,genre\n");
            for (int i = 0; i < data.get(0).size(); i++){
                for (ArrayList<String> datum : data) {
                    line.add(datum.get(i));
                }
                String rowString = String.join(",", line);
                writer.write(rowString + "\n");
                line.clear();
            }
        } catch (IOException e) {
            System.err.println("Error writing to database" + e.getMessage());
        }
    }
    void sendSaveRequest(){
        saveFiles(databaseFilePath, database);
        saveFiles(issuedDatabaseFilePath, issuedDatabase);
    }

    void showAvailableBooks() {
        if (database.isEmpty()) {
            System.out.println("There aren't any books available choose option 3 to add or return books ");
        }
        else {
            try {
                System.out.println("The available books are : ");
                System.out.printf("| %-40s | %-40s | %-10s | %-10s | %-30s |\n", "NAME", "AUTHOR", "LANGUAGE", "ID", "GENRE");
                for (int i = 0; i < database.get(0).size(); i++) {
                    System.out.printf("| %-40s | %-40s | %-10s | %-10s | %-30s |\n",
                            database.get(0).get(i),
                            database.get(1).get(i), database.get(2).get(i),
                            database.get(3).get(i), database.get(4).get(i)
                    );
                }
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
        }
    }

    void databaseAddition() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the names of the books that you want to add or return (in sequence) separated by commas");
        String[] Names = sc.nextLine().split(",");
        database.get(0).addAll(Arrays.asList(Names));

        System.out.println("Enter the IDs of the books that you want to add or return (in sequence) separated by commas");
        database.get(3).addAll(Arrays.asList(sc.nextLine().split(",")));
        for (int j = 0; j < Names.length; j++) {
            for (int i = 1; i < 5; i++) {
                if (i != 3) database.get(i).add("NaN");
            }
        }

        try {
            System.out.println("The following books are added to the onlineLibraryProject ");
            System.out.printf("| %-40s | %-10s |\n", "NAME", "ID");
            for (int i = database.get(0).size() - 1; i >= database.get(0).size() - Names.length; i--) {
                System.out.printf("| %-40s | %-40s |\n",
                        database.get(0).get(i), database.get(3).get(i)
                );
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println();
        }
        System.out.println("Thank you for using our online library! ");
    }
    void issueBook(library_V3 obj) {
        try {
            Scanner sc = new Scanner(System.in);
            if (database.isEmpty()) {
                System.out.println("There are no books to be issued choose option 3 to add or return books ");
            } else {
                int searchIndex;
                String bookID;
                while(true){
                    System.out.println("Enter the ID of the book you want to issue");
                    bookID = sc.next();
                    searchIndex = database.get(3).indexOf(bookID);
                    if(searchIndex == -1) {
                        System.out.println("invalid ID");
                        continue;
                    }
                    break;
                }
                for(int i = 0; i < 5; i++) {
                    issuedDatabase.get(i).add(database.get(i).get(searchIndex));
                }
                System.out.println("You have issued book " + database.get(0).get(searchIndex));
                for(int i = 0; i < 5; i++) {
                    database.get(i).remove(searchIndex);
                }
            }
        }
        catch(InputMismatchException e ){
            System.out.println("Enter a valid Input ");
            obj.issueBook(obj);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    void showIssuedBooks() {
        if (issuedDatabase.isEmpty()) {
            System.out.println("There are no issued books ");
        } else {
            try {
                System.out.println("The issued books in our online Library are : ");
                System.out.printf("| %-40s | %-40s | %-10s | %-10s | %-30s |\n", "NAME", "AUTHOR", "LANGUAGE", "ID", "GENRE");
                for (int i = 0; i < issuedDatabase.get(0).size(); i++) {
                    System.out.printf("| %-40s | %-40s | %-10s | %-10s | %-30s |\n",
                            issuedDatabase.get(0).get(i),
                            issuedDatabase.get(1).get(i), issuedDatabase.get(2).get(i),
                            issuedDatabase.get(3).get(i), issuedDatabase.get(4).get(i)
                    );
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println();
            }
        }
    }
    void libraryInfo() {
        System.out.print("""
                Welcome to The onlineLibraryProject, launched on December 15, 2023. Here, a universe of
                knowledge awaits at your fingertips. Dive into a digital realm where literature,
                information, and imagination converge to create an accessible and dynamic space for
                learning and exploration. Join us in this literary journey as we celebrate the joy of
                reading in the digital age.
                """ + "\n");
        if (!database.isEmpty()) {
            System.out.println("The Total books in our online Library  are : " + database.get(0).size());
        }
        else System.out.println("Our Library is currently empty");
        if (!issuedDatabase.isEmpty()) {
            System.out.println("The Total books issued in our Library  are : " + issuedDatabase.get(0).size());
        }
    }
}
public class online_Library_V3 {
    static Scanner sc = new Scanner(System.in);
    static int userOptionDisplay() {
        System.out.println("\nChoose what you want to do in our online Library ");
        System.out.println("""
                1. See information about the onlineLibraryProject\s
                2. Show available books\s
                3. Add or return books\s
                4. issue books\s
                5. See the issued books\s
                6. Exit from the online library\s
                """);
        return sc.nextInt();
    }
    @SuppressWarnings("InfiniteRecursion")
    static void userOption(int opt, library_V3 obj) {
        System.out.println();
        switch (opt) {
            case 1:
                obj.libraryInfo();
                break;
            case 2:
                obj.showAvailableBooks();
                break;
            case 3:
                obj.databaseAddition();
                break;
            case 4:
                obj.issueBook(obj);
                break;
            case 5:
                obj.showIssuedBooks();
                break;
            case 6:
                System.out.println("Thank you! for using our Online Library");
                obj.sendSaveRequest();
                System.exit(120);
            default:
                System.out.println("Invalid option chosen ");
        }
        opt = userOptionDisplay();
        userOption(opt, obj);
    }
    public static void main(String[] args) {
        library_V3 user_V3 = new library_V3();
        int opt = userOptionDisplay();
        userOption(opt, user_V3);
    }
}