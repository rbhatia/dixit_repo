package com.dixit.domain

//import org.bson.types.ObjectId;

class Function {

	String functionName
	
	
    static constraints = {
    }
	
	/*
	static mapping = {
		id generator: 'increment',
		   params: [table: 'Function', column: 'id']
	}
	*/
	
	String toString(){
		return functionName
	  }
}
