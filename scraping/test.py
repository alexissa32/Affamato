'''
Created on Feb 20, 2019

@author: Cameron
'''
number = "2";
tags = "";
filename = "test.txt"
excelFileName = "testWB.xlsx"
apiKey = "b738ce18bfmsh4e6a7c2d8aff98cp11356cjsn35dc321ea326"


import requests
import json
import xlsxwriter
from xlsxwriter import Workbook

excel = Workbook(excelFileName)
excelWorksheet = excel.add_worksheet()
row = 0
column = 0
baseurl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes"

findIDurl = baseurl + "/random?"


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

recipeResults = recipeJson['recipes']
i = 0
for recipe in recipeResults:
    recipes.append([recipe['id'], recipe['title'], recipe['readyInMinutes'], recipe['servings']])
    #print(str(recipe['id']))
    i = i + 1



'''
cuisine = "";
diet = "";
excludeIng = "";
intolerance = "";
results = "";
offset = "";
type = "";
'''
'''
response = requests.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?diet=vegetarian&number=10&offset=0&type=main+course",
  headers={
    "X-RapidAPI-Key": "b738ce18bfmsh4e6a7c2d8aff98cp11356cjsn35dc321ea326"
  }
)
'''

file = open(filename, 'w')
for recipe in recipes:
    baseurl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"
    id = recipe[0]
    end = "/information"
    findURL = baseurl + str(id) + end
    print(findURL)
    response = requests.get(findURL,
        headers={
            "X-RapidAPI-Key": apiKey
            }
    )
    print(response)

    fullRecipe = response.json()
    '''
    print(fullRecipe);
    print(fullRecipe['extendedIngredients'])
    '''
    extendedIngredients = fullRecipe['extendedIngredients']
    instructions = fullRecipe['analyzedInstructions'][0]['steps']
    vegetarian = fullRecipe['vegetarian']
    vegan = fullRecipe['vegan']
    dairyFree = fullRecipe['dairyFree']
    glutenFree = fullRecipe['glutenFree']
    keto = fullRecipe['ketogenic']

    for item in recipe:
        file.write(str(item) + ",")
        excelWorksheet.write(row, column, str(item))
        column += 1
    file.write("\n")
    row += 1
    column = 0
    for item in extendedIngredients:
        #print(item['name'] + " " + str(item['amount']) + " " + item['unit'])
        file.write(item['name'] + "," + str(item['amount']) + "," + item['unit'] + ",")
        excelWorksheet.write(row, column, item['name'])
        column += 1
        excelWorksheet.write(row, column, str(item['amount']))
        column += 1
        excelWorksheet.write(row, column, item['unit'])
        column += 1
    row += 1
    column = 0
    file.write("\n")
    for step in instructions:
        #print(step['step'])
        file.write("{" + str(step['step'])  + "}"+ ",")
        excelWorksheet.write(row, column, str(step['step']))
        column += 1
    row += 1
    column = 0
    file.write("\n")
    file.write("vegetarian" + "," + str(vegetarian) + "," + "vegan" + "," + str(vegan) + "," + "dairyFree" + "," + str(dairyFree) + "," + "glutenFree" + "," + str(glutenFree) + "," + "ketogenic" + "," + str(keto))
    excelWorksheet.write(row, column, "vegetarian," + str(vegetarian)); column += 1
    excelWorksheet.write(row, column, "vegan," + str(vegan)); column += 1
    excelWorksheet.write(row, column, "dairyFree," + str(dairyFree)); column += 1
    excelWorksheet.write(row, column, "glutenFree," + str(glutenFree)); column += 1
    excelWorksheet.write(row, column, "ketogenic," + str(keto))
    row += 2
    column = 0
    file.write("\n\n")
    
file.close()
excel.close()    



