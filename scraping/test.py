'''
Created on Feb 20, 2019

@author: Cameron
'''
number = "2";
tags = "";
filename = "test.txt"


import requests;
import json;

baseurl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes"

findIDurl = baseurl + "/random?"


if(number == ""):
    number = "10"
number = "number=" + number 
if(tags != ""):
    tags = "&tags=" + tags

findIDurl = findIDurl + number + tags
#print(findIDurl)


recipes = []

responseList = requests.get(findIDurl,
    headers={
    "X-RapidAPI-Key": "b738ce18bfmsh4e6a7c2d8aff98cp11356cjsn35dc321ea326"
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
            "X-RapidAPI-Key": "b738ce18bfmsh4e6a7c2d8aff98cp11356cjsn35dc321ea326"
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
    file.write("\n")
    for item in extendedIngredients:
        #print(item['name'] + " " + str(item['amount']) + " " + item['unit'])
        file.write(item['name'] + "," + str(item['amount']) + "," + item['unit'] + ",")
    file.write("\n")
    for step in instructions:
        #print(step['step'])
        file.write("{" + str(step['step'])  + "}"+ ",")
        
    file.write("\n")
    file.write("vegetarian" + "," + str(vegetarian) + "," + "vegan" + "," + str(vegan) + "," + "dairyFree" + "," + str(dairyFree) + "," + "glutenFree" + "," + str(glutenFree) + "," + "ketogenic" + "," + str(keto))
    file.write("\n\n")



