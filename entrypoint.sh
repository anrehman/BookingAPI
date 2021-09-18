#!/bin/bash
[...]
# launch your spring boot jar
nohup java -jar /BookingAPI-0.0.1-SNAPSHOT.jar
disown %1
while sleep 1
do
  if ! ps auxgww | grep -v grep | grep java
  then
    nohup java -jar /BookingAPI-0.0.1-SNAPSHOT.jar &
    disown %1
  fi
done
