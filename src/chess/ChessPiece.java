package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece{
	private Color color;

	// Construtor utilizando super, porque a classe ChessPiece herda Piece
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
}