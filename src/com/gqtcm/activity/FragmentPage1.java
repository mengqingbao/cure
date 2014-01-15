package com.gqtcm.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.gqtcm.adapter.InMessageRecordArrayAdapter;
import com.gqtcm.adapter.pop.Fr1PopMenuAdapter;
import com.gqtcm.listener.Fr1ItemOnClickListener;
import com.gqtcm.model.InMessage;
import com.gqtcm.persistence.InMessageStore;

public class FragmentPage1 extends Fragment implements OnClickListener{
	private Button addBtn;
	private PopupWindow popw;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		 View view = inflater.inflate(R.layout.fragment_1, container, false);

		 ListView listView = (ListView) view.findViewById(R.id.fragment_1_list);
         
         List<InMessage> msgs = InMessageStore.getInstance().getUserMessage(this.getActivity());
         
         InMessageRecordArrayAdapter friends = new InMessageRecordArrayAdapter(getActivity(),
        		 msgs);
 		listView.setAdapter(friends);
 		listView.setOnItemClickListener(new Fr1ItemOnClickListener(this.getActivity()));
 		addBtn=(Button) view.findViewById(R.id.frag1_add_chat);
 		addBtn.setOnClickListener(this);
         return view;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.frag1_add_chat:
			 popMenu(addBtn);
			break;

		default:
			break;
		}
		
	}

	public void popMenu(View parent){
		
		if(popw==null){
			
			LayoutInflater layoutInflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View popwv=layoutInflater.inflate(R.layout.fragment_1_popmenu, null);
			ListView lv_group=(ListView) popwv.findViewById(R.id.listView1);
			List<String> list=new ArrayList<String>();
			list.add("咨询");
			list.add("问诊");
			Fr1PopMenuAdapter gadpater=new Fr1PopMenuAdapter(this.getActivity(),list);
			lv_group.setAdapter(gadpater);
			popw=new PopupWindow(popwv,100,100);
			lv_group.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> adpater, View view,
						int position, long id) {
					if(popw!=null){
						popw.dismiss();
						Intent intent = new Intent();
						if(position==0){
							intent.setClass(FragmentPage1.this.getActivity(), DoctorSearchActivity.class);
						}else{
							intent.setClass(FragmentPage1.this.getActivity(), DoctorSearchActivity.class);
						}
						Bundle bundle = new Bundle();
						/*bundle.putString("friendId", user.getUserId());
						bundle.putString("friendNick", user.getNick());*/
						intent.putExtras(bundle);
						FragmentPage1.this.getActivity().startActivity(intent);
					}
					
				}});
		}
		popw.setFocusable(true);
		popw.setOutsideTouchable(true);
		popw.setBackgroundDrawable(new BitmapDrawable());
		WindowManager wm=(WindowManager) this.getActivity().getSystemService(Context.WINDOW_SERVICE);
		int xPos=wm.getDefaultDisplay().getWidth()-popw.getWidth();
		System.out.println(xPos);
		popw.showAsDropDown(parent,xPos,0);
	
	}
}
