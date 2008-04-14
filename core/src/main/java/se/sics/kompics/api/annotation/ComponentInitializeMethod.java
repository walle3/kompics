package se.sics.kompics.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotates the <code>initialize</code> method of a component.
 * 
 * @author Cosmin Arad
 * @since Kompics 0.1
 * @version $Id$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentInitializeMethod {
	/**
	 * @return the name of a properties file, provided as a resource of the
	 *         component class, in the same package, used to initialize the
	 *         component.
	 */
	String value() default "";
}