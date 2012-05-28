package com.dixit.domain

//import org.bson.types.ObjectId;

class Language {
	
	String code
	String value
	String description

    static constraints = {
		
		code(size:2..6, blank:false)
		value(size:2..30, blank:false)
		description(size:2..80, blank:true)
    }
	
		String toString(){
		return code+":"+value
	  }
}
