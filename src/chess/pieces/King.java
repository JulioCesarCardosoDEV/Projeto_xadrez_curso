package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position posision) {
		ChessPiece p = (ChessPiece) getBoard().piece(posision);

		// Retonar p se ele for deferente de nulo, ou for uma peça adversária
		return p == null || p.getColor() != getColor();

	}

	// Método que testa se a torre está apta para a jogada roque
	private boolean testRookCastling(Position position) {
		// Pegar peça da posição
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// Realizando movimento para cima
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// Realizando movimento para baixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// Realizando movimento para esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// Realizando movimento para direita
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// Realizando movimento para diagonal noroeste
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// Realizando movimento para diagonal nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// Realizando movimento para diagonal sudoeste
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// Realizando movimento para diagonal sudeste
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// roque, se o rei não foi movimentado antes e ele não está em xeque
		// é possível realizar o roque
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// roque pequeno
			Position positionT1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(positionT1)) {
				Position position2 = new Position(position.getRow(), position.getColumn() + 1);
				Position position3 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(position2) == null && getBoard().piece(position3) == null) {
					matriz[position.getRow()][position.getColumn() + 2] = true;
				}
			}

			// roque grande
			Position positionT2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(positionT2)) {
				Position position2 = new Position(position.getRow(), position.getColumn() - 1);
				Position position3 = new Position(position.getRow(), position.getColumn() - 2);
				Position position4 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(position2) == null && getBoard().piece(position3) == null && getBoard().piece(position4) == null){
					matriz[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return matriz;
	}

}
