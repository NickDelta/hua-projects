const express = require('express');
const path = require('path');
const logger = require('morgan');
const bodyLogger = require('morgan-body');
const booksRouter = require('./routes/booksRouter');
const bodyParser = require('body-parser');
const cors = require('cors')
const app = express();
require('dotenv').config()

app.use(logger('combined'));
bodyLogger(app , {
    noColors: true,
    logReqUserAgent: true,
    timezone: "Europe/Athens"
});
app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.json());
app.use(cors());

app.use('/books', booksRouter);

app.listen(process.env.SERVER_PORT || 4000);
console.log('Listening on port ' + process.env.SERVER_PORT);

module.exports.app = app;
