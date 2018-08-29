package com.tcrj.spv.views.interfaces;

import android.location.Location;

/**
 * Created by leict on 2017/12/12.
 */

public interface LocationSource {
    void activate(LocationSource.OnLocationChangedListener listener);

    void deactivate();

    public interface OnLocationChangedListener {
        void onLocationChanged(Location location);
    }
}
