package com.bluemeric.connection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpConnection {

	/**
	 * @param args
	 */
	public int statusCode ;
	public String responseString ;

	private HttpClient client;

	public HttpConnection() throws Exception {
		this.client = new HttpClient();
	}

	public int get(String url) throws Exception
	{
		GetMethod gm = new GetMethod(url);  
		statusCode = client.executeMethod(gm);
		responseString = gm.getResponseBodyAsString();
		gm.releaseConnection();
		
		System.out.println("URL : GET " + url);
		System.out.println(" statusCode = " + statusCode ); 
		System.out.println(" Response = " +  responseString); 
		
		return statusCode;
	}

}
