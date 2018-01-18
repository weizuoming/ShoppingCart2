package com.example.hello.shoppingcart2.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.hello.shoppingcart2.R;
import com.example.hello.shoppingcart2.model.CartBean;
import com.example.hello.shoppingcart2.model.ModelImpl;
import com.example.hello.shoppingcart2.model.MyAdapter;
import com.example.hello.shoppingcart2.presenter.PresenterImpl;

import java.util.List;

public class CartActivity extends AppCompatActivity implements ICartView,View.OnClickListener{
    private static final String TAG = "CartActivity";
    private ExpandableListView ex_list_view;
    private CheckBox cart_cb;
    private TextView cart_sum;
    private CartBean cartBean;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
        PresenterImpl presenter = new PresenterImpl();
        presenter.showDataToCart(this, new ModelImpl(),this);
    }

    private void initViews() {
        ex_list_view = (ExpandableListView) findViewById(R.id.ex_list_view);
        cart_cb = (CheckBox) findViewById(R.id.cart_cb);
        cart_sum = (TextView) findViewById(R.id.cart_sum12);
        //全选、反选的点击事件
        cart_cb.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cart_cb:
                //全部置为反
                List<CartBean.DataBean> data = cartBean.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setParentIsSelected(!data.get(i).isParentIsSelected());
                    //子元素
                    List<CartBean.ChildBean> list = data.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        list.get(j).setChildIsSelected(!list.get(j).isChildIsSelected());
                    }
                }
                //通知刷新
                myAdapter.notifyDataSetChanged();
                //计算
                PresenterImpl presenter = new PresenterImpl();
                presenter.jisuan(new ModelImpl(), cartBean, this);
                break;
        }
    }



    @Override
    public void showDataToCart(Context context, CartBean cartBean) {
        this.cartBean = cartBean;
        //设置适配器

        myAdapter = new MyAdapter(context, cartBean, this);
        ex_list_view.setAdapter(myAdapter);
        //将expanablelistview全部展开
        int childCount = myAdapter.getGroupCount();
        for (int i = 0; i < childCount; i++) {
            ex_list_view.expandGroup(i);
        }
    }

    @Override
    public void showSum(double sum) {
        Log.d(TAG, "showSum() returned: " + sum + "--" + cart_sum);
        TextView tv = (TextView) findViewById(R.id.cart_sum12);
        tv.setText("总价：" + sum);
    }

    @Override
    public CartBean getCartBean() {
        return cartBean;
    }
}
