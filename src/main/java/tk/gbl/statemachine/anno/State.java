package tk.gbl.statemachine.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Date: 2015/8/1
 * Time: 9:23
 *
 * @author Tian.Dong
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface State {
  String name();
}
