use std::fs::File;
use std::io::Read;
use json::{JsonValue, parse};

fn main() {
    read_json_file("./bookstore.json").expect("A panic");
}

fn read_json_file(path:&str) -> Result<(), Box<dyn std::error::Error>> {
    let mut file = File::open(path)?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;

    let json: JsonValue = parse(&contents).unwrap();

    let books = &json["bookstore"]["book"];

    for book in books.members() {
        let title = &book["title"];
        let author = &book["author"];
        let year = &book["year"];
        let price = &book["price"];

        println!("Title: {}, Author: {}, Year: {}, Price: {}", title, author, year, price);
    }

    Ok(())
}
