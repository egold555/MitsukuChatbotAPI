package org.golde.mitsukuchatbot;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MitsukuResponce {


	@SerializedName("status")
	@Expose
	public String status;
	@SerializedName("responses")
	@Expose
	public List<String> responses = null;
	@SerializedName("sessionid")
	@Expose
	public Integer sessionid;
	@SerializedName("channel")
	@Expose
	public Integer channel;

	public String getResponce() {
		return responses.get(0);
	}
	
	public String getSanatizedResponce() {
		return getResponce().replaceAll("<image>[\\s\\S]*?</image>","");
	}

}
