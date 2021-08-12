package ru.donz.wf.behaviors;

import lombok.Data;

public interface FlowEvent {

    @Data
    class IncomingExternalMessage implements FlowEvent {
        private final String message;
    }

}
