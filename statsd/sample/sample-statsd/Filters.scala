import javax.inject.Inject

import play.api.http.DefaultHttpFilters
import play.modules.statsd.api.StatsdFilter

class Filters @Inject() (statsD: StatsdFilter) extends DefaultHttpFilters(statsD)
