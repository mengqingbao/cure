package com.gqtcm.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.gqtcm.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends Activity implements OnClickListener{

	private Button sendBtn,backBtn;
	private String fileName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		sendBtn=(Button) this.findViewById(R.id.camera_btn_finish);
		backBtn=(Button) this.findViewById(R.id.camera_btn_back);
		sendBtn.setOnClickListener(this);
		backBtn.setOnClickListener(this);
	}
	
	

	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
        startActivityForResult(intent, 1);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.camera_btn_back:
			this.finish();
			break;
		case R.id.camera_btn_finish:
			
			break;
		default:
			break;
		}
	}
	 @Override  
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        super.onActivityResult(requestCode, resultCode, data);  
	        if (resultCode == Activity.RESULT_OK) {  
	            String sdStatus = Environment.getExternalStorageState();  
	            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����  
	                Log.i("TestFile",  
	                        "SD card is not avaiable/writeable right now.");  
	                return;  
	            }  
	            String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";     
	            Toast.makeText(this, name, Toast.LENGTH_LONG).show();  
	            Bundle bundle = data.getExtras();  
	            Bitmap bitmap = (Bitmap) bundle.get("data");// ��ȡ���ص���ݣ���ת��ΪBitmapͼƬ��ʽ  
	          
	            FileOutputStream b = null;  
	           //???????????????????????????????Ϊʲô����ֱ�ӱ�����ϵͳ���λ���أ�����������������������  
	            File file = new File("/sdcard/myImage/");  
	            file.mkdirs();// �����ļ���  
	            fileName = "/sdcard/myImage/"+name;  
	            try {  
	                b = new FileOutputStream(fileName);  
	                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// �����д���ļ�  
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	            } finally {  
	                try {  
	                    b.flush();  
	                    b.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	            ImageView iv= (ImageView) findViewById(R.id.camera_image_view);
	           iv.setImageBitmap(bitmap);// ��ͼƬ��ʾ��ImageView��  
	        }  
	    }



	@Override
	public void finish() {
		super.finish();
		//ɾ��ͼƬ
		File file=new File(fileName);
		if(file.exists()){
			file.delete();
		}
	}  
	//����ͼƬ
	public void sendPic(){
		//TODO add send pic code.
	}
	}  
