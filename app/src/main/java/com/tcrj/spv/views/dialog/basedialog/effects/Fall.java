package com.tcrj.spv.views.dialog.basedialog.effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * author: leict on 2017/10/9
 * created on: 2017/10/9 11:51
 * description:
 */

public class Fall extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 2, 1.5f, 1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view,"scaleY",2,1.5f,1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration*3/2)

        );
    }
}
