package com.gqtcm.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gqtcm.activity.R;
import com.gqtcm.model.Care;
import com.gqtcm.model.InUser;
import com.gqtcm.model.StringVar;

public class Fr2ListAdapter extends  ArrayAdapter<Care> {
	public Fr2ListAdapter(Context context, int resource,
			List<Care> objects) {
		super(context, resource, objects);
		this.init(context, resource);
	}
	public Fr2ListAdapter(Context context, int resource) {
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
			Care car=this.getItem(postion);
			convertView = this.mInflater.inflate(R.layout.in_frag2_list_item,
					null);
			((TextView) convertView.findViewById(R.id.in_fr2_tv1)).setText(car.getTitle());
			((TextView) convertView.findViewById(R.id.in_fr2_tv2)).setText(car.getDraw());
			((TextView) convertView.findViewById(R.id.in_fr2_tv3)).setText("");
		return convertView;
	}

}
