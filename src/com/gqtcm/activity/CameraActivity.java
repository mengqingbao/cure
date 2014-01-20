package com.gqtcm.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.gqtcm.activity.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

	private static final int NONE = 0;
	    private static final int PHOTO_GRAPH = 1;// 拍照
	    private static final int PHOTO_ZOOM = 2; // 缩放
	    private static final int PHOTO_RESOULT = 3;// 结果
	    private static final String IMAGE_UNSPECIFIED = "image/*";
	    private ImageView imageView = null;
	    private Button btnFinish = null;
	    private Button btnBack = null;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.camera);
	        
	        imageView = (ImageView) findViewById(R.id.camera_image_view);
	        btnBack = (Button) findViewById(R.id.camera_btn_back);
	        btnBack.setOnClickListener(this);
	        btnFinish = (Button) findViewById(R.id.camera_btn_finish);
	        btnFinish.setOnClickListener(this);
	    }
	    
	    

	    @Override
		protected void onResume() {
	    	Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(),"temp.jpg")));
            startActivityForResult(intent2, PHOTO_GRAPH);
			super.onResume();
		}



		@Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == NONE)
	            return;
	        // 拍照
	        if (requestCode == PHOTO_GRAPH) {
	            // 设置文件保存路径
	            File picture = new File(Environment.getExternalStorageDirectory()
	                    + "/temp.jpg");
	            startPhotoZoom(Uri.fromFile(picture));
	        }

	        if (data == null)
	            return;

	        // 读取相册缩放图片
	        if (requestCode == PHOTO_ZOOM) {
	            startPhotoZoom(data.getData());
	        }
	        // 处理结果
	        if (requestCode == PHOTO_RESOULT) {
	            Bundle extras = data.getExtras();
	            if (extras != null) {
	                Bitmap photo = extras.getParcelable("data");
	                ByteArrayOutputStream stream = new ByteArrayOutputStream();
	                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
	                //此处可以把Bitmap保存到sd卡中，具体请看：http://www.cnblogs.com/linjiqin/archive/2011/12/28/2304940.html
	                imageView.setImageBitmap(photo); //把图片显示在ImageView控件上
	            }

	        }

	        super.onActivityResult(requestCode, resultCode, data);
	    }

	    /**
	     * 收缩图片
	     * 
	     * @param uri
	     */
	    public void startPhotoZoom(Uri uri) {
	        Intent intent = new Intent("com.android.camera.action.CROP");
	        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
	        intent.putExtra("crop", "true");
	        // aspectX aspectY 是宽高的比例
	        intent.putExtra("aspectX", 1);
	        intent.putExtra("aspectY", 1);
	        // outputX outputY 是裁剪图片宽高
	        intent.putExtra("outputX", 300);
	        intent.putExtra("outputY", 500);
	        intent.putExtra("return-data", true);
	        startActivityForResult(intent, PHOTO_RESOULT);
	    }

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.camera_btn_back:
				Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                startActivityForResult(intent, PHOTO_ZOOM);
				break;
			case R.id.camera_btn_finish:
				
				break;
			default:
				break;
			}
		}
		
		public boolean checkCameraHardWare(Context context){
			return false;
		}
	}  
