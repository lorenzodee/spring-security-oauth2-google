package web.sample;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		/*
		 * Needed to support a "request" scope in Spring Security filters,
		 * since they're configured as a Servlet Filter. But not necessary
		 * if they're configured as interceptors in Spring MVC.
		 */
		servletContext.addListener(new RequestContextListener());
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {
			WebMvcConfig.class,
			OAuth2ClientSecurityConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
