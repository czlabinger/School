var http = require('http');
var url = require('url');
const fs = require('fs');
const { equal } = require('assert');
var json = "";

fs.readFile('/home/stoffi05/Documents/School/3xHIT/Sem2/MEDT/WEB/books.json', 'utf8', (err, data) => {
  if (err) {
    console.error(err);
    return;
  }
  json = JSON.parse(data);
});


http.createServer(function (req, res) {

    res.writeHead(200, {'Content-Type': 'text/plain'});
    var u = url.parse(req.url, true)

    if(req.method == "GET" && u.pathname == "/add"){
      var queryObj = url.parse(req.url, true).query;
      let book = {
        name : queryObj.name,
        autor : queryObj.autor,
        jahr : queryObj.jahr,
        seiten : queryObj.seiten
      }
      json.push(book);

      fs.writeFile('/home/stoffi05/Documents/School/3xHIT/Sem2/MEDT/WEB/books.json', JSON.stringify(json), err => {
        if (err) {
          console.error(err);
        }
      });
    }

    res.end(JSON.stringify(json));

  }).listen(8080); 