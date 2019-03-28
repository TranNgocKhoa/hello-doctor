cd common
call mvn -Dmaven.test.skip=true clean install
cd ..
cd hellodoctor-api-gateway
call mvn -Dmaven.test.skip=true clean package
cd ..
cd hellodoctor-configserver
call mvn -Dmaven.test.skip=true clean package
cd ..
cd hellodoctor-discovery-server
call mvn -Dmaven.test.skip=true clean package
cd ..
cd test-service
call mvn -Dmaven.test.skip=true clean package
cd ..
cd auth
call mvn -Dmaven.test.skip=true clean package
cd ..
cd booking
call mvn -Dmaven.test.skip=true clean package