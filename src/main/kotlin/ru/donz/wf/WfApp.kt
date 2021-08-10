//package ru.donz.wf
//
//import akka.actor.typed.ActorSystem
//import akka.actor.typed.Behavior
//import akka.actor.typed.javadsl.ActorContext
//import akka.actor.typed.javadsl.Behaviors
//import akka.cluster.typed.Cluster
//import akka.cluster.typed.Join
//
//
//fun main(args: Array<String>) {
//    println("hi")
//    val wfApp = WfApp()
//    wfApp.startCluster()
//}
//
//class WfApp {
//    fun startCluster() {
//        val actorSystem = ActorSystem.create(Behaviors.setup() { context ->
//            Behaviors.receive(String::class.java).onMessage(String::class.java) { m ->
//                printIt(m)
//            }
//        },
//        "wfSystem")
//        val cluster = Cluster.get(actorSystem)
//        cluster.manager().tell(Join.create(cluster.selfMember().address()))
//        actorSystem.tell("Ups")
//    }
//
//    fun printIt(s: String) : Behavior<String> {
//        println(s)
//        return Behaviors.same()
//    }
//
//    fun setupStr(context: ActorContext<String>) {
//        Behaviors.receive(String::class.java).onMessage(String::class.java) { m ->
//            printIt(m)
//        }
//    }
////    private fun active(data: Todo): Behavior<Event?>? {
////        return Behaviors.withTimers<Event?>(
////            Function<TimerScheduler<Event?>, Behavior<Event?>> { timers: TimerScheduler<Event?> ->
////                // State timeouts done with withTimers
////                timers.startSingleTimer("Timeout", Timeout.INSTANCE, Duration.ofSeconds(1))
////                Behaviors.receive(Event::class.java)
////                    .onMessage(Queue::class.java) { message -> active(data.addElement(message)) }
////                    .onMessage(Flush::class.java) { message -> activeOnFlushOrTimeout(data) }
////                    .onMessage(Timeout::class.java) { message -> activeOnFlushOrTimeout(data) }
////                    .build()
////            })
////    }
////private object RootBehavior {
////    fun create(): Behavior<Void?> {
////        return Behaviors.setup { obj: ActorContext<Void?>? -> apply() }
////    }
////
////    private fun apply(context: ActorContext<Void>): Behavior<Void?> {
////        val cluster = Cluster.get(context.system)
////        if (cluster.selfMember().hasRole("backend")) {
////            val workersPerNode = context.system.settings().config().getInt("transformation.workers-per-node")
////            for (i in 0 until workersPerNode) {
////                context.spawn(Worker.create(), "Worker$i")
////            }
////        }
////        if (cluster.selfMember().hasRole("frontend")) {
////            context.spawn(Frontend.create(), "Frontend")
////        }
////        return Behaviors.empty()
////    }
////}
//}