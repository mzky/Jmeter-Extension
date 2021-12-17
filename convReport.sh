#!/bin/bash
file=`date '+%Y%m%d%H%M%S'`
mkdir $PWD/ResultReport/$file
chmod 777 $PWD/ResultReport/$file
jtlist=`ls jtl/|grep jtl`
echo ----------------- jtl list -----------------
echo $jtlist
echo ----------------- jtl list -----------------
cp -f bin/reportgenerator.properties.linux bin/reportgenerator.properties
echo ----------------- Report Begin -----------------
for jtl in $jtlist
do
	echo ./bin/jmeter -g $PWD/jtl/$jtl -e -o $PWD/ResultReport/$file/${jtl%%.*}
	./bin/jmeter -g $PWD/jtl/$jtl -e -o $PWD/ResultReport/$file/${jtl%%.*}
done
echo ----------------- Report End -----------------
cd ResultReport
ps -ef|grep httpServer|grep -v grep|awk '{print $2}'|xargs kill -9 
./httpServer &
