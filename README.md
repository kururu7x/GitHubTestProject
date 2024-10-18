**PRECONDITIONS**
1. You need to have an account on GitHub

2. Create Personal access token - Repo permissions:
- Administration (read and write)
- Contents (read and write)
- Metadata (read-only)

3. Fill config.properties file (...src/test/resources/config.properties):
- set your login
- set your personal access token

4. Open pom.xml (.../pom.xml) and click 'Load Maven changes' button.

5. ATTENTION: Make sure that none of your repository name has prefix "Repo_"
   All repositories with name "Repo_*" will be deleted
   

**RUN TESTS from Intellij:**

a) To run tests one by one:
    Right click on tests folder (.../src/test/java/com/rest/tests) > Run 'Tests in "com.rest.tests"'

b) To run tests parallel:
   Right click on testng.xml file (.../testng.xml) > Run '...\testng.xml'
   

**TEST REPORT:** \
In terminal: Go to project location > enter command: 
> serve target/allure-results

Alure report will be opened in browser
