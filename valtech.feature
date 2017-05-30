Feature: Valtech QA Challenge

  @valtech
  Scenario: 1 - Latest news assertion
    Given I am on valtech homepage
    Then I check for "Latest news" section

    Scenario Outline: 2 - ABOUT, SERVICES and WORK
      Given I am on valtech homepage
      When I click on the "<link>"
      Then I should see the "<PageHeading>" in "h1"
      Examples:
      | link | PageHeading |
      | ABOUT | About |
      | SERVICES |Services |
      | WORK | Work |

      Scenario: 3 - How many contacts
        Given I am on valtech homepage
        Then check how many offices