# test_cucumber

# Steps:

1. mvn clean install
2. java -jar target/Test_Cucumber-1.1-SNAPSHOT-jar-with-dependencies.jar --glue cucumber.defs --plugin html:report.html classpath:list.feature 

Test Results could be generated as a json / html / pretty plain text with the --plugin option:

1. --plugin html:report.html # Generates results as HTML  
2. --plugin json:report.json # Generates results as JSON  
3. --plugin pretty # Prints results on console
