package com.dixit.domain

class Users {
	
	int userId
	String firstName
	String lastName
	String userName
	String password
	Boolean isEnabled
	int roleId
	Date dateAdded
	
	
    static constraints = {
		
		userId(blank:false)
		firstName(blank:false)
		lastName(blank:false)
		userName(blank:false)
		password(blank:false)
		isEnabled(blank:false)
		
    }
	
	def beforeInsert() {
		dateAdded = new Date()
	}
	
	static mapping = {
		id generator: 'userId_Auto',
		   params: [table: 'Users', column: 'userId']
	}
	
	String toString(){
		return firstName+" "+lastName
	  }

 
	
}
