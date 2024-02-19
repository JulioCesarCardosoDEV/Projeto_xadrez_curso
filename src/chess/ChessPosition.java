package chess;

import boardgame.Position;

public class ChessPosition {
	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		// Posição do tabuleiro de xadrez é diferente da Classe Position
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Erro ao realizar movimento, os valores precisam ser de a1 a h8");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		// 8 - row = numeração das linhas
		// column - 'a' = o caracter a vale = 1, ou seja, é uma numeração decrescente
		return new Position(8 - row, column - 'a');
	}
	
	// Classe para saber de qual posição a peça veio
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
	
}
