package ru.onix.wf

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.AskPattern
import akka.persistence.typed.PersistenceId
import org.slf4j.LoggerFactory
import ru.onix.wf.behaviors.FlowBehavior
import ru.onix.wf.behaviors.FlowProtocol
import java.time.Duration

fun Any.log() = LoggerFactory.getLogger(javaClass)

fun main() {
    WfApp.startCluster()
}

object WfApp {
    fun startCluster() {
        val actorSystem = ActorSystem.create(FlowBehavior(PersistenceId.ofUniqueId("1")), "wfSystem")
        log().info("Before sending")

        val completionStage = AskPattern.ask<FlowProtocol, String>(
            actorSystem,
            { FlowProtocol.IncomingExternalMessage("Azaza", it) },
            Duration.ofSeconds(10),
            actorSystem.scheduler()
        )

        completionStage.whenComplete { res, ex ->
            if (res != null) {
                printIt(res)
            } else if (ex != null) {
                printIt(ex.toString())
            } else {
                printIt("Everything is null")
            }
        }

        log().info("After sending")
    }

    fun printIt(string: String) {
        log().info("Received response {}", string)
    }
}

