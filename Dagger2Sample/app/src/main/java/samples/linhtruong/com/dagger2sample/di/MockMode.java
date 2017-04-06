package samples.linhtruong.com.dagger2sample.di;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 4/6/17 - 13:46.
 * @organization VED
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface MockMode {
    String value() default "";
}
