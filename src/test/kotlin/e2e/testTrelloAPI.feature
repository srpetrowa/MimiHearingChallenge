Feature: Get Trello List

  Background:
      * def auth = "key=" + trelloKey + "&token=" + trelloToken
      * def listPath = baseUrl + "/lists"
      * def boardPath = baseUrl + "/boards"
      * def boardName = "MimiBoard42"

    Given url boardPath + "?name=" + boardName + "&" + auth + "&defaultLists=false"
    And request {}
    When method POST
    Then status 200
    And match response.id == '#notnull'
    And def uniqueBoardID = response.id

  Scenario: Get trello board

    Given url boardPath + '/' + uniqueBoardID + "?" + auth
    When method GET
    Then status 200
    And match response.name == "MimiBoard42"

  Scenario: Post and get trello list

    * def listName = "ImportantList"

    Given url listPath + "?name=" +listName + "&idBoard=" + uniqueBoardID + "&" + auth
    And request {}
    When method POST
    Then status 200
    And match response.id == '#notnull'
    And def uniqueListID = response.id


    Given url listPath + '/' + uniqueListID + "?" + auth
    When method GET
    Then status 200
    And match response.name == "ImportantList"

  Scenario: Delete trello board and verify

    Given url boardPath + '/' + uniqueBoardID + "?" + auth + "&Id=" + uniqueBoardID
    And request {}
    When method DELETE
    Then status 200

    Given url boardPath + '/' + uniqueBoardID + "?" + auth
    When method GET
    Then status 404






