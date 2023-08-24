const mysql = require('mysql');
const express = require('express');
const app = express();
const port = 3000;
const fs = require('fs');
const path = require('path');

// 정적 파일 제공 설정
app.use('/', express.static(path.join(__dirname, 'public'))); // 'public' 폴더에 프론트엔드 파일을 저장합니다.

const absolutePath = path.resolve("/home/bjm1263115/mysql/DigiCertGlobalRootCA.crt.pem");

var connection = mysql.createConnection({
    host: "farmserver.mysql.database.azure.com",
    user: "user1",
    password: "park!258412",
    database: "farm",
    port: 3306,
    ssl: { ca: fs.readFileSync(absolutePath) }
});

connection.connect(error => {
    if (error) throw error;
    console.log('Connected to the database');
});

app.get('/data', (req, res) => {
    var sql = 'SELECT * FROM sensor';
    connection.query(sql, (error, rows, fields) => {
        if (error) {
            console.error('Error fetching data:', error);
            res.status(500).send('Error fetching data');
            return;
        }
        res.json(rows);
    });
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}/`);
});
