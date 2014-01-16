package com.gqtcm.persistence;

import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BaseDao {
	protected SQLiteDatabase db;
	
	public String getString(Cursor c,String key){
		String result;
		try {
			byte[] temp=c.getBlob(c.getColumnIndex(key));
			if(temp!=null){
			result = new String(c.getBlob(c.getColumnIndex(key)),"gb2312");
			}else{
				return "";
			}
		} catch (UnsupportedEncodingException e) {
			return "error";
		}
		return result;
	}
	
	protected SQLiteDatabase getDb(boolean isRead,Context context) {
		
		/*InSQLiteOpenHelper isqloh = new InSQLiteOpenHelper(context);
		if(isRead){
			return isqloh.getReadableDatabase();
		}else{
			return isqloh.getWritableDatabase();
		}*/
		return SQLiteDatabase.openOrCreateDatabase("data/data/com.gqtcm.activity/databases/mydata.db", null);
	}
}
