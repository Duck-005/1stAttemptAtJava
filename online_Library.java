import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
class library_V2 {
    final private ArrayList<String> databaseName = new ArrayList<>(), issuedBooksName = new ArrayList<>();
    final private ArrayList<Integer> databaseID = new ArrayList<>(), issuedBooksID = new ArrayList<>();
    void showAvailableBooks() {
        if (databaseID.isEmpty()) {
            System.out.println("There aren't any books available choose option 3 to add or return books ");
        } else {
            try {
                System.out.println("The available books are : ");
                System.out.println("Book Name               Book ID");
                for (int i = 0; i < databaseName.size(); i++) {
                    System.out.println(databaseName.get(i) + "                    " + databaseID.get(i));
                }
            }
            catch(IndexOutOfBoundsException e){
                System.out.println();
            }
        }
    }
    void databaseAddition() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of books you want to add or return ");
        int userEntry1 = sc.nextInt();
        int userEntry2 = userEntry1;
        System.out.println("Enter the name of the books that you want to add or return (in sequence) ");
        while(userEntry2 > 0) {
            databaseName.add(sc.next());
            userEntry2--;
        }
        userEntry2 = userEntry1;
        System.out.println("Enter the ID of the books that you want to add or return (in sequence) ");
        while(userEntry2 > 0) {
            databaseID.add(sc.nextInt());
            userEntry2--;
        }
        userEntry2 = userEntry1;
        try {
            System.out.println("The following books are added to the library ");
            System.out.println(" Book Name               Book ID");
            for (int i = 0; i <= databaseName.size(); i++) {
                System.out.println(databaseName.get(databaseName.size() - userEntry2 + i) + "               " + databaseID.get(databaseID.size() - userEntry2 + i));
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println();
        }
        System.out.println("Thank you for using our library! ");
    }
    int searchIndex(int bookID) {
        for (int i = 0; i <= databaseID.size(); i++) {
            if (databaseID.get(i) == bookID) {
                return i;
            }
        }
        return 0;
    }
    void issueBook(library_V2 obj) {
        try {
            Scanner sc = new Scanner(System.in);
            if (databaseID.isEmpty()) {
                System.out.println("There are no books to be issued choose option 3 to add or return books ");
            } else {
                System.out.println("Enter the ID of the book you want to issue");
                int bookID = sc.nextInt();
                int searchIndex = searchIndex(bookID);
                System.out.println("You have issued book " + databaseName.get(searchIndex));
                issuedBooksName.add(databaseName.get(searchIndex));
                issuedBooksID.add(databaseID.get(searchIndex));
                databaseName.remove(searchIndex);
                databaseID.remove(searchIndex);
            }
        }
        catch(InputMismatchException e ){
            System.out.println("Enter a valid Input ");
            obj.issueBook(obj);
        }
    }
    void showIssuedBooks() {
        if (issuedBooksID.isEmpty()) {
            System.out.println("There are no issued books ");
        } else {
            try {
                System.out.println("The issued books in our library are : ");
                System.out.println(" Book Name               Book ID");
                for (int i = 0; i <= issuedBooksID.size(); i++) {
                    System.out.println(issuedBooksName.get(i) + "               " + issuedBooksID.get(i));
                }
            }
            catch(IndexOutOfBoundsException e){
                System.out.println();
            }
        }
    }
    void libraryInfo() {
        System.out.print("""
                Welcome to our online library, launched on December 15, 2023. Here, a universe of
                knowledge awaits at your fingertips. Dive into a digital realm where literature,
                information, and imagination converge to create an accessible and dynamic space for
                learning and exploration. Join us in this literary journey as we celebrate the joy of
                reading in the digital age.
                """ + "\n");
        if (!databaseID.isEmpty()) {
            System.out.println("The Total books in our library are : " + databaseID.size());
        }
        else System.out.println("Our Library is currently empty");
        if (!issuedBooksID.isEmpty()) {
            System.out.println("The Total books in our Library  are : " + issuedBooksID.size());
        }
    }
}
public class online_Library_V2 {
    static Scanner sc = new Scanner(System.in);
    static int userOptionDisplay() {
        System.out.println("Choose what you want to do in our online library ");
        System.out.println("""
                1. See information about the online library\s
                2. Show available books\s
                3. Add or return books\s
                4. issue books\s
                5. See the issued books\s
                6. Exit from the library\s
                """ + "\n");
        return sc.nextInt();
    }
    @SuppressWarnings("InfiniteRecursion")
    static void userOption(int opt, library_V2 obj) {
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
                System.exit(120);
            default:
                System.out.println("Invalid option chosen ");
        }
        opt = userOptionDisplay();
        userOption(opt, obj);
    }
    public static void main(String[] args) {
        library_V2 user_V2 = new library_V2();
        int opt = userOptionDisplay();
        userOption(opt, user_V2);
    }
}
