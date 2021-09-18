package ru.onix.wf.behaviors

import akka.persistence.typed.PersistenceId
import akka.persistence.typed.javadsl.CommandHandler
import akka.persistence.typed.javadsl.EventHandler
import akka.persistence.typed.javadsl.EventSourcedBehavior

class FlowBehavior(val persistenceId: PersistenceId) :
    EventSourcedBehavior<FlowCommand, FlowEvent, FlowState>(persistenceId) {

    override fun emptyState(): FlowState {
        return FlowState("", "", "")
    }

    override fun commandHandler(): CommandHandler<FlowCommand, FlowEvent, FlowState> {
        return newCommandHandlerBuilder()
            .forAnyState()
            .onCommand(FlowCommand.IncomingExternalMessage::class.java, this::onIncomingExternalMessage)
            .build()
    }

    override fun eventHandler(): EventHandler<FlowState, FlowEvent> {
        return newEventHandlerBuilder()
            .forAnyState()
            .onEvent(FlowEvent.IncomingExternalMessage::class.java, FlowState::newIncomingExternalMessage)
            .build()
    }

    private fun onIncomingExternalMessage(
        state: FlowState,
        message: FlowCommand.IncomingExternalMessage,
    ) = Effect()
        .persist(FlowEvent.IncomingExternalMessage(message.message))
        .thenReply(message.replyTo, { "OK" })
}