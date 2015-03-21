package us.harvey.proof.log4j2.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins. PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

@Plugin(name = "LevelRangeFilter", category = "Core", elementType = "filter", printObject = true)
public class LevelRangeFilter extends AbstractFilter {
    private final Level minLevel;
    private final Level maxLevel;

    private LevelRangeFilter(final Level minLevel, final Level maxLevel, final Result onMatch, final Result onMismatch) {
        super(onMatch, onMismatch);
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object... params) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Object msg, final Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Message msg, final Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(final LogEvent event) {
        return filter(event.getLevel());
    }

    private Result filter(final Level level) {
        boolean isBetween = (maxLevel.intLevel()) <= level.intLevel() && (minLevel.intLevel() >= level.intLevel());
        return isBetween ? onMatch : onMismatch;
    }

    /**
     * Create a LevelRangeFilter.
     *
     * @param minLevelName The minimum log Level.
     * @param maxLevelName The maximum log level.
     * @param match The action to take on a match.
     * @param mismatch The action to take on a mismatch.
     * @return The created ThresholdFilter.
     */
    @PluginFactory
    public static LevelRangeFilter createFilter(@ PluginAttribute("minLevel") final String minLevelName, @ PluginAttribute("maxLevel") final String maxLevelName, @ PluginAttribute("onMatch") final String match, @ PluginAttribute("onMismatch") final String mismatch) {
        final Level minLevel = Level.toLevel(minLevelName, Level.ERROR);
        final Level maxLevel = Level.toLevel(maxLevelName, Level.ERROR);
        final Result onMatch = Result.toResult(match, Result.NEUTRAL);
        final Result onMismatch = Result.toResult(mismatch, Result.DENY);
        return new LevelRangeFilter(minLevel, maxLevel, onMatch, onMismatch);
    }
}
