package ru.onix.wf.behaviors

import akka.actor.typed.ActorRef

interface FlowCommand {
    data class IncomingExternalMessage(
        val message: String,
        val replyTo: ActorRef<String>,
    ) : FlowCommand

    data class C01(
        val phoneNumber: String,
    ) : FlowCommand
}