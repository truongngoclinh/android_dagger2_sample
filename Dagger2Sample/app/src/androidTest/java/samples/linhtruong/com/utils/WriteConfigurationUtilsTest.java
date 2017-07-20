package samples.linhtruong.com.utils;

import android.content.Context;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import samples.linhtruong.com.utils.test.WriteConfigurionUtils;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.FileOutputStream;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 7/20/17 - 15:29.
 * @organization VED
 */

public class WriteConfigurationUtilsTest {

    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();

    @Mock
    Context mContext;

    @Mock
    FileOutputStream mFileOutputStream;

    /**
     * open fileOutputStream 1 time
     * write to file 2 times
     */
    @Test
    public void testFileOutputStream() {
       try {
           when(mContext.openFileOutput(anyString(), anyInt())).thenReturn(mFileOutputStream);
           WriteConfigurionUtils.writeConfiguration(mContext);
           verify(mContext, times(1)).openFileOutput(anyString(), anyInt());
           verify(mFileOutputStream, times(2)).write(any(byte[].class));
       } catch (Exception e) {
           fail(e.toString());
       }
    }
}
