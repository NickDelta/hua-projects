require('dotenv').config()

const mysql = require('mysql');
const pool = mysql.createPool({
    connectionLimit: 10,
    host: process.env.DB_HOST,
    port: process.env.DB_PORT,
    user: process.env.DB_USER,
    password: process.env.DB_PASS,
    database: process.env.DB_SCHEMA
});

module.exports = pool;