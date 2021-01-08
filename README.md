---
- __[DotaApp.zip](https://github.com/VincentSastra/HeroDrafter/raw/master/target/DotaApp.zip)__ - Download zip file here
- Extract the zip file
- Launch by using the launcher.sh
---

#### Dota App for analyzing best pick against enemy matchups

All data are gathered from dotabuff.com. 

Code is divided into two sections:

1. HTMLParser process website into a Document
    - Remove all scripts, comments, and style from the html document
    - Parse html using regex to extract tables and its data
2. Main which takes the Document and parse it into data to be displayed in the App
    - Keep track of which enemy hero is the user selecting
    - Calculate the win-rate of each hero based on the enemy selection
    - Display all the data using JavaFX

The App's front-end uses JavaFX.

The zip file is created through JLINK
