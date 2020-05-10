#!/bin/bash
total=0
count=50
no=0

if [ -n "$1" ]
then
	if [ $1 == "-n" ]
	then
		no=1
	else
		if [ $1 -ge 0 ]
		then count=$1
		fi
	fi
fi

if [ -n "$2" ] && [ $2 == "-n" ]
then no=1
fi

for (( i=1; i<=$count; i++))
do
	n=$(( $(java -jar target/rubik-1.jar -s -l | wc -l) ))
	if [ $no = 1 ]
	then
		echo $i.	$n	-	3=	$(($n-3))
	fi
	total=$(( $n-3+$total ))
done
average=$(($total/$count))
reset='\033[0m'
if [ $average -ge 150 ]
then valid='\033[0;31m'
else valid='\033[0;32m'
fi
printf "average = ${valid}${average}${reset}\n"