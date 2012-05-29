<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="copyMain" />
<title>User Login</title>
</head>
<body>
${flash.message} 
	<div class="body">
		<g:form action="doLogin" method="post">
			<div class="dialog">
				
			</div>
			<div class="buttons">
				 You are logged IN.....				

			</div>
		</g:form>
		<div>
			<g:renderErrors bean="${user}" as="list" />
		</div>
		
		<div>
			<g:link controller="login" action="doLogOff" >Log Off</g:link>
		</div>
		
	</div>
</body>
</html>