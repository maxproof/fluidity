package org.fluidity.soap.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.EndpointSelectionInterceptor;
import org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class ServiceInterceptor extends AbstractSoapInterceptor {
	
	public ServiceInterceptor() {
		super(Phase.INVOKE);
		addAfter(ReadHeadersInterceptor.class.getName());
		addAfter(EndpointSelectionInterceptor.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		
		System.out.println("Soap Message Contains Headers: " + message.hasHeaders());
		
	}

}
