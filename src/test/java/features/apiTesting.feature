Feature: Bookstore API Operations
#  mvn clean test -Dcucumber.features=src/test/java/features/apiTesting.feature -Dcucumber.filter.tags="@HealthCheck"

  @HealthCheck
  Scenario: Validate health check endpoint
    Given I send a GET request to "/health"
    Then the response status should be 200

    @Signup
  Scenario: User Signup
    Given I send a POST request to "/signup"
    Then the response status should be 200

    @Login
  Scenario: User Login
    Given I send a POST request to "/login"
    Then the response status should be 200
    And I save the access token
    And I send a POST request to "/books/" with name "Test Book", author "Tester", published_year 2025, and book_summary "Test summary."
    Then the response status should be 200
    And I save the book ID

  @Post
  Scenario: Create a new book
    Given I send a POST request to "/books/" with name "Test Book", author "Tester", published_year 2025, and book_summary "Test summary."
    Then the response status should be 200
    And I save the book ID

  @Get
  Scenario: Retrieve book by ID
    Given I send a POST request to "/login"
    Then the response status should be 200
    And I save the access token
    And I send a GET request to "/books/1" with authorization
    Then the response status should be 200

  @Put
  Scenario: Update book details
    Given I send a POST request to "/login"
    Then the response status should be 200
    And I save the access token
    And I send a PUT request to "/books/1" with new name "Updated new Book", author "Updated new Author", published_year 2026, and book_summary "Updated new content."
    Then the response status should be 200

  @delete
  Scenario: Delete book
    Given I send a POST request to "/login"
    Then the response status should be 200
    And I save the access token
    And I send a DELETE request to "/books/1" with authorization
    Then the response status should be 200