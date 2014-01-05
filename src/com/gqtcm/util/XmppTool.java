package com.gqtcm.util;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

import android.util.Log;



public class XmppTool {

	private static XMPPConnection con = null;
	
	private static void openConnection() {
		try {
			//url���˿ڣ�Ҳ�����������ӵķ��������֣���ַ���˿ڣ��û���
			ConnectionConfiguration connConfig = new ConnectionConfiguration("192.168.100.101", 5222);

			con = new XMPPConnection(connConfig);
			con.connect();
			con.addConnectionListener(new ConnectionListener() {
	            @Override
				public void connectionClosed() {
	                
	            	System.out.println("�ر�����");
	            }

	            @Override
				public void connectionClosedOnError(Exception e) {
	            	System.out.println("�ر������쳣");
	            }

	            @Override
				public void reconnectingIn(int seconds) {
	            	System.out.println("��������"+seconds);
	            }

	            @Override
				public void reconnectionFailed(Exception e) {
	            	System.out.println("��������ʧ��");
	            }

	            @Override
				public void reconnectionSuccessful() {
	            	System.out.println("�������ӳɹ�");
	            }
	        });
			


			
			
		}
		catch (XMPPException xe) 
		{
			xe.printStackTrace();
		}
	}

	public static XMPPConnection getConnection() {
		if (con == null) {
			openConnection();
		}
		return con;
	}

	public static void closeConnection() {
		con.disconnect();
		con = null;
	}

	public static void addPacketListener(PacketListener myListener,
			OrFilter allPF) {
		con.addPacketListener(myListener, allPF);
	}
}
