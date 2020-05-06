# Rubik
_*`~Abduraghmaan Gabriels 2020`*_

A Simple 3x3 Rubiks cube solver developed in Java
Uses the Half-Turn Metric system and does not include slices
(does not rotate the middle layer in any of the axis)
## To compile:
Make sure you have Maven and Java JRE version 8 (minimum requirement) installed
then open you command line and run:
`sh compile.sh`
>
alternatively:
>
`clear && mvn clean && mvn package`
>Note: all *'/'* must be replaced with *'\\'* when running in windows command terminal (*cmd.exe*)
## To Run:
- Arguments:
	`java -cp target/rubik-1.jar rubik.Driver <flags> <starting scramble>`
>
- Starting Scramble:
	must on contain the following instructions and be enveloped in quotation marks:
	`U D L R F B` for clockwise rotations of the relevant faces
	>
	`U' D' L' R' F' B'` for anti-clockwise rotations of the relevant faces
	>
	`U2 D2 L2 R2 F2 B2` for double rotations of the relevant faces
>
- Flags:
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
	`-p Display Soloution in different stages`
>
# Examples
- in manual input mode:
	`java -cp target/rubik-1.jar rubik.Driver`
	>
	or : `java -cp target/rubik-1.jar rubik.Driver -i`
>
- for scrambled input:
	`java -cp target/rubik-1.jar rubik.Driver -s`
	>
	or with a deterimined scrambled size: `java -cp target/rubik-1.jar rubik.Driver -s 40`
>
- for pre-determined scramble:
	`java -cp target/rubik-1.jar rubik.Driver "B U' R D2 F L2"`