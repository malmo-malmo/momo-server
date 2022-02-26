package com.momo.filter;

import static ch.qos.logback.classic.Level.ERROR_INT;
import static ch.qos.logback.core.spi.FilterReply.DENY;
import static ch.qos.logback.core.spi.FilterReply.NEUTRAL;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class SlackErrorLevelLogFilter extends Filter<ILoggingEvent> {

    private final int level;

    public SlackErrorLevelLogFilter() {
        this.level = ERROR_INT;
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        int eventLevel = event.getLevel().levelInt;
        if (eventLevel != level) {
            return DENY;
        }
        return NEUTRAL;
    }
}
