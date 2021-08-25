#!/bin/bash
#$0表示shell本身的名字，$1表示传递给shell的第一个参数，$2表示传递个shell的第二个参数
#$0表示shell脚本本身名字，这里是stop.sh,`dirname $0`使用"`"倒引号括起来的表示要执行的命令，如`pwd`命令，`dirname $0`执行命令，获取shell程序所在的目录，这里是bin/目录，
#cd `dirname $0`表示进入shell程序的目录，也就是进入bin目录。
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
#定义发布包的目录，/app/datadelivery/anti-fraud-datadelivery-admin/
DEPLOY_DIR=`pwd`
#定义配置目录变量
CONF_DIR=$DEPLOY_DIR/config
#定义服务名变量 使用$来获取变量值。获取变量值使用双引号和不适用双引号的区别，建议使用双引号。如果变量不用双引号括起来，比如echo $a，那么隐含的意义就是，把$a变量的字符串，按照空格、制表符、换行符等符号来分割开。然后把这些分割后的#每一项再按 glob 模式展开，最后把这些值，再用一个空格重新连接起来，并打印出来
SERVER_NAME="$DEPLOY_DIR"
#awk命令是列提取命令，类似cut，默认使用空格，制表符分割，awk '{print $2}'文本中的每一行都执行{print $2}这个动作，输出第二列,进程PID是第二列，每行使用空格分割的，只能用a#awk来提取
#这里获取到的进程号可能是多个，使用echo $PIDS输出如11 22 33使用空格来分隔的。如果取变量值的时候使用双引号，echo "$PIDS"如果是多行的，会进行换行，那么循环就不好使了。
#循环默认使用空格来分割
PIDS=`ps -ef | grep java | grep "$CONF_DIR" |awk '{print $2}'`
# if中-z表示长度为0则返回true,"$PIDS"获取变量值,由于-z 判断长度的参数，所以取变量值的时候要加""，会返回多行数据，而不是一行数据使用空格分隔。
if [ -z "$PIDS" ]; then
  echo "ERROR: The $SERVER_NAME does not started!"
  exit 1
fi
if [ "$1" != "skip" ]; then
   $BIN_DIR/dump.sh
fi
echo -e "Stopping the $SERVER_NAME ...\c"
#for循环in后面可以跟环境变量的值，后面要加分号，去变量值的时候不能加双引号，默认用空格连接，for循环的时候默认使用空格切割,$PIDS值如1350 1351 1352
for PID in $PIDS ; do
  kill $PID > /dev/null 2>&1
done
COUNT=0

while [ $COUNT -lt 1 ]; do
  echo -e ".\c"
  sleep 1
  COUNT=1
  for PID in $PIDS ; do
    PID_EXIST=`ps -f -p $PID | grep java`
    if [ -n "$PID_EXIST" ]; then
      COUNT=0
      break
    fi
  done
done
echo "OK!"
echo "PID: $PIDS"
