import java.io.*;
import java.util.Scanner;

public class customDecryptor {
    String shiftDecrypt(String cypherText, int key) {
        int[] ASCII_val = new int[cypherText.length()];
        char[] temp = new char[cypherText.length()];
        for (int i = 0; i < cypherText.length(); i++) {
            ASCII_val[i] = cypherText.charAt(i);
            if(ASCII_val[i] <= 47 && ASCII_val[i] >= 32){
                temp[i] = (char) ASCII_val[i];
            }
            else {
                int shifted = ASCII_val[i] >> key;
                temp[i] = (char) shifted;
            }
        }
        return new String(temp);
    }
    void manageFiles(int key){
        BufferedReader vaultReader;
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("dump.txt", true);
            vaultReader = new BufferedReader(new FileReader("vault.txt"));
            String line = vaultReader.readLine();
            while(line != null){
                fileWriter.write(shiftDecrypt(line, key) + "\n");
                line = vaultReader.readLine();
            }
            vaultReader.close();
            fileWriter.close();
        }
        catch(IOException e){
            System.out.print("An error occurred\n");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        customDecryptor crypto = new customDecryptor();
        System.out.print("Enter the decryption key : ");
        int key = sc.nextInt();
        crypto.manageFiles(key);
        System.out.println("The file has been decrypted and saved\n");
    }
}

