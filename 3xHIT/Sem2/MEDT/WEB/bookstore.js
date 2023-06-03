var http = require('http');
var url = require('url');

let books = [];

let nextID = 1;

function addBuch(name, autor, jahr, seiten){
    let book = {
        ID : nextID,
        Name : name,
        Autor : autor,
        Jahr : jahr,
        Seiten : seiten
    };
    books.push(book);
    nextID++;
}

function toJSON() {
    return JSON.stringify(books);
}

function deleteBuch(id){
    books = books.filter(function(book) {
        return book.ID != id;
    });
}

function updateBook(query, res) {
    const bookId = parseInt(query.id);
    const bookIndex = books.findIndex(book => book.id === bookId);
    if (bookIndex !== 0) {
      const updatedBook = {
        id: bookId,
        name: query.name,
        autor: query.autor,
        jahr: query.jahr,
        seitenanzahl: query.seiten
      };
      books[bookIndex] = updatedBook;
      res.end(toJSON());
    }else{
        res.end("Not a valid book ID");
    }
  }


http.createServer(function (req, res) {
    const { pathname, query } = url.parse(req.url, true);

    if (req.method === 'GET' && req.url === '/') {
        res.writeHead(200, {'Content-Type': 'text/plain'});   
        res.end(toJSON());

      } else if (req.method === 'GET' && req.url.startsWith('/add')) {
        var queryObject = url.parse(req.url, true).query;
        var name = queryObject.name;
        var author = queryObject.autor;
        var year = queryObject.jahr;
        var pages = queryObject.seiten;
        addBuch(name, author, year, pages);
        res.writeHead(200, {'Content-Type': 'text/plain'});
        res.end(toJSON());

      } else if (req.method === 'GET' && req.url.startsWith('/del')) {
        const queryObject = url.parse(req.url, true).query;
        const id = queryObject.id;
        deleteBuch(id);
        res.end(toJSON());

      }else if (req.method === 'GET' && req.url.startsWith('/update')) {
        updateBook(query, res);
      }else {
        res.writeHead(404, {'Content-Type': 'text/plain'});
        res.end('404 Errorr Not Found');
      }
}).listen(8080); 