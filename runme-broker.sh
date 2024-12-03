#!/bin/bash

KAFKA_HOME=/home/eduardo/desenvolvimento/servers/kafka/kafka_2.13-3.9.0
echo KAFKA_HOME=$KAFKA_HOME

# Start the Kafka broker service
#$ bin/kafka-server-start.sh config/server.properties
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties

