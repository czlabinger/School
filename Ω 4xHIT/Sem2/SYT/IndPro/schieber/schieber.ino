#define CUSTOM
#include "stuppnig_gmbh.h"
#include "EasyCAT.h"
EasyCAT easycat;

const int lever_pin = 4;
// white
const int motor_direction_pin = 3;
// green
const int motor_enabled_pin = 2;
// steps - blue
const int motor_pulse_pin = 10;

bool is_lever_pressed = false;
bool is_motor_enabled = false;
bool is_linear_calibrated = false;

float cm_is_to_drive = 42;

// if motor high -> forward
// else backwards

// 14.5 cm = 1600

// HIGH = calibrating


void setup() {
  easycat.Init();

  pinMode(lever_pin, INPUT_PULLUP);
  pinMode(motor_direction_pin, OUTPUT);
  pinMode(motor_pulse_pin, OUTPUT);
  pinMode(motor_enabled_pin, OUTPUT);
  Serial.begin(9600);
  digitalWrite(motor_enabled_pin, LOW);
  calibrate();
}

void loop() {

  Serial.println(easycat.BufferOut.Cust.lieferPlatine);

  if (easycat.BufferOut.Cust.lieferPlatine == 1) {
    Serial.println("Moving");
    // forward
    digitalWrite(motor_direction_pin, LOW);
    for (int j = 0; j < 1600 / 19.5 * cm_is_to_drive; j++) {
      // These four lines result in 1 step:LOW
      digitalWrite(motor_pulse_pin, HIGH);
      delay(2);
      digitalWrite(motor_pulse_pin, LOW);
      delay(2);
    }
    // When done
    Serial.println("Ready");
    easycat.BufferIn.Cust.platineDa = 1;

    while (easycat.BufferOut.Cust.lieferPlatine != 0) {
      Serial.println("Waiting for roboter");
      easycat.MainTask();
    }

    Serial.println("Moving back");
    easycat.BufferIn.Cust.platineDa = 0;

    calibrate();
  }

  easycat.MainTask();
}

void calibrate() {
  is_lever_pressed = false;
  while (!is_lever_pressed && !is_linear_calibrated) {
    for (int j = 0; j < 1600; j++) {
      digitalWrite(motor_direction_pin, HIGH);
      digitalWrite(motor_pulse_pin, HIGH);
      delay(2);
      digitalWrite(motor_pulse_pin, LOW);
      delay(2);
      is_lever_pressed = !digitalRead(lever_pin);
      if (is_lever_pressed) break;
    }
    is_lever_pressed = !digitalRead(lever_pin);
  }
}