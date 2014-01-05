package com.gqtcm.activity;

import com.gqtcm.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentPage4 extends Fragment{
	private WebView wv=null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		 View view = inflater.inflate(R.layout.fragment_4, container, false);
		 wv=(WebView)view.findViewById(R.id.webView);
		 wv.getSettings().setJavaScriptEnabled(true);  
		  wv.getSettings().setUseWideViewPort(true);
		  wv.getSettings().setSupportZoom(true);
		  wv.getSettings().setBuiltInZoomControls(true);
		  wv.getSettings().setLoadWithOverviewMode(true);
		  wv.getSettings().setDefaultTextEncodingName("utf-8");
		  wv.requestFocus();
		  wv.loadUrl("http://www.baidu.com");
         return view;	
	}	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
	}

}