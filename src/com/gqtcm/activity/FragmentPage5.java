package com.gqtcm.activity;

import com.gqtcm.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentPage5 extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		 View view = inflater.inflate(R.layout.fragment_5, container, false);

		 ListView listView = (ListView) view.findViewById(R.id.fragment_5_list);

         String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
              "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
         "Linux", "OS/2" };
         ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                  android.R.layout.simple_list_item_1, 
                  values);

         listView.setAdapter(files);
         return view;		
	}	
}