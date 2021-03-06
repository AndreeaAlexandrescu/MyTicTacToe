import java.util.Scanner;

public class MyTicTacToe {

    public static final char SYMBOL_X = 'X';
    public static final char SYMBOL_0 = '0';

    public static final int GAME_SIZE = 3;
    char[][] game = new char[GAME_SIZE][GAME_SIZE];

    Player player1;
    Player player2;

    public MyTicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void showGame() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void initBoard() {

        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                game[i][j] = '.';
            }
        }
    }

    public Move readMove() {
        Scanner s = new Scanner(System.in);
        System.out.println("make move");
        String myMove = s.nextLine();
        int myline;
        int mycol;
        String[] myString = myMove.split("-");
        myline = Integer.valueOf(myString[0]);
        mycol = Integer.valueOf(myString[1]);
        Move move = new Move(myline, mycol);
        return move;
    }

    public void makeMove(Move move, char symbol) {
        game[move.line][move.col] = symbol;
    }

    public boolean isWinLine(int line, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[line][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinCol(int col, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][col] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag1(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }


    public boolean isWinDiag2(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][GAME_SIZE - i - 1] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWin(Move move, char symbol) {
        boolean isWin = false;

        //testez linii
        isWin = isWinLine(move.line, symbol);
        if (isWin == false) {

            //testez coloane
            isWin = isWinCol(move.col, symbol);
        }
        if (isWin == false && move.line == move.col) {
            //testez diag1
            isWin = isWinDiag1(symbol);
        }
        if (isWin == false && move.line == GAME_SIZE - move.col - 1) {
            //testez diag2
            isWin = isWinDiag2(symbol);
        }
        return isWin;

    }

    public boolean validValueIndex(int line, int col) {
        boolean validMove = true;
        if (line < 0 || line >= GAME_SIZE) {
            validMove = false;
        }
        if (col < 0 || col >= GAME_SIZE) {
            validMove = false;
        }
        return validMove;
    }

    public boolean validValueOverwrite (int line, int col) {
        boolean validMove = true;
        if (game[line][col] != '.'){
            validMove = false;
        }
        return validMove;
    }




    public void playGame() {
        initBoard();
        System.out.println("incepe jocul");
        showGame();

        Player currentPlayer = player1;
        char currentSymbol = SYMBOL_X;

        int nrMoves = 0;
        boolean isWin = false;


        while (isWin == false && nrMoves < (GAME_SIZE * GAME_SIZE)) {
            boolean validMove = false;
            Move move = new Move(0,0);
            while (validMove == false){
                //citesc mutarea
                move = readMove();
                System.out.println(move.line);
                System.out.println(move.col);

                //validez mutarea
                if((validValueIndex(move.line,move.col) == false) || (validValueOverwrite(move.line, move.col)==false)) {
                    System.out.println("the values are not correct, please try again");
                }
                if ((validValueIndex(move.line,move.col) == true) && (validValueOverwrite(move.line, move.col)== true)) {
                    validMove = true;
                }
            }


            //efectuez mutarea
            makeMove(move, currentSymbol);
            showGame();
            //numar mutare
            nrMoves++;

            if (nrMoves >= (2 * GAME_SIZE - 1)) {
                //testez daca am stare de WIN
                isWin = isWin(move, currentSymbol);
            }


            //daca nu e win sau mai am mutari -- schimb jucatorul
            if (!isWin) {
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                    currentSymbol = SYMBOL_0;

                } else {
                    currentPlayer = player1;
                    currentSymbol = SYMBOL_X;
                }

            }
        }

        // afisez mesajul corespunzator
        if (isWin == true) {
            System.out.println(currentPlayer.name+" WINED!!");
        } else {

            System.out.println("No winner");
        }
    }
}

