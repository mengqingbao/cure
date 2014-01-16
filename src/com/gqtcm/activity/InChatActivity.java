package com.gqtcm.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gqtcm.component.InMessageArrayAdapter;
import com.gqtcm.model.InMessage;
import com.gqtcm.persistence.InMessageStore;
import com.gqtcm.util.XmppTool;

public class InChatActivity extends Activity implements OnClickListener{

	private ListView listView;
	private InMessageArrayAdapter iadapter;
	
	//点
	private ChatManager cm;
	private Chat chat;
	private String friendId;
	private String friendNick;
	private String userId;
	private String nick;
	private List<InMessage> msgs;
	private Button sendBtn;
	private Button fotoBtn,cameraBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.in_chat_activity);

		listView = (ListView) this.findViewById(R.id.listview);
		String defaultValue = getResources().getString(
				R.string.username_store_key);
		friendId = getIntent().getStringExtra("docId");
		friendNick=getIntent().getStringExtra("docNick");
		SharedPreferences sharedPref = this.getSharedPreferences(
				getString(R.string.in_chat_store), Context.MODE_PRIVATE);
		userId = sharedPref.getString(getString(R.string.username_store_key),
				"");
		nick=sharedPref.getString(getString(R.string.username_store_key), "");
		// get chat history data from db
		msgs = InMessageStore.getInstance().getMessages(userId, friendId, 0,5, this);
		if(msgs==null){
			msgs=new ArrayList<InMessage>();
		}
		iadapter = new InMessageArrayAdapter(this,msgs);
		listView.setAdapter(iadapter);
		cm = XmppTool.getConnection().getChatManager();
		chat = cm.createChat(friendId, null);

		// 注册接受其
		IntentFilter intentFilter = new IntentFilter(
				"pro.chinasoft.activity.InChatActivity");
		registerReceiver(mReceiver, intentFilter);
		
		init();
	}

	private void init(){
	   //this.findViewById(R.id.rl_bottom_more).setVisibility(View.GONE);
	   TextView tv = (TextView) this.findViewById(R.id.in_chat_activity_title);
	   tv.setText(friendId);
	   sendBtn=(Button) this.findViewById(R.id.btn_send);
	   sendBtn.setOnClickListener(this);
	   this.fotoBtn=(Button) this.findViewById(R.id.foto_btn);
	   this.cameraBtn=(Button) this.findViewById(R.id.btn_voice);
	   fotoBtn.setOnClickListener(this);
	   cameraBtn.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		unregisterReceiver(mReceiver);
		InMessageStore.getInstance().close();
		this.finish();
	}

	//type:true message from yourself,false:msg from friend
	private void refresh(String content,boolean type) {
		InMessage msg = new InMessage();
		msg.setContent(content);
		msg.setCreateDate(new Date());
		msg.setType(type);
		msgs.add(msg);
		iadapter.notifyDataSetChanged();
		listView.setSelection(msgs.size()-1);
	}
	BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String friendId = intent.getStringExtra("friendId");
			if (friendId.equals(friendId)) {
				String content = intent.getStringExtra("content");
				refresh(content,true);
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_voice:
			System.out.println("voice");
			break;
		case R.id.btn_send:
			sendMessage();	
			break;	
		case R.id.foto_btn:
			System.out.println("foto_btn");
			break;
		case R.id.button3:
			Intent intent = new Intent();
			intent.setClass(this, CameraActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {


		return super.onTouchEvent(event);

		}
	
	
	//send message
	public void sendMessage() {
		EditText text = (EditText) this.findViewById(R.id.et_sendmessage);
		String message = text.getText().toString();
		if(message==null||message==""){
			return;
		}
		// refresh ui
		refresh(message,false);
		
		//save the message comed from friends
		InMessageStore.getInstance().saveOrUpdate(userId, friendId,friendNick, message,false,nick,this);
		try {
			chat.sendMessage(message);
		} catch (XMPPException e) {
			System.out.println(e.getMessage()+"exception");
		}
		//clear the enter editText;
		text.setText("");
	}
	
	public void cancel(View v){
		this.unregisterReceiver(mReceiver);
		this.finish();
	}

	
}
