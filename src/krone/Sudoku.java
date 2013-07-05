/*
* ----------------------------------------------------------------------------
* "THE BEER-WARE LICENSE":
* <kevin.krone@t-online.de> wrote this file. As long as you retain this notice you
* can do whatever you want with this stuff. If we meet some day, and you think
* this stuff is worth it, you can buy me a beer in return. Kevin Krone
* http://en.wikipedia.org/wiki/Beerware
*
* "THE BEER-WARE LICENSE":
* <kevin.krone@t-online.de> schrieb diese Datei. Solange Sie diesen Vermerk nicht entfernen, können
* Sie mit dem Material machen, was Sie möchten. Wenn wir uns eines Tages treffen und Sie
* denken, das Material ist es wert, können Sie mir dafür ein Bier ausgeben. Kevin Krone
* http://de.wikipedia.org/wiki/Beerware
* ----------------------------------------------------------------------------
*/

package krone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author krone
 *
 */
@SuppressWarnings("serial")
public class Sudoku extends JPanel {
	
	/**
	 * Größe eines Feldes
	 */
	public static final int RECT_SIZE = 25; 
	
	/**
	 * Größe des Sudokufeld
	 */
	private final int size;
	
	/**
	 * Feld mit Sudoku-Zahlen
	 */
	private int[][] feld;
	
	
	/**
	 * Initialisiert das Objekt
	 * @param s
	 * @throws Exception 
	 */
	public Sudoku(int s) throws Exception {
		if (s < 9 || s % 3 != 0) throw new Exception("Ungültige Größe");
		
		size = s;
		Reset();
	}
	
	/**
	 * Setzt eine Zahl ins Feld
	 * @param x
	 * @param y
	 * @param value
	 * @throws Exception 
	 */
	public void Set(int x, int y, int value) throws Exception {
		if (x > feld.length || x < 0) throw new Exception("Ungültiger X-Wert");
		if (y > feld.length || y < 0) throw new Exception("Ungültiger Y-Wert");
		
		feld[x][y] = value;
	}
	
	/**
	 * Prüft ob das Spiel beendet ist
	 * @return
	 */
	public boolean Finished() {
		for (int reihe=0; reihe < feld.length; ++reihe) {
			for (int spalte=0; spalte < feld.length; ++spalte) {
				if (!Valid(reihe, spalte)) return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Löst das Sudokurätsel
	 * @return
	 */
	public boolean Solver() {
		for (int reihe = 0; reihe < feld.length; ++reihe) {
			for (int spalte = 0; spalte < feld.length; ++spalte) {
				if (feld[reihe][spalte] != 0) continue;
				
				for (int i=1; i <= 9; ++i) {
					feld[reihe][spalte] = i;

					repaint();
					
					if (Valid(reihe, spalte)) {
						if (Solver()) return true;
					}
				}
				
				feld[reihe][spalte] = 0;
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Prüft ob Feld validen Wert enthält
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean Valid(int x, int y) {
		if (x > feld.length || x < 0 || y > feld.length || y < 0) 
			return false;
		
		final int number = feld[x][y];
		
		if (number == 0) return false;
		
		//Prüfe Spalte
		for (int s=0; s < feld.length; ++s) {
			if (feld[x][s] == number && s != y) return false;
		}
		
		//Prüfe Zeile
		for (int z=0; z < feld.length; ++z) {
			if (feld[z][y] == number && z != x) return false;
		}
		
		//Prüfe Block
		int blockX = (x / 3) * 3;
		int blockY = (y / 3) * 3;
		
		for (int s=blockX; s < blockX+3; ++s) {
			for (int z=blockY; z < blockY+3; ++z) {
				if (feld[s][z] == number && s != x && z != y) return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Setzt den Feld-Array auf 0
	 */
	public void Reset() {
		feld = new int[size][size];
		
		for (int x=0; x < size; ++x) {
			for (int y=0; y < size; ++y) {
				feld[x][y] = 0;
			}
		}
	}
	
	/**
	 * Zeichnet das Sudokubrett
	 */
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		
		final int paddX = 20;
		final int paddY = 20;
		
		int y = paddY;
		int x = paddX;
		
		g.setFont(new Font(Font.SANS_SERIF, 13, 25));
		
		for (int reihe = 0; reihe < feld.length; ++reihe) {
			x = paddX;
			
			for (int spalte = 0; spalte < feld.length; ++spalte) {
				if (spalte % 3 == 0) {
					x += 5;
				}
				
				g.drawRect(x, y, RECT_SIZE, RECT_SIZE);
				
				int number = feld[reihe][spalte];
				if (number != 0) {
					if (!Valid(reihe, spalte)) {
						g.setColor(Color.RED);
					}
					
					g.drawString(
						Integer.toString(number), 
						x + (int)(RECT_SIZE * 0.2), 
						y + (int)(RECT_SIZE * 0.9)
					);
					

					g.setColor(Color.BLACK);
				}
				
				x += RECT_SIZE;
			}
			
			if ((reihe+1) % 3 == 0) {
				y += 5;
			}
			
			y += RECT_SIZE;
		}
		
	}
	
}
