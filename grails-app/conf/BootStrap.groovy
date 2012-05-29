import java.util.Date;


import com.dixit.domain.*

import org.bson.types.ObjectId

class BootStrap {

    def init = { servletContext ->
		 
		
	
			def Language lang1=new Language(code: "de_DE",value:"German",description:"German")
			def Language lang2=new Language(code: "en_GB",value:"English",description:"English")
			def Language lang3=new Language(code: "es_CA",value:"Spanish Castilian",description:"Spanish Castilian")
			def Language lang4=new Language(code: "fr_FR",value:"French",description:"French")
			def Language lang5=new Language(code: "it_IT",value:"Italian",description:"Italian")
			def Language lang6=new Language(code: "ja_JP",value:"Japanese",description:"Japanese")
			def Language lang7=new Language(code: "ko_KR",value:"Korean",description:"Korean")
			def Language lang8=new Language(code: "zh_CN",value:"Italian",description:"Italian")
			def Language lang9=new Language(code: "zh_TW",value:"Japanese",description:"Japanese")
			
		
		
			
			if (!Language.count()) {
				
				lang1.save(failOnError: true)
				lang2.save(failOnError: true)
				lang3.save(failOnError: true)
				lang4.save(failOnError: true)
				lang5.save(failOnError: true)
				lang6.save(failOnError: true)
				lang7.save(failOnError: true)
				lang8.save(failOnError: true)
				lang9.save(failOnError: true)
			}
		
			
			
			def Role role1=new Role(roleName: "Admin")
			def Role role2=new Role(roleName: "CopyWriter")
			def Role role3=new Role(roleName: "Reviewer")
			def Role role4=new Role(roleName: "Translator")
			
		
		
			if (!Role.count()) {
				role1.save(failOnError: true)
				role2.save(failOnError: true)
				role3.save(failOnError: true)
				role4.save(failOnError: true)
			}
		
			
			def Function func1=new Function(functionName: "Search")
			def Function func2=new Function(functionName: "Edit")
			def Function func3=new Function(functionName: "Delete")
			
			
		if (!Function.count()) {
			func1.save(failOnError: true)
			func2.save(failOnError: true)
			func3.save(failOnError: true)
			
		}
		
		
		def user1=new User(firstName:"Sam", lastName:"Sami", userName:"samu",password:"sam",isEnabled:new Boolean(true),role:role1,dateAdded:new Date())
		def user2=new User(firstName:"Pam", lastName:"Pami", userName:"pamu",password:"pam",isEnabled:new Boolean(true),role:role2,dateAdded:new Date())
		def user3=new User(firstName:"Aan", lastName:"Aani", userName:"aanu",password:"aan",isEnabled:new Boolean(true),role:role3,dateAdded:new Date())
		
		
		
		if (!User.count())
		{
			user1.save(failOnError: true)
			user2.save(failOnError: true)
			user3.save(failOnError: true)
			
		}
		
		
		
		def userLanguage1=new UserLanguage(users:user1,languages:lang1)
		def userLanguage2=new UserLanguage(users:user1,languages:lang2)
		def userLanguage3=new UserLanguage(users:user1,languages:lang3)
		
		def userLanguage4=new UserLanguage(users:user2,languages:lang1)
		def userLanguage5=new UserLanguage(users:user2,languages:lang2)
		def userLanguage6=new UserLanguage(users:user2,languages:lang3)
		
		def userLanguage7=new UserLanguage(users:user3,languages:lang1)
		def userLanguage8=new UserLanguage(users:user3,languages:lang2)
		def userLanguage9=new UserLanguage(users:user3,languages:lang3)
		
		if(!UserLanguage.count())
		{
			 userLanguage1.save(failOnError: true)
			 userLanguage2.save(failOnError: true)
			 userLanguage3.save(failOnError: true)
			
			 userLanguage4.save(failOnError: true)
			 userLanguage5.save(failOnError: true)
			 userLanguage6.save(failOnError: true)
			
			 userLanguage7.save(failOnError: true)
			 userLanguage8.save(failOnError: true)
			 userLanguage9.save(failOnError: true)
		}
		
		
		
		
		
		
		
		
		def UserFunction1=new UserFunction(users:user1,function:func1)
		def UserFunction2=new UserFunction(users:user1,function:func2)
		def UserFunction3=new UserFunction(users:user1,function:func3)
		
		def UserFunction4=new UserFunction(users:user2,function:func1)
		def UserFunction5=new UserFunction(users:user2,function:func2)
		def UserFunction6=new UserFunction(users:user2,function:func3)
		
		def UserFunction7=new UserFunction(users:user3,function:func1)
		def UserFunction8=new UserFunction(users:user3,function:func2)
		def UserFunction9=new UserFunction(users:user3,function:func3)
		
		if(!UserFunction.count())
		{
			 UserFunction1.save(failOnError: true)
			 UserFunction2.save(failOnError: true)
			 UserFunction3.save(failOnError: true)
			
			 UserFunction4.save(failOnError: true)
			 UserFunction5.save(failOnError: true)
			 UserFunction6.save(failOnError: true)
			
			 UserFunction7.save(failOnError: true)
			 UserFunction8.save(failOnError: true)
			 UserFunction9.save(failOnError: true)
		}
		
		
		
		def copyStatus1=new CopyStatus(status:"Approved");
		def copyStatus2=new CopyStatus(status:"Declined");
		def copyStatus3=new CopyStatus(status:"Draft");
				
		
		if(!CopyStatus.count())
		{
		
			copyStatus1.save(failOnError: true)
			copyStatus2.save(failOnError: true)
			copyStatus3.save(failOnError: true)
		}
		
		
		/*
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
		*/
		
		def copy1=new Copy(language:lang1,heading:"Heading 1",summary:"Summary 1",mainText:"Main Text 1",altText:"Alt Text 1",status:copyStatus1,user:user1,dateAdded:new Date())//, dateUpdated:null, dateApproved:null)
		def copy2=new Copy(language:lang1,heading:"Heading 2",summary:"Summary 2",mainText:"Main Text 2",altText:"Alt Text 2",status:copyStatus2,user:user2,dateAdded:new Date())//, dateUpdated:null, dateApproved:null)
		def copy3=new Copy(language:lang1,heading:"Heading 3",summary:"Summary 3",mainText:"Main Text 3",altText:"Alt Text 3",status:copyStatus3,user:user3,dateAdded:new Date())//, dateUpdated:null, dateApproved:null)
		
		if(!Copy.count())
		{
		
			copy1.save(failOnError: true)
			copy2.save(failOnError: true)
			copy3.save(failOnError: true)
		}
		
		
		
    }
    def destroy = {
    }
}
