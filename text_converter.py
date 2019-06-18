def removeNewLines(x):
    newString = ""
    for char in x:
        if char == "\n":
            continue
        else:
            newString += char
    return newString

def addNewLinesAfterPeriods(x):

"""
Example Usage:
>>> string = "Hello World!"
>>> charToGedRidOf = "o"
>>> charToAdd = "a"
>>> print(swap(string, charToGedRidOf, charToAdd))
Hella Warld!
"""
def swap(string, charToGedRidOf, charToAdd):
    newString = ""
    for char in x:
        if char != charToGedRidOf:
            newString += char
        else:
            newString += charToAdd
    return newString

def 
