#include "Schieber.h"

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

Schieber schieber(lever_pin, motor_direction_pin, motor_enabled_pin, motor_pulse_pin, 420, 0);

void setup() {

  Serial.begin(9600);
  schieber.begin();
  schieber.calibrate();
}

void loop() {

    //schieber.move_to_mm(10);
    schieber.move_forwards(10);
    delay(2000);
    schieber.move_forwards(20);
    delay(2000);
    schieber.move_forwards(30);
    delay(2000);
    schieber.move_backwards(70);
    delay(2000);
    schieber.move_forwards(1000);
    delay(2000);
    

}
