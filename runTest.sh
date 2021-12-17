#!/bin/bash
ps -ef|grep jmeter|grep -v grep |awk '{print $2}'|xargs kill -9
#rm -f jmeter.log nohup.out
file=`date '+%Y%m%d%H%M%S'`
mkdir $PWD/jtl/$file
mv $PWD/jtl/*.jtl $PWD/jtl/$file
jmxlist=`ls jmx/`
echo ----------------- jmx list -----------------
echo $jmxlist
echo ----------------- Test Begin -----------------
for jmx in $jmxlist
do
	./bin/jmeter -n -t jmx/$jmx -l jtl/${jmx%%.*}.jtl
done
echo ----------------- Test End -----------------
