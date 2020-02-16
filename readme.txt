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
- Sources / References

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

Start up Zookeeper and Kafka:
	$ cd ~/kafka/kafka_2.12-2.4.0/bin
	- Start Zookeeper:
		$ sh zookeeper-server-start.sh config/zookeeper.properties
	..
	[2020-01-08 12:18:46,645] INFO Creating new log file: log.1 (org.apache.zookeeper.server.persistence.FileTxnLog)

	--> zookeeper starts.

	- Start Kafka:
		- $ sh /kafka-server-start.sh config/server.properties
	..
	[2020-01-08 12:18:48,220] INFO [KafkaServer id=0] started (kafka.server.KafkaServer)

	--> Kafka starts.

Kafka is now up and running.


Overview of commands that can be used for handling of Kafka on the commandline.

- List the topics available:
	$ sh kafka-topics.sh --zookeeper localhost:2181 --list
	$ sh kafka-topics.sh --zookeeper localhost:2181 --describe

- Create a topic:
	$ sh ./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic sampleTopic
	$ sh ./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic sampleTopic --if-not-exists

- Start a producer:
	$ sh ./kafka-console-producer.sh --broker-list localhost:9092 --topic sampleTopic

- Start a consumer:
	$ sh ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sampleTopic --from-beginning

- Delete the messages from a topic:
	- Effectively setting the retentiontime for messages to 1 second.
	- In practice this takes a while before it takes effect :-)

	$ sh kafka-configs.sh --zookeeper localhost:2181 --alter --entity-type topics --entity-name sampleTopic --add-config retention.ms=1000

	Result:
	Completed Updating config for entity: topic 'bookTopic'.

	Verify if the retention policy value changed by running the below command.

	$ sh kafka-configs.sh --zookeeper localhost:2181 --entity-type topics --describe --entity-name sampleTopic

	Result:
	Configs for topic 'bookTopic' are retention.ms=1000

	Set back the retention-time to the standard value:

	$ sh kafka-configs.sh --zookeeper localhost:2181 --alter --entity-type topics --entity-name sampleTopic --add-config retention.ms=604800000


Command line example of Kafka producer / consumer.
	- Open a new terminal for the commandline producer.
		$ sh $KAFKA_HOME/kafka-console-producer.sh --broker-list localhost:9092 --topic sampleTopic
	- Open a new terminal for the commandline consumer.
		$ sh ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sampleTopic --from-beginning

	- Now messages can be sent to topic sampleTopic.
	- These messages can be read from the consumer reading from topic sampleTopic.


A basic java example:
- producer_consumer_example
	- kafka_producer
		- A Java REST Spring Boot application.
			- Based on spring-boot-starter-web
	- kafka_consumer
		- A Java Spring Boot application.
			- Based on spring-boot-starter.
			- The use of '@EnableKafka' in class KafkaConfiguration acts as a @Service and will keep the application up.
- Postman:
	POST: http://localhost:9999/api/kafka
	- Body (raw, JSON):
		{
		  "name": "Apache Kafka in Action",
		  "details": "Based on Kafka version kafka_2.12-2.4.0 test 03"
		}

Result:
- The JSON book instance will be received by the producer.
- The producer will send the instance to Kafka under topic 'bookTopic'.
- The consumer, listening to topic 'bookTopic' will receive the message.


A more extensive java example:
- trafficSpeedControl
	- InterstateTrafficSensor
	- InterstateSpeedService
	- InterstateCarRegistryService

