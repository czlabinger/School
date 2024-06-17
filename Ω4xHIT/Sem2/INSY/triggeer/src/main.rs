use postgres::{Client, NoTls, Error};
use tabled::{Tabled, Table};
use chrono::NaiveDate;
use rust_decimal::Decimal;

fn main() -> Result<(), Error> {
    let mut client = Client::connect("postgresql://postgres:postgres@localhost/kontodaten", NoTls)?;
    
    client.batch_execute("
        CREATE OR REPLACE FUNCTION calc_statistics() RETURNS VOID AS $$
        DECLARE
            current_date DATE;
            sum_amount DECIMAL;
            avg_amount DECIMAL;
            max_amount DECIMAL;
            taxes DECIMAL;
        BEGIN
            FOR current_date IN SELECT DISTINCT date FROM transfers WHERE date = current_date LOOP
                RAISE NOTICE 'Processing date: %', current_date;

                SELECT SUM(amount) INTO sum_amount FROM transfers WHERE date = current_date;
                SELECT AVG(amount) INTO avg_amount FROM transfers WHERE date = current_date;
                SELECT MAX(amount) INTO max_amount FROM transfers WHERE date = current_date;
                SELECT SUM(amount * CASE WHEN date < '2020-01-01' THEN 0.02 ELSE 0.01 END) INTO taxes FROM transfers WHERE date = current_date;

                RAISE NOTICE 'Sum: %, Avg: %, Max: %, Taxes: %', sum_amount, avg_amount, max_amount, taxes; -- Debugging-Information
                
                INSERT INTO statistics (date, sum_amount, avg_amount, max_amount, taxes)
                VALUES (current_date, sum_amount, avg_amount, max_amount, taxes);
            END LOOP;
        END;
        $$ LANGUAGE plpgsql;
    ")?;

    client.batch_execute("SELECT calc_statistics();")?;

    let rows = client.query("SELECT * FROM statistics", &[]).unwrap();

    // Convert the rows into a vector of your struct
    let data: Vec<Statistics> = rows.iter().map(|row| {
        Statistics {
            date: row.get("date"),
            sum_amount: row.get("sum_amount"),
            avg_amount: row.get("avg_amount"),
            max_amount: row.get("max_amount"),
            taxes: row.get("taxes"),
        }
    }).collect();

    // Print the data as a table
    let table = Table::new(&data);
    println!("{}", table);

    Ok(())
}

#[derive(Tabled)]
pub struct Statistics {
    date: NaiveDate,
    sum_amount: Decimal,
    avg_amount: Decimal,
    max_amount: Decimal,
    taxes: Decimal,
}
