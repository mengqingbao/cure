package com.gqtcm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CareDetailActivity extends Activity  implements OnClickListener{

	private Button backBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.care_detail);
		backBtn=(Button) this.findViewById(R.id.care_comment_btn_back);
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.care_detail_btn_back:
			this.finish();
			break;

		default:
			break;
		}
	}
}
