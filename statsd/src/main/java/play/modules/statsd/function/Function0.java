package play.modules.statsd.function;

/**
 * A Function with no arguments.
 */

@FunctionalInterface
public interface Function0<R> {
    public R apply() throws Throwable;
}