/* Validation class v0.7.2
 * Checks whether input move is possible or not
 * Returns 1 for legal move
 * Returns 0 for illegal move
 * ChangeLog :
	* In the bishop and rook method, added allowed piece
	* to be queen as well as those methods are called within
	* the queen method.
*/

package chess;

import chess.tracking;

public class validation
{
	// defining piece variables
	public final static int WHITE_ROOK_LEFT = 1;
	public final static int WHITE_KNIGHT_LEFT = 2;
	public final static int WHITE_BISHOP_LEFT = 3;
	public final static int WHITE_QUEEN = 4;
	public final static int WHITE_KING = 5;
	public final static int WHITE_BISHOP_RIGHT = 6;
	public final static int WHITE_KNIGHT_RIGHT = 7;
	public final static int WHITE_ROOK_RIGHT = 8;
	public final static int BLACK_ROOK_LEFT = 32;
	public final static int BLACK_KNIGHT_LEFT = 31;
	public final static int BLACK_BISHOP_LEFT = 30;
	public final static int BLACK_QUEEN = 29;
	public final static int BLACK_KING = 28;
	public final static int BLACK_BISHOP_RIGHT = 27;
	public final static int BLACK_KNIGHT_RIGHT = 26;
	public final static int BLACK_ROOK_RIGHT = 25;
	public final static int WHITE_PAWN_1 = 16;
	public final static int WHITE_PAWN_2 = 15;
	public final static int WHITE_PAWN_3 = 14;
	public final static int WHITE_PAWN_4 = 13;
	public final static int WHITE_PAWN_5 = 12;
	public final static int WHITE_PAWN_6 = 11;
	public final static int WHITE_PAWN_7 = 10;
	public final static int WHITE_PAWN_8 = 9;
	public final static int BLACK_PAWN_1 = 17;
	public final static int BLACK_PAWN_2 = 18;
	public final static int BLACK_PAWN_3 = 19;
	public final static int BLACK_PAWN_4 = 20;
	public final static int BLACK_PAWN_5 = 21;
	public final static int BLACK_PAWN_6 = 22;
	public final static int BLACK_PAWN_7 = 23;
	public final static int BLACK_PAWN_8 = 24;
	
	public static boolean is_valid(int x1, int y1, int x2, int y2, int board[][])
	{
		int piece = board[x1][y1];
		boolean flag = false;
		
		// check whose turn it is and which piece is being moved
		// WHITE TURN
		if (tracking.turn == 1)
		{
			// BLACK PIECES
			if (piece >= 17 && piece <= 32)
			{
				flag = false;
				return flag;
			}
		}
		// BLACK TURN
		else
		{
			// WHITE PIECES
			if (piece >= 1 && piece <= 16)
			{
				flag = false;
				return false;
			}
		}
		
		// For Rooks
		if (piece == WHITE_ROOK_LEFT || piece == WHITE_ROOK_RIGHT || 
		piece == BLACK_ROOK_LEFT || piece == BLACK_ROOK_RIGHT)
		{
			flag = rook(x1, y1, x2, y2, board);
		}
		// For Bishops
		else if (piece == WHITE_BISHOP_LEFT || piece == WHITE_BISHOP_RIGHT || 
		piece == BLACK_BISHOP_LEFT || piece == BLACK_BISHOP_RIGHT)
		{
			flag = bishop(x1, y1, x2, y2, board);
		}
		// For Queens
		else if (piece == WHITE_QUEEN || piece == BLACK_QUEEN)
		{
			flag = queen(x1, y1, x2, y2, board);
		}
		// For Kings
		else if (piece == WHITE_KING || piece == BLACK_KING)
		{
			flag = king(x1, y1, x2, y2, board);
		}
		// For Pawns
		else if (piece >= 9 && piece <= 24)
		{
			flag = pawn(x1, y1, x2, y2, board);
		}
		// For Knights
		else if (piece == WHITE_KNIGHT_LEFT || piece == WHITE_KNIGHT_RIGHT || piece == BLACK_KNIGHT_LEFT || piece == BLACK_KNIGHT_RIGHT)
		{
			flag = knight(x1, y1, x2, y2, board);
		}
		
		// if move is valid, check if same colored king
		// comes under check, if yes, then move is invalid
		if (flag)
		{
			int[][] board1 = new int[8][8];
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			tracking.updateBoard(x1, y1, x2, y2, board1);
			if (tracking.turn == 1)
			{
				if (checking.is_white_check(board1))
				{
					flag = false;
				}
			}
			else
			{
				if (checking.is_black_check(board1))
				{
					flag = false;
				}
			}
		}
		
		// finally we check the value of flag
		// if it is false, illegal move
		// if it is true, legal move.
		return flag;
	}
	
	public static boolean rook(int x1, int y1, int x2, int y2, int board[][])
	{
		// move is possible if final and initial rows or columns are same
		// but not both at the same time(i.e. final and initial position
		// is given same).
		boolean flag = false;
		if ((x1 == x2 && y1 != y2) || (x1 != x2 && y1 == y2))
		{
			flag = true;
		}
		else
		{
			return flag;
		}
		// flag1 is 1 if we swap y1 and y2 or x1 and x2
		// to make sure to swap them back later.
		int flag1 = 0;
		
		// now we check whether any pieces lie in the path
		
		// case where x1 == x2
		if (x1 == x2)
		{
			// if y1 is less, swap y1 and y2
			// i.e. make y1 larger in any case
			if (y1 < y2)
			{
				int temp = y2;
				y2 = y1;
				y1 = temp;
				flag1 = 1;
			}
			// initialize a counter
			int k = y2 + 1;
			// while there are all empty squares between [x1, y2 + 1] 
			// and [x1, y1 - 1], keep flag equal to 1
			// if not move is illegal, hence return 0
			while (board[x1][k] == 0 && k < y1)
			{
				flag = true;
				k++;
			}
			// if k is not y1, it means some square has a piece
			// and move cannot be completed, so return 0.
			if (k != y1)
			{
				flag = false;
				return false;
			}
			// if k is y1, then we have to check whether the piece(if any) at
			// [x1, y2] is the same color or not
			// because if it is, then move is not allowed
			// otherwise it is allowed
			
			// swap back y1 and y2 if necessary
			if (flag1 == 1)
			{
				int temp = y2;
				y2 = y1;
				y1 = temp;
				flag1 = 0;
			}
			
			// now check piece at [x1, y2]
			// white rook case
			if (board[x1][y1] == WHITE_ROOK_LEFT || board[x1][y1] == WHITE_ROOK_RIGHT)
			{
				// white piece at [x1, y2] or black king
				if ((board[x1][y2] >= 1 && board[x1][y2] <= 16) || board[x1][y2] == BLACK_KING)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			// black rook case
			else
			{
				// black piece at [x1, y2] or white king
				if ((board[x1][y2] >= 17 && board[x1][y2] <= 32) || board[x1][y2] == WHITE_KING)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		// case where y1 == y2
		else
		{
			// if x1 is less than x2, swap them
			// i.e. make x1 always larger
			if (x1 < x2)
			{
				int temp = x2;
				x2 = x1;
				x1 = temp;
				flag1 = 1;
			}
			// initialize a counter
			int k = x2 + 1;
			// while there are all empty squares between [x2 + 1, y1] 
			// and [x1 - 1, y1], keep flag 1
			// otherwise move is not allowed
			while (board[k][y1] == 0 && k < x1)
			{
				flag = true;
				k++;
			}
			// if now k is not x1, means some piece is there in the path
			if (k != x1)
			{
				flag = false;
				return false;
			}
			// if k is x1, then we have to check whether the piece(if any) at
			// [x2, y1] is the same color or not
			// because if it is, then move is not allowed
			// otherwise it is allowed
			
			// swap back x1 and x2 if necessary
			if (flag1 == 1)
			{
				int temp = x2;
				x2 = x1;
				x1 = temp;
				flag1 = 0;
			}
			
			// now check piece at [x2, y2]
			// white rook or queen case
			// because we use this method in the queen method
			if (board[x1][y1] == WHITE_ROOK_LEFT || board[x1][y1] == WHITE_ROOK_RIGHT || board[x1][y1] == WHITE_QUEEN)
			{
				// white piece at [x2, y2]
				if (board[x2][y2] >= 1 && board[x2][y2] <= 16)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			// black rook case
			else
			{
				// black piece at [x2, y2]
				if (board[x2][y2] >= 17 && board[x2][y2] <= 32)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
	}
	
	public static boolean bishop(int x1, int y1, int x2, int y2, int board[][])
	{
		boolean flag = false;
		// for bishops, initial and final positions must be in a diagonal sense.
		// so absolute difference of x and y coordinates 
		// must be same but not zero
		// as zero means that position itself
		// if not, then move is invalid
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		
		if (xdiff == ydiff && xdiff != 0)
		{
			flag = true;
		}
		else
		{
			return false;
		}
		
		// now that move is allowed on an empty board
		// we check if the path is clear
		if (x1 < x2 && y1 < y2)
		{
			for (int i = x1 + 1, j = y1 + 1; i < x2 && j < y2; i++, j++)
			{
				if (board[i][j] == 0)
				{
					// this place is empty, so continue
					// checking next place
					flag = true;
				}
				else
				{
					// invalid move
					return false;
				}
			}
		}
		else if (x1 < x2 && y1 > y2)
		{
			for (int i = x1 + 1, j = y1 - 1; i < x2 && j > y2; i++, j--)
			{
				if (board[i][j] == 0)
				{
					// this place is empty, so continue
					// checking next place
					flag = true;
				}
				else
				{
					// invalid move
					return false;
				}
			}
		}
		else if (x1 > x2 && y1 < y2)
		{
			for (int i = x1 - 1, j = y1 + 1; i > x2 && j < y2; i--, j++)
			{
				if (board[i][j] == 0)
				{
					// this place is empty, so continue
					// checking next place
					flag = true;
				}
				else
				{
					// invalid move
					return false;
				}
			}
		}
		else
		{
			for (int i = x1 - 1, j = y1 - 1; i > x2 && j > y2; i--, j--)
			{
				if (board[i][j] == 0)
				{
					// this place is empty, so continue
					// checking next place
					flag = true;
				}
				else
				{
					// invalid move
					return false;
				}
			}
		}
		// now we know that the path is clear
		// we check the final position
		
		// for white bishops or queen
		// as we use this method within the queen method
		if (board[x1][y1] == WHITE_BISHOP_LEFT || board[x1][y1] == WHITE_BISHOP_RIGHT || board[x1][y1] == WHITE_QUEEN)
		{
			// if there are white pieces or black king
			// move is invalid
			if ((board[x2][y2] >= 1 && board[x2][y2] <= 16) || board[x2][y2] == BLACK_KING)
			{
				flag = false;
				return flag;
			}
			// otherwise valid
			else
			{
				return true;
			}
		}
		// for black bishops
		else
		{
			// if there are black pieces or white king
			// move is invalid
			if ((board[x2][y2] >= 17 && board[x2][y2] <= 32) || board[x2][y2] == WHITE_KING)
			{
				flag = false;
				return flag;
			}
			// otherwise valid
			else
			{
				return true;
			}
		}
	}
	
	public static boolean queen(int x1, int y1, int x2, int y2, int board[][])
	{
		// for a queen, move is possible if either row is same, or column is same
		// or if initial and final positions are in diagonal sense
		// i.e. a combination of rook and bishop movements
		boolean flag = false;
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		// conditions for move possibility in an empty board
		if ((x1 == x2 && y1 != y2) || (x1 != x2 && y1 == y2))
		{
			// rook type move possible
			// so use that function already written
			flag = rook(x1, y1, x2, y2, board);
		}
		else if (xdiff == ydiff && xdiff != 0)
		{
			// bishop type move possible
			// so use that function already written
			flag = bishop(x1, y1, x2, y2, board);
		}
		return flag;
	}
	
	public static boolean king(int x1, int y1, int x2, int y2, int board[][])
	{
		// for a king, move is possible only if final position
		// shares a side or  an edge with initial position.
		boolean flag = false;
		
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		
		// first, we check whether move is possible on empty board
		
		// if final position is above or below current position
		if (xdiff == 0 && ydiff == 1)
		{
			flag = true;
		}
		// if final position is left or right of current position
		else if (xdiff == 1 && ydiff == 0)
		{
			flag = true;
		}
		// if final position shares edge with initial position
		else if (xdiff == 1 && ydiff == 1)
		{
			flag = true;
		}
		// if not any of the above cases
		// then move is invalid
		else
		{
			return flag;
		}
		
		// now that move is possible on empty board,
		// we check piece at final position, if any
		
		// for white king
		if (board[x1][y1] == WHITE_KING)
		{
			// if any white pieces or black king at final position
			// then, invalid move
			if ((board[x2][y2] >= 1 && board[x2][y2] <= 16) || (board[x2][y2] == BLACK_KING))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		// for black king
		else
		{
			// if any black pieces or white king at final position
			// then, invalid move
			if ((board[x2][y2] >= 17 && board[x2][y2] <= 32) || (board[x2][y2] == WHITE_KING))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
	
	public static boolean pawn(int x1, int y1, int x2, int y2, int board[][])
	{
		// for a pawn, move is valid if in the same column, or
		// diagonally one step if opponent piece is present or
		// two steps forward for first time
		
		boolean flag = false;
		
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		int piece = board[x1][y1];
		int piece_final = board[x2][y2];
		// for white pawns
		if (piece >= 9 && piece <= 16)
		{
			// same column and final row is above initial row
			// white is on the lower half of the board i.e. indexes are higher
			if (y1 == y2 && (x1 - x2 == 1 || (x1 - x2 == 2 && x1 == 6)))
			{
				flag = true;
				// now check final position for any piece
				// if empty, valid move
				if (piece_final == 0)
				{
					flag = true;
					return flag;
				}
				// otherwise return false
				else
				{
					flag = false;
					return flag;
				}
			}
			// diagonal one step and final row is lower
			else if (xdiff == 1 && ydiff == 1 && x1 > x2)
			{
				flag = true;
				// then check piece at final position
				// if filled with white piece or black king or empty
				// then invalid move
				if ((piece_final >= 1 && piece_final <= 16) || piece_final == BLACK_KING || piece_final == 0)
				{
					flag = false;
					return flag;
				}
				// otherwise valid
				else
				{
					flag = true;
					return flag;
				}
			}
			// if none of the above, then invalid
			else
			{
				flag = false;
				return flag;
			}
		}
		// for black pawns
		else if (piece >= 17 && piece <= 24)
		{
			// same column and final row greater than initial row
			if (y1 == y2 && (x2 - x1 == 1 || (x2 - x1 == 2 && x1 == 1)))
			{
				flag = true;
				// now check pieces at final position
				// if empty, valid move
				if (piece_final == 0)
				{
					flag = true;
					return flag;
				}
				// otherwise, invalid
				else
				{
					flag = false;
					return flag;
				}
			}
			// diagonal one step and final row is higher
			else if (xdiff == 1 && ydiff == 1 && x1 < x2)
			{
				flag = true;
				// now check piece at final position
				// if filled with black piece or white king or empty
				// then, invalid move
				if ((piece_final >= 17 && piece_final <= 32) || piece_final == WHITE_KING || piece_final == 0)
				{
					flag = false;
					return flag;
				}
				// otherwise, valid move
				else
				{
					flag = true;
					return flag;
				}
			}
			// if none of the above is true
			// then, invalid move
			else
			{
				flag = false;
				return flag;
			}
		}
		// if not a pawn
		else
		{
			return false;
		}
	}
	
	public static boolean knight(int x1, int y1, int x2, int y2, int board[][])
	{
		// for a knight, move is valid if final and initial
		// positions are the end points of the capital
		// letter L
		boolean flag = false;
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		
		// single step left or right and then 2 steps up or down cases 
		if (ydiff == 1 && xdiff == 2)
		{
			flag = true;
		}
		// single step up or down and then 2 steps left or right cases
		else if (xdiff == 1 && ydiff == 2)
		{
			flag = true;
		}
		// no other case possible
		else
		{
			return flag;
		}
		
		int piece = board[x1][y1];
		int piece_final = board[x2][y2];
		// now check piece at final position
		
		// for white knights
		if (piece == WHITE_KNIGHT_LEFT || piece == WHITE_KNIGHT_RIGHT)
		{
			// if final position has white pieces or black king
			// invalid move
			if ((piece_final >= 1 && piece_final <= 16) || piece_final == BLACK_KING)
			{
				flag = false;
				return flag;
			}
			// otherwise valid
			else
			{
				return flag;
			}
		}
		// for dark knights :P
		else
		{
			// if final position has black pieces or white king
			// invalid move
			if ((piece_final >= 17 && piece_final <= 32) || piece_final == WHITE_KING)
			{
				flag = false;
				return flag;
			}
			else
			{
				return flag;
			}
		}
	}
}
