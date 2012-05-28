package com.dixit.domain

import org.bson.types.ObjectId

class User {
	
	//ObjectId  userId
	String firstName
	String lastName
	String userName
	String password
	Boolean isEnabled
	Role roleId
	Date dateAdded
	
	
    static constraints = {
		
		//userId(blank:false)
		firstName(blank:false)
		lastName(blank:false)
		userName(blank:false)
		password(blank:false)
		isEnabled(blank:false)
		
    }
	
	def beforeInsert() {
		dateAdded = new Date()
	}
	/*
	static mapping = {
		id generator: 'increment',params: [table: 'Users', column: 'id']
	}
	*/
	String toString(){
		return firstName+" "+lastName
	  }

 
	
}
