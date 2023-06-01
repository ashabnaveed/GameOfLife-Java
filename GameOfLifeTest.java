import junit.framework.TestCase;

public class GameOfLifeTest extends TestCase {

    public void testConstructors() {

        //test for null board
        GameOfLife nullGame = new GameOfLife(null);
        nullGame.setAlive(1, 2, true);
        assertEquals(false, nullGame.isAlive(0,0));
        
        boolean[][] board = new boolean[10][10];
        board[1][2] = true;
        board[9][9] = true;
        GameOfLife game = new GameOfLife(board);
        assertEquals(true, game.isAlive(1,2));
        assertEquals(true, game.isAlive(9,9));

    }
    
    public void testAccessors() {

        boolean[][] board = new boolean[10][10];
        GameOfLife game = new GameOfLife(board);

        //test bounds
        assertEquals(false, game.isAlive(-1,0));
        assertEquals(false, game.isAlive(0,-1));
        assertEquals(false, game.isAlive(11,0));
        assertEquals(false, game.isAlive(0,11));
        assertEquals(false, game.isAlive(20,20));        
    }
    
    public void testMutators() {

        boolean[][] board = new boolean[10][10];
        GameOfLife game = new GameOfLife(board);

        //test for both legal and illegal cells
        for (int i = -2; i < board.length + 1; i++) {
            for (int j = -2; j < board[0].length + 1; j++) {
                game.setAlive(i, j, true);
            }
        }        
    }
    
		
	public void testGame(){
		
		
		boolean[][] board = new boolean[10][10];
		GameOfLife game = new GameOfLife(board);

		//set up a glider
		game.setAlive(1, 0, true);
		game.setAlive(2, 1, true);
		game.setAlive(0, 2, true);
		game.setAlive(1, 2, true);
		game.setAlive(2, 2, true);

		//test if cells were set correctly
		assertEquals(false, game.isAlive(0,0));
		assertEquals(false, game.isAlive(3,1));
		assertEquals(true, game.isAlive(2,1));
        assertEquals(true, game.isAlive(2,2));
				
		//run ten generations, and test if the end state is correct
		game.print();
		for (int i = 1; i <= 10; i++) {
			game.calculateNextGeneration();
			System.out.println("generation:" + i);
			game.print();
		}

		assertEquals(false, game.isAlive(2,3));
		assertEquals(false, game.isAlive(3,3));
		assertEquals(true, game.isAlive(4,3));
		
		assertEquals(true, game.isAlive(2,4));
		assertEquals(false, game.isAlive(3,4));
		assertEquals(true, game.isAlive(4,4));
		
		assertEquals(false, game.isAlive(2,5));
		assertEquals(true, game.isAlive(3,5));
		assertEquals(true, game.isAlive(4,5));	
	}

}
