cd ../../

if "%time:~0,2%" lss "10" (
mvn versions:set -DnewVersion=0.0.%date:~0,4%%date:~5,2%%date:~8,2%.%time:~1,1%-SNAPSHOT
) else (
mvn versions:set -DnewVersion=0.0.%date:~0,4%%date:~5,2%%date:~8,2%.%time:~0,2%-SNAPSHOT
)