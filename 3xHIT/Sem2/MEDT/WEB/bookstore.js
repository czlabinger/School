 var http = require('http');
var url = require('url');

http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/plain'});
    var u = url.parse(req.url, true)
    console.log(u.query)
    console.log(u.pathname)
    res.end('Hello World!');
  }).listen(8080); 
