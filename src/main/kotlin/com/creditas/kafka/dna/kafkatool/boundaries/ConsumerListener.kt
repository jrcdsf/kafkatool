package com.creditas.kafka.dna.kafkatool.boundaries

import org.springframework.kafka.annotation.DltHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.kafka.retrytopic.DltStrategy
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.retry.annotation.Backoff
import org.springframework.stereotype.Component

@Component
class ConsumerListener {

    @RetryableTopic(
        attempts = "3",
        backoff = Backoff(delay = 1000, multiplier = 2.0),
        autoCreateTopics = "false", dltStrategy = DltStrategy.FAIL_ON_ERROR,
        topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,

    )
    @KafkaListener(topics = ["\${kafka-topics.topic1}"])
    fun listenTo(message: String, @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String) {
        println("Received message $message from topic $topic")
        throw RuntimeException("Unable to listen")
    }

    @DltHandler
    fun dltHandler(message: String, @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String) {
        println("Received DLT message $message from topic $topic")
    }
}
