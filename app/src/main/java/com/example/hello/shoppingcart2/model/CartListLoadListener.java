package com.example.hello.shoppingcart2.model;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public interface CartListLoadListener {
	//购物车数据，加载成功
	void loadCartSuccess(String json);

	//加载失败
	void loadCartError(String error);
}
