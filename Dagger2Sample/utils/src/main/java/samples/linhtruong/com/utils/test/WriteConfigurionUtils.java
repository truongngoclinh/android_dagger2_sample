package samples.linhtruong.com.utils.test;

import android.content.Context;

import java.io.FileOutputStream;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 7/20/17 - 15:27.
 * @organization VED
 */

public class WriteConfigurionUtils {

    public static void writeConfiguration(Context ctx) {
        try {
            FileOutputStream openFileOutput =
                    ctx.openFileOutput("config.txt", Context.MODE_PRIVATE);
            openFileOutput.write("This is a test1.".getBytes());
            openFileOutput.write("This is a test2.".getBytes());
        } catch (Exception e) {
            // not handled
        }
    }
}
