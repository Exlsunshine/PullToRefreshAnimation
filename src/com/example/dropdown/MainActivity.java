package com.example.dropdown;

import java.util.ArrayList;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity 
{
	private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);
		list = (ListView) findViewById(R.id.main_list);
		mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() 
		{
			@Override
			public void onRefresh() 
			{
				// Do work to refresh the list here.
				new Task().execute();
			}
		});
		
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++)
			array.add(i);
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, array);
		list.setAdapter(adapter);
	}

	private class Task extends AsyncTask<Void, Void, String[]> 
	{
		@Override
		protected void onPostExecute(String[] result) 
		{
			// Call setRefreshing(false) when the list has been refreshed.
			mWaveSwipeRefreshLayout.setRefreshing(false);
			super.onPostExecute(result);
		}

		@Override
		protected String[] doInBackground(Void... params) 
		{
			int counter = 1;
			while (counter < 5)
			{
				try {
					Thread.sleep(1000);
					counter++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}