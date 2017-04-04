package net.yumig.mkmj.test;

/**
 * Created by T5-Jusenr on 2017/4/4.
 */

public class RetrofitBean<T> {

    private T results;
    private boolean error;//Gank接口状态码

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
