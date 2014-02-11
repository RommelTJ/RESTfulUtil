package edu.sandiego.restfulUtil.resources;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.servlet.http.Cookie;
import javax.ws.rs.core.MultivaluedMap;

public class TestRestFulClient {
	
	public static void testGetParentInfo() {
		String testReturn = "";
		try {
			 
			
			
			//***********
			
			
			    Client clientForGetParentInformation = Client.create();
				WebResource webResourceForGetParentInformation = clientForGetParentInformation.resource("http://localhost:8080/restfulUtil/getInfo/USDRESTfulUtil/getParentInfo");
				MultivaluedMap formDataForGetParentInformation = new MultivaluedMapImpl();
				formDataForGetParentInformation.add("childPidm","612291");
				ClientResponse respForGetParentInformation = webResourceForGetParentInformation.post(ClientResponse.class,formDataForGetParentInformation);
				if (respForGetParentInformation.getStatus() != 200) {
				}
				
				String output = respForGetParentInformation.getEntity(String.class);
				//out.println(output);
				System.out.println(output);
			
			
			//************
			
			
			
	 
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
		
	 
		}
	
	public static Cookie testGetParentPortalCookie(String childPidm, String parentPidm){
		String output=null;
		Cookie retCk=null;
		
try {
			 
			
			
			//***********
			 	
			    Client clientForGetParentInformation = Client.create();
				WebResource webResourceForGetParentPortalCookie = clientForGetParentInformation.resource("http://localhost:8080/restfulUtil/getInfo/USDRESTfulUtil/getParentPortalCookie");
				MultivaluedMap formDataForGetParentPortalCookie  = new MultivaluedMapImpl();
				formDataForGetParentPortalCookie .add("childPidm","404554");
				formDataForGetParentPortalCookie .add("parentPidm","1070741");
				ClientResponse respForGetParentPortalCookie  = webResourceForGetParentPortalCookie .post(ClientResponse.class,formDataForGetParentPortalCookie);
				if (respForGetParentPortalCookie.getStatus() != 200) {
				}
				
				 output = respForGetParentPortalCookie.getEntity(String.class);
				 System.out.println("COOKIEEEEEEEEEEEEEEEEEEEEEEEEE--: "+output);
				 retCk = new Cookie("WEB4PARENT", output);
				 retCk.setPath("/");
				 retCk.setDomain(".sandiego.edu");
				//out.println(output);
				 
				 
				//*************************
		        	Client clientForGetEmailAddress = Client.create();
		        	WebResource webResourceForGetEmailAddress = clientForGetEmailAddress.resource("http://allproxy.sandiego.edu/restfulUtil/getInfo/USDRESTfulUtil/getEmailAddress");
		        	MultivaluedMap formDataForGetEmailAddress = new MultivaluedMapImpl();
		        	formDataForGetEmailAddress.add("pidm","612291");
		        	ClientResponse respForGetEmailAddress = webResourceForGetEmailAddress.post(ClientResponse.class,formDataForGetEmailAddress);
		        	if(respForGetEmailAddress.getStatus()!=200){
		        		System.out.println(respForGetEmailAddress.getStatus());
		        	}
		        	
		        	
		        	String emailAddress = respForGetEmailAddress.getEntity(String.class);
		        	System.out.println("-----------------------: "+emailAddress);
				 
				 
				 
				return retCk;
			
			
			//************
			
				
			
			
	 
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
return retCk;
			
		
	 
		}
	
	
	

}
