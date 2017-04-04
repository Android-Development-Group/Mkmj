package net.yumig.mkmj.test;

import android.support.annotation.Nullable;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by T5-Jusenr on 2017/4/4.
 */

public class RXHelper {

    private static RXHelper instance = null;

    public static RXHelper getInstance() {
        if (instance == null) {
            synchronized (RXHelper.class) {
                if (instance == null) {
                    instance = new RXHelper();
                }
            }
        }
        return instance;
    }

    /**
     * @param <T>
     * @param observable retrofit rx callback
     * @param subscriber callback
     * @param scheduler  observer thread
     */
    public <T> void getRxResult(Observable<T> observable, Subscriber<T> subscriber, @Nullable Scheduler scheduler) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler == null ? AndroidSchedulers.mainThread() : scheduler)
                .subscribe(subscriber);
    }

    public <T> void getRxResult(Observable<T> observable, final Action1<T> action, @Nullable Scheduler scheduler) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler == null ? AndroidSchedulers.mainThread() : scheduler)
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String msg = e.getMessage();
                    }

                    @Override
                    public void onNext(T t) {
                        action.call(t);
                    }
                });
    }
}
