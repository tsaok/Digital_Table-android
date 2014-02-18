package com.example.digitaltablesutil;
import com.pusher.client.Pusher;
import com.pusher.client.channel.*;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

public class PushUtil {

	public PushUtil() {		
		Pusher pusher = new Pusher("63122");
		pusher.connect(new ConnectionEventListener() {
		    @Override
		    public void onConnectionStateChange(ConnectionStateChange change) {
		        System.out.println("State changed to " + change.getCurrentState() +
		                           " from " + change.getPreviousState());
		    }
	
		    @Override
		    public void onError(String message, String code, Exception e) {
		        System.out.println("There was a problem connecting!");
		    }
		}, ConnectionState.ALL);
	
		Channel channel = pusher.subscribe("my-channel", new ChannelEventListener() {
		    @Override
		    public void onSubscriptionSucceeded(String channelName) {
		        System.out.println("Subscribed!");
		    }
	
		    @Override
		    public void onEvent(String channelName, String eventName, String data) {
		        // Called for incoming events names "foo", "bar" or "baz"
		    }
		}, "foo", "bar", "baz");
	}


}
