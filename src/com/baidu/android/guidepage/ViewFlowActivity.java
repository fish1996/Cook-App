package com.baidu.android.guidepage;


import org.taptwo.android.widget.ViewFlow;
import org.taptwo.android.widget.ViewFlow.ViewSwitchListener;

import com.baidu.speech.recognizerdemo.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewFlowActivity extends Activity {

	private ViewFlow viewflow;
	private ViewFlowAdapter adapter;
	private TextView skipBtn;
	private Button experienceBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.viewflow_main);
		viewflow=(ViewFlow) this.findViewById(R.id.viewflow);
		skipBtn = (TextView)this.findViewById(R.id.userguide_btn_skip);
		experienceBtn = (Button) this.findViewById(R.id.experienceBtn);
		experienceBtn.setVisibility(View.INVISIBLE);
		adapter = new ViewFlowAdapter(this);
		
		viewflow.setAdapter(adapter);
		// 当切换至最后一页时 退出用户导航
		viewflow.setOnViewSwitchListener(new ViewSwitchListener() {
			@Override
			public void onSwitched(View arg0, int arg1) {
				if (arg1 >= Constant.pageNum - 1) {
					experienceBtn.setVisibility(View.VISIBLE);
				}
				else {
					experienceBtn.setVisibility(View.INVISIBLE);
				}
			}
		});
		skipBtn.setOnClickListener(new TextView.OnClickListener(){//创建监听    
            public void onClick(View v) {   
            	finish();
            }
		 });
		
		experienceBtn.setOnClickListener(new Button.OnClickListener(){//创建监听    
            public void onClick(View v) {   
            	finish();
            }
		 });
	}

	@Override
	protected void onStop() {
		super.onStop();
	//	adapter.recyleBitMaps();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		viewflow.onConfigurationChanged(newConfig);
	}
	
	class ViewFlowAdapter extends BaseAdapter{

		private LayoutInflater mInflater;
		private final int[] pics = { R.drawable.page1, R.drawable.page2, R.drawable.page3 };
		private Bitmap[] bitMaps;
		
		public ViewFlowAdapter(ViewFlowActivity context){
			mInflater = LayoutInflater.from(context);
			// 初始化图片资源
			bitMaps = new Bitmap[pics.length];
			for (int i = 0; i < pics.length; i++) {
				bitMaps[i] = BitMapUtil.readBitMap(context, pics[i]);
			}
		}

		public void recyleBitMaps() {
			for (Bitmap bitmap : bitMaps) {
				bitmap.recycle();
			}
		}
		
		@Override
		public int getCount() {
			return pics.length;
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.common_userguide_imageitem, null);
			}
			ImageView imgView = (ImageView) convertView.findViewById(R.id.common_userguide_image_imgView);
			imgView.setImageBitmap(bitMaps[position]);
			return convertView;
		}
		
	}
}
