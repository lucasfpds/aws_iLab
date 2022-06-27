#!/bin/bash
export KAFKA_HOST="192.168.0.6:9092"
export KAFKA_TOPIC="UM_TOPICO"

export CLASSPATH=target/kafka_producer-1.0-SNAPSHOT.jar
export className=App
echo "## Running $className..."
shift
mvn exec:java -Dexec.mainClass="br.com.kafka_producer.$className"