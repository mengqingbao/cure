package com.gqtcm.listener;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

public class InChatListener implements MessageListener  {

	public InChatListener(){}
	
	@Override
	public void processMessage(Chat arg0, Message message) {
		System.out.println("processMessage -----����-----");
		System.out
        .println(message.getFrom() + ":" + message.getBody());
	}


}