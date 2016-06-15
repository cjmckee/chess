package chess;

import java.util.ArrayList;

public class Knight extends Piece{
	
	public Knight() {
		super(3, 0, 0, "Knight", null);
	}
	
	public Knight(PieceColor c, int row, int col) {
		super(3, row, col, "Knight", c);
	}
	
	public ArrayList<Square> getMoves(Board board) {
		if (color == null || type == "")
		{
			System.out.println("I ain't got no type.");
			return null;
		}
		
		ArrayList<Square> moves = new ArrayList<Square>();
		
		if (isLegal(x+1, y+2) && (!board.getSquare(x+1, y+2).isOccupied() || board.getSquare(x+1, y+2).getPiece().isOpponent(this)))
			moves.add(board.getSquare(x+1, y+2));
		if (isLegal(x-1, y+2) && (!board.getSquare(x-1, y+2).isOccupied() || board.getSquare(x-1, y+2).getPiece().isOpponent(this)))
			moves.add(board.getSquare(x-1, y+2));
		if (isLegal(x+1, y-2) && (!board.getSquare(x+1, y-2).isOccupied() || board.getSquare(x+1, y-2).getPiece().isOpponent(this)))
			moves.add(board.getSquare(x+1, y-2));
		if (isLegal(x-1, y-2) && (!board.getSquare(x-1, y-2).isOccupied() || board.getSquare(x-1, y-2).getPiece().isOpponent(this)))
			moves.add(board.getSquare(x-1, y-2));
		
		if (isLegal(x+2, y+1) && (!board.getSquare(x+2, y+1).isOccupied() || board.getSquare(x+2, y+1).getPiece().isOpponent(this)))
			moves.add(board.getSquare(x+2, y+1));
		if (isLegal(x-2, y+1) && (!board.getSquare(x-2, y+1).isOccupied() || board.getSquare(x-2, y+1).getPiece().isOpponent(this)))
			moves.add(board.getSquare(x-2, y+1));
		if (isLegal(x+2, y-1) && (!board.getSquare(x+2, y-1).isOccupied() || board.getSquare(x+2, y-1).getPiece().isOpponent(this)))
			moves.add(board.getSquare(x+2, y-1));
		if (isLegal(x-2, y-1) && (!board.getSquare(x-2, y-1).isOccupied() || board.getSquare(x-2, y-1).getPiece().isOpponent(this)))
			moves.add(board.getSquare(x-2, y-1));
		
		return moves;
	}

}
