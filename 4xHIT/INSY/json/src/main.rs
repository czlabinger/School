use std::fs::File;
use std::io::Read;
use serde_json::Value;

fn main() {
    read_json_file("./bookstore.json").expect("A panic");
}

fn read_json_file(path:&str) -> Result<(), Box<dyn std::error::Error>> {
    let mut file = File::open(path)?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;

    let json: Value = serde_json::from_str(&contents)?;

    if let Some(value) = json.get("bookstore") {
        println!("Value of bookstore: {}", value)
    }

    Ok(())
}
