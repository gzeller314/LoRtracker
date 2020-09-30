import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class MainWindow {

    public static void main(String[] args) {
    	//Create new overlay window
        JFrame frame = new JFrame("LoR Tracker");
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setAlwaysOnTop(true);
        // Without this, the window is draggable from any non transparent
        // point, including points  inside textboxes.
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //initialize variables for working with content in window
        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        //add cards
        //ImageIcon icon = new ImageIcon("img/test.png");
        //frame.getContentPane().add(new JLabel(icon), java.awt.BorderLayout.CENTER);
        ArrayList<JTextField> cards = new ArrayList<JTextField>();
        for (int i = 0; i < 40; i++)
        {
            cards.add(new JTextField("card" + java.lang.Integer.toString(i))); 
            cards.get(i).setToolTipText("some information");
            contentPane.add(cards.get(i));
            if (i != 0)
            	layout.putConstraint(SpringLayout.NORTH, cards.get(i), 0, SpringLayout.SOUTH, cards.get(i-1));
            else
        	layout.putConstraint(SpringLayout.NORTH, cards.get(i), 50, SpringLayout.NORTH, contentPane);
        }
        
        //standardize card width
        Spring cardMax = layout.getConstraints(contentPane.getComponent(0)).getWidth();
        for (int i = 1; i < cards.size(); i++)
        	cardMax = Spring.max(cardMax, layout.getConstraints(contentPane.getComponent(i)).getWidth());
        for (int i = 0; i < cards.size(); i++)
        	layout.getConstraints(contentPane.getComponent(i)).setWidth(cardMax);
        
        
        frame.setVisible(true);
    }
}