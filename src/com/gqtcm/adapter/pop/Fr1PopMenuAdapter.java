package com.gqtcm.adapter.pop;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gqtcm.activity.R;

public class Fr1PopMenuAdapter extends BaseAdapter {
	private Context context;
	private List<String> list;
	public Fr1PopMenuAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup g) {
		view = LayoutInflater.from(context).inflate(R.layout.fragment_1_popmenu_item,null);
		((TextView) view.findViewById(R.id.textView1)).setText(list.get(position));
		return view;
	}

}
