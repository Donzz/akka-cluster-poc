package ru.donz.wf;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.AskPattern;
import akka.actor.typed.javadsl.Behaviors;
import akka.cluster.typed.Cluster;
import akka.cluster.typed.Join;
import akka.japi.function.Function;
import akka.pattern.Patterns;
import akka.persistence.typed.PersistenceId;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ru.donz.wf.behaviors.FlowBehavior;
import ru.donz.wf.behaviors.FlowProtocol;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

@Slf4j
public class WfApp {
    public static void main(String[] args) {
        val wfApp = new WfApp();
        wfApp.startCluster();
    }

    private void startCluster() {
        ActorSystem<FlowProtocol> actorSystem = ActorSystem.create(new FlowBehavior(PersistenceId.ofUniqueId("1")),"wfSystem");
//        val cluster = Cluster.get(actorSystem);
//        cluster.manager().tell(Join.create(cluster.selfMember().address()));

//        val message = new FlowProtocol.IncomingExternalMessage("Azaza",
//                Behaviors.receive(String.class).onMessage(String.class, this::printIt).build());

        log.info("Before sending");
        CompletionStage<String> cs = AskPattern.ask(actorSystem,
                (Function<ActorRef<String>, FlowProtocol>) ref -> new FlowProtocol.IncomingExternalMessage("Azaza", ref), Duration.ofSeconds(1), actorSystem.scheduler());
        cs.whenComplete((s, t) -> {
            if(s != null) {
                printIt(s);
            } else if (t != null) {
                printIt(t.toString());
            } else {
                printIt("Everything is null");
            }
        });
    }

    String printIt(String s) {
        log.info("Received response {}", s);
        return s;
    }

//    Behavior<String> setupStr(ActorContext<String> context) {
//        return Behaviors.receive(String.class).onMessage(String.class, this::printIt).build();
//    }

}
