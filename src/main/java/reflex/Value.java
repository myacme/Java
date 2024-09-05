package reflex;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited // 可继承
public @interface Value {
    String value();

    String tag();
}
