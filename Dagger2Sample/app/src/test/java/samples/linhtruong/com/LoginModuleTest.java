package samples.linhtruong.com;

import org.junit.Test;
import static org.junit.Assert.*;

import samples.linhtruong.com.dagger2sample.login.LoginPresenter;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;

import javax.inject.Inject;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 7/20/17 - 10:35.
 * @organization VED
 */

public class LoginModuleTest {

    @Inject
    LoginRequest mLoginRequest;

    @Inject
    LoginSession mLoginSession;

    @Test
    public void testLoginRequest() {
        assertNull(mLoginRequest);
    }

    @Test
    public void testLoginSession() {
        assertNull(mLoginSession);
    }

    @Test
    public void testFakeLoadingTime() {
        assertEquals("FAKE_LOADING_TIME is not 2000", 2000, LoginPresenter.FAKE_LOADING_TIME);
    }
}
