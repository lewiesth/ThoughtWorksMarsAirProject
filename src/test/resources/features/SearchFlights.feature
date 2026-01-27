@Regression
Feature: Search flights flow

  Background:
    Given user navigates to "MarsAirSearch" page
  @UserStory1 @Sanity
  Scenario Outline: Average user searches for valid flight
    When user selects a departing date "<DepartureDate>"
    And user selects a returning date "<ReturnDate>"
    And clicks on search
    Then show seat available message
    Examples:
      | DepartureDate | ReturnDate |
      | July          | December (two years from now) |

  @UserStory1 @Sanity
  Scenario Outline: Average user searches for valid flight that is fully booked
    When user selects a departing date "<DepartureDate>"
    And user selects a returning date "<ReturnDate>"
    And clicks on search
    Then show no more seats available message
    Examples:
      | DepartureDate | ReturnDate |
      | July          | July (two years from now) |

  @UserStory2 @Sanity
  Scenario Outline: User enters a valid promo code
    Given user selects a departing date "<DepartureDate>"
    And user selects a returning date "<ReturnDate>"
    When user enters a valid promo code
    And clicks on search
    Then show seat available message
    And promo code is valid message
    Examples:
      | DepartureDate | ReturnDate |
      | July          | December (two years from now) |

  @UserStory2 @Sanity
  Scenario Outline: User enters an invalid promo code
    Given user selects a departing date "<DepartureDate>"
    And user selects a returning date "<ReturnDate>"
    When user enters a invalid promo code
    And clicks on search
    Then show seat available message
    And promo code is invalid message
    Examples:
      | DepartureDate | ReturnDate |
      | July          | December (two years from now) |


  @UserStory3
  Scenario Outline: User redirects to homepage on clicking MarsAir logo
    Given user selects a departing date "<DepartureDate>"
    And user selects a returning date "<ReturnDate>"
    When clicks on search
    And clicks on logo
    Then verify redirect to homepage
    @Sanity
    Examples:
      | DepartureDate | ReturnDate |
      | July          | December (two years from now) |
    Examples:
      | DepartureDate | ReturnDate |
      | July          | July (two years from now) |
      | July          | December |

  @UserStory3
  Scenario Outline: User redirects to homepage on clicking back button for search results
    Given user selects a departing date "<DepartureDate>"
    And user selects a returning date "<ReturnDate>"
    When clicks on search
    And clicks back button
    Then verify redirect to homepage
    @Sanity
    Examples:
      | DepartureDate | ReturnDate |
      | July          | December (two years from now) |
    Examples:
      | DepartureDate | ReturnDate |
      | July          | July (two years from now) |
      | July          | December |

  @UserStory4
  Scenario Outline: Average user searches for invalid flight
    When user selects a departing date "<DepartureDate>"
    And user selects a returning date "<ReturnDate>"
    And clicks on search
    Then show invalid schedule message
    @Sanity
    Examples:
      | DepartureDate | ReturnDate |
      | July          | December |
    Examples:
      | DepartureDate | ReturnDate |
      | December      | December |
      | December      | July     |