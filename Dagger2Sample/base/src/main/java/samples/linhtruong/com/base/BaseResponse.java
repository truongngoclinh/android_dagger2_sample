package samples.linhtruong.com.base;

import com.google.gson.annotations.SerializedName;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 00:41.
 * @organization VED
 */

public class BaseResponse {

    /**
     * error code
     * */
    @SerializedName("error")
    public String error;

    /**
     *  result = "0" is ok
     *  result = "1" is error, need to check error code
     * */
    @SerializedName("result")
    public String result;
}
