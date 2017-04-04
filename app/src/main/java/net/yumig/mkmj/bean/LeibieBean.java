package net.yumig.mkmj.bean;

/**
 * Description: 附近模块横向recycleview类别对象
 * Copyright  : Copyright (c) 2016
 * Company    : www.yumig.net
 * Author     : Gaoxichao
 * Email      : 1024003167@qq.com
 * Date       : 2017/3/27 10:31
 */

public class LeibieBean {
    public int    img;
    public String name;


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LeibieBean(int img, String name) {
        this.img = img;
        this.name = name;
    }
}
