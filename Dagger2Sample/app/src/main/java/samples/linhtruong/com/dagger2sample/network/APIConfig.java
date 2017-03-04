package samples.linhtruong.com.dagger2sample.network;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 00:39.
 * @organization VED
 */

public class APIConfig {

    public static final String BASE_URL = "https://linhtruong.me/restful/v1/";

    public interface MOCK {
        String ERROR_NONE = "0";
        String RESULT_OK = "0";
        String RESULT_FAILED = "1";
    }

    public interface MOCK_LOGIN {
        String TOKEN = "1234567890";
    }

    public interface MOCK_USER1 {
        String UID = "111111";
        int AGE = 20;
        String gender = "male";
    }

    public interface MOCK_USER2 {
        String UID = "222222";
        int AGE = 22;
        String gender = "female";
    }
}
