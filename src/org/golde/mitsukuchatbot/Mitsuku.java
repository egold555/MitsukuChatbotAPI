package org.golde.mitsukuchatbot;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Mitsuku {
	
	private final String client_name;
	private final int sessionid;
	private final int channel;
	
	private static final Gson gson = new Gson();
	
	private static final MitsukuResponce RESPONSE_ERROR = new MitsukuResponce();
	
	static {
		RESPONSE_ERROR.channel = -1;
		RESPONSE_ERROR.sessionid = -1;
		RESPONSE_ERROR.status = "An error occurred. Check stacktrace.";
	}
	
	public Mitsuku(String client_name, int sessionid, int channel) {
		this.client_name = client_name;
		this.sessionid = sessionid;
		this.channel = channel;
	}
	
	public MitsukuResponce getResponce(String message) {
		
		try {
		
		OkHttpClient client = new OkHttpClient();

		RequestBody body = RequestBody.create(null, ""); //No body
		
		Request request = new Request.Builder()
		  .url(getRequestUrl(message))
		  .post(body)
		  .addHeader("Origin", "http://www.square-bear.co.uk")
		  .addHeader("Referer", "http://www.square-bear.co.uk/mitsuku/nfchat.htm")
		  .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
		  .build();

		Response response = client.newCall(request).execute();
		String gotBack = response.body().string();
		
		return gson.fromJson(gotBack, MitsukuResponce.class);
		
		
		}
		catch(Exception e) {
			e.printStackTrace();
			return RESPONSE_ERROR;
		}
	}
	
	private String getRequestUrl(String input) throws UnsupportedEncodingException {
		input = URLEncoder.encode(input, "UTF-8");
		return "https://miapi.pandorabots.com/talk?botkey=n0M6dW2XZacnOgCWTp0FRaadjiO5TASZD_5OKHTs9hqAp62JnACkE6BQdHSvL1lL7jiC3vL-JS0~&input=" + input + "&client_name=" + client_name + "&sessionid=" + sessionid + "&channel=" + channel;
	}
	
}
