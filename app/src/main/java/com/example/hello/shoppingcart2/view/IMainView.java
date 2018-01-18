package com.example.hello.shoppingcart2.view;

import com.example.hello.shoppingcart2.model.DetailBean;

/**
 * Created by 韦作铭 on 2018/1/17.
 */

public interface IMainView {
    void showDetailData(DetailBean detailBean);

    void jumpToCatActivity();

    void addToCart();
    //获取PID的方法
    String getPid();
    //成功的提示
    void showAddSucess();
    //失败的提示
    void shoAddError();
}

