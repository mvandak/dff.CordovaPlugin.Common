package com.dff.cordova.plugin.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.action.CordovaAction;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.common.service.action.BindService;
import com.dff.cordova.plugin.common.service.action.ServiceAction;
import com.dff.cordova.plugin.common.service.action.UnbindService;

public abstract class CommonServicePlugin extends CommonPlugin {
	private static final String TAG = "com.dff.cordova.plugin.common.service.CommonServicePlugin";
	private HashMap<String, Class<? extends ServiceAction>> actions = new HashMap<String, Class<? extends ServiceAction>>();
	protected ServiceHandler serviceHandler;
	private ServiceConnectionListener serviceConnectionListener;
	
	public CommonServicePlugin(String TAG) {
		super(TAG);
		
		actions.put(BindService.ACTION_NAME, BindService.class);
		actions.put(UnbindService.ACTION_NAME, UnbindService.class);
	}

	public void pluginInitialize(ServiceHandler serviceHandler) {
		super.pluginInitialize();
		this.serviceHandler = serviceHandler;
		this.serviceConnectionListener = new ServiceConnectionListener();
		this.serviceHandler.addServiceConnection(this.serviceConnectionListener);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		this.serviceHandler.unbindService();
		this.serviceConnectionListener.onDestroy();
	}
	
   /**
    * Executes the request.
    *
    * This method is called from the WebView thread.
    * To do a non-trivial amount of work, use:
    * cordova.getThreadPool().execute(runnable);
    *
    * To run on the UI thread, use:
    * cordova.getActivity().runOnUiThread(runnable);
    *
    * @param action The action to execute.
    * @param args The exec() arguments.
    * @param callbackContext The callback context used when calling back into JavaScript.
    * @return Whether the action was valid.
    */
	@Override
    public boolean execute(String action
    		, final JSONArray args
    		, final CallbackContext callbackContext)
        throws JSONException {
		CordovaPluginLog.d(TAG, "call for action: " + action + "; args: " + args);
		
    	CordovaAction cordovaAction = null;
    	
    	if (action.equals("onServiceConnectionChange")) {
    		this.serviceConnectionListener.setCallBack(callbackContext);
    		return true;
    	}
     	else if (actions.containsKey(action)) {     		
     		Class<? extends ServiceAction> actionClass = actions.get(action);
     		
     		CordovaPluginLog.d(TAG, "found action: " + actionClass.getName());
     		
     		try {
				cordovaAction = actionClass.getConstructor(
						String.class
						, JSONArray.class
						, CallbackContext.class
						, CordovaInterface.class
						, ServiceHandler.class
					)
					.newInstance(action, args, callbackContext, this.cordova, this.serviceHandler);
			} catch (InstantiationException e) {
				CordovaPluginLog.e(TAG, e.getMessage(), e);
			} catch (IllegalAccessException e) {
				CordovaPluginLog.e(TAG, e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				CordovaPluginLog.e(TAG, e.getMessage(), e);
			} catch (InvocationTargetException e) {
				CordovaPluginLog.e(TAG, e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				CordovaPluginLog.e(TAG, e.getMessage(), e);
			} catch (SecurityException e) {
				CordovaPluginLog.e(TAG, e.getMessage(), e);
			}
     	}
    	
    	if (cordovaAction != null) {
    		cordova.getThreadPool().execute(cordovaAction);
            return true;
    	}    	

        return super.execute(action, args, callbackContext);
    }	
}
