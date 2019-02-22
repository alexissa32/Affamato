In test.py:
number = "" line determines the amount of recipes to fetch from spoonacular
tags = "" to change the tags if looking for something in particular (not tested yet, may have to change how it is appended)
filename = "" sets the filename to write to. PLEASE change if you don't want to overwrite a previous entry. Format: *.txt
excelFileName = "" sets the excel filename to write to. PLEASE change if you don't want to overwrite a previous entry. Format: *.txt
apiKey = "" is the api key for rapidAPI

Usage:
Each api key gets a MAX of 50 requests and 500 results
Each call will use NUMBER + 1 API calls. The first call will get all the recipe name and id's. Then a unique api call will find the ingredients for each recipe id
ie NUMBER = 49 should be the max