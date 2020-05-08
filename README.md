# Rubik
_*`~Abduraghmaan Gabriels 2020`*_

A Simple 3x3 Rubiks cube solver developed in Java
>
Uses the Half-Turn Metric system and does not include slices
>
(does not rotate the middle layer in any of the axis)
## To compile:
Make sure you have Maven and Java JRE version 8 (minimum requirement) installed
>
then open your command line and run:
`sh compile.sh`
>
alternatively:
>
`clear && mvn clean && mvn package`
>Note: all *'/'* must be replaced with *'\\'* when running in windows command terminal (*cmd.exe*)
## To Run:
- Arguments:
	>
	`java -jar target/rubik-1.jar <flags> <starting scramble>`
>
- Starting Scramble:
	>
	must on contain the following instructions and be enveloped in quotation marks:
	>
	`U D L R F B` for clockwise rotations of the relevant faces
	>
	`U' D' L' R' F' B'` for anti-clockwise rotations of the relevant faces
	>
	`U2 D2 L2 R2 F2 B2` for double rotations of the relevant faces
>
- Flags:
	>
	`-i	Manual input mode`
	>
	`-l	Output each result on a new line`
	>
	`-n	Do not display the solution`
	>
	`-o No visual representation of cube`
	>
	`-v Display visual state of cube after each instruction`
	>
	`-s AutoScramble on, accepts a numeric parameter for number of scrambles`
	>
	`-p Display Solution in different stages`
>
# Examples
- in manual input mode:
	`java -jar target/rubik-1.jar`
	>
	or : 
	>
	`java -jar target/rubik-1.jar -i`
>
- for scrambled input:
	`java -jar target/rubik-1.jar -s`
	>
	or with a deterimined scrambled size:
	>
	`java -jar target/rubik-1.jar -s 40`
>
- for pre-determined scramble:
	`java -jar target/rubik-1.jar "B U' R D2 F L2"`