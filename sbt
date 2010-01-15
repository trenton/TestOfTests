#!/bin/sh

VER=0.5.6
SBT=$HOME/.sbt/sbt-launcher-${VER}.jar

URL="http://simple-build-tool.googlecode.com/files/sbt-launcher-${VER}.jar"

if [ ! -f $SBT ]; 
then
	echo "Could not find ${SBT}, downloading..."
	mkdir -p $HOME/.sbt
	wget --progress=bar "${URL}" -O ${SBT}
fi

java -jar ${SBT} "$@"

