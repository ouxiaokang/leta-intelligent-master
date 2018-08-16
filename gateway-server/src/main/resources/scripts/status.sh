#!/bin/bash

APP_MAIN=cn.leta.gateway.GatewayApplication

PID=0
javaps=`ps -ef | grep java | grep $APP_MAIN`
if [ -n "$javaps" ]; then
    PID=`echo $javaps  |awk '{print $2}'`
else
    PID=0
fi

if [ $PID -ne 0 ]; then
     echo "$APP_MAIN is running(PID=$PID)"
else
    echo "$APP_MAIN is not running"
fi