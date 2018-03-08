

package homework;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class GridTest extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;
	private JButton[] buttons;
	//private int[] moles = {(int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 100), 
		//	(int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 100), 
			//(int)(Math.random() * 100), (int)(Math.random() * 100)};
	private int[] moles = {17, 96};
	private int guessCount = 50;
	public static int counter = 0;
	private int moleCount = moles.length;
	private static ImageIcon whackIcon; //Still need to figure out the dimensions for the photos
	private static ImageIcon whackedMole; //same as comment above
	
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                makeGUIVisible();
            }
        });
	}
	
	
	//try to figure out how to get the grid to be centered in any screen regardless of size
	private static void makeGUIVisible(){
		whackIcon = new ImageIcon("smallSQwhackb.jpg");
		whackedMole = new ImageIcon("whackamole.png");
		GridTest grid = new GridTest(10, 10);
		grid.setLocation(300, 200); 
		//grid.setLocation(Component.WIDTH/2, Component.HEIGHT/2); 
		grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grid.pack();
		grid.setVisible(true);
	}
	
	
	public GridTest(int rows, int cols) {
		buttons = new JButton[100];
	    final Container pane = getContentPane();
	    
	    pane.setLayout(new GridLayout(rows, cols));
	    setTitle("Whack-A-Mole   Guesses Remaining: " + guessCount + "   Moles Remaining: " + moleCount);
	    
	    for (int i = 0; i < 100; i++) {
	    	counter = i;
	    	buttons[i] = new JButton(Integer.toString(i + 1));
	    	buttons[i].addActionListener(this);
	    	pane.add(buttons[i]);
	    }
	   
	  }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		boolean guess = false;
		JButton b = ((JButton) source);
		int num;
		
		if(!b.getText().equals("W") && !b.getText().equals("M")){
			num = Integer.parseInt(b.getText());
			if (source instanceof JButton) {
				guessCount--;
				setTitle("Whack-A-Mole   Guesses Remaining: " + guessCount + "   Moles Remaining: " + moleCount);
				
				//checks to see if the user has run out of guesses
				if(guessCount == 0){
					
					int result = JOptionPane.showConfirmDialog(null,
					        "Sorry, you have run out of guesses. Please try again.",
					        null, JOptionPane.CLOSED_OPTION);
					System.exit(0);	
					
				
					makeGUIVisible();//try to make it so the old game exits before the new game starts
					//currently overlapping game layers so multiple windows are open
				}
				
				
				
		    	for(int i = 0; i < moles.length; i++){
		    		if(moles[i] == num && !guess){
		    			guess = true;
		    			moleCount--;
		    			setTitle("Whack-A-Mole   Guesses Remaining: " + guessCount + "   Moles Remaining: " + moleCount);
		    			
		    			if(moleCount == 0){
		    				int result = JOptionPane.showConfirmDialog(null,
							        "CONGRATS! You got it in only " + Integer.toString(50 - guessCount) + " turns!",
							        null, JOptionPane.CLOSED_OPTION);
		    						System.exit(0); 
		    			}
		    			
		    			moles[i] = 0;
		    		}
		    	}
		    	
		    	if(guess){
		    		((JButton) source).setForeground(Color.RED);
		    		((JButton) source).setText("M");
		    	}else{
		    		((JButton) source).setText("W");
		    		
		    	}
		    }
		}
	}

}
