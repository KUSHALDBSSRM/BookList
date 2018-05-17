# BookList
For this project, we will be creating a book listing android app. A user should be able to enter a keyword, press the search button, and recieve a list of published books which relate to that keyword.

To achieve this, we will make use of the Google Books API. This is a well-maintained API which returns information in a JSON format.

We suggest first exploring the API and learning what information it returns given a particular query. An example query that we found useful was

  https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1
Once you've explored the API, begin work in Android Studio. We have created a simple layout initially, with an editable TextView and a 'search' button.

Then, we will build the AsyncTask that queries the API. This is a complex step, so be sure to reference the course materials when needed.

Once we have queried the API, we will parse the result. This will involve storing the information returned by the API in a custom class.

Finally, we will use the List and Adapter pattern to populate a list on the user's screen with the information stored in the custom objects you wrote earlier.
