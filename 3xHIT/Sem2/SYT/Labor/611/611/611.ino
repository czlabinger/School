#include <ESP32Servo.h>
#include "DHT.h"

#define TRIGGER 23 
#define ECHO 22
#define SERVO 14
#define dhtPin 5
#define DHTTYPE DHT22

long duration;
float distanceCm;
Servo servo;
DHT dht(dhtPin, DHTTYPE);
float servoOut;

long maxDistance;
long minDistance;

float sound_speed;

void setup() {
  Serial.begin(9600);
  pinMode(TRIGGER, OUTPUT);
  pinMode(ECHO, INPUT);
  pinMode(2, OUTPUT);
  pinMode(dhtPin, INPUT);

  servo.setPeriodHertz(50);
  servo.attach(SERVO, 500, 2400);

  dht.begin();

  float h = dht.readHumidity();

  float t = dht.readTemperature();

  Serial.print(F("Humidity: "));
  Serial.print(h);
  Serial.print(F("%  Temperature: "));
  Serial.print(t);
  Serial.println(F("°C "));

  sound_speed = sqrt(t * (101.325/h));

  //Setup time
  float starttime = millis();
  float endtime = starttime;  

  minDistance = 400;

  digitalWrite(2, HIGH);

  while (endtime - starttime <= 10000) {
    
    digitalWrite(TRIGGER, HIGH);
    delayMicroseconds(10);
    digitalWrite(TRIGGER, LOW);

    duration = pulseIn(ECHO, HIGH);
  
    distanceCm = duration * sound_speed/2; 

    if(distanceCm < minDistance) minDistance = distanceCm;
    if(distanceCm > maxDistance) maxDistance = distanceCm;

    Serial.print("max ");
    Serial.println(maxDistance);

    Serial.print("min ");
    Serial.println(minDistance);

    delay(2000);

    endtime = millis();
  }

  digitalWrite(2, LOW);

}

void loop() {
  //DHT

  float h = dht.readHumidity();

  float t = dht.readTemperature();

  Serial.print(F("Humidity: "));
  Serial.print(h);
  Serial.print(F("%  Temperature: "));
  Serial.print(t);
  Serial.println(F("°C "));

  sound_speed = sqrt(t * (101.325/h));

  //Clear TRIGGER
  digitalWrite(TRIGGER, LOW);
  delay(2);

  //Send
  digitalWrite(TRIGGER, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIGGER, LOW);
  
  //read
  duration = pulseIn(ECHO, HIGH);
  
  distanceCm = duration * sound_speed/2;

  long mapped = map(distanceCm, 0, 400, minDistance, maxDistance);

  if(minDistance < 0 || mapped > maxDistance) Serial.println("Values outside of Configuration");
  else Serial.println(mapped);
  
  //Servo

  Serial.print("Distance (cm): ");
  Serial.println(distanceCm);

  servoOut = map(distanceCm, 0, 400, 0, 180);

  servo.write(servoOut);

  delay(5000);
}
