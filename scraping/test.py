'''
Created on Feb 20, 2019

@author: Cameron
'''

import requests;

ingredients = False;
baseurl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes"

findIDurl = baseurl + "/random?"

number = "10";
tags = "";

if(number == ""):
    number = "10"
number = "number=" + number 
if(tags != ""):
    tags = "&tags=" + tags

findIDurl = findIDurl + number + tags
print(findIDurl)

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
response = requests.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/726058/information",
  headers={
    "X-RapidAPI-Key": "b738ce18bfmsh4e6a7c2d8aff98cp11356cjsn35dc321ea326"
  }
)
'''
'''
response = requests.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?diet=vegetarian&number=10&offset=0&type=main+course",
  headers={
    "X-RapidAPI-Key": "b738ce18bfmsh4e6a7c2d8aff98cp11356cjsn35dc321ea326"
  }
)
'''
'''
print(response);

x = response.json();

print(x);
'''


