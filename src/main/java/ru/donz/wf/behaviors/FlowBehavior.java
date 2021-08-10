package ru.donz.wf.behaviors;

import akka.persistence.typed.PersistenceId;
import akka.persistence.typed.javadsl.CommandHandler;
import akka.persistence.typed.javadsl.EventHandler;
import akka.persistence.typed.javadsl.EventSourcedBehavior;
import lombok.Data;

public class FlowBehavior extends EventSourcedBehavior<FlowBehavior.Command, FlowBehavior.Event, FlowBehavior.State> {

    public FlowBehavior(PersistenceId persistenceId) {
        super(persistenceId);
    }

    @Override
    public State emptyState() {
        return new State(null, null);
    }

    @Override
    public CommandHandler<Command, Event, State> commandHandler() {
        return null;
    }

    @Override
    public EventHandler<State, Event> eventHandler() {
        return null;
    }

    interface Command {}
    interface Event {}

    @Data
    public final class State {
        private final String txId;
        private final String lastMessageType;
    }
}
