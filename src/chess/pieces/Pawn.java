package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

	public Pawn(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		if(getColor() == Color.WHITE) {
			// Posicao para frente
			p.setValues(position.getRow() -1, position.getColumn());
			// Se existir uma posição a frente do peão e, for uma casa vazia, é possível ir para frente
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			// Primeiro movimento do peão, é possível andar até 2 casas para frente
			p.setValues(position.getRow() -2, position.getColumn());
			Position p2 = new Position(position.getRow() -1, position.getColumn());
			// Se o contador de movimentos for igual a 0, significa que é o primeiro movimento do peão
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() -1, position.getColumn() -1);
			// Se tiver uma peça inimiga na diagonal esquerda, é uma movimento possível
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() -1, position.getColumn() +1);
			// Se tiver uma peça inimiga na diagonal direita, é uma movimento possível
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		else {
			// Posicao para frente
			p.setValues(position.getRow() + 1, position.getColumn());
			// Se existir uma posição a frente do peão e, for uma casa vazia, é possível ir para frente
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			// Primeiro movimento do peão, é possível andar até 2 casas para frente
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			// Se o contador de movimentos for igual a 0, significa que é o primeiro movimento do peão
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			// Se tiver uma peça inimiga na diagonal esquerda, é uma movimento possível
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			// Se tiver uma peça inimiga na diagonal direita, é uma movimento possível
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
			
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
}
