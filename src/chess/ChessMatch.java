package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	// Definindo o tamanho do tabuleiro
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		check = false;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// Utilizado DownCasting na matriz abaixo
				// Porque queremos que a matriz receba as posições da classe ChessPiece
				// E não da outra e maior classe Piece

				matriz[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return matriz;
	}

	// Método que informa as posições possíveis para fazer a jogada
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);

		return board.piece(position).possibleMoves();
	}

	// Método que realiza o movimento da peça, recebendo posição de origem e destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		// Se o jogador fizer uma jogada que se auto coloca como xeque, está jogada é inválida
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Você não pode se colocar em xeque");
		}
		
		// se o jogador colocar o oponente em xeque, a variável xeque vira true
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}else {
			// Troca de turno
			nextTurn();
		}
		
		return (ChessPiece) capturedPiece;
	}

	// Método que valida, a possível existência da peça na casa inicial
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe uma peça nessa origem");
		}

		// Método que verifica se a peça pertence ao jogador
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("A peça escolhida não é sua");
		}

		// Método que trata a exceção = movimento escolhido não existe
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Não existe movimentos para a peça escolhida");
		}

	}

	// Valida se é possível fazer um movimento para a posição de destino
	private void validateTargetPosition(Position source, Position target) {
		// Se não for possível a peça na posição de origem ir para o destino
		// Tratar esse erro:
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("A peça escolhida não pode se mover para o destino");
		}
	}

	private Piece makeMove(Position source, Position target) {
		// Remove a peça da casa de origem
		Piece p = board.removePiece(source);
		// Remove a possivel peça na posição de destino
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		// Sempre que capturar uma peça, está será removida da lista de peças do
		// tabuleiro e
		// A será adicionada na lista de peças capturadas
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	// Método que desfaz a jogada quando houver check
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	// Método que realiza a troca de turnos
	private void nextTurn() {
		// Próximo turno
		turn++;
		// Se o jogador atual = white, ele agora vai passar a vez para a cor black
		// Se o jogador atual = black, ele agora vai passar a vez para a cor white
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	// Cor da peça do oponente
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// Método que filtra as peças e verifica se o rei existe
	// Importante: O downcasting utilizado com (ChessPiece), 
	// porque a classe Piece não possui os atributos de cor
	private ChessPiece king(Color color) {
		List<Piece> list= piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece) p;
			}
		}
		
		throw new IllegalStateException("Não existe o rei de cor " + color);
	}
	
	// Método que realiza a checagem da jogada xeque
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		// Verifica na lista de peças se a cor for a do oponente, é ume peça adversária 
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		
		// Testa a existência de alguma peça que pode atingir o rei
		for(Piece p : opponentPieces) {
			boolean[][] matriz = p.possibleMoves();
		// se a matriz de movimentos puder fazer um movimento até o rei, é um xeque
			if(matriz[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		
		return false;
	}
	
	// Método que testa a possibilidade do checkMate
	private boolean testCheckMate(Color color) {
		// se a peça não estiver em check, portanto, não existe um checkMate
		if(!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color ).collect(Collectors.toList());
		for(Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			
			for(int i = 0; i<board.getRows(); i++) {
				for(int j = 0; j<board.getColumns(); j++) {
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						
		// Se o jogador fizer um movimento que tira o rei do check, return false
		// Ou seja, não há checkMate
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						
						if(!testCheck){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		// Adicionando as peças na lista de peças
		piecesOnTheBoard.add(piece);
	}

	// Definindo uma nova peça para o tabuleiro
	private void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		
		placeNewPiece('b', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 8, new King(board, Color.BLACK));

	}
}
