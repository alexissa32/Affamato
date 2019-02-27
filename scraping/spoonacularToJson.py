'''
Created on Feb 20, 2019

@author: Cameron
'''
number = "2";
tags = "";
#filename = "test.txt"
excelFileName = "testWB.xlsx"
apiKey = "db59991420msh5663a602972f8cbp111839jsn1f773f6d4213"


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
    newfile = str(i) + ".json"
    with open(newfile, 'w') as outfile:
        json.dump(recipeJson, outfile)
    i = i + 1
