'''
Created on Feb 20, 2019

@author: Cameron
'''

apiKey = "b738ce18bfmsh4e6a7c2d8aff98cp11356cjsn35dc321ea326"
option = "recipes"
'''
options are:
'recipes'
'food/ingredients'
'''

#if doing recipes, modify the next 3
number = "2";
tags = "";
random = True

#if doing ingredients, modify the next 3
ingredientID = "11215"
amount = "10"
unit = "gram"


#end of modifiable

#filename = "test.txt"
excelFileName = "testWB.xlsx"

import requests
import json
import xlsxwriter
import os
from xlsxwriter import Workbook

excel = Workbook(excelFileName)
excelWorksheet = excel.add_worksheet()
row = 0
column = 0
baseurl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/"
baseOption = baseurl + option
ingredient = False

if random and option == "recipes":
    findIDurl = baseOption + "/random?"
elif option == "food/ingredients":
    findIDurl = baseOption + "/" + ingredientID + "/information"
    ingredient = True
    if amount != "" and unit != "":
        findIDurl = findIDurl + "?" + "amount=" + amount + "&" + "unit=" + unit

if not ingredient:
    if(number == ""):
        number = "1"
    number = "number=" + number 
    if(tags != ""):
        tags = "&tags=" + tags

    findIDurl = findIDurl + number + tags
    #print(findIDurl)
    
    
recipes = []
    
responseList = requests.get(findIDurl,
    headers={
    "X-RapidAPI-Key": apiKey
  }
)
    
print(responseList)
    
recipeJson = responseList.json();
    
    
print(recipeJson)
cwd = os.getcwd()
if ingredient:
    dumpDir = cwd + "\\spoonacularIngredientJSONs"
else:
    dumpDir = cwd + "\\recipeJSONs"
    
if not ingredient:
    recipeResults = recipeJson['recipes']
    
if not os.path.isdir(dumpDir):
    os.mkdir(dumpDir)

if not ingredient:
    i = 0
    for recipe in recipeResults:
        newfile = str(i) + ".json"
        with open(os.path.join(dumpDir,newfile), "w") as file1:
            json.dump(recipe, file1)        
        i = i + 1
        
else:
    newfile = ingredientID + ".json"
    with open(os.path.join(dumpDir,newfile), "w") as file1:
            json.dump(recipeJson, file1) 
     
