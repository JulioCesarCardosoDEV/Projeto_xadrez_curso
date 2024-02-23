package chess;

import boardgame.BoardException;

public class ChessException extends BoardException{

	private static final long serialVersionUID = 1L;
	
	// Tratamento de exceções do pacote chess
	public ChessException(String mensagem) {
		super(mensagem);
	}
	
}	
