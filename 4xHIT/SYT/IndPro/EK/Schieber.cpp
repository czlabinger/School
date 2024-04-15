#include "Arduino.h"
#include "Schieber.h"

Schieber::Schieber(int lever_pin, int motor_direction_pin, int motor_enabled_pin, int motor_pulse_pin) {
    _lever_pin = lever_pin;
    _motor_direction_pin = motor_direction_pin;
    _motor_enabled_pin = motor_enabled_pin;
    _motor_pulse_pin = motor_pulse_pin;

    _upper_limit = 450;
    _lower_limit = 0;
}

Schieber::Schieber(int lever_pin, int motor_direction_pin, int motor_enabled_pin, int motor_pulse_pin, int upper_limit, int lower_limit){
    _lever_pin = lever_pin;
    _motor_direction_pin = motor_direction_pin;
    _motor_enabled_pin = motor_enabled_pin;
    _motor_pulse_pin = motor_pulse_pin;

    _upper_limit = upper_limit;
    _lower_limit = lower_limit;
    
}

void Schieber::begin() {
    pinMode(_lever_pin, INPUT_PULLUP);
    pinMode(_motor_direction_pin, OUTPUT);
    pinMode(_motor_pulse_pin, OUTPUT);
    pinMode(_motor_enabled_pin, OUTPUT);
    digitalWrite(_motor_enabled_pin, LOW);
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
  _pos = 0;
}

void Schieber::move_to_mm(int mm) {
  bool motor_direction = LOW;
  if(_pos > mm) motor_direction = HIGH;
   digitalWrite(_motor_direction_pin, motor_direction);

   int mm_to_actually_move = mm - _pos;
   if(_pos > mm) mm_to_actually_move = _pos - mm;
   
   int steps_to_drive = 1600 / 19.5 * mm_to_actually_move / 10;
    for (int j = 0; j < steps_to_drive; j++) {
      // These four lines result in 1 step:LOW
      digitalWrite(_motor_pulse_pin, HIGH);
      delay(2);
      digitalWrite(_motor_pulse_pin, LOW);
      delay(2);
    }
    _pos = mm;
}

void Schieber::move_forwards(int mm) {
   int pos_to_move = _pos + mm;
  if(pos_to_move > _upper_limit) pos_to_move = _upper_limit;
  Serial.println(pos_to_move);
  move_to_mm(pos_to_move);
}

void Schieber::move_backwards(int mm) {
  int pos_to_move = _pos - mm;
  if(pos_to_move < _lower_limit) pos_to_move = _lower_limit;
  Serial.println(pos_to_move);
  move_to_mm(pos_to_move);
}
