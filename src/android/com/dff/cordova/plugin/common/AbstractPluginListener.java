package com.dff.cordova.plugin.common;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractPluginListener {
	protected CallbackContext callback;

	public void setCallBack(CallbackContext callback) {
		this.callback = callback;
	}
	
	public void onDestroy() {
		if (this.callback != null) {
			this.callback.success();
		}
	}
	
	protected void sendPluginResult() {
		if (this.callback != null) {
			PluginResult result = new PluginResult(PluginResult.Status.OK);
            result.setKeepCallback(true);
            this.callback.sendPluginResult(result);
            
		}
	}
	
	protected void sendPluginResult(JSONObject message) {
		if (this.callback != null) {
			PluginResult result = new PluginResult(PluginResult.Status.OK, message);
            result.setKeepCallback(true);
            this.callback.sendPluginResult(result);
            
		}
	}
	
	protected void sendPluginResult(JSONArray message) {
		if (this.callback != null) {
			PluginResult result = new PluginResult(PluginResult.Status.OK, message);
            result.setKeepCallback(true);
            this.callback.sendPluginResult(result);
            
		}
	}

	protected void sendPluginResult(String message) {
		if (this.callback != null) {
			PluginResult result = new PluginResult(PluginResult.Status.OK, message);
            result.setKeepCallback(true);
            this.callback.sendPluginResult(result);
            
		}
	}
	
	protected void sendPluginResult(boolean b) {
		if (this.callback != null) {
			PluginResult result = new PluginResult(PluginResult.Status.OK, b);
            result.setKeepCallback(true);
            this.callback.sendPluginResult(result);
            
		}
	}
	
	protected void sendPluginResult(int i) {
		if (this.callback != null) {
			PluginResult result = new PluginResult(PluginResult.Status.OK, i);
            result.setKeepCallback(true);
            this.callback.sendPluginResult(result);
            
		}
	}
	
	protected void sendPluginResult(JSONException je) {
		if (this.callback != null) {
			PluginResult result = new PluginResult(PluginResult.Status.JSON_EXCEPTION, je.getMessage());
            result.setKeepCallback(true);
            this.callback.sendPluginResult(result);
		}
	}
}
