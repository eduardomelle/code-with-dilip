#!/bin/bash

KAFKA_HOME=/home/eduardo/desenvolvimento/tools/kafka/kafka_2.13-3.9.0
echo KAFKA_HOME=$KAFKA_HOME

KAFKA_CLUSTER_ID="$($KAFKA_HOME/bin/kafka-storage.sh random-uuid)"
echo KAFKA_CLUSTER_ID=$KAFKA_CLUSTER_ID

$KAFKA_HOME/bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c $KAFKA_HOME/config/kraft/reconfig-server.properties
echo bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c config/kraft/reconfig-server.properties

$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/kraft/reconfig-server.properties
echo bin/kafka-server-start.sh config/kraft/reconfig-server.properties

