package chess;

public class Square {
	
	private int x, y;
	private Piece piece;
	
	public Square() {
		x = 0;
		y = 0;
		piece = null;
	}
	
	public Square(int hor, int ver, Piece p) {
		x = hor;
		y = ver;
		piece = p;
	}
	
	public boolean isOccupied() {
		return piece != null;
	}
	
	public void setPiece(Piece p) {
		piece = p;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public boolean equals(Square other) {
		return this.x == other.x && this.y == other.y && this.piece == other.piece;
	}

}
