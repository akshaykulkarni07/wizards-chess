/* Speech Recognition Code for Wizards' Chess
 * v0.3.1
 * ChangeLog - 
		* Removed takeInput method to avoid error after first
		* move, migrated functionality to main method.
		* Made other methods static to access from other methods
		* using only class name.
 */

package chess;

//Imports
import chess.convert;

public class spc 
{
	public static int countWords(String str)
	{
		int words = 0;
		for (int i = 0; i < str.length() - 1; i++)
		{
			// if current character is space and next one is not, 
			// then we have found a word.
			if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ')
			{
				words++;
			}
		}
		// last word won't be detected by this method
		// so we add one manually
		words++;
		return words;
	}
	
	// function to take input from speech recognition
	// and returns integer array containing converted
	// numbers.
	public static int[] convert(String str)
	{
		// split string into words and store in word string array
		String[] word = str.split(" ");
		int[] pos = new int[4];
		// change words to integers and store in pos array
		for (int i = 0; i < 4; i++)
		{
			if (word[i].equals("ONE"))
			{
				pos[i] = 1;
			}
			else if (word[i].equals("TWO"))
			{
				pos[i] = 2;
			}
			else if (word[i].equals("THREE"))
			{
				pos[i] = 3;
			}
			else if (word[i].equals("FOUR"))
			{
				pos[i] = 4;
			}
			else if (word[i].equals("FIVE"))
			{
				pos[i] = 5;
			}
			else if (word[i].equals("SIX"))
			{
				pos[i] = 6;
			}
			else if (word[i].equals("SEVEN"))
			{
				pos[i] = 7;
			}
			else if (word[i].equals("EIGHT"))
			{
				pos[i] = 8;
			}
		}
		
		// CONVERTING COORDINATES FROM CHESS BOARD TO 2-D ARRAY
		int[] pos1 = new int[2];
		pos1 = convert.toArrayCoordinates(pos[0], pos[1]);
		pos[1] = pos1[0];
		pos[0] = pos1[1];
		pos1 = convert.toArrayCoordinates(pos[2], pos[3]);
		pos[3] = pos1[0];
		pos[2] = pos1[1];
		return pos;
	}
}