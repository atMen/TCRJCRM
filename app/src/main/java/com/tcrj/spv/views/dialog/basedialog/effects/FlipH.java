package com.tcrj.spv.views.dialog.basedialog.effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * author: leict on 2017/10/9
 * created on: 2017/10/9 14:01
 * description:
 */

public class FlipH extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationY", -90, 0).setDuration(mDuration)

        );
    }
}