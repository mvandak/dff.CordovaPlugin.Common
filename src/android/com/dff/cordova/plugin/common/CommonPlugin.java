package com.dff.cordova.plugin.common;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.common.log.LogListener;

import android.content.Intent;

public class CommonPlugin extends CordovaPlugin {
	private static final String LOG_TAG = "com.dff.cordova.plugin.common.CommonPlugin";
	// log service
	protected LogListener logListener;
	
   /**
	* Called after plugin construction and fields have been initialized.
	*/
	@Override
	public void pluginInitialize() {
		super.pluginInitialize();
		
    	this.logListener = new LogListener();
    	CordovaPluginLog.addLogListner(this.logListener);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		CordovaPluginLog.removeLogListener(this.logListener);
		this.logListener.onDestroy();
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
    	CordovaPluginLog.i(LOG_TAG, "onActivityResult - requestCode: " + requestCode + "; resultCode: " + resultCode + "; intent: " + intent.toString());
    }
    
    /**
     * Called when the activity receives a new intent.
     */
    @Override
    public void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
    	CordovaPluginLog.i(LOG_TAG, "new intent: " + intent.getAction() + " " + intent.getType() + " " + intent.getScheme());
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
 		
     	CordovaPluginLog.i(LOG_TAG, "call for action: " + action + "; args: " + args);
     	
     	// check super
     	boolean superResult = super.execute(action, args, callbackContext);
     	
     	if (action.equals("onLog")) {
     		this.logListener.setCallBack(callbackContext);
     		return true;
     	}
     	
     	return superResult;
     }
}
