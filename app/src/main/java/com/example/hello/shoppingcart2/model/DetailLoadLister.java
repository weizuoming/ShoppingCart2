package com.example.hello.shoppingcart2.model;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public interface DetailLoadLister {
	void detailLoadSuccess(String json);
	void detailLoadError(String error);
}
