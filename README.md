## to run
- docker needed
- run `./start-dependencies.sh`
- run `./gradlew run` and the app will connect to localstack sns and sqs to send and receive messages.

# to see the bug
- run `./start-dependencies.sh`
- run `./gradlew clean nativeImage`
- run `./build/native-image/application` 
