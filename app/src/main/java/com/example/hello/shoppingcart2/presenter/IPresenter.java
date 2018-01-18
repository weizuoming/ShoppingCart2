package com.example.hello.shoppingcart2.presenter;


import android.content.Context;

import com.example.hello.shoppingcart2.model.CartBean;
import com.example.hello.shoppingcart2.model.IModel;
import com.example.hello.shoppingcart2.view.ICartView;
import com.example.hello.shoppingcart2.view.IMainView;


/**
 * Created by Administrator on 2018/1/10 0010.
 */

public interface IPresenter {
	//将数据显示咋Detail页面
	void showDataToDetai(IModel iModel, IMainView iMainView);

	//跳转
	void jumpToCart(IMainView iMainView);

	//添加
	void addToCart(IModel iModel, IMainView iMainView);

	//购物车--
	void showDataToCart(Context context, IModel iModel, ICartView iCartView);

	void jisuan(IModel iModel, CartBean cartBean, ICartView iCartView);


}
