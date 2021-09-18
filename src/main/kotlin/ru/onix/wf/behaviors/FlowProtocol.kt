package ru.onix.wf.behaviors

import akka.actor.typed.ActorRef

interface FlowProtocol {
    data class IncomingExternalMessage(
        val message: String,
        val replyTo: ActorRef<String>,
    ) : FlowProtocol

    data class C01(
        val phoneNumber: String,
    ) : FlowProtocol
}