package com.example.digitaltablesutil;
import java.lang.reflect.Type;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.digitaltable.PusherActivity;
import com.example.digitaltablemodels.DTMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.*;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.util.HttpAuthorizer;

public class PushUtil {
	private Pusher pusher;
	private PrivateChannel channel;
	private PusherActivity act;
	
	public PushUtil(final PusherActivity act) {	
		this.act = act;
		Log.d("Hello", "init");
		HttpAuthorizer authorizer = new HttpAuthorizer("http://digitaltable.parseapp.com/pusher/auth");
		PusherOptions options = new PusherOptions().setAuthorizer(authorizer);
		pusher = new Pusher("3c7da757d0d8da6f399e", options);
		pusher.connect(new ConnectionEventListener() {
		    @Override
		    public void onConnectionStateChange(ConnectionStateChange change) {
		        System.out.println("State changed to " + change.getCurrentState() +
		                           " from " + change.getPreviousState());
		    }
	
		    @Override
		    public void onError(String message, String code, Exception e) {
		    	Log.d("ConnectionError", "There was a problem connecting!");
		        System.out.println("There was a problem connecting!");
		    }
		}, ConnectionState.ALL);
	
		channel = pusher.subscribePrivate("private-my-channel", new PrivateChannelEventListener() {
		    @Override
		    public void onSubscriptionSucceeded(final String channelName) {
		    	Log.d("Subscription", "success!");
		    	System.out.println("Subscribed!");
		    	Handler refresh = new Handler(Looper.getMainLooper());
		    	refresh.post(new Runnable() {
		    	    public void run()
		    	    {
		    	    	act.onSubscriptionSucceeded(channelName);
		    	    }
		    	});

		    	
		    }
	
		    @Override
		    public void onEvent(final String channelName, final String eventName, final String data) {
		        // Called for incoming events names "foo", "bar" or "baz"
		    	Log.d("Hello", "asdfasdf");
		    	Handler refresh = new Handler(Looper.getMainLooper());
		    	refresh.post(new Runnable() {
		    	    public void run()
		    	    {
				    	act.onEvent(channelName, eventName, data);
		    	    }
		    	});

		    }

			@Override
			public void onAuthenticationFailure(final String arg0, final Exception arg1) {
				// TODO Auto-generated method stub
				Log.d("Hello", "Auth failed");
		    	Handler refresh = new Handler(Looper.getMainLooper());
		    	refresh.post(new Runnable() {
		    	    public void run()
		    	    {
		    			act.onAuthenticationFailure(arg0, arg1);
		    	    }
		    	});

	
			}
		}, "foo", "bar", "Message");
	}

	
	public boolean sendMessage(String s) {
		channel.trigger("client-Message", "{\"message:\":\"" + s + "\"}");
		return true;
	}

}
