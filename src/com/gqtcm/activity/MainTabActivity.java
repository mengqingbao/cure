package com.gqtcm.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

import com.gqtcm.persistence.InMessageStore;
import com.gqtcm.service.InSmsService;
import com.gqtcm.util.StringUtils;
import com.gqtcm.util.XmppTool;

import com.gqtcm.activity.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * @author mengqingbao
 *	
 */
public class MainTabActivity extends FragmentActivity{	
	private FragmentTabHost mTabHost;
	private LayoutInflater layoutInflater;
	private Class fragmentArray[] = {FragmentPage1.class,FragmentPage2.class,FragmentPage3.class,FragmentPage4.class,FragmentPage5.class};
	
	private int mImageViewArray[] = {R.drawable.tab_message_btn,R.drawable.tab_selfinfo_btn,
									 R.drawable.tab_square_btn,R.drawable.tab_more_btn,R.drawable.tab_home_btn};
	
	private String mTextviewArray[] = {"记录", "自珍", "寻医问药", "新闻","我"};
	private String userid;
	private List<Msg> listMsg = new ArrayList<Msg>();

	
	public class Msg {
		String userid;
		String msg;
		String date;
		String from;

		public Msg(String userid, String msg, String date, String from) {
			this.userid = userid;
			this.msg = msg;
			this.date = date;
			this.from = from;
		}
	}
	@Override
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       this.userid = getIntent().getStringExtra("USERID");
       
/*       	Intent intent = new Intent(this,InSmsService.class);
		intent.putExtra("USERID", userid);
		this.startService(intent);*/
		
       //消息监听 聊天必须start
      /* ChatManager cm = XmppTool.getConnection().getChatManager();
       cm.addChatListener(new ChatManagerListener() {
			@Override
			public void chatCreated(Chat chat, boolean able) 
			{
				chat.addMessageListener(new MessageListener() {
					@Override
					public void processMessage(Chat chat2, Message message)
					{
	
						
						String from = message.getFrom();
						String friendId=null;
						if(from.contains("/")){
							friendId=from.substring(0,from.lastIndexOf("/"));
							Log.v("--tags--", "--tags-form--接受到 "+friendId);
							Log.v("--tags--", "--tags-message--信息 "+message.getBody());
						}
						try {
							InMessageStore.getInstance().saveOrUpdate(userid, friendId,StringUtils.getUsername(friendId), message.getBody(), true,StringUtils.getUsername(userid),MainTabActivity.this.getApplicationContext());
						} catch (Exception e) {
							System.out.println(e.getMessage()+"exception");
							Log.i("--tags--", e.getMessage());
						}
						//发送广播通知更新聊天页面内容
						Intent intent = new Intent("pro.chinasoft.activity.InChatActivity");
						intent.putExtra("content", message.getBody());
						intent.putExtra("friendId", friendId);
						intent.putExtra("friendNick", StringUtils.getUsername(friendId));
				        sendBroadcast(intent);
					}
				});
			}
		});
       
		  PacketFilter filter = new AndFilter(new PacketTypeFilter(  
                  Presence.class));  
          PacketListener listener = new PacketListener() {  

              @Override  
              public void processPacket(Packet packet) {  
                  Log.i("Presence", "PresenceService------" + packet.toXML());  
                  //看API可知道   Presence是Packet的子类  
                  if (packet instanceof Presence) {  
                      Log.i("Presence", packet.toXML());  
                      Presence presence = (Presence) packet;  
                      //Presence还有很多方法，可查看API   
                      String from = presence.getFrom();//发送方  
                      String to = presence.getTo();//接收方  
                      //Presence.Type有7中状态  
                      if (presence.getType().equals(Presence.Type.subscribe)) {//好友申请  
                            System.out.println("好友申请 +++++");
						   确认                         Presence presence = new Presence(
						                                    Presence.Type.subscribed);//同意是
						subscribed   拒绝是unsubscribe
						                    presence.setTo(...);//接收方jid
						                    presence.setFrom(...);//发送方jid
						connection.sendPacket(presence);//connection是你自己的XMPPConnection链接
                      } else if (presence.getType().equals(  
                              Presence.Type.subscribed)) {//同意添加好友  
                            
                      } else if (presence.getType().equals(  
                              Presence.Type.unsubscribe)) {//拒绝添加好友  和  删除好友  
                            
                      } else if (presence.getType().equals(  
                              Presence.Type.unsubscribed)) {//这个我没用到  
                      	
                      } else if (presence.getType().equals(  
                              Presence.Type.unavailable)) {//好友下线   要更新好友列表，可以在这收到包后，发广播到指定页面   更新列表  
                            
                      } else {//好友上线  
                            
                      }  
                  }  
              }  
          };  
          XmppTool.getConnection().addPacketListener(listener, filter);  聊天必须*/
       setContentView(R.layout.main_tab_layout);
        
        initView();
       
    }
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	/**
	 * 初始化界面
	 */
	private void initView(){
		layoutInflater = LayoutInflater.from(this);
				
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);	
		
		int count = fragmentArray.length;	
				
		for(int i = 0; i < count; i++){	
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
	}
				
	private View getTabItemView(int index){
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);
		
		//ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		//imageView.setImageResource(mImageViewArray[index]);
		
		TextView textView = (TextView) view.findViewById(R.id.textview);		
		textView.setText(mTextviewArray[index]);
	
		return view;
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) 
		{
							
			switch (msg.what) {
			case 1:
				String[] args = (String[]) msg.obj;
				listMsg.add(new Msg(args[0], args[1], args[2], args[3]));
				break;			
			}
		};
	};	

	//退出
		@Override
		public void onBackPressed()
		{
			
			//AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainTabActivity.this);//(FragmentPage2.this);  
	      /*  logoutDialog.setTitle("确定退出IN-CHAT吗？");  
	        logoutDialog.setIcon(R.drawable.icon_home_nor);  
	        logoutDialog.setPositiveButton("确认", new DialogInterface.OnClickListener()  
	                {           
	                    @Override    
	                    public void onClick(DialogInterface dialog, int which)   
	                        {  */

	                          // 点击“<strong>确认</strong>”后的操作    
	                   		XmppTool.closeConnection();
	                   		//关闭数据库
	                   		InMessageStore.getInstance().close();
	                   		System.exit(0);
	                   		android.os.Process.killProcess(android.os.Process.myPid());
	                                            
	                        /*}    
	                    });    
	        logoutDialog.setNegativeButton("返回", new DialogInterface.OnClickListener()   
	                        {           
	                            @Override    
	                            public void onClick(DialogInterface dialog, int which){    
	                                // 点击“<strong>返回</strong>”后的操作,这里不设置没有任何操作    
	                            }    
	                        });
	        logoutDialog.show();
	        
*/	         
		}

		private boolean isExit=false;
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			
			if(keyCode==KeyEvent.KEYCODE_BACK){
				if(!isExit){
					isExit=true;
					Toast.makeText(this.getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
					new Handler().postDelayed(new Runnable(){
						public void run(){
							isExit=false;
						}
					},2000);
					return false;
				}
			}
			
			return super.onKeyDown(keyCode, event);
		}
		
}