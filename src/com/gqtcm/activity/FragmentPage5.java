package com.gqtcm.activity;

import java.util.ArrayList;
import java.util.List;

import com.gqtcm.activity.R;
import com.gqtcm.adapter.Fr3ListAdapter;
import com.gqtcm.adapter.Fr5ListAdapter;
import com.gqtcm.model.Config;
import com.gqtcm.model.StringVar;
import com.gqtcm.persistence.ConfigStroe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentPage5 extends Fragment{

	private Fr5ListAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		 View view = inflater.inflate(R.layout.fragment_5, container, false);

		 ListView listView = (ListView) view.findViewById(R.id.fragment_5_list);
		 List<Config> cfgs=ConfigStroe.getInstance().queryPageList(0, 10, this.getActivity(),3);
		 adapter= new Fr5ListAdapter(this.getActivity(),R.layout.in_frag5_list_item,cfgs);
         listView.setAdapter(adapter);
         
         
         return view;		
	}	
}