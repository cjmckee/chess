package chess;

import java.util.*;

public abstract class Piece {
	
	protected int points, x, y;
	protected String type;
	protected PieceColor color;
	protected boolean moved;
	
	public Piece() {
		points = 0;
		x = 0;
		y = 0;
		type = "";
		color = null;
		moved = false;
	}
	
	public Piece(int p, int row, int col, String t, PieceColor c) {
		points = p;
		x = row;
		y = col;
		type = t;
		color = c;
		moved = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setLocation (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String getType() {
		return type;
	}
	
	public PieceColor getColor() {
		return color;
	}
	
	public void setColor(PieceColor c) {
		color = c;
	}
	
	public boolean isOpponent(Piece p) {
		return this.color != p.getColor();
	}
	
	public boolean hasMoved() {
		return moved;
	}
	
	public void moved() {
		moved = true;
	}
	
	public boolean equals(Piece other) {
		return this.points == other.points && this.x == other.x && this.y == other.y && this.type.equals(other.type) && this.color == other.color;
	}
	
	public boolean givesCheck(Board board, int x, int y) {
		ArrayList<Square> moves = getMoves(board);
		
		for (int i = 0; i < moves.size(); i++)
		{
			if (moves.get(i).equals(board.getSquare(x, y)))
				return true;
		}
		return false;
	}
	
	public boolean move(Board board, int x, int y) {
		if (this.getColor() == board.getTurn() && getMoves(board).contains(board.getSquare(x, y)))
		{
			Piece tempPiece = null;
			if (board.getSquare(x, y).getPiece() != null) {
				tempPiece = board.getSquare(x, y).getPiece();
				board.take(board.getSquare(x, y));
			}
			
			board.getSquare(this.x, this.y).setPiece(null);
			
			int tempX = this.x;
			int tempY = this.y;
			
			this.x = x;
			this.y = y;
			board.getSquare(x, y).setPiece(this);
			
			if (this.getType().equals("King") && this.getColor() == PieceColor.WHITE)
				board.setKing(PieceColor.WHITE, x, y);
			else if (this.getType().equals("King") && this.getColor() == PieceColor.BLACK)
				board.setKing(PieceColor.BLACK, x, y);
			
			switch (board.findCheck()) {
				case 0: break;
				
				case 1: if (this.getColor() == PieceColor.WHITE) {
						//System.out.println("1");
						undoMove(board, x, y, tempPiece, tempX, tempY);
						
						return false;
					}
					break;
				case 2: if (this.getColor() == PieceColor.BLACK) {
					//System.out.println("2");
					undoMove(board, x, y, tempPiece, tempX, tempY);
					
					return false;
				}
				break;
				
				case 3: //System.out.println("3");
				undoMove(board, x, y, tempPiece, tempX, tempY);
				
				return false;
			}
			moved = true;
			
			return true;
		}
		return false;
	}
	
	public void undoMove (Board board, int x, int y, Piece prev, int prevX, int prevY) {
		board.getSquare(x, y).setPiece(prev);
		this.x = prevX;
		this.y = prevY;
		board.getSquare(this.x, this.y).setPiece(this);
		
		if (this.getType().equals("King") && this.getColor() == PieceColor.WHITE)
			board.setKing(PieceColor.WHITE, this.x, this.y);
		else if (this.getType().equals("King") && this.getColor() == PieceColor.BLACK)
			board.setKing(PieceColor.BLACK, this.x, this.y);
		
		
		if (prev != null)
			board.addPiece(prev);
	}
	
	public boolean isLegal(int x, int y) {
		return x >= 0 && x <= 7 && y >= 0 && y <= 7;
	}
	
	public void printMoves(Board board) {
		ArrayList<Square> list = getMoves(board);
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println(type + ": " + list.get(i).getX() + " " + list.get(i).getY());
		}
	}
	
	public abstract ArrayList<Square> getMoves (Board board);

}
