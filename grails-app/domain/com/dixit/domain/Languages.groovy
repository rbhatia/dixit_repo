package com.dixit.domain

class Languages {
	
	String code
	String value
	String description

    static constraints = {
		
		code(size:2..6, blank:false)
		value(size:2..30, blank:false)
		description(size:2..80, blank:true)
    }
}
