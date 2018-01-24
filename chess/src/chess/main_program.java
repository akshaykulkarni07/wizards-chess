/* Main Program for Wizards' Chess
 * v0.4.3
 * ChangeLog :
 	* Added else condition to 4 words rule, due to which any
 	* no. of words not 4 were giving invalid move.
 	* Added break statements to end game after completion of game.
 */
package chess;

import chess.spc;
import chess.tracking;
import chess.validation;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;
import chess.checking;
import chess.ChessBoard;
import chess.convert;

public class main_program
{
	public static void main(String args[]) throws IOException
	{
		ChessBoard gui = new ChessBoard();
		gui.animation(31, 125, 125);
		while (true)
		{
			// Configuration Object
			Configuration configuration = new Configuration();
		
			// Set path to the acoustic model.
			configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
			// Set path to the dictionary.
			configuration.setDictionaryPath("src/1032.dic");
			// Set path to the language model.
			configuration.setLanguageModelPath("src/1032.lm");
		
			// Recognizer object, Pass the Configuration object
			LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
		
			// Start Recognition Process (The boolean parameter clears the previous cache if true)
			recognize.startRecognition(true);
		
			// Create SpeechResult Object
			SpeechResult result;
		
			// Checking if recognizer has recognized the speech
			inner:
			while ((result = recognize.getResult()) != null) 
			{
				// Get the recognized speech
				String command = result.getHypothesis();
				System.out.println(command);
				while (!checking.is_white_checkmate(tracking.board) && !checking.is_black_checkmate(tracking.board) && !checking.is_white_stalemate(tracking.board) && !checking.is_black_stalemate(tracking.board) || !checking.is_draw(tracking.board))
				{
					int[] pos = new int[4];
					if (spc.countWords(command) == 4)
					{
						// if correct input is received
						// proceed to conversion into integers
						pos = spc.convert(command);
					}
					else
					{
						// in case input is incorrect, continue to start of while
						// loop with label inner
						continue inner;
					}
					// Also, because primary axis of alphabets from a - h corresponds to y
					// and secondary axis of numbers 1 - 8 corresponds to x, 
					// we have x1 correspond to 2nd coordinate spoken and so on.
					int x1 = pos[1];
					int y1 = pos[0];
					int x2 = pos[3];
					int y2 = pos[2];
					
					// check validity of input move
					boolean valid = validation.is_valid(x1, y1, x2, y2, tracking.board);
					if (valid != true)
					{
						System.out.println("Invalid move!");
						continue inner;
					}
					// subtract one from piece because array indexing
					// (in animation class) starts from zero
					int piece = tracking.board[x1][y1] - 1;
					int[] pos1 = new int[2];
					pos1 = convert.toGUICoordinates(x2, y2);
					int x3 = pos1[0];
					int y3 = pos1[1];
					
					// show animation
					gui.animation(piece, x3, y3);
					
					// update the board
					tracking.board = tracking.updateBoard(x1, y1, x2, y2, tracking.board);
					
					// change of turns
					if (tracking.turn == 1)
					{
						tracking.turn = 0;
					}
					else
					{
						tracking.turn = 1;
					}
				}
				// break out of loop once game has finished
				break;
			}
			// stop recognition process
			recognize.stopRecognition();
			// break out of while(true) loop
			break;
		}
	}
}