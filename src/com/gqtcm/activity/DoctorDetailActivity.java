package com.gqtcm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DoctorDetailActivity extends Activity implements OnClickListener{

	private Button backBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_detail);
		backBtn=(Button) this.findViewById(R.id.doctor_detail_btn_back);
		backBtn.setOnClickListener(this);
	}
	

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.doctor_list_btn_back:
			this.finish();
			break;
		default:
			break;
		}
	}
	

	@Override
	public void finish() {
		super.finish();
	}  
	}  
