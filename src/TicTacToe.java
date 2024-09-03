import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Random;

/**
 * Runner for Tic-Tac-Toe game. Developed to experiment testing GUI.
 *
 * @author Andrew Foerst
 */
public class TicTacToe implements ActionListener{
    private int[][] board;
    private JFrame gameScreen;
    private JButton[][] guiBoard;
    private int playerTurn;

    public static void main(String[] args) {
        play();
    }

    public TicTacToe(int size) {
        if(decideStartTurn()){
            playerTurn = 1;
        }else{
            playerTurn = 2;
        }
        int tempSize;
        if(size<3 || size>9){
            board = new int[3][3];
            tempSize = 3;
        }else {
            board = new int[size][size];
            guiBoard = new JButton[size][size];
            tempSize = size;
        }

        gameScreen = new JFrame();
        gameScreen.setLayout(null);
        gameScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameScreen.setSize(1920,1080);

        guiBoard = new JButton[tempSize][tempSize];
        for(int r = 0; r<tempSize; r++){
            for(int c = 0; c<tempSize; c++){
                guiBoard[r][c] = new JButton();
                guiBoard[r][c].addActionListener(this);
                guiBoard[r][c].setFocusable(true);
                guiBoard[r][c].setVisible(true);
                guiBoard[r][c].setBackground(Color.WHITE);
            }
        }

        //TODO SIZE OF BUTTONS: 540/TEMPSIZE
        int tempX = 380;
        int tempY = 75;
        for(int r = 0; r<tempSize; r++){
            for(int c = 0; c<tempSize; c++){
                guiBoard[r][c].setBounds(tempX,tempY,540/tempSize, 540/tempSize);
                tempX+=540/tempSize;
            }
            tempY+= 540/tempSize;
            tempX = 380;
        }

        for(int r = 0; r<tempSize; r++){
            for(int c = 0; c<tempSize; c++){
                gameScreen.add(guiBoard[r][c]);
            }
        }
        gameScreen.setVisible(true);
    }

    public int getBoardSize(){
        return board.length;
    }

    public boolean decideStartTurn(){
        Random random = new Random();
        return random.nextInt(2)%2==0;
    }


    public void setPos(int row, int col, int val){
        board[row][col] = val;
    }

    public boolean checkRow(int row){
        HashSet<Integer> r = new HashSet<>();

        for(int c = 0; c<board.length; c++){
            r.add(board[row][c]);
        }

        if(r.contains(0))
            return false;

        return r.size() <= 1;
    }

    public boolean checkCol(int col){
        HashSet<Integer> column = new HashSet<>();
        for(int r = 0; r < board.length; r++){
            column.add(board[r][col]);
        }

        if(column.contains(0))
            return false;

        return column.size() <= 1;
    }

    public boolean checkIncreasing(){
        HashSet<Integer> diag = new HashSet<>();
        for(int i = 0; i < board.length; i++){
            diag.add(board[board.length-(i+1)][i]);
        }

        if(diag.contains(0)){
            return false;
        }

        return diag.size() <= 1;
    }

    public boolean checkDecreasing() {
        HashSet<Integer> diag = new HashSet<>();
        for(int i = 0; i < board.length; i++){
            diag.add(board[i][i]);
        }
        if(diag.contains(0)){
            return false;
        }

        return diag.size() <= 1;
    }

    public boolean boardFull(){
        for (int[] ints : board) {
            for (int c = 0; c < board.length; c++) {
                if (ints[c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private String getPlayerSymbol(){
        if(playerTurn % 2 == 0){
            return "X";
        }else{
            return "O";
        }
    }

    private int getWinner(String letter){
        if(letter.equals("X")){
            return 2;
        }else{
            return 1;
        }
    }

    private static void play(){
        JFrame start = new JFrame();
        start.setSize(1920,1080);
        start.setLayout(null);
        start.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Integer[] boardSizes = {3,4,5,6,7,8,9};
        int boardSize = (int) JOptionPane.showInputDialog(
                null,
                "How large of a board? (3-9)",
                "Choose Number,",
                JOptionPane.QUESTION_MESSAGE,
                null,
                boardSizes,
                boardSizes[0]);
        TicTacToe play = new TicTacToe(boardSize);
    }
    private void winnerWinnerChickenDinner(int winner){
        JFrame winScreen = new JFrame();
        winScreen.setLayout(null);
        winScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        winScreen.setSize(1920,1080);

        JTextField winnerText = new JTextField("Player "+ winner + " wins!");
        winnerText.setFont(new Font("Courier", Font.BOLD, 80));
        winnerText.setBounds(400,0,550,200);
        winnerText.setEditable(false);
        winnerText.setVisible(true);
        winnerText.setFocusable(false);
        winScreen.add(winnerText);

        JButton playAgain = new JButton("Play Again?");
        playAgain.setFont(new Font("Courier", Font.PLAIN, 35));
        playAgain.setBounds(500,250,300,200);
        ActionListener action = e -> {
            if(e.getSource().equals(playAgain)){
                play();
            }
        };
        playAgain.addActionListener(action);
        winScreen.add(playAgain);

        gameScreen.setVisible(false);
        winScreen.setVisible(true);
    }

    private void tie(){
        JFrame tieScreen = new JFrame();
        tieScreen.setSize(1920,1080);
        tieScreen.setLayout(null);
        tieScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextField tieText = new JTextField("Tie!");
        tieText.setFont(new Font("Courier", Font.PLAIN, 80));
        tieText.setBounds(550,0,200,200);
        tieText.setEditable(false);
        tieText.setFocusable(false);
        tieScreen.add(tieText);


        JButton playAgain = new JButton("Play Again?");
        playAgain.setFont(new Font("Courier", Font.PLAIN, 35));
        playAgain.setBounds(500,250,300,200);
        ActionListener action = e -> {
            if(e.getSource().equals(playAgain)){
                play();
            }
        };
        playAgain.addActionListener(action);
        tieScreen.add(playAgain);

        gameScreen.setVisible(false);
        tieScreen.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int r = 0; r<getBoardSize(); r++){
            for(int c = 0; c<getBoardSize(); c++){
                if(e.getSource().equals(guiBoard[r][c])){
                    if(board[r][c] == 0) {
                        guiBoard[r][c].setFont(new Font("Courier", Font.BOLD, 30));
                        if (playerTurn % 2 == 0) {
                            guiBoard[r][c].setBackground(Color.YELLOW);
                        } else {
                            guiBoard[r][c].setBackground(Color.LIGHT_GRAY);
                        }
                        guiBoard[r][c].setText(getPlayerSymbol());
                        if (playerTurn % 2 == 0) {
                            board[r][c] = 1;
                        } else {
                            board[r][c] = 2;
                        }
                        playerTurn++;
                        if (checkCol(c) || checkRow(r) || checkIncreasing() || checkDecreasing()) {
                            winnerWinnerChickenDinner(getWinner(guiBoard[r][c].getText()));
                        } else if (boardFull()) {
                            tie();
                        }
                    }
                }
            }
        }

    }
}
