# Library Management Application

## Types of Objects:

1. **Book** - Class used to store information about books.

2. **User** - Information about the reader who is going to borrow books.

3. **Reservation** - Class used to keep information about a potential reservation of one or more books by a reader.

4. **Loan** - Class that retains information about the loan of one or more books by a user.

5. **Publisher** - Class used to store information about a publisher that publishes books.

6. **Author** - Class used to store information about a specific book author.

7. **Library Card** - Class that encapsulates a user's information and grants them the right to borrow and reserve books.

8. **Manual** - Class that stores information about an educational book (inherited from the Book class).

9. **Magazine** - Class that stores information about a commercial book (inherited from the Book class).

## Actions/Queries:

1. **Add Book to Library**: With this option, users can add new books to the library.

2. **Display Books in the Library**: With this option, users can view all existing books in the library, along with information such as title, author, publisher, and year of publication.

3. **Check if a Book is in the Library**: With this option, users can check if a specific book is in the library. The user must enter the name of the book.

4. **Add User Account**: With this option, the librarian can create a user account in the library application.

5. **Create Library Card for a User**: With this option, the librarian can create a library card for a user. To create a card, the librarian must enter the user's phone number.

6. **Reserve a Book**: With this option, the librarian can reserve a specific book for a user. The librarian must enter the name of the book to make the reservation.

7. **Borrow a Book**: With this option, the librarian can loan a specific book from the library to a user. The librarian must enter the name of the book to borrow it.

8. **Display a User's Reservation List**: With this option, users can view the list of reservations they have made. The librarian must enter the phone number to see the reservation list.

9. **Display a User's Loan History**: With this option, users can view their loan history. The librarian must enter the phone number to see the history.

10. **Display All Users**: With this option, the librarian can view a list of all users registered in the application.

11. **Find All Books by an Author**: With this option, users can find all books written by a specific author. The librarian must enter the author's name to find the books.

12. **Find All Books from a Specific Publisher**: With this option, users can find all books published by a specific publisher. The librarian must enter the publisher's name to find the books.

13. **Exit**: With this option, librarians can exit the application.

The application is designed from the perspective of a librarian, and users are readers who authenticate with their phone number.
