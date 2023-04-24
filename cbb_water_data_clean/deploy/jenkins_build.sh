#!/bin/sh -l
set -e

# 组件标签   全公司唯一
component_tag="cbb_web_service_skeleton"


# 项目名称
projectName=${component_tag}_${BRANCH_NAME_GIT}
# 变量替换
sed -i 's,${projectName},'${projectName}',g' ./.env


#服务器网卡名称
networkCardName="`ip addr | grep 'state UP' | sed -r -n ' s/^[0-9]+: (.*):.*/\1/p' | grep -v "veth\|br\|overlay\|lo" | grep -v "docker"`"
echo "networkCardName：${networkCardName}"
#注册中心IP
registryCenterIP="`ifconfig ${networkCardName} | grep "inet 1" | awk '{print $2}'`"
# 获取本地ip 作为注册中心ip
echo "registryCenterIP：${registryCenterIP}"
# 变量替换
sed -i 's,${registryCenterIP},'${registryCenterIP}',g' ./.env


echo "编译"${projectName}

cd ${WORKSPACE}/

mvn clean

mvn install

mvn com.summit:cbb_maven_software:push

if [ $? = 0 ]
then
 echo "maven构建成功"
else
 exit 1
fi


echo "部署"${projectName}"(Docker版本)"

cd ${WORKSPACE}/deploy

docker-compose -p ${component_tag} down  --rmi all
docker-compose -p ${component_tag} up  -d
exit 0