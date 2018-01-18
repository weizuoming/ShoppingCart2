package com.example.hello.shoppingcart2.model;

import android.util.Log;

import com.example.hello.shoppingcart2.http.OkHttpUtils;
import com.example.hello.shoppingcart2.http.OkLoadListener;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class ModelImpl implements IModel {
	//加载详情页
	@Override
	public void getDetailData(String url, Map<String, String> parms, final DetailLoadLister detailLoadLister) {
		//用OkHttp请求，之后调用接口
		OkHttpUtils ok = OkHttpUtils.getInstance();
		ok.okPost(url, parms);
		ok.setOkLoadListener(new OkLoadListener() {
			@Override
			public void okLoadSuccess(String json) {
				detailLoadLister.detailLoadSuccess(json);
			}

			@Override
			public void okLoadError(String error) {
				detailLoadLister.detailLoadError(error);
			}
		});
	}


	private static final String TAG = "ModelImpl";
	//添加
	@Override
	public void addToCart(String url, Map<String, String> parms, final AddListener addListener) {
		//用OkHttp请求，之后调用接口
		Log.d("Main--", "点击---添加的方法" );
		OkHttpUtils ok = OkHttpUtils.getInstance();
		ok.okPost(url, parms);
		Log.d(TAG, "addToCart() returned: " + parms);
		ok.setOkLoadListener(new OkLoadListener() {
			@Override
			public void okLoadSuccess(String json) {
				addListener.addSucess(json);
			}

			@Override
			public void okLoadError(String error) {
				addListener.addError(error);
			}
		});
	}
	//购物车里面的加载数据的方法
	@Override
	public void showDataToCart(String url, Map<String, String> parms, final CartListLoadListener cartListLoadListener) {
		//开始加载
		//用OkHttp请求，之后调用接口
		OkHttpUtils ok = OkHttpUtils.getInstance();
		ok.okPost(url, parms);
		Log.d(TAG, "购物车数据---" + parms);
		ok.setOkLoadListener(new OkLoadListener() {
			@Override
			public void okLoadSuccess(String json) {
				cartListLoadListener.loadCartSuccess(json);
			}

			@Override
			public void okLoadError(String error) {
				cartListLoadListener.loadCartError(error);
			}
		});
	}
	//计算
	@Override
	public double calcaulate(CartBean cartBean) {
		double sum = 0;
		Log.d(TAG, "calcaulate() returned: " + cartBean + "===" + cartBean.getData());
		List<CartBean.DataBean> data = cartBean.getData();
		for (int i = 0; i < data.size(); i++) {
			List<CartBean.ChildBean> list = data.get(i).getList();
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).isChildIsSelected()){
					double bargainPrice = list.get(j).getBargainPrice();
					sum+=bargainPrice;
				}

			}
		}
		return sum;
	}
}
