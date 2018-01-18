package com.example.hello.shoppingcart2.view;

import android.content.Context;

import com.example.hello.shoppingcart2.model.CartBean;

/**
 * Created by 韦作铭 on 2018/1/17.
 */

public interface ICartView {
    void showDataToCart(Context context, CartBean cartBean);
    //全选、反选

    //显示总价
    void showSum(double sum);

    CartBean getCartBean();


}
