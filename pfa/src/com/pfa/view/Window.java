package com.pfa.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import com.pfa.model.SimilarityDisplay;

import java.awt.event.*;
import java.util.ArrayList;
	 
	// Extends JFrame so it can create frames
	 
	public class Window extends JFrame{
	     
	    private JButton button;
	    private JTextField textField;
	    private JTextArea textArea;
	    
	     
	   
	   
     
	    public Window(){
	         
	        // Define the size of the frame
	        this.setSize(1000, 1000);
              
	        Toolkit tk = Toolkit.getDefaultToolkit();
                
	        Dimension dim = tk.getScreenSize();
      // this.getWidth returns the width of the frame you are making
	                 
	        int xPos = (dim.width / 2) - (this.getWidth() / 2);
	        int yPos = (dim.height / 2) - (this.getHeight() / 2);
                 
	        //this.setLocation(xPos, yPos);
               
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
	        this.setTitle("Semantic Search");
                
	        JPanel thePanel = new JPanel();
        
	        button = new JButton("Search");
         
	       
	         
	        
	        thePanel.add(button);
        
	        textField = new JTextField("Type Here", 40);
	                 
	        thePanel.add(textField);
         
	        textArea = new JTextArea(60,90);
	                 
	        textArea.setText("Tracking Events\n");
        
	        textArea.setLineWrap(true);
                
	        textArea.setWrapStyleWord(true);
	                 
	        // Adds scroll bars to the text area ----------
	                 
	        JScrollPane scrollbar1 = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	                 
	        // Other options: VERTICAL_SCROLLBAR_ALWAYS, VERTICAL_SCROLLBAR_NEVER
	                 
	        thePanel.add(scrollbar1);
	         
	        this.add(thePanel);

	         
	        this.setVisible(true);
	         
	    }
	    public String getQuery(){
	    	return textField.getText();
	    }
	    public void setResult(ArrayList<SimilarityDisplay>simList){
	    			textArea.setText("");
	    		for(int i=0;i<simList.size();i++){
	    			textArea.append(simList.get(i).toString()+"\n");
	    		}
	    	}
	    public void addSearchListener(ActionListener listenerForSearchButton){
	    	button.addActionListener(listenerForSearchButton);
	    }	     
	}
