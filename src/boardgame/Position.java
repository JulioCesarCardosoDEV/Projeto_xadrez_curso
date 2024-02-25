package boardgame;

public class Position {
	
	// Linhas e Colunas que serão utilizadas para movimentar 
	// As peças
	
	private int row;
	private int column;
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	// Retorna posição da linha
	public int getRow() {
		return row;
	}
	
	// Modifica posição da linha
	public void setRow(int row) {
		this.row = row;
	}
	
	// Retorna posição da coluna
	public int getColumn() {
		return column;
	}
	
	// Modifica posição da coluna
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setValues(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public String toString() {
		return row + ", " + column;
	}
	
	
}
