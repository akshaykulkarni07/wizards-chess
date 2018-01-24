/* Conversion Class for Wizards' Chess
 * v0.1
 * ChangeLog :
 	* Added 3 functions with descriptions
 	* This class is not expected to be modified further	
 */
package chess;

public class convert 
{
	public static int[] toBoardCoordinates(int x, int y)
	{
		int[] pos = new int[2];
		// CONVERTING COORDINATES FROM 2-D ARRAY TO CHESS BOARD
		// reverse process of toArrayCoordinates
		pos[0] = 8 - y;
		pos[1] = x + 1;
		return pos;
	}
	
	public static int[] toArrayCoordinates(int x, int y)
	{
		int[] pos = new int[2];
		// CONVERTING COORDINATES FROM CHESS BOARD TO 2-D ARRAY
		// subtract x coordinates from 7 because array numbering is inverted
		// w.r.t. board numbering
		// add one to x coordinates due to indexing from zero
		// subtract one from y coordinates due to indexing from zero
		// difference of addition and subtraction for x and y is due to
		// inverted nature w.r.t. x and not y.
		pos[0] = 7 - y + 1;
		pos[1] = x - 1;
		return pos;
	}
	
	public static int[] toGUICoordinates(int x, int y)
	{
		int pos[] = new int[2];
		// CONVERTING COORDINATES FROM ARRAY TO GUI
		pos[0] = 125 + (y * 100);
		pos[1] = 125 + (x * 100);
		return pos;
	}
}
