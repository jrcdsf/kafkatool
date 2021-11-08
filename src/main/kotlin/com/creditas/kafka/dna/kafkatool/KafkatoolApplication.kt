package com.creditas.kafka.dna.kafkatool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkatoolApplication

fun main(args: Array<String>) {
    runApplication<KafkatoolApplication>(*args)
}
