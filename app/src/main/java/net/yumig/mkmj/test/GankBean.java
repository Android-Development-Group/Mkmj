package net.yumig.mkmj.test;

import java.io.Serializable;

/**
 * http://gank.io/api/data/Android/10/1
 * Created by T5-Jusenr on 2017/4/4.
 */

public class GankBean implements Serializable {

//    "_id": "58ddcb2b421aa969f51a911e",
//            "createdAt": "2017-03-31T11:21:15.303Z",
//            "desc": "3-31",
//            "publishedAt": "2017-03-31T11:26:54.330Z",
//            "source": "chrome",
//            "type": "\u798f\u5229",
//            "url": "http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-31-17662615_1819763634937161_2829588299293655040_n.jpg",
//            "used": true,
//            "who": "dmj"

    public String desc;
    public String url;
    public boolean used;
    public String who;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "GankBean{" +
                "desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }
}
