
mongodb {
	host = 'localhost' // adjust this according to your settings
	port = 27017
	databaseName = 'dixit'
	username = 'dixit'  // database user and password, if server requires authentication
	password = 'd1x1t'
  }


environments {
	development {
		mongodb {
			dbCreate = "create-drop" // one of 'create', 'createeate-drop','update'
			//url = "jdbc:hsqldb:mem:devDB"
		}
	}
	test {
		mongodb {
			dbCreate = "update"
			//url = "jdbc:hsqldb:mem:testDb"
		}
	}
	production {
		mongodb {
			dbCreate = "update"
			//url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}
