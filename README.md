# fluidity
SAOP (CXF):

http://localhost:8081/fluidity/services/dateTimeZoneService?wsdl

maven:

ws-build: ${workspace_loc:/fluidity} --> goals: clean install

ws-run: ${workspace_loc:/fluidity} --> goals: org.apache.tomcat.maven:tomcat7-maven-plugin:2.2:run

WARNING: to fix:

Security processing failed (actions mismatch)
org.apache.cxf.phase.PhaseInterceptorChain doDefaultLogging
Interceptor for {http://service.soap.fluidity.org/}DateTimeZoneServiceService#{http://service.soap.fluidity.org/}getDateTimeByZone has thrown exception, unwinding now
org.apache.cxf.binding.soap.SoapFault: An error was discovered processing the <wsse:Security> header
	at org.apache.cxf.ws.security.wss4j.WSS4JUtils.createSoapFault(WSS4JUtils.java:234)
	at org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor.handleMessageInternal(WSS4JInInterceptor.java:340)
	at org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor.handleMessage(WSS4JInInterceptor.java:175)
	at org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor.handleMessage(WSS4JInInterceptor.java:86)
	at org.apache.cxf.phase.PhaseInterceptorChain.doIntercept(PhaseInterceptorChain.java:308)
	at org.apache.cxf.endpoint.ClientImpl.doInvoke(ClientImpl.java:516)
	at org.apache.cxf.endpoint.ClientImpl.invoke(ClientImpl.java:425)
	at org.apache.cxf.endpoint.ClientImpl.invoke(ClientImpl.java:326)
	at org.apache.cxf.endpoint.ClientImpl.invoke(ClientImpl.java:279)
	at org.apache.cxf.frontend.ClientProxy.invokeSync(ClientProxy.java:96)
	at org.apache.cxf.jaxws.JaxWsClientProxy.invoke(JaxWsClientProxy.java:139)
	at com.sun.proxy.$Proxy42.getDateTimeByZone(Unknown Source)
	at org.fluidity.soap.client.SoapServiceClient.main(SoapServiceClient.java:90) <--

	
----------------------------

REST:
	
WARNING: to fix:
	
Exception in thread "main" javax.ws.rs.NotFoundException: HTTP 404 Not Found
at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
at org.apache.cxf.jaxrs.client.AbstractClient.convertToWebApplicationException(AbstractClient.java:514)
at org.apache.cxf.jaxrs.client.WebClient.doInvoke(WebClient.java:904)
at org.apache.cxf.jaxrs.client.WebClient.doInvoke(WebClient.java:866)
at org.apache.cxf.jaxrs.client.WebClient.invoke(WebClient.java:431)
at org.apache.cxf.jaxrs.client.WebClient.post(WebClient.java:461)
at org.fluidity.rest.client.RestServiceClient.createEmployee(RestServiceClient.java:65)
at org.fluidity.rest.client.RestServiceClient.main(RestServiceClient.java:104)
	