package org.fluidity.soap.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;

//basic security mechanism
public class BasicAuthenticationHandler implements ContainerRequestFilter {

	private static Map<String, String> usersMap = new HashMap<String, String>();
	
	//put user in map
	static {
		usersMap.put("max", "proof");
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		//authenticate user after getting credentials
		
		Response failureResponse = Response.status(401).header("WWW-Authenticate", "Basic").build();
		Message message = JAXRSUtils.getCurrentMessage();
		
		//get authorization policy
		AuthorizationPolicy policy = (AuthorizationPolicy) message.get(AuthorizationPolicy.class);
		if(policy == null) {
			System.out.println("User name and password missing.");
			//break filter
			requestContext.abortWith(failureResponse);
		} else {
			//get user name and password
			String userName = policy.getUserName();
			String password = policy.getPassword();
			//authenticate user name and password
			if(usersMap.containsKey(userName) || (usersMap.get(userName).equals(password))) {
				System.out.println("User name and password provided but could not be authenticate.");
				requestContext.abortWith(failureResponse);
			} else {
				System.out.println("User name " + userName + " authenticated.");
			}
		}
	}

}
