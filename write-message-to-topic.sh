#!/bin/sh

echo "Type the message and hit Enter. Execute CTRL+D to stop"
docker exec --interactive --tty broker kafka-console-producer --bootstrap-server broker:9092 --topic topic-test-1
