package net.yumig.mkmj.test.entity.resulte;


/**
 * Created by T5-Jusenr on 2017/4/5.
 */
public class BaseGankResultEntity<T> {

    /**
     * "error": false,
     * "results": []
     */

    //判断标示
    private boolean error;
    //提示信息
    private String msg;
    //显示数据（用户需要关心的数据）
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
