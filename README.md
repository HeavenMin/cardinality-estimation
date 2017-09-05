# cardinality_estimation

#### Main task
A java program for cardinality estimation.
* Accurate
* Fast
* Innovative
* Well documemnted
* Well tested

Report part
* track how fast the algorithms are
* track how much space the algorithms use
  * record the space consumed by each call to new and try to account for situations where some objects might reasonably have been garbage collected.

#### Algorithm for this task
* AMS
* BJKST
* Hyperloglog
* Hyperbitbit

Different hash function should be considered,too.

#### Input for the program
File with lines
* Strings
* Integers

> Argument: `filename`

#### Output for the program
A single number that estimates the number of distinct lines (or integers) in the file.

#### Test
Take prefixes of files in 100MB, 200MB, 400MB.
