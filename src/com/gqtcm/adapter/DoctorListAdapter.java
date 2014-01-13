package com.gqtcm.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gqtcm.activity.R;
import com.gqtcm.model.CareComment;
import com.gqtcm.model.Doctor;

public class DoctorListAdapter extends  ArrayAdapter<Doctor> {
	public DoctorListAdapter(Context context, int resource,
			List<Doctor> objects) {
		super(context, resource, objects);
		this.init(context, resource);
	}
	public DoctorListAdapter(Context context, int resource) {
		super(context, resource);
		this.init(context, resource);
	}
	private void init(Context context, int resource) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	private LayoutInflater mInflater;

	@Override
	public View getView(int postion, View convertView, ViewGroup vg) {
		Doctor doctor=this.getItem(postion);
			convertView = this.mInflater.inflate(R.layout.doctor_list_item,
					null);
			((TextView) convertView.findViewById(R.id.doctor_nick)).setText(doctor.getNick());
			((TextView) convertView.findViewById(R.id.doctor_distance)).setText(doctor.getDesc());
			//((TextView) convertView.findViewById(R.id)).setText("评价："+total+" / "+str);
		return convertView;
	}

}
