package chess;

import java.util.ArrayList;

public class Bishop extends Piece{
	
	public Bishop() {
		super(3, 0, 0, "Bishop", null);
	}
	
	public Bishop(PieceColor c, int row, int col) {
		super(3, row, col, "Bishop", c);
	}
	
	public ArrayList<Square> getMoves(Board board) {
		if (color == null || type == "")
		{
			System.out.println("I ain't got no type.");
			return null;
		}
		
		ArrayList<Square> moves = new ArrayList<Square>();
		
		boolean ldu, ldd, rdu, rdd;
		ldu = ldd = rdu = rdd = true;
		
		for (int i = 1; i < 8; i++)
		{
			if (isLegal(x+i, y+i) && rdu && (!board.getSquare(x+i, y+i).isOccupied() || board.getSquare(x+i, y+i).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x+i, y+i));
			if (isLegal(x+i, y+i) && board.getSquare(x+i, y+i).isOccupied())
				rdu = false;
			
			if (isLegal(x-i, y+i) &&  ldu && (!board.getSquare(x-i, y+i).isOccupied() || board.getSquare(x-i, y+i).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x-i, y+i));
			if (isLegal(x-i, y+i) && board.getSquare(x-i, y+i).isOccupied())
				ldu = false;
			
			if (isLegal(x+i, y-i) && rdd && (!board.getSquare(x+i, y-i).isOccupied() || board.getSquare(x+i, y-i).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x+i, y-i));
			if (isLegal(x+i, y-i) && board.getSquare(x+i, y-i).isOccupied())
				rdd = false;
			
			if (isLegal(x-i, y-i) && ldd && (!board.getSquare(x-i, y-i).isOccupied() || board.getSquare(x-i, y-i).getPiece().isOpponent(this)))
				moves.add(board.getSquare(x-i, y-i));
			if (isLegal(x-i, y-i) && board.getSquare(x-i, y-i).isOccupied())
				ldd = false;
		}
		
		return moves;
	}

}
