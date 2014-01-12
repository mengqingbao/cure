package com.gqtcm.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gqtcm.adapter.InMessageRecordArrayAdapter;
import com.gqtcm.listener.Fr2ItemOnClickListener;
import com.gqtcm.model.InMessage;
import com.gqtcm.persistence.InMessageStore;

public class FragmentPage1 extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		 View view = inflater.inflate(R.layout.fragment_1, container, false);

		 ListView listView = (ListView) view.findViewById(R.id.fragment_1_list);
         
         List<InMessage> msgs = InMessageStore.getInstance().getUserMessage(this.getActivity());
         
         InMessageRecordArrayAdapter friends = new InMessageRecordArrayAdapter(getActivity(),
        		 msgs);
 		listView.setAdapter(friends);
 		//listView.setOnItemClickListener(new Fr2ItemOnClickListener(this.getActivity()));
         return view;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}



}
