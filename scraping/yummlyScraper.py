'''
Created on Feb 22, 2019

@author: Cameron
'''
import requests
import json
from builtins import object

baseurl = "https://api.yummly.com/v1/api"
appId = "6a989a19"
apiKey = "941a3a61531c7aa09019d509ec4c2989"
type = "/recipes?"
params = "q=carrot"


appIdPrefix = "_app_id="
apiPrefix = "_app_key="

requestUrl = baseurl + type + appIdPrefix + appId + "&" + apiPrefix + apiKey + "&" + params



print(requestUrl)
'''    headers={
    "X-Yummly-App-ID" : appId,
    "X-Yummly-App-Key" : apiKey
    }
'''
yum = requests.get(requestUrl
)

print(yum)


yumJson = yum.json()

print(yumJson)

print(yum.raw)

print(yum.text)

print("here")

print(yum.raw.read())

string = yum.raw.read().decode("utf-8")
print(string)
json_obj = json.loads(string)

print(json_obj)

'''
Not sure why I can't convert the yum response to a string or json object
'''