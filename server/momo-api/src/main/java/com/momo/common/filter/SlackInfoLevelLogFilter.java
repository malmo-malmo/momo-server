package com.momo.common.filter;

import static ch.qos.logback.classic.Level.INFO_INT;
import static ch.qos.logback.core.spi.FilterReply.DENY;
import static ch.qos.logback.core.spi.FilterReply.NEUTRAL;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.momo.common.aop.LoggingAspect;
import com.momo.common.aop.PerformanceAspect;
import java.util.List;

public class SlackInfoLevelLogFilter extends Filter<ILoggingEvent> {

    private final int level;
    private final List<String> accept;

    public SlackInfoLevelLogFilter() {
        this.level = INFO_INT;
        this.accept = List.of(LoggingAspect.class.getName(), PerformanceAspect.class.getName());
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        int eventLevel = event.getLevel().levelInt;
        if (eventLevel != level) {
            return DENY;
        }
        return checkAcceptLogs(event.getLoggerName());
    }

    private FilterReply checkAcceptLogs(String loggerName) {
        if (accept.contains(loggerName)) {
            return NEUTRAL;
        }
        return DENY;
    }
}
