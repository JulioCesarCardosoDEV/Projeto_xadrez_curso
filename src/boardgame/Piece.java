package boardgame;

public abstract class Piece {
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
	
	public abstract boolean[][] possibleMoves();
	
	// roque methods = método concreto que faz um gancho com o método abstrato
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	// Método que verifica se a jogada está disponível para aquela peça
	public boolean isThereAnyPossibleMove() {
		boolean[][]matriz = possibleMoves();
		
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz.length; j++) {
				if(matriz[i][j]) {
					return true;
				}
			}
		}
		
		return false;
	}

}
