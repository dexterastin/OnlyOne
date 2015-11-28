/**
 * 
 */
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

public class EditMoney {
	protected static final String CODE = "513215312";	// 유저 코드
	protected static final int MONEY = 5000;	// 들어올(빠져나갈 돈)
	public static void main(String[] args) {		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
	    try {
	    	HttpGet httpget = new HttpGet("http://ykh.zhost.kr/pay/editMoney.php?code=" + CODE + "&em=" + String.valueOf(MONEY));
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
	        
//	        User user = UserJSONParser.parse(responseBody);
//	        System.out.println(user.getCode() + " 님의 현재 잔액 : " + user.getMoney());
	        // 에디트 머니는 요청만 때리는걸로(출력 안함)
	        
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
