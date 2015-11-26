package org.dimigo.pay;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Connect {
	private static final String CODE = "123456789";
	
	public static void main(String[] args) {		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
	    try {
	    	HttpGet httpget = new HttpGet("http://ykh.zhost.kr/pay/?code=" + CODE);
	        System.out.println("Executing request : " + httpget.getRequestLine());

	        // Create a custom response handler
	        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	            @Override
	            public String handleResponse(
	                    final HttpResponse response) throws ClientProtocolException, IOException {
	                int status = response.getStatusLine().getStatusCode();
	                if (status >= 200 && status < 300) {
	                    HttpEntity entity = response.getEntity();
	                    return entity != null ? EntityUtils.toString(entity) : null;
	                } else {
	                    throw new ClientProtocolException("Unexpected response status: " + status);
	                }
	            }

	        };
	        
	        String responseBody = httpclient.execute(httpget, responseHandler);
	        System.out.println("------------------------------------------------");
	        System.out.println(responseBody);
	        
	        User user = UserJSONParser.parse(responseBody);	// 만든 유저에 파싱한 데이터를 입력함
	        System.out.println(user.getCode() + " 님의 현재 잔액 : " + user.getMoney());
	        
	    } catch (IOException e) {
			e.printStackTrace();
		} finally {
	        try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	}

}
