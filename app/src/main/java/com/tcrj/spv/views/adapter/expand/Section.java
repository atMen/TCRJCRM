package com.tcrj.spv.views.adapter.expand;

/**
 * Created by leict on 2017/11/8.
 */

public class Section {
    private final String name;

    public boolean isExpanded = false;

    public Section(String name) {
        this.name = name;
        this.isExpanded = false;
    }

    public String getName() {
        return name;
    }
}
