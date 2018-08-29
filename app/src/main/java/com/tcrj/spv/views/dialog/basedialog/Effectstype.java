package com.tcrj.spv.views.dialog.basedialog;


import com.tcrj.spv.views.dialog.basedialog.effects.BaseEffects;
import com.tcrj.spv.views.dialog.basedialog.effects.FadeIn;
import com.tcrj.spv.views.dialog.basedialog.effects.SlideTop;

import com.tcrj.spv.views.dialog.basedialog.effects.Fall;
import com.tcrj.spv.views.dialog.basedialog.effects.FlipH;
import com.tcrj.spv.views.dialog.basedialog.effects.FlipV;
import com.tcrj.spv.views.dialog.basedialog.effects.SlideBottom;

/**
 * author: leict on 2017/10/9
 * created on: 2017/10/9 9:54
 * description:
 */

public enum Effectstype {
    Fadein(FadeIn.class),
    Slidetop(SlideTop.class),
    Fall(Fall.class),
    FlipH(FlipH.class),
    FlipV(FlipV.class),
    SlideBottom(SlideBottom.class);

    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects = null;
        try {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException e) {
            throw new Error("Can not init animatorClazz instance");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }
}
