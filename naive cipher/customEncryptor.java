import java.util.Scanner;
import java.io.*;
public class customEncryptor {
    final private int key = 2;
    String shiftEncrypt(String clearText){
        int[] ASCII_val = new int[clearText.length()];
        char[] temp = new char[clearText.length()];
        for(int i = 0; i < clearText.length(); i++){
            ASCII_val[i] = clearText.charAt(i);
            if(ASCII_val[i] <= 47 && ASCII_val[i] >= 32){
                temp[i] = (char) ASCII_val[i];
            }
            else {
                int shifted = ASCII_val[i] << key;
                temp[i] = (char) shifted;
            }
        }
        return new String(temp);
    }
    void manageFile(String cypherText, String clearText){
        try {
            File crypto = new File("vault.txt");
            crypto.createNewFile();
            FileWriter fileWriter = new FileWriter("vault.txt", true);
            fileWriter.write("Clear Text : " + clearText + "\n");
            fileWriter.write("Cypher Text : " + cypherText + "\n\n");
            fileWriter.close();
        }
        catch(IOException e){
            System.out.print("An error occurred");
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        customEncryptor crypto = new customEncryptor();
        System.out.print("Enter the clear text : ");
        String clearText = sc.nextLine();
        String cypherText = crypto.shiftEncrypt(clearText);
        crypto.manageFile(cypherText, clearText);
    }
}
