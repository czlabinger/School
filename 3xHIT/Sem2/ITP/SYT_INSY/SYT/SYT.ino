#include <WiFi.h>
#include <MySQL_Connection.h>
#include <MySQL_Cursor.h>

byte mac_addr[] = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF};

IPAddress server_addr(192, 168, 0, 11);
char user[] = "loggeruser";
char password[] = "secure";

char INSERT_SQL[255];

char ssid[] = "WLAN_STROHMER";
char passwd[] = "TGM123456";

WiFiClient client;
MySQL_Connection conn(&client);
MySQL_Cursor* cursor;

void setup() {
  Serial.begin(115200);
  while (!Serial);

  Serial.printf("\nConnecting to %s", ssid);
  WiFi.begin(ssid, passwd);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("\nConnected to network");
  Serial.print("My IP address is: ");
  Serial.println(WiFi.localIP());

  Serial.print("Connecting to SQL...  ");
  if (conn.connect(server_addr, 3306, user, password))
    Serial.println("OK.");
  else
    Serial.println("FAILED.");
  
  cursor = new MySQL_Cursor(&conn);
}

void loop() {
  // put your main code here, to run repeatedly:

}
