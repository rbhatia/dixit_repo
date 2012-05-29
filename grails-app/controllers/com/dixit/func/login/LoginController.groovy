package com.dixit.func.login

import com.dixit.domain.User

class LoginController {

   
	def doLogin = {
		
		LoginCommand cmd,User userE ->
		if(cmd.hasErrors()) {
			println "Command has`errors==>"+cmd.errors.toString()
			
			if (cmd.hasErrors())
			{
				log.debug('validation failed')
				render(view:'login', model:[user:userE])
		
			}
				
				
			//redirect(controller:'user',action:'login')
		}
		else {
			println "Command has NO errors"
				
			def user = User.findWhere(userName:params['userId'],
				password:params['password'])
				session.user = user
				println "Type name==>"+user.role.roleName
				if (user)
				{
					render "Login successful"
					flash.message = "Flash:- Login successful"
					redirect(controller:'login',action:'login')
				}
				else
				{
					render "Login Failed !!"
					flash.message = "Flash:-Login Failed !!"
					redirect(controller:'login',action:'login')
				}
			
			
		}
	}
		
	
	
	def login = {
		
		if(session.user)
		{
			render "Already Logged In"
			flash.message = "Flash:- Already Logged In"
			//redirect(controller:'user',action:'logedIn')
			render(view: 'logedIn')
			
			
		}
		else
		{
			flash.message = "Flash:- Login Please !!"
		}
	}
	
	
	
	class LoginCommand {
		String userId
		String password
		static constraints = {
			userId(blank:false, minSize:2)
			password(blank:false, minSize:2)
		}
	}	
	
	
	def doLogOff = {
		
		if (session.user)
		{
			session.user=null
		}
		redirect(action:'login')
	}
	
	
	
}
