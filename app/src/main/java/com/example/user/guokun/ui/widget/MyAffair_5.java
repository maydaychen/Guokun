package com.example.user.guokun.ui.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.user.guokun.R;

/**
 * Created by user on 2017/8/22.
 */

public class MyAffair_5 extends RelativeLayout {

    private Context mContext;

    private RelativeLayout titleRl;

    private LinearLayout infoRl;

    private ImageView directionIv;

    private View baseV;

    private boolean isOpened = false;

    private boolean animatorLock = false;

    public MyAffair_5(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public MyAffair_5(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyAffair_5(Context context) {
        this(context, null);
    }

    private void init() {
        baseV = LayoutInflater.from(mContext).inflate(R.layout.affair_5, this);
        titleRl = baseV.findViewById(R.id.affair_title_rl);
        infoRl = baseV.findViewById(R.id.tv_text2);
        directionIv = baseV.findViewById(R.id.iv_image);
        titleRl.setOnClickListener(v -> {
            if (!animatorLock) {
                getObjectAnimator().start();
                if (isOpened) {
                    directionIv.setImageResource(R.drawable.zuo_jian_tou);
                } else {
                    directionIv.setImageResource(R.drawable.back);
                }
            }
        });
    }

    private ObjectAnimator getObjectAnimator() {
        Animator.AnimatorListener changeStatusListener = new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                animatorLock = true;
                if (!isOpened) {
                    infoRl.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isOpened) {
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
        if (!isOpened) {
            result = ObjectAnimator.ofFloat(infoRl, "scaleY", 0, 1f);
        } else {
            result = ObjectAnimator.ofFloat(infoRl, "scaleY", 1f, 0);
        }
        result.setDuration(200);
        result.addListener(changeStatusListener);
        return result;
    }

}
