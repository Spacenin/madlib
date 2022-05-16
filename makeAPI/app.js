//Setup dependent modules
var express = require("express");
var mysql = require("mysql2");
var fs = require("fs");

//Create needed connections
var app = express();
var port = 3000;

//Read secrets file for stuff
var data = fs.readFileSync("secrets.json");
var jsonData = JSON.parse(data);
var dbpass = jsonData["dbpass"];
var dbuser = jsonData["dbuser"];

var conn = mysql.createConnection({
    host:"localhost",
    user:dbuser,
    password:dbpass,
    database:"data1"
});

function errored(err) {
    if (err) throw err;
}

//Setup colors API call
app.get("/colors", (req, res) => {
    console.log("Got a get on colors!");

    //Read colors json file and print to screen
    fs.readFile("colorsEx.json", (err, data) => {
        errored(err);

        res.send(JSON.parse(data));
    });
});

//Setup words API call
app.get("/words", (req, res) => {
    console.log("Got a get on words!");

    //Connect and select query
    conn.connect((err) => {
        errored(err);

        conn.query("SELECT wordType, word FROM words", (err, result, fields) => {
            errored(err);

            res.send(result);
        });
    });
});

//Setup nouns API call
app.get("/words/nouns", (req, res) => {
    console.log("Got a get on nouns!");

    //Connect and select query
    conn.connect((err) => {
        errored(err);

        conn.query("SELECT word FROM words WHERE wordType = 'noun'", (err, result, fields) => {
            errored(err);

            res.send(result);
        });
    });
});

//Setup adjectives API call
app.get("/words/adjs", (req, res) => {
    console.log("Got a get on adjs!");

    //Connect and select query
    conn.connect((err) => {
        errored(err);

        conn.query("SELECT word FROM words WHERE wordType = 'adj'", (err, result, fields) => {
            errored(err);

            res.send(result);
        });
    });
});

//Setup verbs API call
app.get("/words/verbs", (req, res) => {
    console.log("Got a get on verbs!");

    //Connect and select query
    conn.connect((err) => {
        errored(err);

        conn.query("SELECT word FROM words WHERE wordType = 'verb'", (err, result, fields) => {
            errored(err);

            res.send(result);
        });
    });
});

//Listen on port
var server = app.listen(port);