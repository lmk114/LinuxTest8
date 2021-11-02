ping -c 3  www.baidu.com  >> /dev/null
if [ $? -ne 0 ]
then echo NotFoundIp
else echo Suceess
fi
