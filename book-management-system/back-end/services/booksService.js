const dbPool = require('../db_config');

function getBooksByKeyword(keyword) {

    return new Promise((resolve, reject) => {
        dbPool.getConnection((err, connection) => {

            if (err) {
                console.error('Error: ' + err.message);
                return reject(err);
            }

            // Use the connection
            connection.query({
                sql: 'SELECT * FROM books WHERE title LIKE ?',
                values: ['%' + keyword + '%'] //match any title that contains the keyword in any part of it
            }, (error, results) => {

                // When done with the connection, release it.
                connection.release();
                // Handle error after the release.
                if (error) {
                    console.error('Error: ' + error.message);
                    return reject(error);
                }

                resolve(results);

            });
        })

    });
}

function postBook(book) {
    return new Promise((resolve, reject) => {

        dbPool.getConnection((err, connection) => {

            if (err) {
                console.error('Error: ' + err.message);
                return reject(err);
            }

            // Use the connection
            connection.query('INSERT INTO books SET ?', book, (error) => {

                // When done with the connection, release it.
                connection.release();
                // Handle error after the release.
                if (error) {
                    console.error('Error: ' + error.message);
                    return reject(error);
                }

                resolve({message: "Book successfully inserted!"});

            });
        })
    })
}

module.exports.getBooksByKeyword = getBooksByKeyword;
module.exports.postBook = postBook;