import java.util.Scanner;
class library{
    final private String[] databaseName = new String[100], issuedBooksName = new String[100];
    final private int[] databaseID = new int[100], issuedBooksID = new int[100];
    int count = 0, issueCount = 0;
    boolean isEmptyLib = true;
    void showAvailableBooks(){
        if(isEmptyLib){
            System.out.println("There aren't any books available choose option 3 to add or return books ");
        }
        else {
            System.out.println("The available books are : ");
            System.out.println("Book Name               Book ID");
            for (int i = 0; i < count-1; i++) {
                System.out.println(databaseName[i+1] + "                    " + databaseID[i]);
            }
        }
    }
    void databaseAddition(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of books you want to add or return ");
        int num = sc.nextInt();
        int c1 = count, c2 = count;
        System.out.println("Enter the name of the books that you want to add or return (in sequence) ");
        for(int i = 0; i <= num; i++, count++){
           databaseName[count] = sc.nextLine();
        }
        System.out.println("Enter the ID of the books that you want to add or return (in sequence) ");
        for(int i = 0; i < num; i++, c1++){
            databaseID[c1] = sc.nextInt();
        }
        System.out.println("The following books are added to the library ");
        System.out.println(" Book Name               Book ID");
        for (int i = 0; i < count-1; i++, c2++) {
            System.out.println(databaseName[c2+1] + "               " + databaseID[c2]);
        }
        System.out.println("Thank you for using our library! ");
        isEmptyLib = false;
    }
    int searchIndex(int bookID){
        for(int i = 0; i <= count; i++){
            if(databaseID[i] == bookID){
                return i;
            }
        }
        return 0;
    }
    void issueBook(){
        Scanner sc = new Scanner(System.in);
        if(isEmptyLib){
            System.out.println("There are no books to be issued choose option 3 to add or return books ");
        }
        else {
            System.out.println("Enter the ID of the book you want to issue");
            int bookID = sc.nextInt();
            int searchIndex = searchIndex(bookID);
            System.out.println("You have issued book " + databaseName[searchIndex]);
            for (int i = searchIndex; i <= count; i++) {
                databaseName[i] = databaseName[i + 1];
                databaseID[i] = databaseID[i + 1];
            }
            count--;
            for (int i = 0; i <= issueCount; i++) {
                issuedBooksName[issueCount] = databaseName[searchIndex];
                issuedBooksID[issueCount] = bookID;
            }
        }
    }
    void issuedBooks(){
        if(isEmptyLib){
            System.out.println("There are no issued books ");
        }
        else {
            System.out.println("The issued books in our library are : ");
            System.out.println(" Book Name               Book ID");
            for (int i = 0; i <= issueCount; i++) {
                System.out.println(issuedBooksName[i] + "               " + issuedBooksID[i]);
            }
        }
    }
    void libraryInfo(){
        System.out.print("""
                Welcome to our online library, launched on December 15, 2023. Here, a universe of
                knowledge awaits at your fingertips. Dive into a digital realm where literature,
                information, and imagination converge to create an accessible and dynamic space for
                learning and exploration. Join us in this literary journey as we celebrate the joy of
                reading in the digital age.
                """ + "\n");
        if(!isEmptyLib) {
            System.out.println("The Total books in our library are : " + count);
        }
    }
}
public class online_Library {
    static Scanner sc = new Scanner(System.in);
    static int userOptionDisplay(){
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
    static void userOption(int opt, library obj){
        switch(opt){
            case 1: obj.libraryInfo();
                break;
            case 2: obj.showAvailableBooks();
                break;
            case 3: obj.databaseAddition();
                break;
            case 4: obj.issueBook();
                break;
            case 5: obj.issuedBooks();
                break;
            case 6: System.exit(120);
            default: System.out.println("Invalid option chosen ");
        }
        opt = userOptionDisplay();
        userOption(opt, obj);
    }
    public static void main(String[] args) {
        library user = new library();
        int opt = userOptionDisplay();
        userOption(opt, user);
    }
}
