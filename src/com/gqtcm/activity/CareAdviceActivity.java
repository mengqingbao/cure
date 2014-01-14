package com.gqtcm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.gqtcm.adapter.CareAdviceListAdapter;
import com.gqtcm.model.CareAdvice;

public class CareAdviceActivity extends Activity  implements OnClickListener {

	private ListView listView;
	
	private Button cancelBtn;
	
	private CareAdviceListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.care_advice);
		cancelBtn=(Button) this.findViewById(R.id.care_advice_btn_back);
		cancelBtn.setOnClickListener(this);
		initData();
	}
	
	public void initData(){
		listView=(ListView) this.findViewById(R.id.care_advice_list_view);
		adapter=new CareAdviceListAdapter(this,R.layout.care_advice_item);
		for(int i=0;i<5;i++){
			CareAdvice cc=new CareAdvice();
			cc.setSubject("症状");
			cc.setContent("很好，一级就好了");
			adapter.add(cc);
		}
		listView.setAdapter(adapter);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.care_advice_btn_back:
			this.finish();
			break;

		default:
			break;
		}
	}
}
