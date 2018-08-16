#!/bin/bash
if [ ! -n "$JAVA_HOME" ]; then
	export JAVA_HOME="/opt/jdk1.8.0_171"
fi

cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`
APP_LOG=${DEPLOY_DIR}/logs
CONFIG_FILE=DEPLOY_DIR/conf/bootstrap.yml

APP_MAIN=cn.leta.upms.LetaUpmsApplication

JAVA_OPTS="-server -Xms512m -Xmx1024m -Xmn256m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -Xloggc:$APP_LOG/gc.log -Dfile.encoding=UTF-8 -DlogPath=$APP_LOG"

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

if [ $PID -ne 0 ]; then
    echo "$APP_MAIN already started(PID=$PID)"
else
    echo -n "Starting $APP_MAIN"
    for appJar in "$APP_HOME"/lib/*.jar;
    do
       CLASSPATH="$CLASSPATH":"$appJar"
    done
    if [ ! -d "$APP_LOG" ]; then
        mkdir "$APP_LOG"
    fi
    nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CONF_DIR:$LIB_JARS $APP_MAIN > $APP_LOG/nohup.log 2>&1 &
    for i in $(seq 5)
    do
        sleep 0.8
        echo -e  ".\c"
    done
    getPID
    if [ $PID -ne 0 ]; then
        echo "(PID=$PID)...[Success]"
    else
        echo "[Failed]"
    fi
fi