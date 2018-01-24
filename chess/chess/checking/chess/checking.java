/* Checking 	Class for Wizards' Chess
 * v0.1.4
 * ChangeLog:
 	* In the checkmate methods, added check for piece not
 	* on board, which was not being checked earlier
 */
package chess;

public class checking
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
	
	// variable to store no. of pieces giving check simultaneously
	public static int check_count = 0;
	// variable to store piece giving check when there is
	// single check
	public static int check_piece = 0;
	
	public static boolean flag_white_rook = false;
	public static boolean flag_white_bishop = false;
	public static boolean flag_white_queen = false;
	public static boolean flag_white_knight = false;
	public static boolean flag_white_pawn = false;
	public static boolean flag_black_rook = false;
	public static boolean flag_black_bishop = false;
	public static boolean flag_black_queen = false;
	public static boolean flag_black_knight = false;
	public static boolean flag_black_pawn = false;
	
	// method to check which pieces are available on board
	// and set flags accordingly.
	public static void check_pieces(int board[][])
	{
		// check pieces on board
		// loop through all white pieces
		for (int i = 1; i <= 16; i++)
		{
			int[] pos = new int[2];
			// look for current piece
			pos = find(board, i);
			// if piece is found on board
			if (pos[0] != -1 && pos[1] != -1)
			{
				// pawn is present
				if (i >= 9 && i <= 16)
				{
					flag_white_pawn = true;
				}
				// rook is present
				else if (i == WHITE_ROOK_LEFT || i == WHITE_ROOK_RIGHT)
				{
					flag_white_rook = true;
				}
				// bishop is present
				else if (i == WHITE_BISHOP_LEFT || i == WHITE_BISHOP_RIGHT || i == BLACK_BISHOP_LEFT || i == BLACK_BISHOP_RIGHT)
				{
					flag_white_bishop = true;
				}
				// knight is present
				else if (i == WHITE_KNIGHT_LEFT || i == WHITE_KNIGHT_RIGHT || i == BLACK_KNIGHT_LEFT || i == BLACK_KNIGHT_RIGHT)
				{
					flag_white_knight = true;
				}
				// queen is present
				else if (i == WHITE_QUEEN || i == BLACK_QUEEN)
				{
					flag_white_queen = true;
				}
			}
		}
		// loop through black pieces now
		for (int i = 17; i <= 32; i++)
		{
			int[] pos = new int[2];
			// look for current piece
			pos = find(board, i);
			// if piece is found on board
			if (pos[0] != -1 && pos[1] != -1)
			{
				// pawn is present
				if (i >= 17 && i <= 24)
				{
					flag_black_pawn = true;
				}
				// rook is present
				else if (i == BLACK_ROOK_LEFT || i == BLACK_ROOK_RIGHT)
				{
					flag_black_rook = true;
				}
				// bishop is present
				else if (i == BLACK_BISHOP_LEFT || i == BLACK_BISHOP_RIGHT)
				{
					flag_black_bishop = true;
				}
				// knight is present
				else if (i == BLACK_KNIGHT_LEFT || i == BLACK_KNIGHT_RIGHT)
				{
					flag_black_knight = true;
				}
				// queen is present
				else if (i == BLACK_QUEEN)
				{
					flag_black_queen = true;
				}
			}
		}
	}
	
	// Method to determine if black king is in check
	public static boolean is_black_check(int board[][])
	{
		checking.check_piece = 0;
		checking.check_count = 0;
		boolean flag = false;
		// checking white pieces threatening black king
		// search for black king in array beforehand
		int[] pos_king = new int[2];
		// black king is denoted by 28 in array
		pos_king = checking.find(board, 28);
		int x2 = pos_king[0];
		int y2 = pos_king[1];
		for (int i = 1; i <= 16; i++)
		{
			// search for the piece in the array
			int[] pos = new int[2];
			pos = checking.find(board, i);
			int x1 = pos[0];
			int y1 = pos[1];
			// in case, not found, i.e. already killed
			// no need to check this
			if (x1 == -1 && y1 == -1)
			{
				continue;
			}
			else
			{
				int piece = board[x1][y1];
				// now check which piece it is and
				// call corresponding function
				// for rooks
				if (piece == WHITE_ROOK_LEFT || piece == WHITE_ROOK_RIGHT)
				{
					flag = rook(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for bishops
				else if (piece == WHITE_BISHOP_LEFT || piece == WHITE_BISHOP_RIGHT)
				{
					flag = bishop(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for queen
				else if (piece == WHITE_QUEEN)
				{
					flag = queen(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for pawns
				else if (piece >= 9 && piece <= 16)
				{
					flag = pawn(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for king
				else if (piece == WHITE_KING)
				{
					flag = king(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for knights
				else if (piece == WHITE_KNIGHT_LEFT || piece == WHITE_KNIGHT_RIGHT)
				{
					flag = knight(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				
				// if for any piece flag is true
				// then there is check on the king
				// otherwise continue checking
				if (flag == true)
				{
					return flag;
				}
			}
		}
		// in case control reaches here, white king is not in check
		// so return false
		flag = false;
		return flag;
	}
	
	// Method to determine if white king is in check
	public static boolean is_white_check(int board[][])
	{
		checking.check_piece = 0;
		checking.check_count = 0;
		boolean flag = false;
		// checking black pieces threatening white king
		// search for white king in array beforehand
		int[] pos_king = new int[2];
		// white king is denoted by 5 in array
		pos_king = checking.find(board, 5);
		int x2 = pos_king[0];
		int y2 = pos_king[1];
		for (int i = 17; i <= 32; i++)
		{
			// search for the piece in the array
			int[] pos = new int[2];
			pos = checking.find(board, i);
			int x1 = pos[0];
			int y1 = pos[1];
			// in case, not found, i.e. already killed
			// no need to check this
			if (x1 == -1 && y1 == -1)
			{
				continue;
			}
			else
			{
				int piece = board[x1][y1];
				// now check which piece it is and
				// call corresponding function
				// for rooks
				if (piece == BLACK_ROOK_LEFT || piece == BLACK_ROOK_RIGHT)
				{
					flag = rook(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for bishops
				else if (piece == BLACK_BISHOP_LEFT || piece == BLACK_BISHOP_RIGHT)
				{
					flag = bishop(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for queen
				else if (piece == BLACK_QUEEN)
				{
					flag = queen(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for pawns
				else if (piece >= 17 && piece <= 24)
				{
					flag = pawn(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for king
				else if (piece == BLACK_KING)
				{
					flag = king(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				// for knights
				else if (piece == BLACK_KNIGHT_LEFT || piece == BLACK_KNIGHT_RIGHT)
				{
					flag = knight(x1, y1, x2, y2, board);
					// if true, increment counter
					if (flag)
					{
						checking.check_count++;
						checking.check_piece = piece;
					}
				}
				
				// if for any piece flag is true
				// then there is check on the king
				// otherwise continue checking
				if (flag == true)
				{
					return flag;
				}
			}
		}
		// in case control reaches here, black king is not in check
		// so return false
		flag = false;
		return flag;
	}
	
	// Method to determine whether white is check mated
	public static boolean is_white_checkmate(int board[][])
	{
		boolean flag = false;
		// white king is denoted by 5
		int[] pos_white_king = find(board, 5);
		int x1 = pos_white_king[0];
		int y1 = pos_white_king[1];
		// check if white king is under check first
		boolean f1 = checking.is_white_check(board);
		// if it is, then we check for check mate
		if (f1 == true)
		{
			int[][] board1 = new int[8][8];
			// copying values of board to board1
			// for further use
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king upwards if possible
			if (x1 > 0)
			{
				if (board1[x1 - 1][y1] == 0 || ((board1[x1 - 1][y1] >= 17 && board1[x1 - 1][y1] <= 32) && board1[x1 - 1][y1] != 28))
				{
					board1 = tracking.updateBoard(x1, y1, x1 - 1, y1, board1);
					flag = is_white_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king downwards if possible
			if (x1 < 7)
			{
				if (board1[x1 + 1][y1] == 0 || ((board1[x1 + 1][y1] >= 17 && board1[x1 + 1][y1] <= 32) && board1[x1 + 1][y1] != 28))
				{
					board1 = tracking.updateBoard(x1, y1, x1 + 1, y1, board1);
					flag = is_white_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king right if possible
			if (y1 < 7)
			{
				if (board1[x1][y1 + 1] == 0 || ((board1[x1][y1 + 1] >= 17 && board1[x1][y1 + 1] <= 32) && board1[x1][y1 + 1] != 28))
				{
					board1 = tracking.updateBoard(x1, y1, x1, y1 + 1, board1);
					flag = is_white_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king left if possible
			if (y1 > 0)
			{
				if (board1[x1][y1 - 1] == 0 || ((board1[x1][y1 - 1] >= 17 && board1[x1][y1 - 1] <= 32) && board1[x1][y1 - 1] != 28))
				{
					board1 = tracking.updateBoard(x1, y1, x1, y1 - 1, board1);
					flag = is_white_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king upwards left diagonally if possible
			if (x1 > 0 && y1 > 0)
			{
				if (board1[x1 - 1][y1 - 1] == 0 || ((board1[x1 - 1][y1 - 1] >= 17 && board1[x1 - 1][y1 - 1] <= 32) && board1[x1 - 1][y1 -1] != 28))
				{
					board1 = tracking.updateBoard(x1, y1, x1 - 1, y1 - 1, board1);
					flag = is_white_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king upwards right diagonally if possible
			if (x1 > 0 && y1 < 7)
			{
				if (board1[x1 - 1][y1 + 1] == 0 || ((board1[x1 - 1][y1 + 1] >= 17 && board1[x1 - 1][y1 + 1] <= 32) && board1[x1 - 1][y1 + 1] != 28))
				{
					board1 = tracking.updateBoard(x1, y1, x1 - 1, y1 + 1, board1);
					flag = is_white_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king downwards right diagonally if possible
			if (x1 < 7 && y1 < 7)
			{
				if (board1[x1 + 1][y1 + 1] == 0 || ((board1[x1 + 1][y1 + 1] >= 17 && board1[x1 + 1][y1 + 1] <= 32) && board1[x1 + 1][y1 + 1] != 28))
				{
					board1 = tracking.updateBoard(x1, y1, x1 + 1, y1 + 1, board1);
					flag = is_white_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king downwards left diagonally if possible
			if (x1 < 7 && y1 > 0)
			{
				if (board1[x1 + 1][y1 - 1] == 0 || ((board1[x1 + 1][y1 - 1] >= 17 && board1[x1 + 1][y1 - 1] <= 32) && board1[x1 + 1][y1 - 1] != 28))
				{
					board1 = tracking.updateBoard(x1, y1, x1 + 1, y1 - 1, board1);
					flag = is_white_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// check again for check, to update piece giving
			// check and number of pieces giving check
			flag = is_white_check(board1);
			// in case control reaches here, flag is true
			// if king is in double check i.e. 2 pieces giving
			// check simultaneously, then check mate
			if (checking.check_count == 2)
			{
				System.out.println("White is checkmated");
				return true;
			}
			// otherwise, further checking needed
			else
			{
				// if control reaches here, there is only a single piece
				// which is giving check, so if it is a knight or a pawn,
				// we have to capture it to remove check
				if ((checking.check_piece >= 17 && checking.check_piece <= 24) || checking.check_piece == BLACK_KNIGHT_LEFT || checking.check_piece == BLACK_KNIGHT_RIGHT)
				{
					// find the checking piece on board
					int[] p = find(board1, checking.check_piece);
					int x = p[0];
					int y = p[1];
					// run a for loop through all other white pieces
					// and check if anyone can kill it
					for (int i = 1; i <= 16; i++)
					{
						// already checked for king, so continue to next piece
						if (i == WHITE_KING)
						{
							continue;
						}
						// find that piece on board
						int[] p1 = find(board1, i);
						int x3 = p1[0];
						int y3 = p1[1];
						flag = validation.is_valid(x3, y3, x, y, board1);
						flag = !flag;
						// if for any piece flag comes out true, then
						// not check mate
						if (!flag)
						{
							return flag;
						}
					}
					// if control reaches here, no piece could capture
					// checking piece, so check mate
					flag = true;
					return flag;
				}
				// for any other piece, either capture or block path to
				// remove check
				else
				{
					// find the checked king on board
					// king is denoted by 5
					int[] king = find(board1, 5);
					int x4 = king[0];
					int y4 = king[1];
					// find the checking piece on board
					int[] p = find(board1, checking.check_piece);
					int x = p[0];
					int y = p[1];
					// run a loop through all other pieces and check
					for (int i = 1; i <= 16; i++)
					{
						// already checked for king, so continue to next piece
						if (i == WHITE_KING)
						{
							continue;
						}
						// find that piece on board
						int[] p1 = find(board1, i);
						int x3 = p1[0];
						int y3 = p1[1];
						// check if killing is possible
						flag = validation.is_valid(x3, y3, x, y, board1);
						flag = !flag;
						// if for any piece, flag comes out true, then
						// not check mate
						if (!flag)
						{
							flag = false;
							return flag;
						}
						// if killing is not possible, check for blocking
						
						if (checking.check_piece == BLACK_ROOK_LEFT || checking.check_piece == BLACK_ROOK_RIGHT || checking.check_piece == BLACK_QUEEN)
						{
							int xdiff = Math.abs(x - x4);
							int ydiff = Math.abs(y - y4);
							// if rook or queen and king are in same row and have
							// at least 1 space free in between
							if (xdiff == 0 && ydiff > 1)
							{
								for (int k = Math.min(y, y4) + 1; k < Math.max(y, y4); k++)
								{
									flag = validation.is_valid(x3, y3, x, k, board1);
									flag = !flag;
									// if flag is true, then not check mate
									if (!flag)
									{
										flag = false;
										return flag;
									}
								}
							}
							// if rook or queen and king are in same column and have
							// at least 1 space free in between
							else if (xdiff > 1 && ydiff == 0)
							{
								for (int k = Math.min(x, x4) + 1; k < Math.max(x, x4); k++)
								{
									flag = validation.is_valid(x3, y3, k, y, board1);
									flag = !flag;
									// if flag is true, then not check mate
									if (!flag)
									{
										flag = false;
										return flag;
									}
								}
							}
						}
						else if (checking.check_piece == BLACK_BISHOP_LEFT || checking.check_piece == BLACK_BISHOP_RIGHT || checking.check_piece == BLACK_QUEEN)
						{
							int xdiff = Math.abs(x - x4);
							int ydiff = Math.abs(y - y4);
							// queen or bishop and king are in a diagonal sense
							// but at least one square in between is free
							if (xdiff == ydiff && xdiff > 1)
							{
								// swapping if necessary
								if (y4 > y)
								{
									int temp = y4;
									y4 = y;
									y = temp;
								}
								if (x4 > x)
								{
									int temp = x4;
									x4 = x;
									x = temp;
								}
								
								// check if check can be blocked
								for (int k = x4 + 1, m = y4 + 1; k < x && m < y; k++, m++)
								{
									flag = validation.is_valid(x3, y3, k, m, board1);
									flag = !flag;
									// if valid, then not check mate
									if (!flag)
									{
										flag = false;
										return flag;
									}
								}
							}
						}
					}
					// if control reaches here, means no piece
					// can remove check from king so check mate.
					flag = true;
					System.out.println("White is checkmated");
					return flag;
				}
			}
		}
		// if not under check, check mate is impossible
		else
		{
			flag = false;
			return flag;
		}
	}
	
	// Method to determine whether black is check mated
	public static boolean is_black_checkmate(int board[][])
	{
		boolean flag = false;
		// black king is denoted by 28
		int[] pos_black_king = find(board, 28);
		int x1 = pos_black_king[0];
		int y1 = pos_black_king[1];
		// check if black king is under check first
		boolean f1 = checking.is_black_check(board);
		// if it is, then we check for check mate
		if (f1 == true)
		{
			int[][] board1 = new int[8][8];
			// copying values of board to board1
			// for further use
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king upwards if possible
			if (x1 > 0)
			{
				if (board1[x1 - 1][y1] == 0 || ((board1[x1 - 1][y1] >= 1 && board1[x1 - 1][y1] <= 16) && board1[x1 - 1][y1] != 5))
				{
					board1 = tracking.updateBoard(x1, y1, x1 - 1, y1, board1);
					flag = is_black_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king downwards if possible
			if (x1 < 7)
			{
				if (board1[x1 + 1][y1] == 0 || ((board1[x1 + 1][y1] >= 1 && board1[x1 + 1][y1] <= 16) && board1[x1 + 1][y1] != 5))
				{
					board1 = tracking.updateBoard(x1, y1, x1 + 1, y1, board1);
					flag = is_black_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king right if possible
			if (y1 < 7)
			{
				if (board1[x1][y1 + 1] == 0 || ((board1[x1][y1 + 1] >= 1 && board1[x1][y1 + 1] <= 16) && board1[x1][y1 + 1] != 5))
				{
					board1 = tracking.updateBoard(x1, y1, x1, y1 + 1, board1);
					flag = is_black_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king left if possible
			if (y1 > 0)
			{
				if (board1[x1][y1 - 1] == 0 || ((board1[x1][y1 - 1] >= 1 && board1[x1][y1 - 1] <= 16) && board1[x1][y1 - 1] != 5))
				{
					board1 = tracking.updateBoard(x1, y1, x1, y1 - 1, board1);
					flag = is_black_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king upwards left diagonally if possible
			if (x1 > 0 && y1 > 0)
			{
				if (board1[x1 - 1][y1 - 1] == 0 || ((board1[x1 - 1][y1 - 1] >= 1 && board1[x1 - 1][y1 - 1] <= 16) && board1[x1 - 1][y1 -1] != 5))
				{
					board1 = tracking.updateBoard(x1, y1, x1 - 1, y1 - 1, board1);
					flag = is_black_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king upwards right diagonally if possible
			if (x1 > 0 && y1 < 7)
			{
				if (board1[x1 - 1][y1 + 1] == 0 || ((board1[x1 - 1][y1 + 1] >= 1 && board1[x1 - 1][y1 + 1] <= 16) && board1[x1 - 1][y1 + 1] != 5))
				{
					board1 = tracking.updateBoard(x1, y1, x1 - 1, y1 + 1, board1);
					flag = is_black_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king downwards right diagonally if possible
			if (x1 < 7 && y1 < 7)
			{
				if (board1[x1 + 1][y1 + 1] == 0 || ((board1[x1 + 1][y1 + 1] >= 1 && board1[x1 + 1][y1 + 1] <= 16) && board1[x1 + 1][y1 + 1] != 5))
				{
					board1 = tracking.updateBoard(x1, y1, x1 + 1, y1 + 1, board1);
					flag = is_black_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// update board to move king downwards left diagonally if possible
			if (x1 < 7 && y1 > 0)
			{
				if (board1[x1 + 1][y1 - 1] == 0 || ((board1[x1 + 1][y1 - 1] >= 1 && board1[x1 + 1][y1 - 1] <= 16) && board1[x1 + 1][y1 - 1] != 5))
				{
					board1 = tracking.updateBoard(x1, y1, x1 + 1, y1 - 1, board1);
					flag = is_black_check(board1);
					// if flag is false, move is possible
					// it is not check mate
					if (flag == false)
					{
						return false;
					}
					// if it is true, we continue checking
				}
			}
			// in case control reaches here, flag is true
			// reset board1 to current position
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board1[i][j] = board[i][j];
				}
			}
			// check again for check, to update piece giving
			// check and number of pieces giving check
			flag = is_black_check(board1);
			// in case control reaches here, flag is true
			// if king is in double check i.e. 2 pieces giving
			// check simultaneously, then check mate
			if (checking.check_count == 2)
			{
				System.out.println("Black is checkmated");
				return true;
			}
			// otherwise, further checking needed
			else
			{
				// if control reaches here, there is only a single piece
				// which is giving check, so if it is a knight or a pawn,
				// we have to capture it to remove check
				if ((checking.check_piece >= 9 && checking.check_piece <= 16) || checking.check_piece == WHITE_KNIGHT_LEFT || checking.check_piece == WHITE_KNIGHT_RIGHT)
				{
					// find the checking piece on board
					int[] p = find(board1, checking.check_piece);
					int x = p[0];
					int y = p[1];
					// run a for loop through all other black pieces
					// and check if anyone can kill it
					for (int i = 17; i <= 32; i++)
					{
						// already checked for king, so continue to next piece
						if (i == BLACK_KING)
						{
							continue;
						}
						// find that piece on board
						int[] p1 = find(board1, i);
						int x3 = p1[0];
						int y3 = p1[1];
						if (x3 == -1 && y3 == -1)
						{
							// could not find that piece on board
							// so move to next iteration.
							continue;
						}
						flag = validation.is_valid(x3, y3, x, y, board1);
						flag = !flag;
						// if for any piece flag comes out true, then
						// not check mate
						if (!flag)
						{
							return flag;
						}
					}
					// if control reaches here, no piece could capture
					// checking piece, so check mate
					flag = true;
					return flag;
				}
				// for any other piece, either capture or block path to
				// remove check
				else
				{
					// find the checked king on board
					// king is denoted by 28
					int[] king = find(board1, 28);
					int x4 = king[0];
					int y4 = king[1];
					// find the checking piece on board
					int[] p = find(board1, checking.check_piece);
					int x = p[0];
					int y = p[1];
					// run a loop through all other pieces and check
					for (int i = 17; i <= 32; i++)
					{
						// already checked for king, so continue to next piece
						if (i == BLACK_KING)
						{
							continue;
						}
						// find that piece on board
						int[] p1 = find(board1, i);
						int x3 = p1[0];
						int y3 = p1[1];
						// check if killing is possible
						if (x3 == -1 && y3 == -1)
						{
							// could not find that piece on board
							// so move to next iteration.
							continue;
						}
						flag = validation.is_valid(x3, y3, x, y, board1);
						flag = !flag;
						// if for any piece, flag comes out true, then
						// not check mate
						if (!flag)
						{
							flag = false;
							return flag;
						}
						// if killing is not possible, check for blocking
						
						if (checking.check_piece == WHITE_ROOK_LEFT || checking.check_piece == WHITE_ROOK_RIGHT || checking.check_piece == WHITE_QUEEN)
						{
							int xdiff = Math.abs(x - x4);
							int ydiff = Math.abs(y - y4);
							// if rook or queen and king are in same row and have
							// at least 1 space free in between
							if (xdiff == 0 && ydiff > 1)
							{
								for (int k = Math.min(y, y4) + 1; k < Math.max(y, y4); k++)
								{
									flag = validation.is_valid(x3, y3, x, k, board1);
									flag = !flag;
									// if flag is true, then not check mate
									if (!flag)
									{
										flag = false;
										return flag;
									}
								}
							}
							// if rook or queen and king are in same column and have
							// at least 1 space free in between
							else if (xdiff > 1 && ydiff == 0)
							{
								for (int k = Math.min(x, x4) + 1; k < Math.max(x, x4); k++)
								{
									flag = validation.is_valid(x3, y3, k, y, board1);
									flag = !flag;
									// if flag is true, then not check mate
									if (!flag)
									{
										flag = false;
										return flag;
									}
								}
							}
						}
						else if (checking.check_piece == WHITE_BISHOP_LEFT || checking.check_piece == WHITE_BISHOP_RIGHT || checking.check_piece == WHITE_QUEEN)
						{
							int xdiff = Math.abs(x - x4);
							int ydiff = Math.abs(y - y4);
							// queen or bishop and king are in a diagonal sense
							// but at least one square in between is free
							if (xdiff == ydiff && xdiff > 1)
							{
								// swapping if necessary
								if (y4 > y)
								{
									int temp = y4;
									y4 = y;
									y = temp;
								}
								if (x4 > x)
								{
									int temp = x4;
									x4 = x;
									x = temp;
								}
								
								// check if check can be blocked
								for (int k = x4 + 1, m = y4 + 1; k < x && m < y; k++, m++)
								{
									flag = validation.is_valid(x3, y3, k, m, board1);
									flag = !flag;
									// if valid, then not check mate
									if (!flag)
									{
										flag = false;
										return flag;
									}
								}
							}
						}
					}
					// if control reaches here, means no piece
					// can remove check from king so check mate.
					flag = true;
					System.out.println("Black is checkmated");
					return flag;
				}
			}
		}
		// if not under check, check mate is impossible
		else
		{
			flag = false;
			return flag;
		}
	}
	
	// Method to determine draw
	public static boolean is_draw(int board[][])
	{
		check_pieces(board);
		// now we know what pieces are on the board
		// so we evaluate various cases
		
		// only kings and knight present, draw
		if (!flag_black_pawn && !flag_black_rook && !flag_black_bishop && !flag_black_queen && (flag_black_knight || flag_white_knight) && !flag_white_pawn && !flag_white_rook && !flag_white_bishop && !flag_white_queen)
		{
			return true;
		}
		// only kings and bishops present, draw
		else if (!flag_black_pawn && !flag_black_rook && !flag_black_knight && !flag_black_queen && (flag_black_bishop || flag_white_bishop) && !flag_white_pawn && !flag_white_rook && !flag_white_knight && !flag_white_queen)
		{
			return true;
		}
		// only kings present, draw
		else if (!flag_black_pawn && !flag_black_rook && !flag_black_bishop && !flag_black_queen && !flag_black_knight && !flag_white_knight && !flag_white_pawn && !flag_white_rook && !flag_white_bishop && !flag_white_queen)
		{
			return true;
		}
		// if only white king is remaining
		// check 50 moves rule
		else if (!flag_white_pawn && !flag_white_rook && !flag_white_queen && !flag_white_bishop && !flag_white_knight)
		{
			if (tracking.moves_white >= 50)
			{
				return true;
			}
			// otherwise game can go on
			else
			{
				return false;
			}
		}
		// if only black king is remaining
		// check 50 moves rule
		else if (!flag_black_pawn && !flag_black_rook && !flag_black_bishop && !flag_black_queen && !flag_black_knight)
		{
			if (tracking.moves_black >= 50)
			{
				return true;
			}
			// otherwise game can go on
			else
			{
				return false;
			}
		}
		// in other cases, not a draw
		else
		{
			return false;
		}
	}
	
	// Method to determine whether white is stale mate
	public static boolean is_white_stalemate(int board[][])
	{
		check_pieces(board);
		// in case only white king left
		if (!flag_white_queen && !flag_white_rook && !flag_white_bishop && !flag_white_knight && !flag_white_pawn)
		{
			boolean flag = false;
			// white king is denoted by 5
			int[] pos_white_king = find(board, 5);
			int x1 = pos_white_king[0];
			int y1 = pos_white_king[1];
			// check if white king is under check first
			boolean f1 = checking.is_white_check(board);
			// if it is not, then we check for stale mate
			if (!f1)
			{
				int[][] board1 = new int[8][8];
				// copying values of board to board1
				// for further use
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king upwards if possible
				if (x1 > 0)
				{
					if (board1[x1 - 1][y1] == 0 || ((board1[x1 - 1][y1] >= 17 && board1[x1 - 1][y1] <= 32) && board1[x1 - 1][y1] != 28))
					{
						board1 = tracking.updateBoard(x1, y1, x1 - 1, y1, board1);
						flag = is_white_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king downwards if possible
				if (x1 < 7)
				{
					if (board1[x1 + 1][y1] == 0 || ((board1[x1 + 1][y1] >= 17 && board1[x1 + 1][y1] <= 32) && board1[x1 + 1][y1] != 28))
					{
						board1 = tracking.updateBoard(x1, y1, x1 + 1, y1, board1);
						flag = is_white_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king right if possible
				if (y1 < 7)
				{
					if (board1[x1][y1 + 1] == 0 || ((board1[x1][y1 + 1] >= 17 && board1[x1][y1 + 1] <= 32) && board1[x1][y1 + 1] != 28))
					{
						board1 = tracking.updateBoard(x1, y1, x1, y1 + 1, board1);
						flag = is_white_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king left if possible
				if (y1 > 0)
				{
					if (board1[x1][y1 - 1] == 0 || ((board1[x1][y1 - 1] >= 17 && board1[x1][y1 - 1] <= 32) && board1[x1][y1 - 1] != 28))
					{
						board1 = tracking.updateBoard(x1, y1, x1, y1 - 1, board1);
						flag = is_white_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king upwards left diagonally if possible
				if (x1 > 0 && y1 > 0)
				{
					if (board1[x1 - 1][y1 - 1] == 0 || ((board1[x1 - 1][y1 - 1] >= 17 && board1[x1 - 1][y1 - 1] <= 32) && board1[x1 - 1][y1 -1] != 28))
					{
						board1 = tracking.updateBoard(x1, y1, x1 - 1, y1 - 1, board1);
						flag = is_white_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king upwards right diagonally if possible
				if (x1 > 0 && y1 < 7)
				{
					if (board1[x1 - 1][y1 + 1] == 0 || ((board1[x1 - 1][y1 + 1] >= 17 && board1[x1 - 1][y1 + 1] <= 32) && board1[x1 - 1][y1 + 1] != 28))
					{
						board1 = tracking.updateBoard(x1, y1, x1 - 1, y1 + 1, board1);
						flag = is_white_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king downwards right diagonally if possible
				if (x1 < 7 && y1 < 7)
				{
					if (board1[x1 + 1][y1 + 1] == 0 || ((board1[x1 + 1][y1 + 1] >= 17 && board1[x1 + 1][y1 + 1] <= 32) && board1[x1 + 1][y1 + 1] != 28))
					{
						board1 = tracking.updateBoard(x1, y1, x1 + 1, y1 + 1, board1);
						flag = is_white_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king downwards left diagonally if possible
				if (x1 < 7 && y1 > 0)
				{
					if (board1[x1 + 1][y1 - 1] == 0 || ((board1[x1 + 1][y1 - 1] >= 17 && board1[x1 + 1][y1 - 1] <= 32) && board1[x1 + 1][y1 - 1] != 28))
					{
						board1 = tracking.updateBoard(x1, y1, x1 + 1, y1 - 1, board1);
						flag = is_white_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// if control reaches here, flag remained true
				// through all conditions, so it is stale mate
				return true;
			}
			// if king is in check, then it cannot be stale mate
			else
			{
				return false;
			}
		}
		// if control reaches here, not stale mate
		return false;
	}
	
	// Method to determine whether black is stale
	public static boolean is_black_stalemate(int board[][])
	{
		check_pieces(board);
		// if only black king is left
		if (!flag_black_queen && !flag_black_rook && !flag_black_bishop && !flag_black_knight && !flag_black_pawn)
		{
			boolean flag = false;
			// black king is denoted by 28
			int[] pos_black_king = find(board, 28);
			int x1 = pos_black_king[0];
			int y1 = pos_black_king[1];
			// check if black king is under check first
			boolean f1 = checking.is_black_check(board);
			// if it is not, then we check for stale mate
			if (!f1)
			{
				int[][] board1 = new int[8][8];
				// copying values of board to board1
				// for further use
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king upwards if possible
				if (x1 > 0)
				{
					if (board1[x1 - 1][y1] == 0 || ((board1[x1 - 1][y1] >= 1 && board1[x1 - 1][y1] <= 16) && board1[x1 - 1][y1] != 5))
					{
						board1 = tracking.updateBoard(x1, y1, x1 - 1, y1, board1);
						flag = is_black_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king downwards if possible
				if (x1 < 7)
				{
					if (board1[x1 + 1][y1] == 0 || ((board1[x1 + 1][y1] >= 1 && board1[x1 + 1][y1] <= 16) && board1[x1 + 1][y1] != 5))
					{
						board1 = tracking.updateBoard(x1, y1, x1 + 1, y1, board1);
						flag = is_black_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king right if possible
				if (y1 < 7)
				{
					if (board1[x1][y1 + 1] == 0 || ((board1[x1][y1 + 1] >= 1 && board1[x1][y1 + 1] <= 16) && board1[x1][y1 + 1] != 5))
					{
						board1 = tracking.updateBoard(x1, y1, x1, y1 + 1, board1);
						flag = is_black_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king left if possible
				if (y1 > 0)
				{
					if (board1[x1][y1 - 1] == 0 || ((board1[x1][y1 - 1] >= 1 && board1[x1][y1 - 1] <= 16) && board1[x1][y1 - 1] != 5))
					{
						board1 = tracking.updateBoard(x1, y1, x1, y1 - 1, board1);
						flag = is_black_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king upwards left diagonally if possible
				if (x1 > 0 && y1 > 0)
				{
					if (board1[x1 - 1][y1 - 1] == 0 || ((board1[x1 - 1][y1 - 1] >= 1 && board1[x1 - 1][y1 - 1] <= 16) && board1[x1 - 1][y1 -1] != 5))
					{
						board1 = tracking.updateBoard(x1, y1, x1 - 1, y1 - 1, board1);
						flag = is_black_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king upwards right diagonally if possible
				if (x1 > 0 && y1 < 7)
				{
					if (board1[x1 - 1][y1 + 1] == 0 || ((board1[x1 - 1][y1 + 1] >= 1 && board1[x1 - 1][y1 + 1] <= 16) && board1[x1 - 1][y1 + 1] != 5))
					{
						board1 = tracking.updateBoard(x1, y1, x1 - 1, y1 + 1, board1);
						flag = is_black_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king downwards right diagonally if possible
				if (x1 < 7 && y1 < 7)
				{
					if (board1[x1 + 1][y1 + 1] == 0 || ((board1[x1 + 1][y1 + 1] >= 1 && board1[x1 + 1][y1 + 1] <= 16) && board1[x1 + 1][y1 + 1] != 5))
					{
						board1 = tracking.updateBoard(x1, y1, x1 + 1, y1 + 1, board1);
						flag = is_black_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// update board to move king downwards left diagonally if possible
				if (x1 < 7 && y1 > 0)
				{
					if (board1[x1 + 1][y1 - 1] == 0 || ((board1[x1 + 1][y1 - 1] >= 1 && board1[x1 + 1][y1 - 1] <= 16) && board1[x1 + 1][y1 - 1] != 5))
					{
						board1 = tracking.updateBoard(x1, y1, x1 + 1, y1 - 1, board1);
						flag = is_black_check(board1);
						// if flag is false, move is possible
						// it is not check mate
						if (flag == false)
						{
							return false;
						}
						// if it is true, we continue checking
					}
				}
				// in case control reaches here, flag is true
				// reset board1 to current position
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board1[i][j] = board[i][j];
					}
				}
				// if flag is true through all these conditions
				// then it is a stalemate
				return true;
			}
			// if king is in check, then it cannot be stale mate
			else
			{
				return false;
			}
		}
		// if control reaches here, not stale mate
		return false;
	}
	
	// Method to find position of any given piece on board
	// returns (-1, -1) if not found
	// Method to find the position of piece in array
	// otherwise returns -1, -1
	public static int[] find(int board[][], int piece)
	{
		int[] pos = new int[2];
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (board[i][j] == piece)
				{
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		pos[0] = -1;
		pos[1] = -1;
		return pos;
	}
	
	// Method to check whether king is at risk from ROOK
	public static boolean rook(int x1, int y1, int x2, int y2, int board[][])
	{
		boolean flag = false;
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		// if king and rook in same row or column
		if ((xdiff == 0 && ydiff != 0) || (xdiff != 0 && ydiff == 0))
		{
			// maybe in check, needs further checking
			flag = true;
		}
		else
		{
			// not in check
			flag = false;
			return flag;
		}
		
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
			}
			// initialize a counter
			int k = y2 + 1;
			// while there are all empty squares between [x1, y2 + 1] 
			// and [x1, y1 - 1], keep flag equal to 1
			// if not then not in check, hence return false
			while (board[x1][k] == 0 && k < y1)
			{
				flag = true;
				k++;
			}
			// if k is not y1, it means some square has a piece
			// and king is not in check, so return false.
			if (k != y1)
			{
				flag = false;
				return false;
			}
			// if k is y1, then king is in check
			else
			{
				flag = true;
				return flag;
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
			}
			// initialize a counter
			int k = x2 + 1;
			// while there are all empty squares between [x2 + 1, y1] 
			// and [x1 - 1, y1], keep flag 1
			// otherwise it is not in check
			while (board[k][y1] == 0 && k < x1)
			{
				flag = true;
				k++;
			}
			// if now k is not x1, means some piece is there in the path
			// so not in check
			if (k != x1)
			{
				flag = false;
				return flag;
			}
			// if k is x1, then king is in check
			else
			{
				flag = true;
				return flag;
			}
		}
	}
	
	// Method to check whether king is at risk from BISHOP
	public static boolean bishop(int x1, int y1, int x2, int y2, int board[][])
	{
		boolean flag = false;
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		// for king to be in check, xdiff should be equal to ydiff
		if (xdiff != ydiff)
		{
			// if not, king is not in check
			flag = false;
			return flag;
		}
		// otherwise, we need to check further
		else
		{
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
						// not in check
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
						// not in check
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
						// not in check
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
						// not in check
						return false;
					}
				}
			}
			// if control reaches here, king is in check
			flag = true;
			return flag;
		}
	}
	
	// Method to check whether king is at risk from QUEEN
	public static boolean queen(int x1, int y1, int x2, int y2, int board[][])
	{
		// for a queen, king will be in check if in the same row, column 
		// or if their positions are in a diagonal sense.
		boolean flag = false;
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		// if king and queen are in rook orientation
		if ((x1 == x2 && y1 != y2) || (x1 != x2 && y1 == y2))
		{
			// rook type move possible
			// so use that function already written
			flag = rook(x1, y1, x2, y2, board);
		}
		// if king and queen are in bishop orientation
		else if (xdiff == ydiff && xdiff != 0)
		{
			// bishop type move possible
			// so use that function already written
			flag = bishop(x1, y1, x2, y2, board);
		}
		// otherwise not in check, so flag is already false
		return flag;
	}
	
	// Method to check whether king is at risk from PAWN
	public static boolean pawn(int x1, int y1, int x2, int y2, int board[][])
	{
		boolean flag = false;
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		int piece = board[x1][y1];
		// if it is a white pawn
		if (piece >= 9 && piece <= 16)
		{
			// diagonal one step and final row is lower
			if (xdiff == 1 && ydiff == 1 && x1 > x2)
			{
				// then king is in check
				flag = true;
				return flag;
			}
			else
			{
				// not in check
				return flag;
			}
		}
		// if it is a black pawn
		else
		{
			if (xdiff == 1 && ydiff == 1 && x1 < x2)
			{
				// then king is in check
				flag = true;
				return flag;
			}
			else
			{
				// not in check
				return flag;
			}
		}
	}
	
	// Method to check whether king is at risk from the other KING
	public static boolean king(int x1, int y1, int x2, int y2, int board[][])
	{
		boolean flag = false;
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		
		// in case king is adjacent to other king
		if ((xdiff == 0 && ydiff == 1) || (xdiff == 1 && ydiff == 0) || (xdiff == 1 && ydiff == 1))
		{
			// king is in check
			flag = true;
			return flag;
		}
		else
		{
			// otherwise not in check
			return flag;
		}
	}
	
	// Method to check whether king is at risk from KNIGHT
	public static boolean knight(int x1, int y1, int x2, int y2, int board[][])
	{
		boolean flag = false;
		int xdiff = Math.abs(x1 - x2);
		int ydiff = Math.abs(y1 - y2);
		
		// in case knight is threatening king
		if ((xdiff == 2 && ydiff == 1) || (ydiff == 2 && xdiff == 1))
		{
			// king is in check
			flag = true;
			return flag;
		}
		// otherwise not in check
		else
		{
			return false;
		}
	}
}