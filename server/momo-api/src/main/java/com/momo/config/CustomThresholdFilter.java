package com.momo.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.momo.domain.common.exception.ErrorCode;
import java.util.List;

public class CustomThresholdFilter extends Filter<ILoggingEvent> {

    Level level;

    private final List<ErrorCode> ignoreErrors;

    public CustomThresholdFilter() {
        this.ignoreErrors = List.of(
            ErrorCode.INVALID_OAUTH_AUTHORIZATION_CODE
        );
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        FilterReply filterReply = FilterReply.DENY;

        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }
        boolean isTargetLevel = event.getLevel().isGreaterOrEqual(level);
        if (isTargetLevel) {
            return checkIgnoreError(event.getMessage());
        }

        return filterReply;
    }

    private FilterReply checkIgnoreError(String errorMessage) {
        FilterReply filterReply = FilterReply.NEUTRAL;

        ErrorCode targetCode = ErrorCode.fromMessage(errorMessage);
        boolean isIgnore = ignoreErrors.contains(targetCode);
        if (isIgnore) {
            return FilterReply.DENY;
        }

        return filterReply;
    }

    public void setLevel(String level) {
        this.level = Level.toLevel(level);
    }

    public void start() {
        if (this.level != null) {
            super.start();
        }
    }
}
