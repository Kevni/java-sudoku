/*
* ----------------------------------------------------------------------------
* "THE BEER-WARE LICENSE" (Revision 42):
* <phk@FreeBSD.ORG> wrote this file. As long as you retain this notice you
* can do whatever you want with this stuff. If we meet some day, and you think
* this stuff is worth it, you can buy me a beer in return Poul-Henning Kamp
* ----------------------------------------------------------------------------
*/

package krone;

import javax.swing.JFrame;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JFrame window = new JFrame();
			window.setSize(300, 310);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Sudoku spiel = new Sudoku(9);
			
			spiel.Set(0,1,3);
			spiel.Set(1,3,1);
			spiel.Set(1,4,9);
			spiel.Set(1,5,5);
			spiel.Set(2,2,8);
			spiel.Set(2,7,6);
			spiel.Set(3,0,8);
			spiel.Set(3,4,6);
			spiel.Set(4,0,4);
			spiel.Set(4,3,8);
			spiel.Set(4,8,1);
			spiel.Set(5,4,2);
			spiel.Set(6,1,6);
			spiel.Set(6,6,2);
			spiel.Set(6,7,8);
			spiel.Set(7,3,4);
			spiel.Set(7,4,1);
			spiel.Set(7,5,9);
			spiel.Set(7,8,5);
			spiel.Set(8,7,7);	
			
			
			window.add(spiel);
			window.setVisible(true);
			

			if(spiel.Solver()) window.repaint();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
