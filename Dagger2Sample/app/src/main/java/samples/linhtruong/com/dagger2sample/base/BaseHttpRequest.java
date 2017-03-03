package samples.linhtruong.com.dagger2sample.base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:58.
 * @organization VED
 */

public abstract class BaseHttpRequest<R extends BaseResponse> {

    private R result = null;

    protected void request() {
        call().enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (response.isSuccessful()) {
                    LogUtils.d("request: " + getURLRequest() + "successfully!");
                    result = response.body();
                } else {
                    LogUtils.d("request: " + getURLRequest() + "failed!");
                    result = null;
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                LogUtils.d("request: " + getURLRequest() + "failed!");
            }
        });
    }

    public R getResponse() {
        request();
        return result;
    }

    protected abstract Call<R> call();

    /**
     * Temporarily debug purpose only
     * */
    protected abstract String getURLRequest();
}
