package org.fluidity.soap.security;

import java.util.HashMap;
import java.util.Map;

import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.common.ext.WSSecurityException.ErrorCode;
import org.apache.wss4j.dom.handler.RequestData;
import org.apache.wss4j.dom.message.token.UsernameToken;
import org.apache.wss4j.dom.validate.Credential;
import org.apache.wss4j.dom.validate.UsernameTokenValidator;

//standard WS-* security UsernameToken profile
public class ServiceUserNameTokenValidator extends UsernameTokenValidator {

	
	private final static Map<String, String> USERS = new HashMap<String, String>();
	
	static {
		USERS.put("max", "proof");
	}
	
	public Credential validate(Credential credential, RequestData request) throws WSSecurityException {
		
		UsernameToken userToken = credential.getUsernametoken();
		
		String userId = userToken.getName();
		String password = userToken.getPassword();
		
		System.out.println("Validating user: " + userId + ", password: " + password);
		
		String pwd = USERS.get(userId);
		if (pwd != null & pwd.equals(password)) {
			System.out.println("User authenticated.");
			return credential;
		} else {
			System.out.println("User authentication failed.");
			throw new WSSecurityException(ErrorCode.FAILED_AUTHENTICATION);
		}
		
	}
	
}
