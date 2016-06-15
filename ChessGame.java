package chess;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
	
	JLayeredPane pane;
	JPanel chessBoard;
	JLabel chessPiece;
	Board board = new Board();
	int xMove, yMove;
	Piece current = null;
	Point original;
	
	public ChessGame() {
		Dimension size = new Dimension(600, 600);
		
		pane = new JLayeredPane();
		getContentPane().add(pane);
		pane.setPreferredSize(size);
		pane.addMouseListener(this);
		pane.addMouseMotionListener(this);
		
		chessBoard = new JPanel();
		pane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8,8));
		chessBoard.setPreferredSize(size);
		chessBoard.setBounds(0,0, size.width, size.height);
		
		 for (int i = 0; i < 64; i++) {
			  JPanel square = new JPanel( new BorderLayout() );
			  chessBoard.add( square );
			 
			  int row = (i / 8) % 2;
			  if (row == 0)
			  square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
			  else
			  square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
			  }
		  
		  JLabel piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_rdt60.png") );
		  JPanel panel = (JPanel)chessBoard.getComponent(0);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_ndt60.png") );
		  panel = (JPanel)chessBoard.getComponent(1);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_bdt60.png") );
		  panel = (JPanel)chessBoard.getComponent(2);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_kdt60.png") );
		  panel = (JPanel)chessBoard.getComponent(3);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_qdt60.png") );
		  panel = (JPanel)chessBoard.getComponent(4);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_bdt60.png") );
		  panel = (JPanel)chessBoard.getComponent(5);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_ndt60.png") );
		  panel = (JPanel)chessBoard.getComponent(6);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_rdt60.png") );
		  panel = (JPanel)chessBoard.getComponent(7);
		  panel.add(piece);
		  
		  for (int i = 8; i < 16; i++)
		  {
			  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_pdt60.png") );
			  panel = (JPanel)chessBoard.getComponent(i);
			  panel.add(piece);
		  }
		  
		  for (int i = 48; i < 56; i++)
		  {
			  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_plt60.png") );
			  panel = (JPanel)chessBoard.getComponent(i);
			  panel.add(piece);
		  }
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_rlt60.png") );
		  panel = (JPanel)chessBoard.getComponent(56);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_nlt60.png") );
		  panel = (JPanel)chessBoard.getComponent(57);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_blt60.png") );
		  panel = (JPanel)chessBoard.getComponent(58);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_klt60.png") );
		  panel = (JPanel)chessBoard.getComponent(59);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_qlt60.png") );
		  panel = (JPanel)chessBoard.getComponent(60);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_blt60.png") );
		  panel = (JPanel)chessBoard.getComponent(61);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_nlt60.png") );
		  panel = (JPanel)chessBoard.getComponent(62);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("C:/Users/cjmckee/Pictures/pieces/Chess_rlt60.png") );
		  panel = (JPanel)chessBoard.getComponent(63);
		  panel.add(piece);
	}
	
	public void mousePressed (MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		
		if (c instanceof JPanel)
			return;
		
		int boardx = chessBoard.getComponentAt(e.getX(), e.getY()).getX() / 75;
		int boardy = chessBoard.getComponentAt(e.getX(), e.getY()).getY() / 75;
		current = board.getSquare(boardx, boardy).getPiece();
		if (current != null)
			System.out.println(current.getType());
		
		original = c.getParent().getLocation();
		xMove = original.x - e.getX();
		yMove = original.y - e.getY();
		chessPiece = (JLabel)c;
		chessPiece.setLocation(e.getX() + xMove, e.getY() + yMove);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		pane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}
	
	public void mouseDragged (MouseEvent e) {
		if (chessPiece == null)
			return;
		chessPiece.setLocation(e.getX() + xMove, e.getY() + yMove);
	}
	
	public void mouseReleased(MouseEvent e) {
		 if(chessPiece == null) return;
		 
		  chessPiece.setVisible(false);
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		  
		  if (c == null) {
			  chessPiece.setLocation(original);
			  Container parent = (Container)chessBoard.getComponentAt(original);
			  parent.add(chessPiece);
			  chessPiece.setVisible(true);
			  return;
		  }
		  
		  int boardx = chessBoard.getComponentAt(e.getX(), e.getY()).getX() / 75;
		  int boardy = chessBoard.getComponentAt(e.getX(), e.getY()).getY() / 75;
		  
		  boolean castleFlag = ((boardx == current.getX() - 2) && current.getType().equals("King"));
		  
		  boolean pieceMove = current.move(board, boardx, boardy);
		  
		  if (pieceMove) {
		 
			  if (c instanceof JLabel){
			  Container parent = c.getParent();
			  parent.remove(0);
			  parent.add( chessPiece );
			  }
			  else {
			  Container parent = (Container)c;
			  parent.add( chessPiece );
			  if (castleFlag)
				  castle(current.getColor());
			  }
			  board.incrementTurn();
		  }
		  else {
			  chessPiece.setLocation(original);
			  Container parent = (Container)chessBoard.getComponentAt(original);
			  parent.add(chessPiece);
		  }
		 
		  chessPiece.setVisible(true);
		  
		  if (board.isCheckmate() != 0)
			  return;
		  
		  if (pieceMove) {
		  
			  Square bot = botMove();
			  
			  chessPiece.setVisible(false);
			  
			  JPanel panel = (JPanel)chessBoard.getComponent(bot.getY() * 8 + bot.getX());
			  
			  if (panel.getComponentCount() != 0) {
				  c =  panel.getComponent(0);
				  Container parent = c.getParent();
				  parent.remove(0);
				  parent.add( chessPiece );
			  }
			  else {
				  Container parent = (Container)panel;
				  parent.add( chessPiece );
			  }
			  board.incrementTurn();
			  chessPiece.setVisible(true);
		  }
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Square botMove() {
		int pieces = board.getPieces(PieceColor.BLACK).size();
		System.out.println(pieces);
		pieces = (int)(Math.random() * pieces);
		Piece randomPiece = board.getPieces(PieceColor.BLACK).get(pieces);
		int moves = randomPiece.getMoves(board).size();
		while (moves == 0) {
			pieces = board.getPieces(PieceColor.BLACK).size();
			pieces = (int)(Math.random() * pieces);
			randomPiece = board.getPieces(PieceColor.BLACK).get(pieces);
			moves = randomPiece.getMoves(board).size();
		}
		moves = (int)(Math.random() * moves);
		Square randomMove = randomPiece.getMoves(board).get(moves);
		
		JPanel panel = (JPanel)chessBoard.getComponent(randomPiece.getY() * 8 + randomPiece.getX());
		chessPiece = (JLabel)panel.getComponent(0);
		
		System.out.println(randomPiece.getX() + ", " + randomPiece.getY());
		
		while(!randomPiece.move(board, randomMove.getX(), randomMove.getY())) {
			pieces = board.getPieces(PieceColor.BLACK).size();
			pieces = (int)(Math.random() * pieces);
			randomPiece = board.getPieces(PieceColor.BLACK).get(pieces);
			moves = randomPiece.getMoves(board).size();
			panel = (JPanel)chessBoard.getComponent(randomPiece.getY() * 8 + randomPiece.getX());
			chessPiece = (JLabel)panel.getComponent(0);
			
			while (moves == 0) {
				pieces = board.getPieces(PieceColor.BLACK).size();
				pieces = (int)(Math.random() * pieces);
				randomPiece = board.getPieces(PieceColor.BLACK).get(pieces);
				moves = randomPiece.getMoves(board).size();
				panel = (JPanel)chessBoard.getComponent(randomPiece.getY() * 8 + randomPiece.getX());
				chessPiece = (JLabel)panel.getComponent(0);
			}
			moves = (int)(Math.random() * moves);
			randomMove = randomPiece.getMoves(board).get(moves);
		}
		return randomMove;
	}
	
	public void castle(PieceColor p) {
		if (p == PieceColor.WHITE) {
			Piece temp = board.getSquare(0, 7).getPiece();
			temp.setLocation(2, 7);
			board.getSquare(2, 7).setPiece(temp);
			board.getSquare(2, 7).getPiece().moved();
			board.getSquare(0, 7).setPiece(null);
			
			JPanel panel = (JPanel)chessBoard.getComponent(56);
			JLabel tempPiece = (JLabel)panel.getComponent(0);
			
			tempPiece.setVisible(false);
			panel.remove(0);
			
			panel = (JPanel)chessBoard.getComponent(58);
			panel.add(tempPiece);
			
			tempPiece.setVisible(true);
		}
		else {
			Piece temp = board.getSquare(0, 0).getPiece();
			temp.setLocation(2, 0);
			board.getSquare(2, 0).setPiece(temp);
			board.getSquare(2, 0).getPiece().moved();
			board.getSquare(0, 0).setPiece(null);
			
			JPanel panel = (JPanel)chessBoard.getComponent(0);
			JLabel tempPiece = (JLabel)panel.getComponent(0);
			
			tempPiece.setVisible(false);
			panel.remove(0);
			
			panel = (JPanel)chessBoard.getComponent(2);
			panel.add(tempPiece);
			
			tempPiece.setVisible(true);
		}
	}
	
	public static void main (String [] args) {
		JFrame frame = new ChessGame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	

}
