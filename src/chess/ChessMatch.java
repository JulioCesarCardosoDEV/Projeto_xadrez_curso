package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Board board;
	
	// Definindo o tamanho do tabuleiro
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				// Utilizado DownCasting na matriz abaixo
				// Porque queremos que a matriz receba as posições da classe ChessPiece
				// E não da maior classe Piece
				
				matriz[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return matriz;
	}
	
	// Método que realiza o movimento da peça, recebendo posição de origem e destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	// Método que valida, a possível existência da peça na casa inicial
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe uma peça nessa origem");
		}
		
		// Método que trata a exceção = movimento escolhido não existe
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Não existe movimentos para a peça escolhida");
		}
		
	}
	
	private Piece makeMove(Position source, Position target) {
		// Remove a peça da casa de origem
		Piece p = board.removePiece(source);
		// Remove a possivel peça na posição de destino
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	// Definindo uma nova peça para o tabuleiro
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
		
	}
}
