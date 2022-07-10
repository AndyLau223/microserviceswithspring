# What's microservice in brief?
The key concepts you need to embrace as you think about microservices are decomposing and deboundling.
The functionality of your applications should be entirely independent of one another. 

# How will developes communicate with your service?
“The first step is to define whether you want a synchronous or asynchronous protocol. For synchronous, the most common communication is HTTP-based REST using XML (Extensible Markup Language), JSON (JavaScript Object Notation), or a binary protocol such as Thrift to send data back and forth to your microservices. For asynchronous, the most popular protocol is AMQP (Advanced Message Queuing Protocol) using a one-to-one (queue) or a one-to-many (topic) with message brokers such as RabbitMQ, Apache Kafka, and Amazon Simple Queue Service (SQS). In later chapters, we’ll learn about the communication protocols”


