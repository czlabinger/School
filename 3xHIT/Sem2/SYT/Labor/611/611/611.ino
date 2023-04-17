#include <ESP32Servo.h>

#define TRIGGER 23
#define ECHO 22
#define SOUND_SPEED 0.034
#define SERVO 14

long duration;
float distanceCm;
Servo servo;
float servoOut;

void setup() {
  Serial.begin(9600);
  pinMode(TRIGGER, OUTPUT);
  pinMode(ECHO, INPUT);

  servo.setPeriodHertz(50);
  servo.attach(SERVO, 500, 2400);
}

void loop() {
  //Clear TRIGGER
  digitalWrite(TRIGGER, LOW);
  delay(2);

  //Send
  digitalWrite(TRIGGER, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIGGER, LOW);
  
  //read
  duration = pulseIn(ECHO, HIGH);
  
  distanceCm = duration * SOUND_SPEED/2;
  
  Serial.print("Distance (cm): ");
  Serial.println(distanceCm);

  servoOut = map(distanceCm, 0, 400, 0, 180);
y
  Serial.println(servoOut);

  servo.write(servoOut);

  delay(200);
}
