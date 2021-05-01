#!/bin/bash

# Topics
awslocal sns create-topic --name file-events.fifo --attributes ContentBasedDeduplication=true,FifoTopic=true

# Queues
awslocal sqs create-queue --queue-name file-created.fifo --attributes FifoQueue=true,ContentBasedDeduplication=true,DeduplicationScope=messageGroup,FifoThroughputLimit=perMessageGroupId
awslocal sqs create-queue --queue-name queue_text --attributes FifoQueue=false,ContentBasedDeduplication=true,DeduplicationScope=messageGroup

# Subscriptions
awslocal sns subscribe --topic-arn arn:aws:sns:eu-central-1:000000000000:file-events.fifo --protocol sqs --notification-endpoint arn:aws:sns:eu-central-1:000000000000:file-created.fifo --attributes '{"FilterPolicy":"{\"event\":[\"FILE_CREATED\"]}"}'
