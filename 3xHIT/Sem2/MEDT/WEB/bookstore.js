var http = require('http')
var url = require('url')
var fs = require('fs')

var list = read()
console.log(list)

var count = list.length -1
var book

http.createServer(function (req, res) {
    const headers = {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "OPTIONS, POST, GET, DELETE, PUT",
      "Access-Control-Max-Age": 2592000,
      'Content-Type': 'text/json'
    };
    res.writeHead(200, headers);
    var u = url.parse(req.url, true)

    if(req.method == 'POST' || u.pathname == '/add'){
      count++;
      book = {id: count, name: u.query.name, author: u.query.author, year: u.query.year, pages: u.query.pages}
      list.push(book)
    }

    if(req.method == 'DELETE' || u.pathname == '/del'){
      list.splice(u.query.id)
      count--
    }

    if(req.method == 'PUT' || u.pathname == '/update'){
      book = {id: count, name: u.query.name, author: u.query.author, year: u.query.year, pages: u.query.pages}
      list[u.query.id] = book
      list[u.query.id].id = u.query.id
    }

    res.write(JSON.stringify(list))
    res.end()

    fs.writeFileSync("books.json", JSON.stringify(list))

  }).listen(8080);

function read(){
  return JSON.parse(fs.readFileSync('books.json'));
}