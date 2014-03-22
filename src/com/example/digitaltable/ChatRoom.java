package com.example.digitaltable;

import com.example.digitaltablesutil.PushUtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatRoom extends Activity {

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
		push = new PushUtil();
		
		// this is terrible, will get response code from subscription
		
	}
	
	public void setupViews() {
		ListViewMessages = (ListView) findViewById(R.id.ListViewMessages);
		EditEnterMessage = (EditText) findViewById(R.id.editTextMessage);
		ButtonSend = (Button) findViewById(R.id.Send);
		messageAdapter = new ArrayAdapter<String>(this, R.layout.message_box, R.id.text_msg);
		ListViewMessages.setAdapter(messageAdapter);

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
		messageAdapter.add("me :" + message);
		messageAdapter.notifyDataSetChanged();
	}
}
