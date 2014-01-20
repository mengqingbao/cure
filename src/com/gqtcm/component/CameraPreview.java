package com.gqtcm.component;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements Callback {

	private SurfaceHolder holder;
	private Camera camera;
	public CameraPreview(Context context,Camera camera) {
		super(context);
		this.camera=camera;
		
		holder=this.getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceChanged(SurfaceHolder hdr, int format, int w, int h) {
		if(holder.getSurface()==null){
			return;
		}
		
		try{
		camera.stopPreview();
		}catch(Exception e){
			Log.d("gqtcmlog", "failed to create directory");
		}
		
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (IOException e) {
			Log.d("gqtcmlog", "failed to startPreview:"+e.getMessage()+e.getCause());
		}		
	}

	@Override
	public void surfaceCreated(SurfaceHolder hl) {
		try {
			camera.setPreviewDisplay(hl);
			camera.startPreview();
		} catch (IOException e) {
			Log.d("gqtcmlog", "failed to startPreview:"+e.getMessage()+e.getCause());
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

}
