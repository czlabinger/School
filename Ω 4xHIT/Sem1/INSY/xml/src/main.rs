use std::fs::File;
use std::io::BufReader;
use xml::reader::{EventReader, XmlEvent};

fn main() {
    read_xml_file("./bookstore.xml").expect("A panic in read_xml_file");
}

fn read_xml_file(file_path: &str) -> Result<(), Box<dyn std::error::Error>> {
    let file:File = File::open(file_path)?;
    let file_reader:BufReader<File> = BufReader::new(file);
    let parser:EventReader<BufReader<File>> = EventReader::new(file_reader);

    for event in parser {
        match event {
            Ok(XmlEvent::StartElement { name, .. }) => {
                println!("Start element: {}", name);
            }

            Ok(XmlEvent::EndElement { name }) => {
                println!("End element: {}", name);
            }

            Ok(XmlEvent::Characters(text)) => {
                println!("Text: {}", text);
            }

            Err(e) => {
                println!("Error: {}", e);
            }
            _ => {}
        }
    }

    Ok(())
}

