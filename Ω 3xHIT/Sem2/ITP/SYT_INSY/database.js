const {createPool} = require('mysql');

const pool = createPool ({
    host: "192.168.0.11",
    user: "loggeruser",
    password: "secure",
    database: "DataLogger",
    connectionLimit: 10
});

/**
 * Alle Daten
 * err: Error
 * res: Result
 * fields: columns der Table
 */
pool.query('SELECT * FROM Datensammlung', (err, res, fields) => {
    if(err){
        return console.log(err);
    }
    return console.log(res);
})

/**
 * Max temp
 */
pool.query('SELECT sensor, MAX(wert) FROM Datensammlung ', (err, res, fields) => {
    if(err){
        return console.log(err);
    }
    return console.log(res);
})


var time = new Date;
console.log(time)
var minute = 1, interval = minute * 60 * 1000;
setInterval(function() {
    minute = pareseInt(time.toString().charAt(17) + time.toString().charAt(18));
    pool.query('SELECT AVG(wert) FROM Datensammlung WHERE', (err, res, fields) => {
        if(err){
            return console.log(err);
        }
        times = res;
    })
}, interval)