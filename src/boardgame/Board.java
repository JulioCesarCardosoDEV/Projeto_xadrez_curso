package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	// As informações das peças irão passar por essa variável declarada
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}
	
	
}
