
package com.pfa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.pfa.model.SimilarityDisplay;
import com.pfa.view.Window;

public class Controller {
	
	private SimilarityDisplay simDisp; // instance of the model 
	private Window window;             // instance of the view
	ArrayList<SimilarityDisplay> resultList=new ArrayList<SimilarityDisplay>(); // data structure  which contains the result 
	// Controller constructor
	public Controller(SimilarityDisplay simDisp, Window window){
		
		this.simDisp=simDisp;
		this.window=window;
		this.window.addSearchListener(new searchListener()); 
	}
	
	// creation of the class searchListener which intervenes when the user click on the search button
	class searchListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			
			String query = window.getQuery();// the String query will contains the query introduced by the user
			resultList=simDisp.search(query);// the method search dose the treatment and return the result in resultList
			simDisp.sort(resultList);        // the method sort dose the sort by the value of the similarity
			window.setResult(resultList);    // the method setResult which display the result on the interface
		}
		
	}

}
