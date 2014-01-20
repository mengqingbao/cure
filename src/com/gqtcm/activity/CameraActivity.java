package com.gqtcm.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

import com.gqtcm.component.CameraPreview;

public class CameraActivity extends Activity implements OnClickListener{
		
	private Camera camera;
	private CameraPreview cameraPreview;
	private Button btnFinish;
		
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		camera=getCameraInstance();
		cameraPreview=new CameraPreview(this,camera);
		FrameLayout preview= (FrameLayout) this.findViewById(R.id.camera_preview);
		preview.addView(cameraPreview);
		btnFinish=(Button) this.findViewById(R.id.camera_btn_finish);
		btnFinish.setOnClickListener(this);
	}

		public boolean checkCameraHardWare(Context context){
			return true;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.camera_btn_finish:
				camera.takePicture(null, null, pictureCallback);
				break;

			default:
				break;
			}
			
		}
		//初始化camera
		public static Camera getCameraInstance(){
			Camera c=null;
			try{
			 c=Camera.open();
			}catch(Exception e){
				return null;
			}
			return c;
			
		}
		
		private static final int MEDIA_TYPE_IMAGE=1;
		private static final int MEDIA_TYPE_VIDEO=2;
		private static Uri getOutputMediaFileUri(int type){
			return Uri.fromFile(getOutputMediaFile(type));
		}
		private static File getOutputMediaFile(int type){
			File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"gqtcm_tem");
			//穿件文件夹
			if(!mediaStorageDir.exists()){
				if(!mediaStorageDir.mkdir()){
					Log.d("gqtcmlog", "failed to create directory");
					return null;
				}
			}
			
			//获得当前时间戳。
			String timeStamp=new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
			File mediaFile;
			if(type==MEDIA_TYPE_IMAGE){
				mediaFile=new File(mediaStorageDir.getPath()+File.separator+"IMG_"+timeStamp+".jgp");
			}else if(type==MEDIA_TYPE_VIDEO){
				mediaFile=new File(mediaStorageDir.getPath()+File.separator+"IMG_"+timeStamp+".mp4");
			}else{
				return null;
			}
			return mediaFile;
		}
		
		
		private PictureCallback pictureCallback = new PictureCallback(){
			
			public void onPictureTaken(byte[] data,Camera camera){
				File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
				if(pictureFile==null){
					Log.d("gqtcmlog", "Error creating media file check storage permissions:");
					return;
				}
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(pictureFile);
					fos.write(data);
					fos.close();
				} catch (FileNotFoundException e) {
					Log.d("gqtcmlog", "File not found: "+e.getMessage());

				} catch (IOException e) {
					Log.d("gqtcmlog", "Error accessing file:　"+e.getMessage());

				}

			}
		};
	}  
