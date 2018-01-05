package com.its.sample.helloakka

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

class Greeter : AbstractActor() {
    data class Greeting(val to: String)

    override fun createReceive(): Receive {
        return receiveBuilder()
                .match(Greeting::class.java, { msg: Greeting -> println("Hello ${msg.to}!") })
                .build()
    }
}

fun main(args: Array<String>) {
    val system = ActorSystem.create("Hello-Akka")
    val greeter = system.actorOf(Props.create(Greeter::class.java), "myActor")
    greeter.tell(Greeter.Greeting("Akka"), ActorRef.noSender())
    system.terminate()
}