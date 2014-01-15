package com.gqtcm.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.gqtcm.model.Doctor;
import com.gqtcm.model.InMessage;
import com.gqtcm.model.InUser;

public class DoctorStore extends BaseDao  {
	private static DoctorStore objself;
	public DoctorStore(){
	}
	
	//自举
	public static DoctorStore getInstance(){
		if(objself==null){
			objself=new DoctorStore();
		}
		return objself;
	}
	
	public List<Doctor> queryPage(int start ,int limit,Context context) {
		List<Doctor> result = new ArrayList<Doctor>();
		db = getDb(true,context);
		Cursor c = db.rawQuery(
				"select * from Doctor where type='1' order by id asc limit "+limit+" offset "+start,
				new String[] {});
		int count=c.getCount();
		if (c.moveToFirst()) {
			for (int i = 0; i < count; i++) {
				Doctor obj = new Doctor();
				obj.setUserId(getString(c,"userId"));
				obj.setNick(getString(c,"nick"));
				obj.setDesc(getString(c,"draw"));
				result.add(obj);
				c.moveToNext();
			}
		}

		return result;
	}
}
