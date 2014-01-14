package com.gqtcm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.gqtcm.adapter.DoctorListAdapter;
import com.gqtcm.listener.DoctorDetailItemOnClickListener;
import com.gqtcm.model.Doctor;

public class DoctorSearchActivity extends Activity implements OnClickListener{

	private Button backBtn;
	private String fileName;
	private DoctorListAdapter adpater;
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_search_activity);
		backBtn=(Button) this.findViewById(R.id.doctor_search_btn_back);
		backBtn.setOnClickListener(this);
		listView=(ListView) this.findViewById(R.id.doctor_search_list_view);
		listView.setOnItemClickListener(new DoctorDetailItemOnClickListener(this));
		initData();
	}
	
	public void initData(){
		adpater=new DoctorListAdapter(this,R.layout.doctor_list_item);
		for(int i=0;i<5;i++){
			Doctor doctor=new Doctor();
			doctor.setNick("张仲景"+i);
			doctor.setDesc(i+"00米以内");
			adpater.add(doctor);
		}
		listView.setAdapter(adpater);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.doctor_search_btn_back:
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
