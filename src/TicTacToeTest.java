import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicTacToeTest {
    @Test
    public void testBoardFullEmpty(){
        TicTacToe tic = new TicTacToe(21);
        assertFalse(tic.boardFull());
    }

    @Test
    public void testBoardFullFull(){
        Random rand = new Random();
        TicTacToe tic = new TicTacToe(21);
        for(int i = 0; i<tic.getBoardSize(); i++){
            for(int j = 0; j< tic.getBoardSize(); j++){
                tic.setPos(i,j,rand.nextInt(2)+1);
            }
        }
        assertTrue(tic.boardFull());
    }

    @Test
    public void testBoardFullPartlyEmpty(){
        Random rand = new Random();
        TicTacToe tic = new TicTacToe(21);
        for(int i = 0; i<tic.getBoardSize()-rand.nextInt(6); i++){
            for(int j = 0; j< tic.getBoardSize()-rand.nextInt(5); j++){
                tic.setPos(i,j,rand.nextInt(2)+1);
            }
        }
        assertFalse(tic.boardFull());
    }
}
