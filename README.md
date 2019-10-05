# Selenium test on Selenium Grid and docker

The ShouldNotLoginTest test is an example of a test run using Selenium Grid in the Page Object Pattern. The test checks the possibility of logging into the https://konto.onet.pl/ platform. 

The FileUploadTest test involves running a docked container on the same machine and moving the file upload test. Test is running on website https://the-internet.herokuapp.com/

To run test you need to give driver in parameter (chrome, firefox, remote, remoteOnLocalhost) example:
* mvn clean test -Dtest=ShouldNotLoginTest -Driver=chrome



The JUnit library was used in the project.
