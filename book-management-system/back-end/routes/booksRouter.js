const express = require('express');
const router = express.Router();

const bookService = require('../services/booksService');

/* GET books that match the keyword. */
router.get('/:keyword', async (req, res) => {

    try {
        const response = await bookService.getBooksByKeyword(req.params.keyword);
        if(response.length > 0)
            res.status(200).json(response);
        else
            res.status(404).json({message: 'Could not find any books'});
    } catch (error) {
        res.status(500).json({error: 'Our back-end currently experiences some issues, please try using our services later'});
    }

});

/* POST a new book. */
router.post('/', async (req, res) => {

    const book = req.body;

    try {
        const response = await bookService.postBook(book);
        res.status(200).json(response);
    } catch (error) {
        res.status(500).json({error: 'Our back-end currently experiences some issues, please try using our services later'});
    }
})

module.exports = router;
