"use strict";

/*Convert form data to JSON*/
function formToJSON() {

    const author = document.getElementById('author-textbox').value;
    const title = document.getElementById('title-textbox').value;

    //Get the selected genre's string
    const selElement = document.getElementById('genre-list');
    const genre = selElement.options[selElement.selectedIndex].value;

    const price = document.getElementById('price-textbox').value;
    const book = {"author": author, "title": title, "genre": genre, "price": price};

    return JSON.stringify(book);
}

function submitPostForm() {
    const request = new XMLHttpRequest();

    //If the response takes longer than 4s then abort the request
    request.timeout = 4000;
    request.ontimeout = function () {
        const alertbox = new AlertBox('alert-area', 'Error');
        alertbox.show("Back-end taking too long to respond. Please read a book while our engineers are on top of it :)");
    }

    //If an error occurs during the response then inform the user in a friendly way
    request.onerror = function() {

        const alertbox = new AlertBox('alert-area', 'Error');
        alertbox.show("We're having issues with our back-end. Please read a book while our engineers are on top of it :)");
    };

    request.onreadystatechange = function () {
        //If the book was submitted successfully found then display a success message to the user
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById('addBook-form').reset();
            const alertbox = new AlertBox('alert-area', 'Success');
            alertbox.show("Book successfully submitted!");
        }
        //If the back-end reports an Internal Error then inform the user about the situation
        else if (this.readyState === 4 && this.status === 500) {

            const alertbox = new AlertBox('alert-area', 'Error');
            alertbox.show(JSON.parse(this.responseText).error);
        }
    };

    request.open('POST', 'http://localhost/api/books', true);
    request.setRequestHeader("Content-Type", "application/json");
    request.send(formToJSON());

     //Hold off the form from submitting (We've already done the request)
    return false;
}
