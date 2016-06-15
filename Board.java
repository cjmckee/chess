package chess;

import java.util.*;

public class Board {
	
	private Square [][] board;
	private PieceColor turn;
	private int gameTurns;
	private ArrayList<Piece> whitePieces, blackPieces;
	private Square whiteKing, blackKing;
	
	public Board () {
		board = new Square [8][8];
		whitePieces = new ArrayList<Piece>(0);
		blackPieces = new ArrayList<Piece>(0);
		
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				board[i][j] = new Square(i, j, null);
			}
		}
		
		board[0][0].setPiece(new Rook(PieceColor.BLACK, 0, 0));
		board[7][0].setPiece(new Rook(PieceColor.BLACK, 7, 0));
		board[2][0].setPiece(new Bishop(PieceColor.BLACK, 2, 0));
		board[5][0].setPiece(new Bishop(PieceColor.BLACK, 5, 0));
		board[1][0].setPiece(new Knight(PieceColor.BLACK, 1, 0));
		board[6][0].setPiece(new Knight(PieceColor.BLACK, 6, 0));
		board[4][0].setPiece(new Queen(PieceColor.BLACK, 4, 0));
		board[3][0].setPiece(new King(PieceColor.BLACK, 3, 0));
		blackKing = board[3][0];
		
		for (int k = 0; k < 8; k++)
			board[k][1].setPiece(new Pawn(PieceColor.BLACK, k, 1));
		
		
		board[0][7].setPiece(new Rook(PieceColor.WHITE, 0, 7));
		board[7][7].setPiece(new Rook(PieceColor.WHITE, 7, 7));
		board[2][7].setPiece(new Bishop(PieceColor.WHITE, 2, 7));
		board[5][7].setPiece(new Bishop(PieceColor.WHITE, 5, 7));
		board[1][7].setPiece(new Knight(PieceColor.WHITE, 1, 7));
		board[6][7].setPiece(new Knight(PieceColor.WHITE, 6, 7));
		board[4][7].setPiece(new Queen(PieceColor.WHITE, 4, 7));
		board[3][7].setPiece(new King(PieceColor.WHITE, 3, 7));
		whiteKing = board[3][7];
		
		for (int k = 0; k < 8; k++)
			board[k][6].setPiece(new Pawn(PieceColor.WHITE, k, 6));
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 2; y++) {
				if (getSquare(x, y).getPiece() != null)
					blackPieces.add(getSquare(x, y).getPiece());
			}
		}
		
		for (int x = 0; x < 8; x++) {
			for (int y = 6; y < 8; y++) {
				if (getSquare(x, y).getPiece() != null)
					whitePieces.add(getSquare(x, y).getPiece());
			}
		}
		
		gameTurns = 0;
		turn = PieceColor.values()[gameTurns % 2];
	}
	
	public void setKing(PieceColor p, int x, int y) {
		if (p == PieceColor.WHITE) {
			whiteKing = board[x][y];
		}
		else {
			blackKing = board[x][y];
		}
	}
	
	public Square getSquare(int x, int y) {
		return board[x][y];
	}
	
	public PieceColor getTurn() {
		return turn;
	}
	
	public void setTurn(PieceColor p) {
		turn = p;
	}
	
	public ArrayList<Piece> getPieces(PieceColor p) {
		if (p == PieceColor.WHITE)
			return whitePieces;
		else
			return blackPieces;
	}
	
	public void incrementTurn() {
		int check = findCheck();
		
		switch (check) {
			case 0: break;
			case 1: System.out.println("Check on White.");
					break;
			case 2: System.out.println("Check on Black.");
					break;
			case 3: System.out.println("Check on White and Black.");
					break;
		}
		
		gameTurns++;
		turn = PieceColor.values()[gameTurns % 2];
		
		int checkmate = isCheckmate();
				
		switch (checkmate) {
			case 0: break;
			case 1: System.out.println("Checkmate on White.");
							break;
			case 2: System.out.println("Checkmate on Black.");
							break;
		}
		
		System.out.println(turn);
	}
	
	public void take(Square sq) {
		PieceColor temp = sq.getPiece().getColor();
		
		if (temp == PieceColor.WHITE)
			whitePieces.remove(sq.getPiece());
		else
			blackPieces.remove(sq.getPiece());
		
		sq.setPiece(null);
	}
	
	public void printPieces() {
		System.out.print("White: ");
		for (int i = 0; i < whitePieces.size(); i++)
			System.out.print(whitePieces.get(i).getType() + " ");
		System.out.print("\nBlack: ");
		for (int j = 0; j < blackPieces.size(); j++)
			System.out.print(blackPieces.get(j).getType() + " ");
		System.out.println();
		System.out.println(turn);
	}
	
	public int findCheck () {
		int x = 0;

			for (int i = 0; i < blackPieces.size(); i++)
			{
				if (blackPieces.get(i).givesCheck(this, whiteKing.getX(), whiteKing.getY())) {
					x = 1;
					//System.out.println("1");
				}
			}
			
			for (int i = 0; i < whitePieces.size(); i++)
			{
				if (whitePieces.get(i).givesCheck(this, blackKing.getX(), blackKing.getY())) {
					if (x == 1) {
						//System.out.println("3");
						x = 3;
					}
					else {
						//System.out.println("2");
						//System.out.println(whitePieces.get(i).getType() + " Gives check to " + blackKing.getX() + ", " +  blackKing.getY());
						x = 2;
					}
				}
			}
		return x;
	}
	
	public void addPiece (Piece p) {
		if (p.getColor() == PieceColor.WHITE) 
			whitePieces.add(p);
		else
			blackPieces.add(p);
	}
	
	public int isCheckmate() {
		if (findCheck() == 1) {
			for (int i = 0; i < whitePieces.size(); i++) {
				Piece current = whitePieces.get(i);
				
				for (int j = 0; j < current.getMoves(this).size(); j++) {
					Piece tempPiece = current.getMoves(this).get(j).getPiece();
					int prevX = current.getX();
					int prevY = current.getY();
					
					boolean possible = current.move(this, current.getMoves(this).get(j).getX(), current.getMoves(this).get(j).getY());
					if (possible) {
						current.undoMove(this, current.getX(), current.getY(), tempPiece, prevX, prevY);
						return 0;
					}
				}
			}
			return 1;
		}
		else if (findCheck() == 2) {
			for (int i = 0; i < blackPieces.size(); i++) {
				Piece current = blackPieces.get(i);
				
				for (int j = 0; j < current.getMoves(this).size(); j++) {
					Piece tempPiece = current.getMoves(this).get(j).getPiece();
					int prevX = current.getX();
					int prevY = current.getY();
					
					boolean possible = current.move(this, current.getMoves(this).get(j).getX(), current.getMoves(this).get(j).getY());
					
					if (possible) {
						current.undoMove(this, current.getX(), current.getY(), tempPiece, prevX, prevY);
						return 0;
					}
				}
			}	
			return 2;
		}
		return 0;
	}
}
