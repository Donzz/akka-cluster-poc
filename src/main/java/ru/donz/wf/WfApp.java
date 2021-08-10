package ru.donz.wf;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.cluster.typed.Cluster;
import akka.cluster.typed.Join;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class WfApp {
    public static void main(String[] args) {
        val wfApp = new WfApp();
        wfApp.startCluster();
    }

    private void startCluster() {
        ActorSystem<String> actorSystem = ActorSystem.create(Behaviors.setup(this::setupStr),"wfSystem");
        val cluster = Cluster.get(actorSystem);
        cluster.manager().tell(Join.create(cluster.selfMember().address()));
        actorSystem.tell("Ups");
    }

    Behavior<String> printIt(String s) {
        log.info(s);
        return Behaviors.same();
    }

    Behavior<String> setupStr(ActorContext<String> context) {
        return Behaviors.receive(String.class).onMessage(String.class, this::printIt).build();
    }

}
