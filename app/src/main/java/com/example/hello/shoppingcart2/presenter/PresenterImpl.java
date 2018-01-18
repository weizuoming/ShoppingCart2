package com.example.hello.shoppingcart2.presenter;

import android.content.Context;
import android.util.Log;

import com.example.hello.shoppingcart2.http.HttpConfig;
import com.example.hello.shoppingcart2.model.AddListener;
import com.example.hello.shoppingcart2.model.CartBean;
import com.example.hello.shoppingcart2.model.CartListLoadListener;
import com.example.hello.shoppingcart2.model.DetailBean;
import com.example.hello.shoppingcart2.model.DetailLoadLister;
import com.example.hello.shoppingcart2.model.IModel;
import com.example.hello.shoppingcart2.view.ICartView;
import com.example.hello.shoppingcart2.view.IMainView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class PresenterImpl implements IPresenter {
	private static final String TAG = "PresenterImpl";

	//详情页显示数据的方法
	@Override
	public void showDataToDetai(IModel iModel, final IMainView iMainView) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pid", "17");
		iModel.getDetailData(HttpConfig.detail_url, map, new DetailLoadLister() {
			@Override
			public void detailLoadSuccess(String json) {
				//json---DetaiBean
				Log.d(TAG, "成---- " + json);
				Gson gson = new Gson();
				DetailBean detailBean = gson.fromJson(json, DetailBean.class);
				//传入View
				iMainView.showDetailData(detailBean);
			}

			@Override
			public void detailLoadError(String error) {
				Log.d(TAG, "detail---pres--shibai");
			}
		});
	}

	//跳转
	@Override
	public void jumpToCart(IMainView iMainView) {
		iMainView.jumpToCatActivity();
	}

	//添加
	@Override
	public void addToCart(IModel iModel, final IMainView iMainView) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pid", iMainView.getPid());
		map.put("uid", "71");
		iModel.addToCart(HttpConfig.add_url, map, new AddListener() {
			@Override
			public void addSucess(String json) {

				Log.d(TAG, "addSucess() returned: " + json);
				try {
					JSONObject object = new JSONObject(json);
					String code = object.getString("code");
					if (code.equals("0")) {
						iMainView.showAddSucess();
					} else {
						iMainView.shoAddError();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}

			@Override
			public void addError(String error) {
				Log.d(TAG, "shibai-----");
				iMainView.shoAddError();
			}
		});
	}

	//在购物车显示数据
	@Override
	public void showDataToCart(final Context context, IModel iModel, final ICartView iCartView) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("uid", "71");
		iModel.showDataToCart(HttpConfig.cartList_url, map, new CartListLoadListener() {
			@Override
			public void loadCartSuccess(String json) {
				Log.d(TAG, "购物车数据----" + json);
				//将json---Bean
				Gson gson = new Gson();
				CartBean cartBean = gson.fromJson(json, CartBean.class);
				//调用view的回调
				iCartView.showDataToCart(context, cartBean);
			}

			@Override
			public void loadCartError(String error) {

			}
		});
	}

	//计算
	@Override
	public void jisuan(IModel iModel, CartBean cartBean, ICartView iCartView) {
		Log.d(TAG, "jisuan() returned: " + iCartView.getCartBean());
		double sum = iModel.calcaulate(cartBean);
		iCartView.showSum(sum);
	}


}
