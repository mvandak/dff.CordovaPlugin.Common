package com.dff.cordova.plugin.common.service.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.action.CordovaAction;
import com.dff.cordova.plugin.common.service.ServiceHandler;

public abstract class ServiceAction extends CordovaAction {
	protected ServiceHandler serviceHandler;

	public ServiceAction(String action, JSONArray args, CallbackContext callbackContext,
			CordovaInterface cordova, ServiceHandler serviceHandler) {
		super(action, args, callbackContext, cordova);
		
		this.serviceHandler = serviceHandler;
	}

}
