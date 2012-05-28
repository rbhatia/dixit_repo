package com.dixit.domain

//import org.bson.types.ObjectId

class Role {
	
	
	String roleName

    static constraints = {
		roleName(blank:false)
		
    }
	
	static mapping = {
		id generator: 'increment',params: [table: 'Role', column: 'roleId']
	}
	
	String toString(){
		return roleName
	  }
	
}
