package com.tcrj.spv.views.dialog.basedialog.effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * author: leict on 2017/10/9
 * created on: 2017/10/9 9:56
 * description:
 */

public class FadeIn extends BaseEffects {
    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration)

        );
    }
}
