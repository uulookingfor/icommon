call mvn package
@echo '========================packge done==============================='
call mvn install:install-file -Dfile=./target/icommon-1.0-SNAPSHOT.jar -DgroupId=com.uulookingfor.icommon -DartifactId=icommon -Dversion=1.0.0 -Dpackaging=jar
@echo '========================install done==============================='
@pause