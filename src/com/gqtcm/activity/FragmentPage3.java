package com.gqtcm.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gqtcm.adapter.Fr3ListAdapter;
import com.gqtcm.listener.Fr3ItemOnClickListener;
import com.gqtcm.model.StringVar;

public class FragmentPage3 extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		 View view = inflater.inflate(R.layout.fragment_3, container, false);

		 ListView listView = (ListView) view.findViewById(R.id.fragment_3_list);

		 List<StringVar> list=new ArrayList<StringVar>();
		for (int i = 0; i < 5; i++) {
			StringVar sv = new StringVar();
			sv.setStr1("附近的医生");
			sv.setStr2("总共1021位");
			list.add(sv);
		}
         Fr3ListAdapter adapter = new Fr3ListAdapter(this.getActivity(),R.layout.in_frag3_list_item,list);
         listView.setAdapter(adapter);
         listView.setOnItemClickListener(new Fr3ItemOnClickListener(this.getActivity()));
         return view;		
	}	
}