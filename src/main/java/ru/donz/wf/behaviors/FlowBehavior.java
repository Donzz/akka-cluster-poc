package ru.donz.wf.behaviors;

import akka.persistence.typed.PersistenceId;
import akka.persistence.typed.javadsl.CommandHandler;
import akka.persistence.typed.javadsl.Effect;
import akka.persistence.typed.javadsl.EventHandler;
import akka.persistence.typed.javadsl.EventSourcedBehavior;

public class FlowBehavior extends EventSourcedBehavior<FlowProtocol, FlowEvent, FlowState> {

    public FlowBehavior(PersistenceId persistenceId) {
        super(persistenceId);
    }

    @Override
    public FlowState emptyState() {
        return new FlowState("", "", "");
    }

    @Override
    public CommandHandler<FlowProtocol, FlowEvent, FlowState> commandHandler() {
        return newCommandHandlerBuilder()
                .forAnyState()
                .onCommand(FlowProtocol.IncomingExternalMessage.class, this::onIncomingExternalMessage)
                .build();
    }

    @Override
    public EventHandler<FlowState, FlowEvent> eventHandler() {
        return newEventHandlerBuilder()
                .forAnyState()
                .onEvent(FlowEvent.IncomingExternalMessage.class, FlowState::newIncomingExternalMessage)
                .build();
    }

    private Effect<FlowEvent, FlowState> onIncomingExternalMessage(FlowState currentState, FlowProtocol.IncomingExternalMessage message) {
        return Effect().persist(new FlowEvent.IncomingExternalMessage(message.getMessage()))
                .thenReply(message.getReplyTo(), s -> "OK");
    }
}
