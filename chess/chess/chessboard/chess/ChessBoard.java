/* Animation and ChessBoard Class for Wizards' Chess
 * v0.5.1
 * ChangeLog :
 	* Added code to make piece disappear in case it gets captured
 */
package chess;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessBoard 
{
	// ARRAYS TO STORE X AND Y COORDINATITES OF 32 PIECES
	static int [] x= new int[]
				{125, 225, 325, 425, 525, 625, 725, 825, 825, 725, 625, 525, 425, 325, 225, 125, 125, 225, 325, 425, 525, 625, 725, 825, 825, 725, 625, 525, 425, 325, 225, 125};
	static int [] y = new int[]
				{825, 825, 825, 825, 825, 825, 825, 825, 725, 725, 725, 725, 725, 725, 725, 725, 225, 225, 225, 225, 225, 225, 225, 225, 125, 125, 125, 125, 125, 125, 125, 125};
	public static int[] z = new int[]
			{
					1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
			};
	public void animation( int piece, int x2, int y2)
	{
		// MAIN FRAME AND ALL LABELS ADDED IN THE MAIN FRAME 
		JFrame frame = new JFrame();
		frame.setSize(1500,1000);
		frame.setTitle("WIZARDS' CHESS");
		frame.setLocationRelativeTo(null);
		DrawPanel draw = new DrawPanel();
		frame.getContentPane().add(draw);
		frame.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Font thefont = new Font("TimesRoman",Font.BOLD,30);
		JLabel p1 = new JLabel();
		JLabel p2 = new JLabel();
		JLabel check = new JLabel();
		JLabel checkmate = new JLabel();
		JLabel winner = new JLabel();
		JButton restart = new JButton();
		JButton undo = new JButton();
		p1.setText("PLAYER 1");
		p1.setFont(thefont);
		p2.setText("PLAYER 2");
		p2.setFont(thefont);
		restart.setText("RESTART");
		undo.setText("UNDO");
		p1.setBounds(1225, 325, 150, 50);
		p2.setBounds(1225, 725, 150, 50);
		check.setBounds(1100, 125, 150, 50);
		checkmate.setBounds(1100, 225, 150, 50);
		if (piece >= 0 && piece <= 15)
		{
		p1.setForeground(Color.GREEN);
		p2.setForeground(Color.RED);
		}
		else if (piece >= 16 && piece <= 31)
		{
		p2.setForeground(Color.GREEN);
		p1.setForeground(Color.RED);
		}
		else
		{
			p1.setForeground(Color.RED);
			p2.setForeground(Color.RED);
		}
		restart.setBounds(1125, 50, 150, 50);
		undo.setBounds(1325, 50, 150, 50);
		frame.add(p1);
		frame.add(p2);
		frame.add(restart);
		frame.add(undo);
		JLabel a, b, c, d, s, f, g, h;
		a = new JLabel("8");
		a.setFont(thefont);
		b = new JLabel("7");
		b.setFont(thefont);
		c = new JLabel("6");
		c.setFont(thefont);
		d = new JLabel("5");
		d.setFont(thefont);
		s = new JLabel("4");
		s.setFont(thefont);
		f = new JLabel("3");
		f.setFont(thefont);
		g = new JLabel("2");
		g.setFont(thefont);
		h = new JLabel("1");
		h.setFont(thefont);
		a.setBounds(50, 125, 50, 50);
		b.setBounds(50, 225, 50, 50);
		c.setBounds(50, 325, 50, 50);
		d.setBounds(50, 425, 50, 50);
		s.setBounds(50, 525, 50, 50);
		f.setBounds(50, 625, 50, 50);
		g.setBounds(50, 725, 50, 50);
		h.setBounds(50, 825, 50, 50);
		frame.add(a);
		frame.add(b);
		frame.add(c);
		frame.add(d);
		frame.add(s);
		frame.add(f);
		frame.add(g);
		frame.add(h);
		JLabel i, j, k, l, m, n, o, p;
		i = new JLabel("1");
		i.setFont(thefont);
		j = new JLabel("2");
		j.setFont(thefont);
		k = new JLabel("3");
		k.setFont(thefont);
		l = new JLabel("4");
		l.setFont(thefont);
		m = new JLabel("5");
		m.setFont(thefont);
		n = new JLabel("6");
		n.setFont(thefont);
		o = new JLabel("7");
		o.setFont(thefont);
		p = new JLabel("8");
		p.setFont(thefont);
		i.setBounds(150, 910, 50, 50);
		j.setBounds(250, 910, 50, 50);
		k.setBounds(350, 910, 50, 50);
		l.setBounds(450, 910, 50, 50);
		m.setBounds(550, 910, 50, 50);
		n.setBounds(650, 910, 50, 50);
		o.setBounds(750, 910, 50, 50);
		p.setBounds(850, 910, 50, 50);
		frame.add(i);
		frame.add(j);
		frame.add(k);
		frame.add(l);
		frame.add(m);
		frame.add(n);
		frame.add(o);
		frame.add(p);
			
		// CODE FOR ANIMATION FOR DIFFERENT CONDITIONS ACCORDING TO COORDINATES OF PLACES
		if( x[piece] <= x2 && y[piece] <= y2)
		{
			for (int w = x[piece]; w <= x2; w++)
			{
				for ( int u = y[piece]; u <= y2; u++)
				{   
					if(x[piece] == x2)
					{
						y[piece]++;
					}
					else if(y[piece] == y2)
					{
						x[piece]++;
					}
					else
					{
						x[piece]++;
						y[piece]++;
					}
					draw.repaint();
					try
					{
						Thread.sleep(25);
					}
					catch(Exception e)
					{}
				}
			}
				 
		}
		else if (x[piece] > x2 && y[piece] <= y2)
		{
			for (int w = x[piece]; w >= x2; w--)
			{
				for ( int u = y[piece]; u <= y2; u++)
				{
					if(x[piece] == x2)
					{
						y[piece]++;
					}
					else if(y[piece] == y2)
					{
						x[piece]--;
					}
					else
					{
						x[piece]--;
						y[piece]++;
					}
					draw.repaint();
					try
					{
						Thread.sleep(25);
					}
					catch(Exception e)
					{}
				}
			}
		}
		else if(x[piece] <= x2 && y[piece] > y2)
		{
			for (int w = x[piece]; w <= x2; w++)
			{
				for ( int u = y[piece]; u >= y2; u--)
				{
					if(x[piece] == x2)
					{
						y[piece]--;
					}
					else if(y[piece] == y2)
					{
						x[piece]++;
					}
					else
					{
						x[piece]++;
						y[piece]--;
					}
					draw.repaint();
					try
					{
						Thread.sleep(25);
					}
					catch(Exception e)
					{}
				}
			}  
		}
		else if(x[piece] > x2 && y[piece] > y2)
		{
			for (int w = x[piece]; w >= x2; w--)
			{
				for ( int u = y[piece]; u >= y2; u--)
				{
					if(x[piece] == x2)
					{
						y[piece]--;
					}
					else if(y[piece] == y2)
					{
						x[piece]--;
					}
					else
					{
						x[piece]--;
						y[piece]--;
					}
					draw.repaint();
					try
					{
						Thread.sleep(25);
					}
					catch(Exception e)
					{}
				}
			}    
		}
		else
		{
		}
	 }
				
	class DrawPanel extends JPanel
	{
		public void paint(Graphics g)
		{  
			// TO DRAW CHESSBOARD
			g.setColor(Color.BLACK);
			g.fillRect(95, 95, 810, 810);
			g.setColor(Color.RED);
		    g.fillRect(100, 100, 800, 800);
		    	for (int i = 100; i <= 800; i += 200)
		    	{
		    		for (int j = 100; j <= 800; j += 200)
		    		{
		    			g.setColor(Color.yellow);
		    			g.fillRect(i, j, 100, 100);
		    		}
		    	}
		    	for (int i = 200; i <= 900; i += 200)
		    	{
		    		for(int j = 200; j <= 900; j += 200)
		    		{
		    			g.setColor(Color.yellow);
		    			g.fillRect(i, j, 100, 100);
		    		}
		    	}
		  
		    	// INCLUDING IMAGES OF PIECES IN THE FRAME AND PLACING ON THEIR PLACES
		    	ImageIcon p1 = new ImageIcon("src/profile.png");
		    	p1.paintIcon(this, g, 1245, 225);
		    	ImageIcon p2 = new ImageIcon("src/profile.png");
		    	p2.paintIcon(this, g, 1245, 625);
		    	ImageIcon bp1 = new ImageIcon("src/black_pawn.png");
		    	if(z[16] == 1)
		    	bp1.paintIcon(this, g, x[16], y[16]);
		    	ImageIcon bp2 = new ImageIcon("src/black_pawn.png");
		    	if(z[17] == 1)
		    	bp2.paintIcon(this, g, x[17], y[17]);
		    	ImageIcon bp3 = new ImageIcon("src/black_pawn.png");
		    	if(z[18] == 1)
		    	bp3.paintIcon(this, g, x[18], y[18]);
		    	ImageIcon bp4 = new ImageIcon("src/black_pawn.png");
		    	if(z[19] == 1)
		    	bp4.paintIcon(this, g, x[19], y[19]);
		    	ImageIcon bp5 = new ImageIcon("src/black_pawn.png");
		    	if(z[20] == 1)
		    	bp5.paintIcon(this, g, x[20], y[20]);
		    	ImageIcon bp6 = new ImageIcon("src/black_pawn.png");
		    	if(z[21] == 1)
		    	bp6.paintIcon(this, g, x[21], y[21]);
		    	ImageIcon bp7 = new ImageIcon("src/black_pawn.png");
		    	if(z[22] == 1)
		    	bp7.paintIcon(this, g, x[22], y[22]);
		    	ImageIcon bp8 = new ImageIcon("src/black_pawn.png");
		    	if(z[23] == 1)
		    	bp8.paintIcon(this, g, x[23], y[23]);
		    	ImageIcon wp1 = new ImageIcon("src/white_pawn.png");
		    	if(z[15] == 1)
		    	wp1.paintIcon(this, g, x[15], y[15]);
		    	ImageIcon wp2 = new ImageIcon("src/white_pawn.png");
		    	if(z[14] == 1)
		    	wp2.paintIcon(this, g, x[14], y[14]);
		    	ImageIcon wp3 = new ImageIcon("src/white_pawn.png");
		    	if(z[13] == 1)
		    	wp3.paintIcon(this, g, x[13], y[13]);
		    	ImageIcon wp4 = new ImageIcon("src/white_pawn.png");
		    	if(z[12] == 1)
		    	wp4.paintIcon(this, g, x[12], y[12]);
		    	ImageIcon wp5 = new ImageIcon("src/white_pawn.png");
		    	if(z[11] == 1)
		    	wp5.paintIcon(this, g, x[11], y[11]);
		    	ImageIcon wp6 = new ImageIcon("src/white_pawn.png");
		    	if(z[10] == 1)
		    	wp6.paintIcon(this, g, x[10], y[10]);
		    	ImageIcon wp7 = new ImageIcon("src/white_pawn.png");
		    	if(z[9] == 1)
		    	wp7.paintIcon(this, g, x[9], y[9]);
		    	ImageIcon wp8 = new ImageIcon("src/white_pawn.png");
		    	if(z[8] == 1)
		    	wp8.paintIcon(this, g, x[8], y[8]);
		    	ImageIcon brl = new ImageIcon("src/black_rook.png");
		    	if(z[31] == 1)
		    	brl.paintIcon(this, g, x[31], y[31]);
		    	ImageIcon brr = new ImageIcon("src/black_rook.png");
		    	if(z[24] == 1)
		    	brr.paintIcon(this, g, x[24], y[24]);
		    	ImageIcon wrl = new ImageIcon("src/white_rook.png");
		    	if(z[0] == 1)
		    	wrl.paintIcon(this, g, x[0], y[0]);
		    	ImageIcon wrr = new ImageIcon("src/white_rook.png");
		    	if(z[7] == 1)
		    	wrr.paintIcon(this, g, x[7], y[7]);
		    	ImageIcon bhl = new ImageIcon("src/black_knight.png");
		    	if(z[30] == 1)
		    	bhl.paintIcon(this, g,x[30], y[30]);
		    	ImageIcon bhr = new ImageIcon("src/black_knight.png");
		    	if(z[25] == 1)
		    	bhr.paintIcon(this, g, x[25], y[25]);
		    	ImageIcon whl = new ImageIcon("src/white_knight.png");
		    	if(z[1] == 1)
		    	whl.paintIcon(this, g, x[1], y[1]);
		    	ImageIcon whr = new ImageIcon("src/white_knight.png");
		    	if(z[6] == 1)
		    	whr.paintIcon(this, g, x[6], y[6]);
		    	ImageIcon bbl = new ImageIcon("src/black_bishop.png");
		    	if(z[29] == 1)
		    	bbl.paintIcon(this, g, x[29], y[29]);
		    	ImageIcon bbr = new ImageIcon("src/black_bishop.png");
		    	if(z[26] == 1)
		    	bbr.paintIcon(this, g, x[26], y[26]);
		    	ImageIcon wbl = new ImageIcon("src/white_bishop.png");
		    	if(z[2] == 1)
		    	wbl.paintIcon(this, g, x[2], y[2]);
		    	ImageIcon wbr = new ImageIcon("src/white_bishop.png");
		    	if(z[5] == 1)
		    	wbr.paintIcon(this, g, x[5], y[5]);
		    	ImageIcon bq0 = new ImageIcon("src/black_queen.png");
		    	if(z[28] == 1)
		    	bq0.paintIcon(this, g, x[28], y[28]);
		    	ImageIcon bk0 = new ImageIcon("src/black_king.png");
		    	bk0.paintIcon(this, g, x[27], y[27]);
		    	ImageIcon wq0 = new ImageIcon("src/white_queen.png");
		    	if(z[3] == 1)
		    	wq0.paintIcon(this, g, x[3], y[3]);
		    	ImageIcon wk0 = new ImageIcon("src/white_king.png");
		    	wk0.paintIcon(this, g, x[4], y[4]);
		}
	}
}