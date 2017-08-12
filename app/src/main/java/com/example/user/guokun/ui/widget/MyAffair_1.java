package com.example.user.guokun.ui.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.guokun.R;

public class MyAffair_1 extends RelativeLayout {

    private Context mContext;

    private RelativeLayout titleRl;

    private TextView infoRl;

    private ImageView directionIv;

    private View baseV;

    private boolean isOpened = false;

    private boolean animatorLock = false;

    public MyAffair_1(Context context, AttributeSet attrs, int defStyle, String a) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public MyAffair_1(Context context, AttributeSet attrs, String a) {
        this(context, attrs, 0,a);
    }

    public MyAffair_1(Context context, String a) {
        this(context, null,a);
    }

    private void init() {
        baseV = LayoutInflater.from(mContext).inflate(R.layout.affair_1, this);
        titleRl = baseV.findViewById(R.id.affair_title_rl);
        infoRl = baseV.findViewById(R.id.tv_text);
        directionIv = baseV.findViewById(R.id.iv_image);
        titleRl.setOnClickListener(v -> {
            if (!animatorLock) {
                getObjectAnimator().start();
                if(isOpened){
                    directionIv.setBackgroundResource(R.drawable.back);
                }
                else {
                    directionIv.setBackgroundResource(R.drawable.zuo_jian_tou);
                }
            }
        });
    }

    private ObjectAnimator getObjectAnimator() {
        AnimatorListener changeStatusListener = new AnimatorListener() {
            
            @Override
            public void onAnimationStart(Animator animation) {
                animatorLock = true;
                if(!isOpened){
                    infoRl.setVisibility(View.VISIBLE);
                }
            }
            
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
            
            @Override
            public void onAnimationEnd(Animator animation) {
                if(isOpened){
                    infoRl.setVisibility(View.GONE);
                }
                isOpened = !isOpened;
                animatorLock = false;
            }
            
            @Override
            public void onAnimationCancel(Animator animation) {
            }
        };
        ObjectAnimator result = null;
        if(!isOpened){
            result = ObjectAnimator.ofFloat(infoRl, "scaleY", 0, 1f);
        }
        else {
            result = ObjectAnimator.ofFloat(infoRl, "scaleY", 1f, 0);
        }
        result.setDuration(1000);
        result.addListener(changeStatusListener);
        return result;
    }

}
