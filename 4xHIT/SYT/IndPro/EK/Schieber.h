#ifndef Schieber_h
#define Schieber_h

#include "Arduino.h"

class Schieber {

  public:

    Schieber(int lever_pin, int motor_direction_pin, int motor_enabled_pin, int motor_pulse_pin, float cm_is_to_drive);

    void calibrate();
    void move_cm(int cm);

  private:

    int _lever_pin;
    int _motor_direction_pin;
    int _motor_enabled_pin;
    int _motor_pulse_pin;

    bool _is_lever_pressed = false;
    bool _is_motor_enabled = false;
    bool _is_linear_calibrated = false;

    float _cm_is_to_drive = 42;

};

#endif