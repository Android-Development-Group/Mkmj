package net.yumig.mkmj.test;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by T5-Jusenr on 2017/4/5.
 */
public interface HttpService {

    @GET("福利/10/2")
    Observable<String> getGank();
}
