import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("numele primului jucator: ");
            String playerName = scanner.nextLine();
            System.out.println("numele celui de-al doilea jucator:");
            String player2Name = scanner.nextLine();
            Player player1 = new Player(playerName);
            Player player2 = new Player(player2Name);

            MyTicTacToe myTicTacToe = new MyTicTacToe(player1, player2);


            Scanner s = new Scanner(System.in);
            boolean nextGame = true;
            while (nextGame == true) {
                myTicTacToe.playGame();
                System.out.println("Do you want to play another game? Y/N");
                String newGame = s.next();
                if(newGame.toLowerCase().compareTo("y") != 0 ){
                    nextGame = false;
                    System.out.println("End Game!");
                }
            }

        }

    }



