import java.util.Scanner;
import java.util.Random;
public class RockPaperScissor_game {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    boolean flag = true;
    boolean tieFlag = false;
    static int computerMoveNum;
    String computerMove;
    int userInput() {
        int option;
        System.out.println("Game For Rock Paper Scissor");
        System.out.println("Enter your choice: ");
        System.out.println("1. Rock");
        System.out.println("2. Paper");
        System.out.println("3. Scissor");
        option = sc.nextInt();
        if(option > 4 || option < 0 ){
            System.out.println("Enter a valid option ");
        }
        return option;
    }
    RckPprSc_game() {
        computerMoveNum = random.nextInt(1,3);
    }
    boolean gameResult(int option){
        switch(option){
            case 1: if(computerMoveNum == 2) flag = false;
                    else if(computerMoveNum == option) {
                        tieFlag = true;
                        flag = false;
                    }
                    break;
            case 2: if(computerMoveNum == 3) flag = false;
                    else if(computerMoveNum == option) {
                        tieFlag = true;
                        flag = false;
                    }
                    break;
            case 3: if(computerMoveNum == 1) flag = false;
                    else if(computerMoveNum == option) {
                        tieFlag = true;
                        flag = false;
                    }
                    break;
            default: flag = true;
        }
        return flag;
    }
    String botChoice(int computerMoveNum){
        if(computerMoveNum == 1)
            computerMove = "Rock";
        else if(computerMoveNum == 2)
            computerMove = "Paper";
        else if(computerMoveNum == 3)
            computerMove = "Scissor";
        return computerMove;
    }
    public static void main(String[] args){
        int option;
        boolean flag;
        RockPaperScissor_game game1 = new RockPaperScissor_game();
        option = game1.userInput();
        System.out.println("The computer chose : " + game1.botChoice(computerMoveNum));
        flag = game1.gameResult(option);
        if(flag){
            System.out.println("Congrats! you've won ");
        }
        else if(game1.tieFlag){
            System.out.println("Alas, you've tied with the computer");
        }
        else System.out.println("Alas you've lost, better luck next time ");

    }
}
