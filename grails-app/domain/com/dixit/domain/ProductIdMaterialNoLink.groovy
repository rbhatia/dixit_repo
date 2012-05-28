package com.dixit.domain

class ProductIdMaterialNoLink {
	
	Product product
	Copy copy
	long materialNo

    static constraints = {
		
		product(blank:false)
		copy(blank:false)
		materialNo(blank:false)
    }
}
