- mvn clean package
- docker image build . -t school-search
- docker container rm -f $(docker container ls -q --filter name=school-search*)
- docker run -p 8089:8089 --name school-search school-search

## Spring Social

This module contains articles about Spring Social

### Relevant Articles:
- [A Secondary Facebook Login with Spring Social](https://www.baeldung.com/facebook-authentication-with-spring-security-and-social)
