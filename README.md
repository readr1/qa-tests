# qa-tests
QA test engineer submission

This project is my submission for the QA test engineer role.

##Installation

Use Maven to download the dependancies

```mvn clean intall```

##Running tests
The Runner class will execute all tests under the tag.

src/test/java/stepDefs/Runner.java

Tags can be found in the feature file located in:

src/test/resources/features

By default the tag @apiTests is provided

##Roadmap

In future releases I'd intend to include more robust tests for the Get all products
as at current they will fail if another tests adds a product but fails to clean 
itself down. 

There is also currently no reporting. In future I'd include the reporting capabilities
provided within the Serenity BDD Framework