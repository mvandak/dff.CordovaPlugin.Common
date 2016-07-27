package com.dff.cordova.plugin.common.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

public abstract class CordovaAction implements Runnable {
	private static final String LOG_TAG = "com.dff.cordova.plugin.common.action.CordovaAction";
	
	protected String action;
	protected JSONArray args;
	protected CallbackContext callbackContext;
	protected CordovaInterface cordova;
	
	public CordovaAction(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova) {
		this.action = action;
		this.args = args;
		this.callbackContext = callbackContext;
		this.cordova = cordova;
	}
	
	@Override
	public void run() {
		CordovaPluginLog.i(LOG_TAG, "running action: " + this.action + "; args: " + this.args);
	}

}
