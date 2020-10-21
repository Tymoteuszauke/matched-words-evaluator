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
