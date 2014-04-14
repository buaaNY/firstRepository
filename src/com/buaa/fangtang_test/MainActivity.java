package com.buaa.fangtang_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView curList = (ListView) findViewById(R.id.msglist);
		List<HashMap<String, String>> msgList = getMsg();
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,msgList,R.layout.item, 
				new String[]{"name","date","content"}, new int[]{R.id.name,R.id.date,R.id.content});
		curList.setAdapter(simpleAdapter);
	}

	
	private List<HashMap<String, String>> getMsg(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		GetRepoAttriThread gat = new GetRepoAttriThread();
		gat.setRepoName("rails");
		gat.setUserName("rails");
		gat.threadStart();
		while(!gat.isFinish);
		List<MsgShow> msgList = gat.getMsgList();
		for(MsgShow msg : msgList){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", msg.getAuthor());
			map.put("date","Date: "+msg.getDate().toString());
			map.put("content", msg.getCommitMsg());		
			list.add(map);
		}
		return list;
	}
}
