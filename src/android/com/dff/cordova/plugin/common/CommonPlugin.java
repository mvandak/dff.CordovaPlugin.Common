package com.dff.cordova.plugin.common;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.common.log.LogListener;

import android.content.Intent;
import android.os.Bundle;

public class CommonPlugin extends CordovaPlugin {
	private static final String LOG_TAG = "com.dff.cordova.plugin.common.CommonPlugin";
	private String childLogTag = "";
	// log service
	protected static LogListener logListener;
	
	public CommonPlugin() {
		super();
	}
	
	public CommonPlugin(String childLogTag) {
		this();
		this.childLogTag = childLogTag;
	}
	
   /**
	* Called after plugin construction and fields have been initialized.
	*/
	@Override
	public void pluginInitialize() {
		CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "pluginInitialize");
		super.pluginInitialize();
		
		if (logListener == null) {
			logListener = new LogListener();
			CordovaPluginLog.addLogListner(logListener);
		}
	}
	
    /**
     * Called when the system is about to start resuming a previous activity.
     *
     * @param multitasking		Flag indicating if multitasking is turned on for app
     */
    public void onPause(boolean multitasking) {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "onPause - multitasking: " + multitasking);
    	super.onPause(multitasking);
    }

    /**
     * Called when the activity will start interacting with the user.
     *
     * @param multitasking		Flag indicating if multitasking is turned on for app
     */
    public void onResume(boolean multitasking) {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "onResume - multitasking: " + multitasking);
    	super.onResume(multitasking);
    }

    /**
     * Called when the activity is becoming visible to the user.
     */
    public void onStart() {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "onStart");
    	super.onStart();
    }

    /**
     * Called when the activity is no longer visible to the user.
     */
    public void onStop() {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "onStop");
    	super.onStop();
    }
    
    /**
     * Called when the activity receives a new intent.
     */
    @Override
    public void onNewIntent(Intent intent) {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "onNewIntent: " + intent.getAction() + " " + intent.getType() + " " + intent.getScheme());
    	super.onNewIntent(intent);
    }
    
    /**
     * The final call you receive before your activity is destroyed.
     */
	@Override
	public void onDestroy() {
		CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "onDestroy");		
		super.onDestroy();
	}
  
    /**
     * Called when the Activity is being destroyed (e.g. if a plugin calls out to an external
     * Activity and the OS kills the CordovaActivity in the background). The plugin should save its
     * state in this method only if it is awaiting the result of an external Activity and needs
     * to preserve some information so as to handle that result; onRestoreStateForActivityResult()
     * will only be called if the plugin is the recipient of an Activity result
     *
     * @return  Bundle containing the state of the plugin or null if state does not need to be saved
     */
    public Bundle onSaveInstanceState() {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "onSaveInstanceState");
        return super.onSaveInstanceState();
    }

    /**
     * Called when a plugin is the recipient of an Activity result after the CordovaActivity has
     * been destroyed. The Bundle will be the same as the one the plugin returned in
     * onSaveInstanceState()
     *
     * @param state             Bundle containing the state of the plugin
     * @param callbackContext   Replacement Context to return the plugin result to
     */
    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext) {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")"
    			, "onRestoreStateForActivityResult -"
    					+ " bundle: " + state.toString()
    					+ "; callbackContext: " + callbackContext.toString());
    	
    	super.onRestoreStateForActivityResult(state, callbackContext);
    }


    /**
     * Called when a message is sent to plugin.
     *
     * @param id            The message id
     * @param data          The message data
     * @return              Object to stop propagation or null
     */
    public Object onMessage(String id, Object data) {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")"
    			, "onMessage - "
    					+ " id: " + id
    					+ "; data: " + data.toString());
    	
        return super.onMessage(id, data);
    }
    
    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     *
     * @param requestCode   The request code originally supplied to startActivityForResult(),
     *                      allowing you to identify who this result came from.
     * @param resultCode    The integer result code returned by the child activity through its setResult().
     * @param intent        An Intent, which can return result data to the caller (various data can be
     *                      attached to Intent "extras").
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "onActivityResult - requestCode: " + requestCode + "; resultCode: " + resultCode + "; intent: " + intent.toString());
    	super.onActivityResult(requestCode, resultCode, intent);
    }
    
    /**
     * Called when the WebView does a top-level navigation or refreshes.
     *
     * Plugins should stop any long-running processes and clean up internal state.
     *
     * Does nothing by default.
     */
    public void onReset() {
    	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "onReset");
    	super.onReset();
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
 		
     	CordovaPluginLog.i(LOG_TAG + "(" + this.childLogTag + ")", "call for action: " + action + "; args: " + args);
     	
     	if (action.equals("onLog")) {
     		if (logListener != null) {
     			logListener.setCallBack(callbackContext);     			
     		}
     		else {
     			LOG.e(LOG_TAG, "log listener not initialized");
     		}
     		
     		return true;
     	}
     	
     	return super.execute(action, args, callbackContext);
     }
}
