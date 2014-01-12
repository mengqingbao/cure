package com.gqtcm.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.gqtcm.activity.CareCommentActivity;
import com.gqtcm.activity.CareDetailActivity;

public class Fr3ItemOnClickListener implements  AdapterView.OnItemClickListener{
	private Context context;
	public Fr3ItemOnClickListener(Context context){
		this.context=context;
	}
	@Override
	public void onItemClick(AdapterView<?> adpater, View view, int postion, long id) {
		Intent intent = new Intent();
		intent.setClass(context, CareCommentActivity.class);
		Bundle bundle = new Bundle();
		/*bundle.putString("friendId", user.getUserId());
		bundle.putString("friendNick", user.getNick());*/
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

}
