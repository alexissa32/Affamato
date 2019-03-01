'''
Created on Feb 22, 2019

@author: Cameron
'''
import requests
import json
import os

baseurl = "https://api.yummly.com/v1/api"
appId = "6a989a19"
apiKey = "941a3a61531c7aa09019d509ec4c2989"
type = "/recipes?"
params = "q=carrot"


appIdPrefix = "_app_id="
apiPrefix = "_app_key="

requestUrl = baseurl + type + appIdPrefix + appId + "&" + apiPrefix + apiKey + "&" + params

print(requestUrl
)

yum = requests.get(requestUrl
)

print("yum" + str(yum))

recipeJSON = yum.json()



recipes = recipeJSON['matches']

cwd = os.getcwd()
dumpDir = cwd + "\\yummlyrecipeJSONs"

if not os.path.isdir(dumpDir):
    os.mkdir(dumpDir)

i = 0
for recipe in recipes:
    newfile = str(i) + ".json"
    with open(os.path.join(dumpDir,newfile), "w") as outfile:
        json.dump(recipe, outfile)
    i = i + 1
