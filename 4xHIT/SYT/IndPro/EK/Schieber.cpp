#include "Arduino.h"
#include "Schieber.h"

Schieber:Schieber(int lever_pin, int motor_direction_pin, int motor_enabled_pin, int motor_pulse_pin, float cm_is_to_drive) {
    _lever_pin = lever_pin;
    _motor_direction_pin = motor_direction_pin;
    _motor_enabled_pin = motor_enabled_pin;
    _motor_pulse_pin = motor_pulse_pin;

    _cm_is_to_drive = cm_is_to_drive;
}

void Schieber::calibrate() {
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

void Schieber::move_cm(int cm) {
   digitalWrite(motor_direction_pin, LOW);
    for (int j = 0; j < 1600 / 19.5 * cm_is_to_drive; j++) {
      // These four lines result in 1 step:LOW
      digitalWrite(motor_pulse_pin, HIGH);
      delay(2);
      digitalWrite(motor_pulse_pin, LOW);
      delay(2);
    }
}