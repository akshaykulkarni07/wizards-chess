/* Tracking Class for Wizards'  Chess
 * v0.2.5
 * ChangeLog : 
 	* Added moves variable to count no. of moves since
 	* last pawn move or capture for both sides
 	* Added flag_capture variable to store whether previous move
 	* was a capture or pawn move to facilitate the 50 move rule
 */
package chess;

import java.io.*;
import chess.ChessBoard;

public class tracking
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
	
	// declare File objects
	public static File f1 = new File("00.txt");
	public static File f2 = new File("01.txt");
	
	// variable to indicate if previous move was a capture or not
	// 0 for not capture
	// 1 for capture
	public static int flag_white_capture = 0;
	public static int flag_black_capture = 0;
	// turn variable to track which side's move it is
	// 1 for white, 0 for black
	public static int turn = 1;
	// variable to store no. of moves since last capture or pawn move
	public static int moves_white = 0;
	public static int moves_black = 0;
	// 2D array to track the game
	public static int[][] board = new int[][]
	{
		{32, 31, 30, 29, 28, 27, 26, 25},
		{17, 18, 19, 20, 21, 22, 23, 24},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{16, 15, 14, 13, 12, 11, 10, 9},
		{1, 2, 3, 4, 5, 6, 7, 8}
	};
	
	// Method to update the board after a move
	public static int[][] updateBoard(int x1, int y1, int x2, int y2, int board[][])
	{
		// if final position was empty
		// swap the 2 positions
		if (board[x2][y2] == 0)
		{
			int temp = board[x1][y1];
			board[x1][y1] = board[x2][y2];
			board[x2][y2] = temp;
			// move was not a capture
			if (turn == 1)
			{
				flag_white_capture = 0;
			}
			else
			{
				flag_black_capture = 0;
			}
			// if it was a pawn move, then need
			// to reset the counter, so set flag to 1
			if (board[x2][y2] >= 9 && board[x2][y2] <= 24)
			{
				if (turn == 1)
				{
					flag_white_capture = 1;
				}
				else
				{
					flag_black_capture = 1;
				}
			}
		}
		// This also takes care of killing any piece
		// if not empty, move to final position
		// and set initial position to zero
		else
		{
			// captured piece visibility set to zero
			int p1 = board[x2][y2];
			// subtract 1 due to array indices starting from 0
			ChessBoard.z[p1 - 1] = 0;
			board[x2][y2] = board[x1][y1];
			board[x1][y1] = 0;
			// move was a capture
			if (turn == 1)
			{
				flag_white_capture = 1;
			}
			else
			{
				flag_black_capture = 1;
			}
		}
		// if there was a capture or pawn move, reset the moves counter
		// counting no. of moves since last capture or pawn move
		// reset to -1 so that next increment brings it to zero
		if (turn == 1)
		{
			if (flag_white_capture == 1)
			{
				moves_white = -1;
			}
		}
		else
		{
			if (flag_black_capture == 1)
			{
				moves_black = -1;
			}
		}
		// increment needed after move
		moves_black++;
		moves_white++;
		return board;
	}
	
	// Method to store current state of board in a file
	public static void storeBoard(int board[][])
	{
		// deleting f2 because renaming is not possible for
		// already existing files.
		if (f2.exists())
		{
			f2.delete();
		}
		// Rename to 01.txt to allow next move 
		// to be stored in 00.txt without losing this move.
		f1.renameTo(f2);
		try
		{
			// if parameter were true, means append to existing file
			// false means overwrite, which we want
			BufferedWriter os = new BufferedWriter(new FileWriter(f1, false));
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					os.write(board[i][j] + " ");
				}
				if (i != 7)
				{
					os.newLine();
				}
			}
			os.close();
		}
		catch (IOException e) 
		{
			System.out.println("Could not open file for writing");
		}
	}
	
	// Method to load board from file to array
	public static void loadBoard() throws IOException
	{
		BufferedReader sc = new BufferedReader(new FileReader("01.txt"));
		for (int i = 0; i < 8; i++)
		{
			String[] str;
			str = sc.readLine().split("\\s");
			for (int j = 0; j < 8; j++)
			{
				tracking.board[i][j] = Integer.parseInt(str[j]);
			}
		}
		sc.close();
	}
}