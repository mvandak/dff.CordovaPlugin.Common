/**
 * 
 */
package com.dff.cordova.plugin.common.service;

import java.util.ArrayList;
import java.util.List;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * @author frank
 *
 */
public class AndroidServiceConnectionListener
	extends AbstractPluginListener
	implements ServiceConnection {
	private static final String TAG = "com.dff.cordova.plugin.common.service.AndroidServiceConnectionListener";
	private List<ServiceConnection> serviceConnections = new ArrayList<ServiceConnection>();

	public boolean addServiceConnection(ServiceConnection e) {
		return serviceConnections.add(e);
	}

	public boolean removeServiceConnection(Object o) {
		return serviceConnections.remove(o);
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		CordovaPluginLog.d(TAG, "onServiceConnected: " + name.toString());
		
		for (ServiceConnection sc : this.serviceConnections) {
			sc.onServiceConnected(name, service);
		}
		
		super.sendPluginResult(true);
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		CordovaPluginLog.d(TAG, "onServiceDisconnected: " + name.toString());
		
		for (ServiceConnection sc : this.serviceConnections) {
			sc.onServiceDisconnected(name);
		}
		
		super.sendPluginResult(false);		
	}

}
