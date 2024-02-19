package chess;

public class ChessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	// Tratamento de exceções do pacote chess
	public ChessException(String mensagem) {
		super(mensagem);
	}
	
}	
