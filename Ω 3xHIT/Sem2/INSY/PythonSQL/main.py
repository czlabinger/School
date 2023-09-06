import mariadb
import sys

try:
    conn = mariadb.connect(
        user="root",
        password="example",
        host="127.0.0.1",
        port=3306,
        database="test"
    )

except mariadb.Error as e:
    print(f"Error connecting to MariaDB Platform: {e}")
    sys.exit(1)

cur = conn.cursor()

vorname, nachname = input("Vor- und Nachname eingeben (Mit Space getrennt): ").split(" ")

cur.execute("INSERT INTO test (vorname, nachname) VALUES (?, ?);", (vorname, nachname))

cur.execute("SELECT * FROM test")

conn.commit()

for row in cur:
    print(row)
