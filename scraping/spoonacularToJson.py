'''
Created on Feb 20, 2019

@author: Cameron
'''

def main():
    import requests
    import json
    import xlsxwriter
    import os
    from xlsxwriter import Workbook
    
    apiKey = "b250001dfdmsh3c8933b091aeb47p192fd9jsn276af239edcb" #alex's
    option = "food/ingredients"
    '''
    options are:
    'recipes'
    'food/ingredients'
    '''
        
    #if doing recipes, modify the next 3
    number = "5"
    tags = ""
    random = True #random = False not tested
    ingredientPull = True
        
    #if doing ingredients, modify the next 3
    ingredientID = "11215"
    amount = "10"
    unit = "ounce"
    maxRequests = 49 #set number of max requests when reading from files
    ingredientFromFile = True
    readSpecificFile = False #if false will read every file in the directory
    specificFilePath = "" #if readSpecificFile is False, this needs to be a file. don't include \'s
    useDefaultDir = True #if false, specific file HAS to be the full path name, else it will use default local\ingredientPull\*
    dirToUseGen = "" #only set this if readSpecificFile is False AND useDefaultDir is False. Will instead use this directory to read the files
    checkDuplicates = True
    duplicateDir = ""
        
        
    #end of safe modifiable
    
    
    cwd = os.getcwd()
    if duplicateDir == "":
        duplicateDir = cwd + "\\spoonacularIngredientJSONs"
        
    searchedIngFiles = os.listdir(duplicateDir)
    print(searchedIngFiles)
    
    print("started")
    #filename = "test.txt"
    excelFileName = "testWB.xlsx"
    
    excel = Workbook(excelFileName)
    excelWorksheet = excel.add_worksheet()
    row = 0
    column = 0
    baseurl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/"
    baseOption = baseurl + option
    ingredient = False
    
    ingredientIDs = []
    singleFile = ""
    listOfFiles = []
    
    myDir = cwd;
    
    if ingredientFromFile:
        if readSpecificFile:
            if useDefaultDir:
                singleFile = cwd + "\\" + specificFilePath
            else:
                singleFile = specificFilePath    
        else:
            if useDefaultDir:
                myDir = cwd + "\\ingredientPull"
            else:
                myDir = dirToUseGen  
            listOfFiles = os.listdir(myDir) 
                
    
    listOfValidFiles = []
    for file in listOfFiles:
        if "ing.txt" in file:
            listOfValidFiles.append(file)
    print(listOfValidFiles)
    
    if random and option == "recipes":
        findIDurl = baseOption + "/random?"
    elif option == "food/ingredients":
        findIDurl = baseOption + "/" + ingredientID + "/information"
        ingredient = True
        if amount == "" or unit == "":
            amount = "10"; unit = "oz"
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
    
    if ingredient and not readSpecificFile:
        print("reading ingredients from directory")
        for file in listOfValidFiles:
            print(file)
            readableFile = open(myDir + "\\" + str(file), 'r')
            ingredients = readableFile.read().split(',')
            for singleIngredient in ingredients:
                if singleIngredient != "":
                    findIDurl = baseOption + "/" + singleIngredient + "/information?amount=" + amount + "&unit=" + unit
                    valid = requesting(findIDurl, ingredient, cwd, apiKey, ingredientPull, singleIngredient, searchedIngFiles)
                    if valid:
                        maxRequests -= 1
                        if maxRequests == 0:
                            return
            print("requests left " + str(maxRequests))
    elif ingredient and readSpecificFile:
        print("reading ingredients from a specific file")
        readableFile = open(specificFilePath, 'r')
        ingredients = readableFile.read().split(',')
        for singleIngredient in ingredients:
            if singleIngredient != "":
                findIDurl = baseOption + "/" + singleIngredient + "/information?amount=" + amount + "&unit=" + unit
                valid = requesting(findIDurl, ingredient, cwd, apiKey, ingredientPull, singleIngredient, searchedIngFiles)
                if valid:
                    maxRequests -= 1
                    if maxRequests == 0:
                        return
        print("requests left " + str(maxRequests))            
    else:
        print("reading recipes")
        requesting(findIDurl, ingredient, cwd, apiKey, ingredientPull, ingredientID, searchedIngFiles)



def requesting(findIDurl, ingredient, cwd, apiKey, ingredientPull, ingredientID, existingIng):
    import requests
    import json
    import xlsxwriter
    import os
    from xlsxwriter import Workbook
    
    if str(ingredientID) + ".json" in existingIng:
        print("repeat")
        return False
    
    responseList = requests.get(findIDurl,
        headers={
        "X-RapidAPI-Key": apiKey
      }
    )
        
    print(responseList)
        
    recipeJson = responseList.json();
        
    ingDir = ""
    print(recipeJson)
    
    if ingredient:
        dumpDir = cwd + "\\spoonacularIngredientJSONs"
    else:
        dumpDir = cwd + "\\recipeJSONs"
        if ingredientPull:
            ingDir = cwd + "\\ingredientPull"
            
        
    if not ingredient:
        recipeResults = recipeJson['recipes']
        if ingredientPull and not os.path.isdir(ingDir):
            os.mkdir(ingDir)
        
    if not os.path.isdir(dumpDir):
        os.mkdir(dumpDir)
    
    if not ingredient:
        i = 0
        for recipe in recipeResults:
            newfile = str(i) + ".json"
            with open(os.path.join(dumpDir,newfile), "w") as file1:
                json.dump(recipe, file1)  
            if ingredientPull:
                extendedIngredients = recipe['extendedIngredients']
                ingFile = str(i) + "ing.txt"
                file2 = open(ingDir + "\\" + ingFile, "w")
                for ingredient in extendedIngredients:
                    file2.write(str(ingredient['id']) + ",")
                file2.close()
            i = i + 1
            
    else:
            newfile = ingredientID + ".json"
            with open(os.path.join(dumpDir,newfile), "w") as file1:
                    json.dump(recipeJson, file1) 
         
    return True


if __name__ == '__main__':
    main()