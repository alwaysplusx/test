### Embedded DataBase

### [H2 Database](http://www.h2database.com/html/main.html)

#### 启动数据库

Starting the Server Tool from Command Line

To start the Server tool from the command line with the default settings, run:
	
	java -cp h2*.jar org.h2.tools.Server
	This will start the tool with the default options. To get the list of options and default values, run:
	
	java -cp h2*.jar org.h2.tools.Server -?
	
	java -jar h2*.jar

Starting the TCP Server within an Application

	import org.h2.tools.Server;
	...
	// start the TCP Server
	Server server = Server.createTcpServer(args).start();
	...
	// stop the TCP Server
	server.stop();
	
Using a Servlet Listener to Start and Stop a Database

	<listener>
	    <listener-class>org.h2.server.web.DbStarter</listener-class>
	</listener>

	 try {
            org.h2.Driver.load();

            // This will get the setting from a context-param in web.xml if defined:
            ServletContext servletContext = servletContextEvent.getServletContext();
            String url = getParameter(servletContext, "db.url", "jdbc:h2:~/test");
            String user = getParameter(servletContext, "db.user", "sa");
            String password = getParameter(servletContext, "db.password", "sa");

            // Start the server if configured to do so
            String serverParams = getParameter(servletContext, "db.tcpServer", null);
            if (serverParams != null) {
                String[] params = StringUtils.arraySplit(serverParams, ' ', true);
                server = Server.createTcpServer(params);
                server.start();
            }

            // To access the database in server mode, use the database URL:
            // jdbc:h2:tcp://localhost/~/test
            conn = DriverManager.getConnection(url, user, password);
            servletContext.setAttribute("connection", conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

Using the H2 Console Servlet

The H2 Console is a standalone application and includes its own web server, but it can be used as a servlet as well. To do that, include the the h2*.jar file in your application, and add the following configuration to your web.xml:

	<servlet>
	    <servlet-name>H2Console</servlet-name>
	    <servlet-class>org.h2.server.web.WebServlet</servlet-class>
	    <!--
	    <init-param>
	        <param-name>webAllowOthers</param-name>
	        <param-value></param-value>
	    </init-param>
	    <init-param>
	        <param-name>trace</param-name>
	        <param-value></param-value>
	    </init-param>
	    -->
	    <load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
	    <servlet-name>H2Console</servlet-name>
	    <url-pattern>/console/*</url-pattern>
	</servlet-mapping>

### HSQL Database