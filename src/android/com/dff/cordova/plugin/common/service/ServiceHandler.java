package com.dff.cordova.plugin.common.service;

import org.apache.cordova.CordovaInterface;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;


import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;

public class ServiceHandler	extends AbstractPluginListener implements ServiceConnection {
	private static final String TAG = "com.dff.cordova.plugin.common.service.ServiceHandler";
	private CordovaInterface cordova;
	private Class<? extends Service> serviceClass;
	boolean isBound = false;
	private Messenger service;
	
	public ServiceHandler(CordovaInterface cordova, Class<? extends Service> serviceClass) {
		this.cordova = cordova;
		this.serviceClass = serviceClass;
	}
	
	public boolean bindService() {	
		Intent bindIntent = new Intent(this.cordova.getActivity(), this.serviceClass);
		CordovaPluginLog.d(TAG, "bind service " + bindIntent.toString());
		return this.cordova.getActivity().bindService(bindIntent, this, Context.BIND_AUTO_CREATE);
	}
	
	public void unbindService() {
		if (isBound) {
			CordovaPluginLog.d(TAG, "unbind service " + this.serviceClass.toString());
			this.cordova.getActivity().unbindService(this);
		}		
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		CordovaPluginLog.d(TAG, "onServiceConnected: " + name.toString());
		this.setService(new Messenger(service));
		this.isBound = true;
		super.sendPluginResult(isBound);
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		CordovaPluginLog.d(TAG, "onServiceDisconnected: " + name.toString());
		setService(null);
		this.isBound = false;
		super.sendPluginResult(isBound);
	}

	public Messenger getService() {
		return service;
	}

	public void setService(Messenger service) {
		this.service = service;
	}
}
