package samples.linhtruong.com.dagger2sample.di.module;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import samples.linhtruong.com.dagger2sample.network.APIConfig;
import samples.linhtruong.com.dagger2sample.network.APIService;
import samples.linhtruong.com.dagger2sample.di.scope.ApplicationScope;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 00:45.
 * @organization VED
 */

@Module
public class NetworkModule {

    private final Retrofit mRetrofit;

    public NetworkModule() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(APIConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @ApplicationScope
    @Provides
    APIService provideAPIService() {
        return mRetrofit.create(APIService.class);
    }
}
