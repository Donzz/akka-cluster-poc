package ru.donz.wf.behaviors;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class FlowState {
    private final String txId;
    private final String lastMessageType;
    private final String lastMessage;

    public FlowState newIncomingExternalMessage(FlowEvent.IncomingExternalMessage externalMessage) {
        log.info("Received incoming external message {}", externalMessage.getMessage());
        return new FlowState(txId, lastMessageType, externalMessage.getMessage());
    }
}
