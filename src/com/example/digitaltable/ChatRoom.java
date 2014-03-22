package com.example.digitaltable;

import com.example.digitaltablemodels.DTMessage;
import com.example.digitaltablesutil.PushUtil;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatRoom extends Activity implements PusherActivity {

	private static final int TRANSCRIPT_MODE_NORMAL = 1;
	private Button ButtonSend;
	private EditText EditEnterMessage;
	private ListView ListViewMessages;
	private ArrayAdapter<String> messageAdapter;
	private PushUtil push;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_room);
		setupPusher();
		setupViews();
	}

	public void setupPusher() {
		push = new PushUtil(this);
		
		// this is terrible, will get response code from subscription
		
	}
	
	public void setupViews() {
		ListViewMessages = (ListView) findViewById(R.id.ListViewMessages);
		EditEnterMessage = (EditText) findViewById(R.id.editTextMessage);
		ButtonSend = (Button) findViewById(R.id.Send);
		messageAdapter = new ArrayAdapter<String>(this, R.layout.message_box, R.id.text_msg);
		ListViewMessages.setAdapter(messageAdapter);
		ListViewMessages.setTranscriptMode(TRANSCRIPT_MODE_NORMAL);
		ListViewMessages.setStackFromBottom(false);
//		EditEnterMessage.setOnKeyListener(new OnKeyListener() {
//
//			@Override
//			public boolean onKey(View v, int keyCode, KeyEvent event) {
//				if (event.getAction() == KeyEvent.ACTION_DOWN) {
//					switch (keyCode) {
//					case KeyEvent.KEYCODE_DPAD_CENTER:
//					case KeyEvent.KEYCODE_ENTER:
//						sendMessage(EditEnterMessage.getText().toString());
//						EditEnterMessage.setText("");
//						return true;
//					default:
//						break;
//					}
//				}
//				return false;
//
//			}
//		});

		ButtonSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				sendMessage(EditEnterMessage.getText().toString());
				push.sendMessage(EditEnterMessage.getText().toString());
				EditEnterMessage.setText("");
			}
		});

	}

	public void sendMessage(String message) {
		messageAdapter.add("Me: " + message);
		messageAdapter.notifyDataSetChanged();
	}

	@Override
	public void onSubscriptionSucceeded(String channelName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(String channelName, String eventName, String data) {
		if (eventName.equals("Message")) {
 	    	Gson g = new Gson();
	    	java.lang.reflect.Type type = DTMessage.class;
	    	DTMessage msg = g.fromJson(data, type);
			messageAdapter.add("You: " + msg);
			messageAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onAuthenticationFailure(String arg0, Exception arg1) {
		// TODO Auto-generated method stub
		
	}
}
