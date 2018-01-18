package com.example.hello.shoppingcart2.model;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public interface IModel {
	//请求接口，得到数据，详情页的
	void getDetailData(String url, Map<String, String> parms, DetailLoadLister detailLoadLister);

	//添加到购物车的而方法
	void addToCart(String url, Map<String, String> parms, AddListener addListener);

	//购物车页面的方法
	//显示
	void showDataToCart(String url, Map<String, String> parms, CartListLoadListener cartListLoadListener);
	//组的checkbox的点击的时候，处理选中状态
//	void setParentIsChecked(boolean isChecked,);
	//全选、反选

	//计算总价
	double calcaulate(CartBean cartBean);
}
