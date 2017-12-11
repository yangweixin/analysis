package top.young.analysis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlVarchar {
    String name() default "";
    int value() default 20;
    SqlColumnCom common() default @SqlColumnCom;
}
