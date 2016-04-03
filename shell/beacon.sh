#!/usr/bin/env bash

if [ "$1" == "--from" ] && [ "$3" == "--to" ]; then
	java -jar ../target/1-1.0-SNAPSHOT.jar "$2" "$4"
	exit;

else
   echo -e "Running beacon app..";
   java -jar ../target/1-1.0-SNAPSHOT.jar
   echo -e "Done!";
   exit;

fi


