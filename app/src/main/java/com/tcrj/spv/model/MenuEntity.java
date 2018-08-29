package com.tcrj.spv.model;

import java.io.Serializable;

/**
 * 菜单实体类
 * Created by leict on 2017/10/25.
 */

public class MenuEntity implements Serializable {
    private int menuId;
    private int imageId;
    private String menuName;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
