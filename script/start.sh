#!/usr/bin/env bash

# 배포할 신규 버전 스프링 부트 프로젝트를 stop.sh로 종료한 'profile'로 실행
# 기본적인 스크립트는 step2의 deploy.sh와 비슷하지만
# 다른 점은 IDEL_PROFILE을 통해 properties 파일을 가져오고(application-$IDLE_PROFILE.properties)
# , active profile을 지정하는 것(-Dspring.profiles.active=$IDLE_PROFILE) 뿐
# 여기서도 IDLE_PROFILE을 사용하니 profile.sh를 가져와야 함

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/monkey-music-service
PROJECT_NAME=monkey-music

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 어플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."
nohup java -jar \
      -Dspring.config.location=classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/monkey-music-service/application-oauth.properties,/home/ec2-user/app/monkey-music-service/application-real-db.properties \
      -Dspring.profiles.active=$IDLE_PROFILE \
      $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
      # nohup 실행 시 CodeDeploy는 무한 대기
      # 이 이슈 해결을 위해 nohup.out 파일을 표준 입출력용으로 별도로 사용
      # 이렇게 하지 않으면 nohup.out 파일이 생기지 않고, CodeDeploy 로그에 표준 입출력이 출력 됨
      # nohup이 끝나기 전까지 CodeDeploy도 끝나지 않으니 꼭 이렇게 해야만 함