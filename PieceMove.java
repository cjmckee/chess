package chess;

public class PieceMove {
	
	private double score;
	private Piece piece;
	private Square square;
	
	public PieceMove(Piece p, Square s) {
		piece = p;
		square = s;
		score = 0;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double x) {
		score = x;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public Square getSquare() {
		return square;
	}

}
