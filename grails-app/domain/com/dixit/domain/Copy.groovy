package com.dixit.domain

class Copy {
	
	Language language
	String heading
	String summary
	String mainText
	String altText
	CopyStatus status
	User user
	Date dateAdded
	Date dateUpdated
	Date dateApproved
	

      static constraints = {
		
		
		language(blank:false)
		heading(blank:false)
		dateUpdated(nullable: true)
		dateApproved(nullable: true)
		
    }
	
	def beforeInsert() {
		dateAdded = new Date()
	}
	
}
