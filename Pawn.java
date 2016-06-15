package chess;

import java.util.*;

public class Pawn extends Piece{
	
	public Pawn() {
		super(1, 0, 0, "Pawn", null);
	}
	
	public Pawn(PieceColor c, int row, int col) {
		super(1, row, col, "Pawn", c);
	}
	
	public ArrayList<Square> getMoves(Board board) {
		if (color == null || type == "")
		{
			System.out.println("I ain't got no type.");
			return null;
		}
		
		ArrayList<Square> moves = new ArrayList<Square>();
		
		if (color == PieceColor.BLACK)
		{
			if (isLegal(x, y+2) && (!moved && !board.getSquare(x, y+2).isOccupied() && !board.getSquare(x, y+1).isOccupied()))
				moves.add(board.getSquare(x, y+2));
			
			if (isLegal(x, y+1) && (!board.getSquare(x, y+1).isOccupied()))
				moves.add(board.getSquare(x, y+1));
			if (isLegal(x+1, y+1) && board.getSquare(x+1, y+1).isOccupied() && (board.getSquare(x+1, y+1).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x+1, y+1));
			if (isLegal(x-1, y+1) && board.getSquare(x-1, y+1).isOccupied() && (board.getSquare(x-1, y+1).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x-1, y+1));
		}
		else
		{
			if (isLegal(x, y-2) && (!moved && !board.getSquare(x, y-2).isOccupied() && !board.getSquare(x, y-1).isOccupied()))
				moves.add(board.getSquare(x, y-2));
			
			if (isLegal(x, y-1) && (!board.getSquare(x, y-1).isOccupied()))
				moves.add(board.getSquare(x, y-1));
			if (isLegal(x+1, y-1) && board.getSquare(x+1, y-1).isOccupied() && (board.getSquare(x+1, y-1).getPiece().isOpponent(this) && isLegal(x+1, y-1)))
				moves.add(board.getSquare(x+1, y-1));
			if (isLegal(x-1, y-1) && board.getSquare(x-1, y-1).isOccupied() && (board.getSquare(x-1, y-1).getPiece().isOpponent(this) && isLegal(x-1, y-1)))
				moves.add(board.getSquare(x-1, y-1));
		}
			
		return moves;
	}

}
