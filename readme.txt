Date: 2020-02-16.

Purpose:

To give an overview of Kafka, it's handling and some code examples.

Topics:
- Downloading
- Setup of Kafka
- Starting up Kafka
- Overview of commands that can be used for handling of Kafka on the commandline.
- Command line example of Kafka producer / consumber.
- A basic java example:
	- producer_consumer_example
		- kafka_producer
		- kafka_consumer
- A more extensive java example:
	- trafficSpeedControl
		- InterstateTrafficSensor
		- InterstateSpeedService
		- InterstateCarRegistryService

Git:
The application will be available on: 
https://github.com/robkremers/KafkaBasics


Initial:
  470  git init
  471  git pull https://github.com/robkremers/KafkaBasics.git
  473  git status
  474  git add .
  480  git commit -m "Initial commit."
  492  git pull https://github.com/robkremers/KafkaBasics.git
  498  git remote add origin https://github.com/robkremers/KafkaBasics.git
  499  git push -u origin master


Downloading:

1. https://kafka.apache.org/downloads.html
	- Download and install in a suitable directory
2. $ brew install kafka
3. https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html#ce-docker-quickstart

(I have chosen option 1).

Setup of Kafka.
- Add to .bash_profile KAFKA_HOME
	- export KAFKA_HOME=/Users/rkremers/kafka/kafka_2.12-2.4.0/
- Add to $KAFKA_HOME/config/server.properties:
	- advertised.host.name=localhost # For test purposes.

