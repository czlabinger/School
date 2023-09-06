#include <ESP32Servo.h>

#define SERVO 14
#define TRIGGER 23
#define ECHO 22

long maxDistance = 0;
long minDistance = 1000;
Servo servo;
float servoOut;

void setup() {
  Serial.begin(9600);
  pinMode(TRIGGER, OUTPUT);
  pinMode(ECHO, INPUT);
  pinMode(2, OUTPUT);
  
  digitalWrite(2, HIGH);
  
  for(int i = 0; i < 10; i++) {
    Serial.print("Calibrating..");
    
    //max
    digitalWrite(TRIGGER, LOW);
    delayMicroseconds(2);
    digitalWrite(TRIGGER, HIGH);
    delayMicroseconds(10);
    digitalWrite(TRIGGER, LOW);
    
    long duration = pulseIn(ECHO, HIGH);
    long distance = duration * 0.034 / 2;
    
    if(distance > maxDistance && distance <= 100) {
      maxDistance = distance;
    }
    
    //min
    digitalWrite(TRIGGER, LOW);
    delayMicroseconds(2);
    digitalWrite(TRIGGER, HIGH);
    delayMicroseconds(10);
    digitalWrite(TRIGGER, LOW);
    
    duration = pulseIn(ECHO, HIGH);
    distance = duration * 0.034 / 2;
    
    if(distance < minDistance && distance >= 4) {
      minDistance = distance;
    }
    
    Serial.print("Max distance: ");
    Serial.print(maxDistance);
    Serial.print(", Min distance: ");
    Serial.println(minDistance);
    
    delay(1000);
  }
  
  servo.setPeriodHertz(50);
  servo.attach(SERVO, 500, 2400);

  digitalWrite(2, LOW);
}

void loop() {
  digitalWrite(TRIGGER, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIGGER, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIGGER, LOW);
  
  long duration = pulseIn(ECHO, HIGH);
  long distance = duration * 0.034 / 2;
  
  Serial.print("Distance: ");
  
  if(distance > maxDistance){
    distance = maxDistance;
  }

  if(distance < minDistance){
    distance = minDistance;
  }

  Serial.println(distance);
  
  servoOut = map(distance, minDistance, maxDistance, 0, 180);

  Serial.print("Servo: ");
  Serial.println(servoOut);

  servo.write(servoOut);

  delay(100);
}
