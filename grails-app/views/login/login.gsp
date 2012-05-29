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
				<p>Enter your login details below:</p>
				<table class="userForm1">
					<tr class='prop'>
						<td valign='top' style='text-align: left;' width='20%'>
							<label for='userId'>User ID:</label>
						</td>
						<td valign='top' style='text-align: left;' width='80%'>
							<input id="userId1" type='text' name='userId' value='${user?.userId}' />
						</td>
					</tr>
					<tr class='prop'>
						<td valign='top' style='text-align: left;' width='20%'>
							<label for='password'>Password:</label>
						</td>
						<td valign='top' style='text-align: left;' width='80%'>
							<input id="password1" type='password' name='password' value='${user?.password}' />
						</td>
					</tr>
					
					
					
				</table>
			</div>
			<div class="buttons">
				<span class="formButton"> <input type="submit" value="Login"></input>
				</span>
			</div>
		</g:form>
		<div>
		
			<g:renderErrors bean="${user}" as="list" />
			
		
		
		</div>
	</div>
</body>
</html>