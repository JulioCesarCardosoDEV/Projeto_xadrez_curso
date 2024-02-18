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

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	// Método para selecionar a peça desejada
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		// Pega a posição dada, coloca na matriz e a peça irá receber a posição
		pieces[position.getRow()][position.getColumn()] = piece;
		
		// Troca da posição da peça
		piece.position = position;
	}
	
}
