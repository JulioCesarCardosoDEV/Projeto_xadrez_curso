package boardgame;

public class BoardException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	// Criação do método que retornará a mensagem de erro
	public BoardException(String mensagem) {
		super(mensagem);
	}
}
