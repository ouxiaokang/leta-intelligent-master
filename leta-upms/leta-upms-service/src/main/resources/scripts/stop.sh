#!/bin/bash
APP_MAIN=cn.leta.upms.LetaUpmsApplication

PID=0
getPID(){
    javaps=`ps -ef | grep java | grep $APP_MAIN`
    if [ -n "$javaps" ]; then
        PID=`echo $javaps  |awk '{print $2}'`
    else
        PID=0
    fi
}

getPID

COUNT=0
if [ $PID -ne 0 ]; then
    echo -e "Stopping the $APP_MAIN ...\c"
    while [ $COUNT -lt 1 ]; do
        echo -e ".\c"
        sleep 1
        COUNT=1
        getPID
        if [ $PID -ne 0 ]; then
            kill $PID > /dev/null 2>&1
            COUNT=0
            break
        fi
    done
    echo "$APP_MAIN(PID=$PID) is stop."
else
    echo "$APP_MAIN is not running"
fi