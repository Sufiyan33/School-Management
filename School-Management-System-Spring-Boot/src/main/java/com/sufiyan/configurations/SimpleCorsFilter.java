package com.sufiyan.configurations;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		Map<String, String> map = new HashMap<String, String>();
		
		String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);  // Allow the origin header
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");  // Allowed HTTP methods
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Pingother, Origin, X-Requested-With, Accept");  // Allowed headers
        response.setHeader("Access-Control-Expose-Headers", "Authorization");  // Expose the Authorization header
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
		
		if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			chain.doFilter(req, resp);
		}
	}
	 @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }

	    @Override
	    public void destroy() {
	    }
}
