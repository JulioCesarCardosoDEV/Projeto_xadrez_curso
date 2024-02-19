package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	// As informações das peças irão passar por essa variável declarada
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Erro criando tabuleiro: é necessário ter 1 linha e 1 coluna");
		}
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
		// Tratamento de exceção: Se não existir a posição no tabuleiro (array)
		if(!positionExists(row, column)) {
			throw new BoardException("Essa posição não existe");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Essa posição não existe");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("Já existe uma peça nessa posição " + position);
		}
		
		// Pega a posição dada, coloca na matriz e a peça irá receber a posição
		pieces[position.getRow()][position.getColumn()] = piece;
		
		// Troca da posição da peça
		piece.position = position;
	}
	
	private boolean positionExists(int row, int column) {
		// Retorna se a posição existe no tabuleiro, a partir do tamanho do tabuleiro
		return row >=0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Essa posição não existe");
		}
		
		// Se piece for diferente de nulo, há uma peça nesta posição
		return piece(position) != null;
	}
	
}
