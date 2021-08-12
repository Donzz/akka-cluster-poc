package ru.donz.wf.behaviors;

import akka.actor.typed.ActorRef;
import lombok.Data;

public interface FlowProtocol {

    @Data
    class IncomingExternalMessage implements FlowProtocol{
        private final String message;
        private final ActorRef<String> replyTo;
    }

    @Data
    class C01 implements FlowProtocol{
        private final String phoneNumber;
    }
}
