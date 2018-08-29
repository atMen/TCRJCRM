package com.tcrj.spv.views.dialog.basedialog.effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * author: leict on 2017/10/9
 * created on: 2017/10/9 14:00
 * description:
 */

public class FlipV extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationX", -90, 0).setDuration(mDuration)

        );
    }
}
