package samples.linhtruong.com.dagger2sample.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import samples.linhtruong.com.dagger2sample.network.APIConfig;
import samples.linhtruong.com.dagger2sample.network.APIService;
import samples.linhtruong.com.dagger2sample.scope.ApplicationScope;

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
        mRetrofit = new Retrofit.Builder()
                .baseUrl(APIConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @ApplicationScope
    @Provides
    APIService provideAPIService() {
        return mRetrofit.create(APIService.class);
    }
}
