#!/bin/bash
export LOCALSTACK_HOSTNAME_EXTERNAL=localhost  && docker-compose -f docker-compose.yml up -d localstack && docker ps -a
