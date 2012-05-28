package com.dixit.domain

class CopyStatus {
	
	String status
	
	

    static constraints = {
		
		status(blank:false)
    }
	
	String toString(){
		return status
	  }
	
}
