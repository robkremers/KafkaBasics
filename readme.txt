Date: 2020-02-16.

Purpose:

To give an overview of Kafka, it's handling and some code examples.

Topics:
- Downloading
- Setup of Kafka
- Starting up Kafka
- Overview of commands that can be used for handling of Kafka / topics on the commandline.
- Command line example of Kafka producer / consumer.
- A basic java example:
	- producer_consumer_example
		- kafka_producer
		- kafka_consumer
- Zookeeper state unix commands
- A more extensive java example:
	- trafficSpeedControl
		- InterstateTrafficSensor
		- InterstateSpeedService
		- InterstateCarRegistryService
- Connectors
- Overview of scripts present in $KAFKA_HOME/bin and their purpose.
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
	- advertised.host.name=localhost 	# For test purposes.
	- delete.topic.enable=true 			# In order to enable the deletion of topics (for the commands see below). 
- Add to $KAFKA_HOME/config/zookeeper/properties:
	# RJWK:
	# See: https://zookeeper.apache.org/doc/r3.4.12/zookeeperAdmin.html
	# This property contains a list of comma separated Four Letter Words commands. 
	# It is introduced to provide fine grained control over the set of commands ZooKeeper 
	# can execute, so users can turn off certain commands if necessary. 
	# By default it contains all supported four letter word commands except "wchp" and "wchc", 
	# if the property is not specified. If the property is specified, then only commands listed in the whitelist are enabled.
	# Here's an example of the configuration that enables stat, ruok, conf, and isro command while disabling the rest of Four Letter Words command:
	#                 4lw.commands.whitelist=stat, ruok, conf, isro, envi, srvr
	# Users can also use asterisk option so they don't have to include every command one by one in the list. As an example, this will enable all four letter word commands:
	4lw.commands.whitelist=*


Start up Zookeeper and Kafka:
	$ cd ~/kafka/kafka_2.12-2.4.0/bin
	- Start Zookeeper:
		$ sh bin/zookeeper-server-start.sh config/zookeeper.properties
	..
	[2020-01-08 12:18:46,645] INFO Creating new log file: log.1 (org.apache.zookeeper.server.persistence.FileTxnLog)

	--> zookeeper starts.

	- Start Kafka:
		- $ sh bin/kafka-server-start.sh config/server.properties
	..
	[2020-01-08 12:18:48,220] INFO [KafkaServer id=0] started (kafka.server.KafkaServer)

	--> Kafka starts.

Kafka is now up and running.


Overview of commands that can be used for handling of Kafka on the commandline.

- List the topics available:
	$ sh bin/kafka-topics.sh --zookeeper localhost:2181 --list
	$ sh bin/kafka-topics.sh --zookeeper localhost:2181 --describe

- Create a topic:
	$ sh bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic sampleTopic
	$ sh bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic sampleTopic --if-not-exists

	Note:
	In a production situation the number of partitions would be such that at least three but often more brokers can support the topic.

- Start a producer:
	$ sh .bin/kafka-console-producer.sh --broker-list localhost:9092 --topic sampleTopic

- Start a consumer:
	$ sh bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sampleTopic --from-beginning

- Delete the messages from a topic:
	- Effectively setting the retentiontime for messages to 1 second.
	- In practice this takes a while before it takes effect :-)

	$ sh bin/kafka-configs.sh --zookeeper localhost:2181 --alter --entity-type topics --entity-name sampleTopic --add-config retention.ms=1000

	Result:
	Completed Updating config for entity: topic 'bookTopic'.

	Verify if the retention policy value changed by running the below command.

	$ sh bin/kafka-configs.sh --zookeeper localhost:2181 --entity-type topics --describe --entity-name sampleTopic

	Result:
	Configs for topic 'bookTopic' are retention.ms=1000

	Set back the retention-time to the standard value:

	$ sh bin/kafka-configs.sh --zookeeper localhost:2181 --alter --entity-type topics --entity-name sampleTopic --add-config retention.ms=604800000

- Remove a Kafka topic:

	$ sh bin/kafka-topics.sh --zookeeper localhost:2181 --topic sampleTopic --delete

	Notes:
	- Sometimes it will take time before this has been executed.
	  In that case a listing of the topics will show that the topic to be deleted is 'marked for deletion'.


Command line example of Kafka producer / consumer.
	- Open a new terminal for the commandline producer.
		$ sh $KAFKA_HOME/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic sampleTopic
	- Open a new terminal for the commandline consumer.
		$ sh $KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sampleTopic --from-beginning

	- Now messages can be sent to topic sampleTopic.
	- These messages can be read from the consumer reading from topic sampleTopic.


Zookeeper state unix commands
	It is possible to send "4 letter words" to the ZooKeeper cluster to query the state
	- https://zookeeper.apache.org/doc/r3.1.2/zookeeperAdmin.html
		- ZooKeeper Commands: The Four Letter Words
	- Note: for the curious ones:
		$ man nc <enter> # telnet-like application
	- See: zookeeper.properties: 4lw.commands.whitelist=* # Should be active. Needs to be added.
	- Examples:
		$ echo "srvr" | nc localhost 2181
		$ echo "ruok" | nc localhost 2181
		$ echo "envi" | nc localhost 2181
		$ echo "stat" | nc localhost 2181
		$ echo "conf" | nc localhost 2181 # What is the configuration ZooKeeper started with.


- RJWK 2020-02-28: TODO: Refer to earlier: Navigate the ZooKeeper tree.


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


Connectors

Apache Kafka connectors
- Purpose:
	Many connectors have been built in order to provide connectivity to and from the Kafka brokers / topic in order to support production level connectivity.

- e.g. Confluent, IBM.
	- Confluent: google apache Kafka connectors.
		--> Out of the box connectors.
- Kafka connector API.

Overview of scripts present in $KAFKA_HOME/bin and their purpose.

Sources / References

	Note on sources:
		Kafka is still developing.
		Therefore examples that are somewhat older may be (slightly) out of date.
		So e.g. scripting options and java functionality may be used that are actually out of date.
		In case of doubt check the latest version of the references, API's, etc.

	References:
	- https://kafka.apache.org/
	- https://kafka.apache.org/quickstart
	- https://zookeeper.apache.org/doc/r3.4.12/zookeeperAdmin.html
	- https://docs.cloudera.com/documentation/kafka/latest/topics/kafka_command_line.html
	- https://www.cloudkarafka.com/blog/2018-07-04-cloudkarafka_what_is_zookeeper.html
		- 2020-02-28: To Read.
	- https://docs.confluent.io/2.0.0/quickstart.html
		- Read from this and further (Serializers, Security)
	- https://docs.confluent.io/current/security/index.html

	API Documentation:
	- https://kafka.apache.org/10/javadoc/index.html
	- https://docs.spring.io/spring-kafka/api/index.html

	Kafka Download:
	- https://www.apache.org/dyn/closer.cgi?path=/kafka/2.4.0/kafka_2.12-2.4.0.tgz

	Tutorials:
	- https://www.tutorialkart.com/apache-kafka/install-apache-kafka-on-mac/
	- TheDeveloperGuy: Creating a Kafka Producer with Spring Boot
		- https://www.youtube.com/watch?v=azPVFi8FkBU
		- May 21, 2019
	- https://www.tutorialspoint.com/apache_kafka/
	- https://data-flair.training/blogs/apache-kafka-tutorial/

	Overviews:
	- Devoxx 2019 Belgium: A Deep Dive into Apache Kafka This is Event Streaming by Andrew Dunnings & Katherine Stanley
		- https://www.youtube.com/watch?v=X40EozwK75s&t=1252s

