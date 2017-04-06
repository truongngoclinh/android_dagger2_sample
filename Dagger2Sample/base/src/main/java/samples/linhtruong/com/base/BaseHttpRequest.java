package samples.linhtruong.com.base;

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
        LogUtils.d("request");
        call().enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (response.isSuccessful()) {
                    LogUtils.d("request: " + getURLRequest() + " successfully!");
                    result = response.body();
                } else {
                    LogUtils.d("request: " + getURLRequest() + " failed!");
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

    /**
     * Create fake data for the request because we dont have server side implementation.
     * It is same like creating mock request for testing purpose.
     * */
    public abstract R getMockResponse();

    /**
     * Temporarily debug purpose only
     * */
    protected abstract String getURLRequest();

    protected abstract Call<R> call();

    /**
     * For gson convert
     * */
//    protected abstract Class<R> getClassType();
}
