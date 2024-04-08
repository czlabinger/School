#include "Arduino.h"
#include "Schieber.h"

Schieber::Schieber(int lever_pin, int motor_direction_pin, int motor_enabled_pin, int motor_pulse_pin, float cm_is_to_drive) {
    _lever_pin = lever_pin;
    _motor_direction_pin = motor_direction_pin;
    _motor_enabled_pin = motor_enabled_pin;
    _motor_pulse_pin = motor_pulse_pin;

    _cm_is_to_drive = cm_is_to_drive;

    pinMode(lever_pin, INPUT_PULLUP);
    pinMode(motor_direction_pin, OUTPUT);
    pinMode(motor_pulse_pin, OUTPUT);
    pinMode(motor_enabled_pin, OUTPUT);
    digitalWrite(motor_enabled_pin, LOW);
}

void Schieber::calibrate() {
  _is_lever_pressed = false;
  while (!_is_lever_pressed && !_is_linear_calibrated) {
    for (int j = 0; j < 1600; j++) {
      digitalWrite(_motor_direction_pin, HIGH);
      digitalWrite(_motor_pulse_pin, HIGH);
      delay(2);
      digitalWrite(_motor_pulse_pin, LOW);
      delay(2);
      _is_lever_pressed = !digitalRead(_lever_pin);
      if (_is_lever_pressed) break;
    }
    _is_lever_pressed = !digitalRead(_lever_pin);
  }
}

void Schieber::move_cm(int cm) {
   digitalWrite(_motor_direction_pin, LOW);
    for (int j = 0; j < 1600 / 19.5 * _cm_is_to_drive; j++) {
      // These four lines result in 1 step:LOW
      digitalWrite(_motor_pulse_pin, HIGH);
      delay(2);
      digitalWrite(_motor_pulse_pin, LOW);
      delay(2);
    }
}
