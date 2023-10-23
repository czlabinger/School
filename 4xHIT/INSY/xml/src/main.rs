use std::env;
use std::fs::File;
use std::io::BufReader;
use xml::EventWriter;
use xml::writer::EmitterConfig;
use xml::reader::{EventReader, XmlEvent};

fn main() {
    let args: Vec<String> = env::args().collect();
    if args[1].eq("read") {
        read_xml_file("./bookstore.xml").expect("A panic in read_xml_file");
    } else if args[1].eq("write") {
        if args[2].eq("") {
            panic!("No content to write")
        }
        write_xml_file("./bookstore2.xml", &args[2]).expect("Panic message");
    }
}

fn read_xml_file(file_path: &str) -> Result<(), Box<dyn std::error::Error>> {
    let file:File = File::open(file_path)?;                                          //Open the file
    let file_reader:BufReader<File> = BufReader::new(file);                    //Open a reader on the file
    let parser:EventReader<BufReader<File>> = EventReader::new(file_reader);  //Open a xml parser on the file

    for event in parser {                                            //For every element in the parsed xml file
        match event { //Switch
            Ok(XmlEvent::StartElement { name, .. }) => {                 //If current element is a start tag
                println!("Start element: {}", name);                                 //Print the tag
            }

            Ok(XmlEvent::EndElement { name }) => {                       //If it is a end tag
                println!("End element: {}", name);                                   //Print the tag
            }

            Ok(XmlEvent::Characters(text)) => {                               //If it is Text
                println!("Text: {}", text);                                          //Print the text
            }

            Err(e) => {                                                        //If panic
                println!("Error: {}", e);                                            //Print the panic message
            }
            _ => {}                                                                  //If something else
        }
    }

    Ok(())                                                                           //Return OK
}

fn write_xml_file(file_path: &str, content: &str) -> Result<(), Box<dyn std::error::Error>> {
    let file = File::create("output.xml")?;


}