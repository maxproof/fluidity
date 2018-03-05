package org.fluidity.soap.client;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.fluidity.soap.dto.AttachmentData;
import org.fluidity.soap.dto.TimeZoneInput;
import org.fluidity.soap.fault.FaultMessage;
import org.fluidity.soap.service.DateTimeZoneService;
import org.fluidity.soap.utils.TimeZoneIds;

//class invoke service (CXF)
public class SoapServiceClient {
	
	static final String serviceEndpoint = "http://localhost:8081/fluidity/services/dateTimeZoneService";
	
	static final String attachmentFile = "D:/testsFiles/AttachmentContents.txt";
	
	public static void main(String[] args) {
		
		//create JAX-WS proxy to invoke service
		//CXF proxy factory bean to create proxy
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(DateTimeZoneService.class);
		factory.setAddress(serviceEndpoint);
		
		//create Wss4j interceptor to send UsernameToken profile as part
		//of the soap request headers
		Map<String, Object> outProperties = new HashMap<String, Object>();
		//profile name i.e. security profile username token profile
		outProperties.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		//put user to validate
		outProperties.put(WSHandlerConstants.USER, "max");
		//password type: we are using plain password text
		outProperties.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		
		//callback used to retrieve pw for the user
		outProperties.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {
			@Override
			public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
				
				WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
				pc.setPassword("proof");
			}
		});
		//put in wss4j interceptor
		WSS4JInInterceptor wssOut = new WSS4JInInterceptor(outProperties);
		//add security interceptor in factory
		factory.getOutInterceptors().clear();
		factory.getOutInterceptors().add(wssOut);
		
		//create JAX-WS proxy to invoke service
		DateTimeZoneService seiProxy = (DateTimeZoneService) factory.create();
		
		//prepare input service data
		TimeZoneInput serviceInput = new TimeZoneInput();
		serviceInput.setZoneName(TimeZoneIds.ACT);
		
		//prepare attachment data to send in the soap request
		DataSource source = new FileDataSource(new File(attachmentFile));
		//cast client's proxy to a BindingProvider object
		BindingProvider bp = (BindingProvider) seiProxy;
		SOAPBinding binding = (SOAPBinding) bp.getBinding();
		//set the binding MTOM enabled property to true
		binding.setMTOMEnabled(true);
		
		//attachment file using AttachmentData
		AttachmentData attachmentData = new AttachmentData();
		attachmentData.setImageData(new DataHandler(source));
		serviceInput.setAttachmentData(attachmentData);
		
		//invoke remote service //WARNING: TO FIX!
		try {
			String dateTime = seiProxy.getDateTimeByZone(serviceInput);
			System.out.println("Date & Time found: " + dateTime);
		} catch (FaultMessage e) {
			e.printStackTrace();
		}
	}

}
