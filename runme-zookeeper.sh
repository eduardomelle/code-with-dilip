#!/bin/bash

KAFKA_HOME=/home/eduardo/desenvolvimento/servers/kafka/kafka_2.13-3.9.0
echo KAFKA_HOME=$KAFKA_HOME

# Start the ZooKeeper service
#$ bin/zookeeper-server-start.sh config/zookeeper.properties
$KAFKA_HOME/bin/zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties

