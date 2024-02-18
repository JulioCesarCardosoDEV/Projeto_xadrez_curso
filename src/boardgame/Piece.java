package boardgame;

public class Piece {
	protected Position position;
	// Protected pois a posição é uma posição simples de matriz, portanto, 
	// é melhor não aparecer na tela
	
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
		
		// position começa nula, porque quem definirá aonde a peça irá ser jogada,
		// é o jogador (o java colocaria a position como nula automaticamente,
		// mas é bom colocar dessa maneira para ficar mais entendível.
	}

	public Board getBoard() {
		return board;
	}

}
