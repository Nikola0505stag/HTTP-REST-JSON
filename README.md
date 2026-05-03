## Exercise 1: The Basic Fetcher (Warm-up)
# Goal: Create a simple GET request and print the status code and body.

# Task: Write a Java method fetchData(String url) that uses HttpClient to send a GET request to a given URL.

# Requirement: Print the Status Code and the Body as a String.

# Bonus: Use HttpRequest.newBuilder() to set a timeout of 1 minute.

---

## Exercise 2: The JSON Parser (Intermediate)
# Goal: Map a JSON response to a Java Object using Gson.

# Task: You receive the following JSON from an API: {"name": "Java Expert", "score": 95}.

# Requirement:

# Create a Java class named Candidate that matches this structure.

# Use gson.fromJson() to convert the string into a Candidate object.

# Print: "Candidate [Name] has a score of [Score]" using the object's getter methods.

---

## Exercise 3: The HackerRank "Total Goals" Challenge (Advanced)
# Goal: Handle multiple pages and perform logic. This is the most common type of interview task.

# Scenario: You need to find the total number of goals scored by a team. The API returns data in this format:


```JSON
{
  "page": 1,
  "total_pages": 2,
  "data": [
    {"team": "Barcelona", "goals": 2},
    {"team": "Barcelona", "goals": 3}
  ]
}

```

# Task: Write a method getTotalGoals(String teamName).

# Logic:

Send a GET request to the API.

Parse the response into a Java object (you'll need a class for the "Result" and a class for the "MatchData").

Loop through the data array and sum the goals.

# Crucial: Check if page < total_pages. If it is, send another request for the next page and add those goals too.

Pro-Tips for Tomorrow's Interview:
Check for Nulls: APIs often return null or missing fields. If you use Gson, it will simply leave the Java field as null—make sure your code doesn't crash!

# Use the Builder: Always use HttpClient.newBuilder() or HttpRequest.newBuilder(). It shows the interviewer you understand the modern Java 11+ syntax.

# Exception Handling: Wrap your .send() call in a try-catch block. Network calls fail often (404, 500, or no internet), and handling that gracefully is a "Senior" move.

# Version Downgrade: Remember that the client defaults to HTTP/2, but it will automatically "downgrade" to HTTP/1.1 if the server is old.
