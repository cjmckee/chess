package chess;

import java.util.ArrayList;

public class King extends Piece{
	
	public King() {
		super(1, 0, 0, "King", null);
	}
	
	public King(PieceColor c, int row, int col) {
		super(1, row, col, "King", c);
	}
	
	public ArrayList<Square> getMoves(Board board) {
		if (color == null || type == "")
		{
			System.out.println("I ain't got no type.");
			return null;
		}
		
		ArrayList<Square> moves = new ArrayList<Square>();
		
			if (isLegal(x+1, y) && (!board.getSquare(x+1, y).isOccupied() || board.getSquare(x+1, y).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x+1, y));
			if (isLegal(x, y+1) && (!board.getSquare(x, y+1).isOccupied() || board.getSquare(x, y+1).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x, y+1));
			if (isLegal(x-1, y) && (!board.getSquare(x-1, y).isOccupied() || board.getSquare(x-1, y).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x-1, y));
			if (isLegal(x, y-1) && (!board.getSquare(x, y-1).isOccupied() || board.getSquare(x, y-1).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x, y-1));
			if (isLegal(x+1, y+1) && (!board.getSquare(x+1, y+1).isOccupied() || board.getSquare(x+1, y+1).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x+1, y+1));
			if (isLegal(x-1, y+1) && (!board.getSquare(x-1, y+1).isOccupied() || board.getSquare(x-1, y+1).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x-1, y+1));
			if (isLegal(x+1, y-1) && (!board.getSquare(x+1, y-1).isOccupied() || board.getSquare(x+1, y-1).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x+1, y-1));
			if (isLegal(x-1, y-1) && (!board.getSquare(x-1, y-1).isOccupied() || board.getSquare(x-1, y-1).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x-1, y-1));
			
			//castle
			if (!moved && isLegal(x-3, y) && board.getSquare(x-3, y).isOccupied() && !board.getSquare(x-1, y).isOccupied() && !board.getSquare(x-2, y).isOccupied() && !board.getSquare(x-3, y).getPiece().hasMoved())
				moves.add(board.getSquare(x-2, y));
			
		return moves;
	}
	
	public boolean protects (Board board, int x, int y) {
		if (x == getX()+1 && y == getY())
			return true;
		if (x == getX()+1 && y == getY()+1)
			return true;
		if (x == getX()+1 && y == getY()-1)
			return true;
		if (x == getX() && y == getY()+1)
			return true;
		if (x == getX() && y == getY()-1)
			return true;
		if (x == getX()-1 && y == getY()+1)
			return true;
		if (x == getX()-1 && y == getY())
			return true;
		if (x == getX()-1 && y == getY()-1)
			return true;
		
		return false;
	}
}
