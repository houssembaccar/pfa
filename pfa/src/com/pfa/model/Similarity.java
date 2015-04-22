package com.pfa.model;

import java.util.ArrayList;

import com.pfa.beans.Concept;

public class Similarity {
	public  int depth(Concept concept){
		if (concept==null)
			return 1;
		int depth=2;
		while(concept.getParent()!=null){
			depth++;
			concept=concept.getParent();
		}
		return depth;
	}	
	
	public int depthInterDepth (Concept concept1 ,Concept concept2){
		
		int depth1=depth(concept1);
		int depth2=depth(concept2);
		//if the depth of one of the two concepts is equal to one the return value of depthInterDepth must be equal to one
		if (depth1==1 || depth2==1)
			return 1;
		//if the two concepts are equal then return the depth of one of them
		if (depth1==depth2){
			if (concept1.isEqualTo(concept2)){
				return depth1;
			}
			else {
				return depthInterDepth(concept1.getParent(),concept2.getParent());
			}
		}/* if the two concepts don't have the same depth we call recursively the method with the parent 
		    of the concept that has the greatest depth and the concept with lowest depth as arguments */
		else{
			if (depth1>depth2){
				return depthInterDepth(concept1.getParent(),concept2);
			}else {
				return depthInterDepth(concept1,concept2.getParent());
			}
		}
		
	}
	public float conceptSim(Concept userConcept,Concept concept){
		return (float)depthInterDepth(userConcept,concept)/(float)depth(userConcept);
	}
	
	
			
			
		
	}
	

