day=$1
currentDir=$(pwd)

echo "Initializing day $day"

dirOfDay="./src/main/kotlin/nl/paulienvanalst/adventOfCode/twentytwentyone/Dec$day"

echo "Initializing day in directory $dirOfDay"

mkdir $dirOfDay

inputFile="$dirOfDay/input.txt"
resultsFile="$dirOfDay/results.txt"
codeFile="$dirOfDay/Dec$day.txt"

touch "$inputFile"
touch "$resultsFile"
touch "$codeFile"

echo "Created all files needed!"

ls "$dirOfDay"

git add "$dirOfDay"

git commit -m "Initial commit of day $day"


