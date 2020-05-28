#!/bin/bash

REPOSITORY=/home/ec2-user/app/monkey-music-service
PROJECT_NAME=monkey-music

cd $REPOSITORY/$PROJECT_NAME/

echo "> Build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

# pgrep : 프로세스ID만 추출
# -f : 프로세스 이름으로 찾기
# 현재 수항 중인 스프링부트 애플리케이션의 프로세스 ID를 찾음
# 실행 중이면 종료하기 위함
# 스프링부트 애플리케이션 이름(monkey-music)으로 된 다른 프로그램들이 있을 수 있어 monkey-music으로 된 jar(pgrep -fl monkey-music |grep jar) 프로세스 ID를 찾음
# (| awk '{print $1}')
echo "> 현재 구동 중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fl monkey-music | grep jar | awk '{print $1}')

echo "현재 구동 중인 애플리케이션 pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
   echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
   echo "> kill -15 $CURRENT_PID"
   kill -15 $CURRENT_PID
   sleep 5
fi

# 최신 JAR 파일을찾음
echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name : $JAR_NAME"

# Jar 파일은 실행 권한이 없는 상태
# nohup으로 실행할 수 있게 실행 권한 부여
echo "> JAR_NAME에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

## nohup으로 실행이유
# -> java -jar로 실행하면 터미널 접속을 끊을 때 애플리케이션도 같이 종료가 되지만
# -> nohup으로 실행 시 끊기지 않는다.
# ------------------------------
## -Dspring.config.location
#  -> 스프링 설정 파일 위치를 지정
#  -> 기본 옵션들을 담고 있는 application.properties와 OAuth 설절등을 담고있는
#     application-oauth.properties의 위치를 지정
#  -> classpath가 붙으면 jar 안에 있는 resources 디렉토리를 기준으로 경로가 생성 됨.
#  -> application-oauth.properties는 절대경로로 접근, JAR  외부에 파일이 있기 때문
## -Dspring.profiles.active=real
#   -> application-real.properties를 활성화
#   -> application-real.properties의 spring.profiles.inclue=oauth, real-db 옵션 때문에
#      real-bd 역시 함께 활성화 대상에 포함
nohup java -jar \
      -Dspring.config.location=classpath:/application.properties,classpath:application-real.properties,\
/home/ec2-user/app/monkey-music-service/application-oauth.properties,\
/home/ec2-user/app/monkey-music-service/application-real-db.properties \
      -Dspring.profiles.active=real \
      # nohup 실행 시 CodeDeploy는 무한 대기
      # 이 이슈 해결을 위해 nohup.out 파일을 표준 입출력용으로 별도로 사용
      # 이렇게 하지 않으면 nohup.out 파일이 생기지 않고, CodeDeploy 로그에 표준 입출력이 출력 됨
      # nohup이 끝나기 전까지 CodeDeploy도 끝나지 않으니 꼭 이렇게 해야만 함
      $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
