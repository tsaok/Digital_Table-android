package com.example.digitaltablesutil;
import android.util.Log;

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

	public PushUtil() {		
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
		    public void onSubscriptionSucceeded(String channelName) {
		    	Log.d("Subscription", "success!");
		    	System.out.println("Subscribed!");
		    }
	
		    @Override
		    public void onEvent(String channelName, String eventName, String data) {
		        // Called for incoming events names "foo", "bar" or "baz"
		    	Log.d("Hello", "asdfasdf");
		    }

			@Override
			public void onAuthenticationFailure(String arg0, Exception arg1) {
				// TODO Auto-generated method stub
				Log.d("Hello", "Auth failed");
			}
		}, "foo", "bar", "baz");
	}

	
	public boolean sendMessage(String s) {
		channel.trigger("client-Message", "{\"message:\":\"" + s + "\"}");
		return true;
	}

}
