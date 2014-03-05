package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class World extends JFrame implements ActionListener, KeyListener{
	private Cell [][] world;
	private int pRow, pCol;
	private Ghost g1;
	private Ghost g2;
	private Ghost g3;
	private Ghost g4;
	private int size=10;
	private int points = 0;
	private int immunity = 0;
	private boolean immune = false;
	private JLabel [][] board;
	JLabel scoreLabel;
	
	private int pellets = size * size ;
//Constructor for World class.
	public World() {
		createWorld();
		Display();
	}
	//Creates the Pacman World out of the Cell class. Forms a 10X10 2D array
	private void createWorld(){
		world = new Cell [size][size];
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++){
				world [i][j] = new Cell();

			}
		}
		Random r = new Random();
		//Randomly finds a spot for Pacmon and the Ghost and places them on the board
		pRow = r.nextInt(size);
		pCol = r.nextInt(size);
		g1 = new Ghost(r.nextInt(size), r.nextInt(size), size);
		g2 = new Ghost(r.nextInt(size), r.nextInt(size), size);
		g3 = new Ghost(r.nextInt(size), r.nextInt(size), size);
		g4 = new Ghost(r.nextInt(size), r.nextInt(size), size);
		world[g1.getRow()][g1.getCol()].set('G');
		world[g2.getRow()][g2.getCol()].set('G');
		world[g3.getRow()][g3.getCol()].set('G');
		world[g4.getRow()][g4.getCol()].set('G');
		world [5][6].setIPellet();
		world [2][3].setIPellet();
		world [8][3].setIPellet();
		world [3][9].setIPellet();
		world[pRow][pCol].set('P');
	}
	//Prints the board so that the user can see it
	public void printBoard() {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++){
				System.out.print(world[i][j].get());
			}
			System.out.println();
			
		}
		System.out.println("Points: " + points);
	}
	//Input method so that the user can tell pacman what to do. This was taken from one of the modules
	private String input(){
		System.out.print ("Enter your next move: ");
		String input_line = "";
	    try {
	      InputStreamReader isr = new InputStreamReader (System.in);
	      LineNumberReader lr = new LineNumberReader (isr);

	      input_line = lr.readLine ();

	    }
	    catch (IOException e) {
	      // If there was a problem...
	      System.out.println (e);
	   

	}
	    return input_line;


	}
	//Checks if all the pellets were "eaten" and declares a victory if so.
	private boolean checkWin() {
		if (points == pellets){
			System.out.println("You Win!");
			System.exit(0);
		}
		if( immune == false) {
		if (g1.getRow()==pRow && g1.getCol() == pCol || g2.getRow() == pRow && g2.getCol() == pCol || g3.getRow() == pRow && g3.getCol() == pCol || g4.getRow()==pRow &&g4.getCol()==pCol) {
			System.out.println("You Lose!");
			System.exit(0);
		}
		}
		return false;
	}
	//Checks if a move is valid and not going off of the board.
	private boolean checkMove(int row, int col) {
		if (row<0 || row > size-1 || col < 0 || col > size-1) {
			return false;
		}
		return true;
	}
	//Randomly moves the ghost in a direction selected by the number generator. If the Ghost happens upon Pacman, the game is over.
	
	
	//Up = W, Left = A, Right = D, Down = X, NorthW = Q, NorthE = E, SouthW= Z, SouthE = C
	//Directions to actually move pacman. Each if statement incorporates the checkmove() method to see if it can continue
	public void eatPellet() {
		if (world[pRow-1][pCol].get() == 'X'){
			points++;
			
		
		}
		if (world[pRow-1][pCol].get() == 'I') {
			immune = true;
			immunity = 10;
			points++;
		}
	}
	
	private boolean movePacman(String m){
		
		
		if (m.equals("W")|| m.equals("w")) {
			if(checkMove(pRow-1, pCol)==false) {
				System.out.println ("Move Invalid");
				return false;
				
			}
			eatPellet();
			world[pRow-1][pCol].set('P');
			world[pRow][pCol].set('O');
			pRow = pRow -1;
			return true;
		}
		
		if (m.equals("X")|| m.equals("x")) {
			if(checkMove(pRow+1, pCol)==false) {
				System.out.println ("Move Invalid");
				return false;
				
			}
			eatPellet();
			world[pRow+1][pCol].set('P');
			world[pRow][pCol].set('O');
			pRow = pRow +1;
			return true;
		}
		if (m.equals("A")|| m.equals("a")) {
			if(checkMove(pRow, pCol-1)==false) {
				System.out.println ("Move Invalid");
				return false;
				
			}
			eatPellet();
			world[pRow][pCol-1].set('P');
			world[pRow][pCol].set('O');
			pCol = pCol -1;
			return true;
		}
		
		if (m.equals("D")|| m.equals("d")) {
			if(checkMove(pRow, pCol+1)==false) {
				System.out.println ("Move Invalid");
				return false;
				
			}
			eatPellet();
			world[pRow][pCol+1].set('P');
			world[pRow][pCol].set('O');
			pCol = pCol +1;
			return true;
		}
		
		if (m.equals("Q")|| m.equals("q")) {
			if(checkMove(pRow-1, pCol-1)==false) {
				System.out.println ("Move Invalid");
				return false;
				
			}
			eatPellet();
			world[pRow-1][pCol-1].set('P');
			world[pRow][pCol].set('O');
			pCol = pCol -1;
			pRow = pRow - 1;
			return true;
		}
		if (m.equals("e")|| m.equals("E")) {
			if(checkMove(pRow-1, pCol+1)==false) {
				System.out.println ("Move Invalid");
				return false;
				
			}
			eatPellet();
			world[pRow-1][pCol+1].set('P');
			world[pRow][pCol].set('O');
			pCol = pCol + 1;
			pRow = pRow - 1;
			return true;
		}
		if (m.equals("Z")|| m.equals("z")) {
			if(checkMove(pRow+1, pCol-1)==false) {
				System.out.println ("Move Invalid");
				return false;
				
			}
			eatPellet();
			world[pRow+1][pCol-1].set('P');
			world[pRow][pCol].set('O');
			pCol = pCol -1;
			pRow = pRow + 1;
			return true;
		}
		if (m.equals("C")|| m.equals("c")) {
			if(checkMove(pRow+1, pCol+1)==false) {
				System.out.println ("Move Invalid");
				return false;
				
			}
			eatPellet();
			world[pRow+1][pCol+1].set('P');
			world[pRow][pCol].set('O');
			pCol = pCol +1;
			pRow = pRow + 1;
			return true;
		}
		
		
		else {
			System.out.println ("Wrong Input");
			return false;
		}
		
		
		
	}
	//The play method that begins the game. While the checkwin() method is false, the game continues to run.
	public void play() {
	
		boolean validMovePacman;
		boolean validMoveGhost;
		
	
		
		while (true) {
			printBoard();
			updateDisplay();
			validMovePacman = movePacman();	
			
			while (!validMovePacman) {
				validMovePacman = movePacman();
			}
			if (immunity == 0 ) {
				immune = false;
			}
			if (immune == true) {
				immunity--;
			}
			checkWin();
			world[g1.getRow()][g1.getCol()].set(world[g1.getRow()][g1.getCol()].getOldValue());
			
			validMoveGhost = g1.moveGhost();
			while (!validMoveGhost) {
				validMoveGhost = g1.moveGhost();
			}
			world[g1.getRow()][g1.getCol()].set('G');
			world[g2.getRow()][g2.getCol()].set(world[g2.getRow()][g2.getCol()].getOldValue());
			validMoveGhost = g2.moveGhost();
			while (!validMoveGhost) {
				validMoveGhost = g2.moveGhost();
			}
			world[g2.getRow()][g2.getCol()].set('G');
			
			world[g3.getRow()][g3.getCol()].set(world[g3.getRow()][g3.getCol()].getOldValue());
			validMoveGhost = g3.moveGhost();
			while (!validMoveGhost) {
				validMoveGhost = g3.moveGhost();
			}

			world[g3.getRow()][g3.getCol()].set('G');

			world[g4.getRow()][g4.getCol()].set(world[g4.getRow()][g4.getCol()].getOldValue());
			validMoveGhost = g4.moveGhost();
			while (!validMoveGhost) {
				validMoveGhost = g4.moveGhost();
			}

			world[g4.getRow()][g4.getCol()].set('G');
			checkWin();
			
		}
		
		
	}
	private void Display() {
		this.setTitle("Pacman");
		this.setResizable(false);
		this.setSize(500, 500);
		
		ImageIcon pacmanIcon = createImageIcon("images/pacman.gif","");
		this.addKeyListener(this);
		Container cPane = this.getContentPane();
		cPane.setLayout (new BorderLayout());
		
		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(Color.black);
		cPane.add(scorePanel, BorderLayout.NORTH);
		scoreLabel = new JLabel("Score: "+ points);
		scoreLabel.setForeground(Color.WHITE);
		scorePanel.add(scoreLabel);
		
		JPanel startPanel = new JPanel();
		startPanel.setBackground(Color.black);
		cPane.add(startPanel, BorderLayout.SOUTH);
		JButton startButton = new JButton("Start");
		startButton.addActionListener(this);
		startPanel.add(startButton);
		JPanel boardPanel = new JPanel();
		boardPanel.setBackground(Color.black);
		boardPanel.setLayout(new GridLayout(size, size));
		cPane.add(boardPanel, BorderLayout.CENTER);
		board = new JLabel [size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				board[i][j] = new JLabel("X");
				board[i][j].setForeground(Color.white);
				boardPanel.add(board[i][j]);
			}
		}
		
		this.setVisible(true);
	}
	private void updateDisplay() {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++){
				board[i][j].setText(""+world[i][j].get());
				if (world[i][j].get() == 'P') {
					board[i][j].setForeground(Color.yellow);
				}
				else if (i==g1.getRow() && j == g1.getCol()) {
					board[i][j].setForeground(Color.blue);
				}
				else if (i==g2.getRow() && j == g2.getCol()) {
					board[i][j].setForeground(Color.green);
				}
				else if (i==g3.getRow() && j == g3.getCol()) {
					board[i][j].setForeground(Color.magenta);
				}
				else if (i==g4.getRow() && j == g4.getCol()) {
					board[i][j].setForeground(Color.red);
				}
				else if (world[i][j].get() == 'X') {
					board[i][j].setForeground(Color.orange);
				}
				else if (world[i][j].get() == 'I') {
					board[i][j].setForeground(Color.white);
				}
				else if (world[i][j].get() == 'O') {
					board[i][j].setForeground(Color.black);
				}
			}
			
			
		}
		scoreLabel.setText("Score: " + points);
	}
	private ImageIcon createImageIcon (String path, String description) {
		java.net.URL imgURL = World.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} 
		else {
			System.err.println("Could not find the image file: " + path);
			return null;
		}
	}
	public void actionPerformed(ActionEvent e) {
		World w = new World();
		w.play();
		
	}
	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		movePacman(""+c);
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

