# new feature
# Tags: optional
    
Feature: Basic operations
Test calculator with basic operations
    
Scenario: Perform basic operations
    Given user navigated to the online calculator
    When user performs the following operations the calculator must display the results
    | firstValue | operator | secondValue | result |
    | 130        | +        | 257         | 387    |
    | 319        | -        | 257         | 62     |
    | 3.14       | *        | 10          | 31.4   |
    | 1291       | /        | 100         | 12.91  |