"use strict";

/*Function that generates a table which contains the results*/
function generateTable(jsonArray) {

    //Create a new div element and apply the form-style style
    const tableDiv = document.createElement("div");
    tableDiv.setAttribute("id", "get-result");
    tableDiv.setAttribute("class", "form-style");

    //Create a new table element and apply the custom-table style
    const table = document.createElement("table");
    table.setAttribute("id", "matched-books-table");
    table.setAttribute("class", "custom-table");

    //Create the column headers
    const headerContainer = document.createElement("tr");
    const columnArray = ["id", "author", "title", "genre", "price"];
    columnArray.forEach((currentValue) => {
        const header = document.createElement("th");
        //Capitalize the first letter for the user display
        header.innerText = currentValue.charAt(0).toUpperCase() + currentValue.slice(1);
        headerContainer.appendChild(header);
    })

    table.appendChild(headerContainer);

    //Append each book to the table
    jsonArray.forEach((currentValue) => {
        const row = document.createElement("tr");
        for (let attribute in currentValue) {
            const col = document.createElement("td");
            col.innerText = currentValue[attribute];
            row.appendChild(col);
        }

        table.appendChild(row);
    })

    tableDiv.appendChild(table);

    return tableDiv;
}

function submitGetForm() {

    const keyword = document.getElementById('title-find-textbox').value;

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

        //If book(s) were found then generate the table and display the modal
        if (this.readyState === 4 && this.status === 200) {
            const jsonArray = JSON.parse(this.responseText)
            const table = generateTable(jsonArray);

            document.getElementById("resultsContent").innerHTML = table.innerHTML;
            document.getElementById("resultsModal").style.display = "block";
        }
        //If no books were found then display the modal with a "No Results Found" message
        else if (this.readyState === 4 && this.status === 404) {

            //Create a paragraph to append to the modal body
            const p = document.createElement('p');
            p.setAttribute('style','text-align:center;font-size:160%;');
            p.innerText = "No results found";

            document.getElementById("resultsContent").innerHTML = p.outerHTML;
            document.getElementById("resultsModal").style.display = "block";
        }
        //If the back-end reports an Internal Error then inform the user about the situation
        else if (this.readyState === 4 && this.status === 500) {
            const alertbox = new AlertBox('alert-area','Error');
            alertbox.show(JSON.parse(this.responseText).error);
        }
    }

    request.open('GET', 'http://localhost/api/books/' + keyword);
    request.send();

    //Hold off the form from submitting (We've already done the request)
    return false;
}
