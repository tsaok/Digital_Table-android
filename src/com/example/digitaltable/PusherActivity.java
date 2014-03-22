package com.example.digitaltable;

public interface PusherActivity {

    public void onSubscriptionSucceeded(String channelName);

    public void onEvent(String channelName, String eventName, String data);

	public void onAuthenticationFailure(String arg0, Exception arg1);
}
