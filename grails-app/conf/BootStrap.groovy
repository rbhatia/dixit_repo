import com.dixit.domain.Languages

class BootStrap {

    def init = { servletContext ->
		 
		
		
			
		if (!Languages.count()) {
			new Languages(code: "de_DE",value:"German",description:"German").save(failOnError: true)
			new Languages(code: "en_GB",value:"English",description:"English").save(failOnError: true)
			new Languages(code: "es_CA",value:"Spanish Castilian",description:"Spanish Castilian").save(failOnError: true)
			new Languages(code: "fr_FR",value:"French",description:"French").save(failOnError: true)
			new Languages(code: "it_IT",value:"Italian",description:"Italian").save(failOnError: true)
			new Languages(code: "ja_JP",value:"Japanese",description:"Japanese").save(failOnError: true)
			new Languages(code: "ko_KR",value:"Korean",description:"Korean").save(failOnError: true)
			new Languages(code: "zh_CN",value:"Italian",description:"Italian").save(failOnError: true)
			new Languages(code: "zh_TW",value:"Japanese",description:"Japanese").save(failOnError: true)
		}
		
		
		
		
		
		
    }
    def destroy = {
    }
}
