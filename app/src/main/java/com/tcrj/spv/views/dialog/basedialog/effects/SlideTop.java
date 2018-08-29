package com.tcrj.spv.views.dialog.basedialog.effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * author: leict on 2017/10/9
 * created on: 2017/10/9 9:59
 * description:
 */

public class SlideTop extends BaseEffects {

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "translationY", -300, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration * 3 / 2)

        );
    }
}