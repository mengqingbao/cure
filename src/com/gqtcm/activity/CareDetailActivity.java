package com.gqtcm.activity;

import com.gqtcm.adapter.CareDetailListAdapter;
import com.gqtcm.model.CareItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class CareDetailActivity extends Activity  implements OnClickListener{

	private Button backBtn;
	private RelativeLayout rl;
	private ListView listView;
	private CareDetailListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.care_detail);
		backBtn=(Button) this.findViewById(R.id.care_detail_btn_back);
		backBtn.setOnClickListener(this);
		rl=(RelativeLayout) this.findViewById(R.id.care_detail_rl_layout2);
		rl.setOnClickListener(this);
		listView=(ListView) this.findViewById(R.id.care_detail_list_view);
		adapter=new CareDetailListAdapter(this,R.layout.care_detail_item);
		initData();
	}
	
	public void initData(){
		for(int i=0;i<5;i++){
			CareItem item=new CareItem();
			item.setTitle("发烧");
			item.setCondition0("不烧");
			item.setCondition1("高烧");
			item.setCondition2("低烧");
			adapter.add(item);
		}
		listView.setAdapter(adapter);
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.care_detail_btn_back:
			this.finish();
			break;
		case R.id.care_detail_rl_layout2:
			Intent intent=new Intent(this,CareCommentActivity.class);
			this.startActivity(intent);
			break;

		default:
			break;
		}
	}
}
