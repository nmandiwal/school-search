### mysql docker
- docker run -d -p 3306:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=school_search" mysql

### spring-boot docker
- mvn clean package
- docker image build . -t school-search
- docker container rm -f $(docker container ls -q --filter name=school-search*)
- docker run -p 443:443 -e mhost=docker-mysql -e muser=root -e mpassword="root" -e fbappid=<your_fbappid> -e fbsecret=<fbsecret> -e jkspassword=<yourjkspassword> --link docker-mysql:mysql --name school-search school-search

## Spring Social

This module contains articles about Spring Social

### Relevant Articles:
- [A Secondary Facebook Login with Spring Social](https://www.baeldung.com/facebook-authentication-with-spring-security-and-social)

### Docker push
- [docker tag school-search:latest mandiwal/dockerhub:school-search]
- [docker push mandiwal/dockerhub:school-search]


