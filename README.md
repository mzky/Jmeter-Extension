# jmeter5.1优化及扩展工具

优化说明：http://mzky.cc/post/75.html


## 自定义目录说明:

example：目录存放各种接口的demo

libprivate：目录存放外部jar包存放路径，外部jar包不要放到lib下

jmx：目录存放jmx场景，可批量执行

jtl：目录存放自动生成jtl文件存放于此

ResultReport：目录存放html报告


## windows下:

jmeter.bat ：win下打开 jmeter

toReport.bat ：批量解析jtl目录下的报告文件生成html报告

toTest.bat ：批量执行jmx目录下的场景脚本


## linux下: 

convReport.sh ：批量解析jtl目录下的报告文件生成html报告

runTest.sh ：批量执行jmx目录下的场景脚本
