package ru.onix.wf.behaviors

import ru.onix.wf.log

data class FlowState(
    val txId: String,
    val lastMessageType: String,
    val lastMessage: String,
) {
    fun newIncomingExternalMessage(externalMessage: FlowEvent.IncomingExternalMessage): FlowState {
        log().info("Received incoming external message {}", externalMessage.message);
        return FlowState(txId, lastMessageType, externalMessage.message);
    }
}