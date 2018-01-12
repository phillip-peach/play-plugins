package play.modules.statsd;

import javax.inject.Inject;
import play.mvc.EssentialAction;
import play.mvc.EssentialFilter;

/**
 * Filter for reporting request metrics to Statsd.
 *
 * Usage:
 *
 * <pre>
 *     public class Global extends GlobalSettings {
 *         public <T extends play.api.mvc.EssentialFilter> Class<T>[] filters() {
 *             return new Class[] {StatsdFilter.class};
 *         }
 *     }
 * </pre>
 */

public class StatsdFilter extends EssentialFilter {
    // We have to keep a static reference since the Java GlobalSettings instantiates a new filter for every request,
    // and the filter holds a cache.
    @Inject private static play.modules.statsd.api.StatsdFilter filter;

    @Override
    public EssentialAction apply(EssentialAction next) {
        return filter.apply(next).asJava();
    }
}
