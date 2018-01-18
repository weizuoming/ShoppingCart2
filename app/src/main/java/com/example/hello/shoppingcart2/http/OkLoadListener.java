package com.example.hello.shoppingcart2.http;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public interface OkLoadListener {
	void okLoadSuccess(String json);

	void okLoadError(String error);
}
