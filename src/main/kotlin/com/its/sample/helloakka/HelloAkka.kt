package com.its.sample.helloakka

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

class Greeter : AbstractActor() {
    data class Greeting(val to: String)

    override fun createReceive(): Receive {
        return receiveBuilder()
                .matchAny { msg -> when (msg) {
                    is Greeting -> println("Hello ${msg.to}!")
                    else -> println("Unknown message received!")
                } }
                .build()
    }
}

fun main(args: Array<String>) {
    val system = ActorSystem.create("Hello-Akka")
    val greeter = system.actorOf(Props.create(Greeter::class.java), "myActor")
    greeter.tell(Greeter.Greeting("Akka"), ActorRef.noSender())
    system.terminate()
}