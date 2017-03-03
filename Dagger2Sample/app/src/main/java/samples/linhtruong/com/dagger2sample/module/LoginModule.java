package samples.linhtruong.com.dagger2sample.module;

import dagger.Module;
import dagger.Provides;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;
import samples.linhtruong.com.dagger2sample.scope.LoginScope;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:52.
 * @organization VED
 */

@Module
public class LoginModule {

    @Provides
    @LoginScope
    LoginRequest provideLoginRequest() {
        return new LoginRequest();
    }
}
