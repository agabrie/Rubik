#!/bin/bash

total=0
reps=50
no=0
if [ -n "$1" ]
then
	echo $1
	if [ $1 == "-n" ]
	then
		no=1
		# reps=50
	else
		if [ $1 -ge 0 ]
		then reps=$1
		# else reps=50
		fi
	fi
fi
	if [ -n "$2" ] && [ $2 == "-n" ]
	then no=1
	# else no=0
	fi

for (( i=1; i<=$reps; i++ ))
do
	n=$(( $(java -jar target/rubik-1.jar -s -l | wc -l) ))
	if [ $no = 1 ]
	then
		echo $i.$n-3=$(($n-3))
	fi
	total=$(( $n-3+$total ))
	# echo total=$total
done
echo average = $(( $total/$reps ))