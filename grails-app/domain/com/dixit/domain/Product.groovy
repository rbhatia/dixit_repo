package com.dixit.domain

import java.util.Date

class Product {
	
	long productId
	Date dateAdded
	Date dateUpdated

    static constraints = {
		
		productId(blank:false)
		
		
    }
	
	static mapping = {
			version false
				columns { id column:'productId' }
 }
	
	
	def beforeInsert() {
		dateAdded = new Date()
	}
	
	/*
	def afterUpdate() {
		dateUpdated = new Date()
	}
	*/
	
	
}
