##Matched words evaluator

####Goals:

1. Application should read all the text files in given directory, build in memory representation of the files and their contents
2. Application should provide command prompt allowing interactive searches to the user 
2. Search tool should take words given on the prompt and return a list of top 10 (maximum) matching **FILENAMES** in rank order, giving the rank score against each match
  The rank score must be 100%
   * The rank score must be 100% if a file contains all the words
   * It must be 0% if it contains none of the words
   * It should be between 0 and 100 if it contains only some of the words but the exact ranking formula
     is up to you to choose and implement

#### How to run:
    ./build.sh (for linux/macos/windows10)
    java -jar build/lib/matched-words-evaluator-1.0-0.jar files

...where *files* is path to directory with text files.
Application supports 2 additional flags:

    --ide (to run jar with your ide (it has to be used on git console too), it will use Scanner for receiving an input instead of System.console)
    --arrayListEvaluator (that will use ArrayList as words data structure, I left it only for comparison tests)
    
#### Developer comments
1. After starting coding task I did some research for proper data structure for this task. I had glimps from the past (studies) that there was a tree structure that would fit got the task.
So after some digging I found [Trie](https://www.baeldung.com/trie-java) and I used this as default implementation.

2. If console had to have commands, my first idea was to use command pattern here. Thanks to this there is a possibility of extending application for more commands if required without.

3. What for I did not have time:
   * Some argument parser. There are nasty methods with nasty if/elses in EvaluatorRunner class.
   * InputReceiver test - I know that I could use ArrayByteInputStream there, it seems I had some issues and couldn't make Scanner.nextLine() to read line properly :(
   * Didn't have time to cover more test cases (EvaluatorRunner)
   * Some naming could be better (need code review to discuss different ideas!)

4. I was not sure whether I was allowed to use assertJ for test assertions. It is assertion library, not "directly" testing :). I'm not big fun of junit assertions and with assertJ I would do more concrete tests.

 