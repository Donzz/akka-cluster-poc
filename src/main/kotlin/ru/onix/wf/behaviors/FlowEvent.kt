package ru.onix.wf.behaviors

interface FlowEvent {
    data class IncomingExternalMessage(
        val message: String
    ) : FlowEvent
}