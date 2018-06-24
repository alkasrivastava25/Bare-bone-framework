# Bare-bone-framework

Have created a automation framework with Cucumber , java and maven.Maven has been used for proper folder structure,build the project and to add all the dependencies required for running this framework.

We have the pom.xml file in which all the required project dependencies have been added.

The framework consist of four main packages under the source folder which are described below :
1.main.java.glue - consists of all the step definition files related to the project. It also consist of the Commons file which contains all the common methods used in the step definition files.

2.main.java.resources - this is just a dummy package which is provided inorder to follow the proper maven folder structure.

3.test.java.runner - Inorder to run anu cucumber test we need to have a TestRunnder class.This is the file which consists of the path of feature file , step definition file/glue file and the tags to run the test.

4.test.resource.feature - This package contains two feature file one with Wikipedia scenarios and one with Travelex scenario.

5.test.resource.objectrepository - This package conatins the property files for each screen.Here all the locators are stored which is inturn called in our step definition with the help of interface concept. 

Also have the screenshot folder where all the screeshots are stored once the test is run.

Have run all the tests successfully through eclipse.And provided the screenshots for your reference.Couldn't run the test through command prompt since it was throwing few errors related to maven.

To run the test - Run as J-unit test from eclipse by using the TestRunner.java file. So this file will call the feature file which is inturn mapped to the respective step definition java file. 
