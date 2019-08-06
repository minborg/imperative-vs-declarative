#!/bin/bash
docker run -d --name sonarqube -p 9000:9000 sonarqube
# login with admin/admin
#
# to analyze: mvn sonar:sonar -Dsonar.host.url=http://localhost:9000
