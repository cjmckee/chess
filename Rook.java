package chess;

import java.util.ArrayList;

public class Rook extends Piece{
	
	public Rook() {
		super(5, 0, 0, "Rook", null);
	}
	
	public Rook(PieceColor c, int row, int col) {
		super(5, row, col, "Rook", c);
	}
	
	public ArrayList<Square> getMoves(Board board) {
		if (color == null || type == "")
		{
			System.out.println("I ain't got no type.");
			return null;
		}
		
		ArrayList<Square> moves = new ArrayList<Square>();
		
		boolean left, right, up, down;
		left = right = up = down = true;
		
		for (int i = 1; i < 8; i++)
		{
			if (isLegal(x+i, y) && right && (!board.getSquare(x+i, y).isOccupied() || board.getSquare(x+i, y).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x+i, y));
			if (isLegal(x+i, y) && board.getSquare(x+i, y).isOccupied())
				right = false;
			
			if (isLegal(x, y+i) && up && (!board.getSquare(x, y+i).isOccupied() || board.getSquare(x, y+i).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x, y+i));
			if (isLegal(x, y+i) && board.getSquare(x, y+i).isOccupied())
				up = false;
			
			if (isLegal(x-i, y) && left && (!board.getSquare(x-i, y).isOccupied() || board.getSquare(x-i, y).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x-i, y));
			if(isLegal(x-i, y) && board.getSquare(x-i, y).isOccupied())
				left = false;
			
			if (isLegal(x, y-i) && down && (!board.getSquare(x, y-i).isOccupied() || board.getSquare(x, y-i).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x, y-i));
			if(isLegal(x, y-i) && board.getSquare(x, y-i).isOccupied())
				down = false;
		}
		
		return moves;
	}

}
