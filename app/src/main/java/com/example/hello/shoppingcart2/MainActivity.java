package com.example.hello.shoppingcart2;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hello.shoppingcart2.model.DetailBean;
import com.example.hello.shoppingcart2.model.ModelImpl;
import com.example.hello.shoppingcart2.presenter.PresenterImpl;
import com.example.hello.shoppingcart2.view.CartActivity;
import com.example.hello.shoppingcart2.view.IMainView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IMainView{
    private ImageView main_big_pic;
    private TextView man_name;
    private TextView main_price;
    private TextView main_price2;
    private PresenterImpl presenter;
    private DetailBean detailBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //调用Prester进行数据加载

        presenter = new PresenterImpl();
        presenter.showDataToDetai(new ModelImpl(), this);

    }

    private void initView() {
        main_big_pic = (ImageView) findViewById(R.id.main_big_pic);
        man_name = (TextView) findViewById(R.id.main_name);
        main_price = (TextView) findViewById(R.id.main_price);
        main_price2 = (TextView) findViewById(R.id.main_price2);
        TextView main_cart = (TextView) findViewById(R.id.main_cart);
        TextView main_add = (TextView) findViewById(R.id.main_add);
        main_cart.setOnClickListener(this);
        main_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_cart:
                //调用跳转
                presenter.jumpToCart(this);
                break;
            case R.id.main_add:
                //添加到购物车
                Log.d("Main--", "点击---添加到购物车" );
                Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                presenter.addToCart(new ModelImpl(), this);
                break;
        }
    }

    @Override
    public void showDetailData(DetailBean detailBean) {
        this.detailBean = detailBean;
        //设置数据
        String images = detailBean.getData().getImages();
        String imgeUrl = images.split(".jpg")[0] + ".jpg";
        Glide.with(MainActivity.this).load(imgeUrl).into(main_big_pic);
        man_name.setText(detailBean.getData().getTitle());
        main_price.setText("原价：" + detailBean.getData().getPrice() + "");
        main_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        main_price2.setText("折扣价" + detailBean.getData().getBargainPrice() + "");
    }

    @Override
    public void jumpToCatActivity() {
        //到购物车页面
        startActivity(new Intent(MainActivity.this, CartActivity.class));
        overridePendingTransition(R.anim.enter, R.anim.out);
        finish();
    }

    @Override
    public void addToCart() {

    }

    @Override
    public String getPid() {
        return detailBean.getData().getPid() + "";
    }

    @Override
    public void showAddSucess() {
        Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shoAddError() {
        Toast.makeText(MainActivity.this, "添加失败", Toast.LENGTH_SHORT).show();

    }
}
