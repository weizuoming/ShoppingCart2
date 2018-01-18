package com.example.hello.shoppingcart2.http;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class OkHttpUtils {
	private static OkHttpUtils okHttpUtils = null;
	private MyHandler myHandler = new MyHandler();
	private OkLoadListener okLoadListener;

	//单例
	public static OkHttpUtils getInstance() {
		if (okHttpUtils == null) {
			okHttpUtils = new OkHttpUtils();
		}
		return okHttpUtils;
	}

	//get
	public void okGet(String url) {
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new MyInter()).build();
		Request request = new Request.Builder().url(url).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Message message = myHandler.obtainMessage();
				message.what = 0;
				message.obj = e.getMessage();
				myHandler.sendMessage(message);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Message message = myHandler.obtainMessage();
				message.what = 1;
				message.obj = response.body().string();
				myHandler.sendMessage(message);
			}
		});
	}

	//post
	public void okPost(String url, Map<String, String> map) {
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new MyInter()).build();
		//创建FormBody
		FormBody.Builder builder = new FormBody.Builder();
		//遍历map
		Set<String> keys = map.keySet();
		for (String key : keys) {
			String value = map.get(key);
			builder.add(key, value+"");
		}
		//build
		FormBody body = builder.build();
		Request request = new Request.Builder().url(url).post(body).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Message message = myHandler.obtainMessage();
				message.what = 0;
				message.obj = e.getMessage();
				myHandler.sendMessage(message);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Message message = myHandler.obtainMessage();
				message.what = 1;
				message.obj = response.body().string();
				myHandler.sendMessage(message);
			}
		});
	}

	//拦截器
	class MyInter implements Interceptor {
		private static final String TAG = "MyInter";
		@Override
		public Response intercept(Chain chain) throws IOException {
			//获取原来的body
			Request request = chain.request();
			RequestBody body = request.body();
			if (body instanceof FormBody) {
				//遍历原来的所有参数，加到新的Body里面，最后将公共参数加到新的Body
				FormBody.Builder newBuilder = new FormBody.Builder();
				for (int i = 0; i < ((FormBody) body).size(); i++) {
					String name = ((FormBody) body).name(i);
					String value = ((FormBody) body).value(i);

					//放入新的
					newBuilder.add(name, value);
				}
				//在将公共参数添加进去
				newBuilder.add("source", "android");
				FormBody newBody = newBuilder.build();
				//创建新的请求
				Request newRequest = request.newBuilder().post(newBody).build();
				Response response = chain.proceed(newRequest);
				return response;
			}

			return null;
		}
	}

	//handler
	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 0:
					//失败
					String e = (String) msg.obj;
					okLoadListener.okLoadError(e);
					break;
				case 1:
					//成功
					String json = (String) msg.obj;
					okLoadListener.okLoadSuccess(json);
					break;
			}
		}
	}

	//提高外部调用的接口
	public void setOkLoadListener(OkLoadListener okLoadListener) {
		this.okLoadListener = okLoadListener;
	}
}
