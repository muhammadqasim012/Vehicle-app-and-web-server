import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.Configuration.ClassList;

public class Controller {

	public static void main(String[] args) throws Exception {
		
		// setting the server up
		Server server = new Server(8005);
		WebAppContext ctx = new WebAppContext();
		ctx.setResourceBase("webapp");
		ctx.setContextPath("/vehicles");
		
		// config
		ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
		ClassList classlist = ClassList.setServerDefault(server);
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", 
				"org.eclipse.jetty.annotations.AnnotationConfiguration");
		
		// Mappings
		ctx.addServlet("servlets.ServletHome", "/home");
		ctx.addServlet("servlets.ServletApi", "/api");
		
		
		
		// Server handler and start up
		server.setHandler(ctx);
		server.start();
		server.join();
	}
}

