package com.example.hello.shoppingcart2.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by 韦作铭 on 2018/1/17.
 */

public class AnimatorUtils {
    public static AnimatorSet setAnimatorset(Context context, View view) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int heightPixels = metrics.heightPixels;
        ValueAnimator tranlate = ObjectAnimator.ofFloat(view, "translationY", 0, heightPixels / 2 - view.getHeight());
        ValueAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 2.0f, 1.0f);
        ValueAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 2.0f, 1.0f);
        ValueAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1.0f);
        ValueAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", 0, 360);
        //创建动画集合
        AnimatorSet set = new AnimatorSet();
        set.playTogether(tranlate, scaleX, scaleY, alpha, rotation);
        set.setDuration(3000);
        set.start();
        return set;
    }
}
