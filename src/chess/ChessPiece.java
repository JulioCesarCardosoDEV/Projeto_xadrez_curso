package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
	private Color color;

	// Construtor utilizando super, porque a classe ChessPiece herda Piece
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	// Retorna a posição 
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		// Se a peça for diferente de nulo e sua cor for diferente da cor da minha peça
		// Logo: É uma peça adversária
		return p!= null && p.getColor()!=color;
 	}
}
