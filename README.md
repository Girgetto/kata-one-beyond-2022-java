# Prerequisites

One of the following is enough to run the implementation:

* Java 1.8 or higher + Maven
* Docker

# What to do

We want to write a Morse decoder. You need to have in mind:

1. Letters are split by one space.
2. Words are split by three spaces.
3. Use this coding-table to convert letters in morse to the Latin-script alphabet:

```                                                                                                                  0,0-1         All
.-     => a
-...   => b
-.-.   => c
-..    => d
.      => e
..-.   => f
--.    => g
....   => h
..     => i
.---   => j
-.-    => k
.-..   => l
--     => m
-.     => n
---    => o
.--.   => p
--.-   => q
.-.    => r
...    => s
-      => t
..-    => u
...-   => v
.--    => w
-..-   => x
-.--   => y
--..   => z
.----  => 1
..---  => 2
...--  => 3
....-  => 4
.....  => 5
-....  => 6
--...  => 7
---..  => 8
----.  => 9
-----  => 0
```

# Where to put your solution

* Don't modify any file in the project. Any pull request that changes any of the existing files will be disqualified. 
* Add your solution to a new file in the `src.main.java.com.onebeyond.kata2022.solutions` package (`src/main/java/com/onebeyond/kata2022/solutions/` folder)
  * Name of the file: `YourTeamNameMorseDecoder.java` (substitute `YourTeamName` by your team's name).
  * This class should implement the `MorseDecoder` interface.
  * You need to override the `decodeMisteryMessage` method providing your team's implementation of the Morse Decoder.

# Goal

The goal is to create the **fastest** solution. Don't waste time creating a nice implementation. 
This is probably not how you would code in a real project, since there probably are other 
requirements, like mantainability and readibility. Just try to create the fastes implementation you can.

# How to build and run with Java

```
mvn clean package
java -jar -Dorg.slf4j.simpleLogger.defaultLogLevel=error target/kata-one-beyond-2022-java-1.0-SNAPSHOT-jar-with-dependencies.jar
```

# How to build and run with Docker

```
docker build -t YOUR_NAME/kata-java:latest
docker run YOUR_NAME/kata-java:latest
```
