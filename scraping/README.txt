In spoonacularToJson.py:

apiKey = "" is the api key for rapidAPI
option = "--" is the option you are requesting. Currently supported are "recipes" and "food/ingredients"

For Recipe requests:
	make sure option is set to "recipes"
	number = "" line determines the amount of recipes to fetch from spoonacular
	tags = "" to change the tags if looking for something in particular (not tested yet, may have to change how it is appended)
	random is boolean for if you are doing random recipe searches

For Ingredient requests:
	make sure option is set to "food/ingredients"
	ingredientID is the spoonacular ID for the ingredient you are looking up IF you aren't reading from file
	amount is the amount (default 10)
	unit is the unit of the amount (default ounce)
	(amount and unit are required)
	maxRequests is the maximum number of ingredient requests the script will run
	ingredientFromFile is True if you are reading ingredient ID's from files, false if you are setting ingredientID yourself
	readSpecificFile is false if you want to iterate through the directory
	specificFilePath is the FULL path if you want to read a specific file
	useDefaultDir is if you want to use the current directory as your path
	if not, set dirToUseGen

Usage:
Each api key gets a MAX of 50 requests and 500 results
Each call will use NUMBER + 1 API calls. The first call will get all the recipe name and id's. Then a unique api call will find the ingredients for each recipe id
ie NUMBER = 49 should be the max

Recipe writes JSON files to a directory local/recipeJSONs
Ingredient writes a JSON file to directory local/spoonacularIngredientJSONs