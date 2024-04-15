#ifndef Schieber_h
#define Schieber_h

#include "Arduino.h"

class Schieber {

  public:

    Schieber(int lever_pin, int motor_direction_pin, int motor_enabled_pin, int motor_pulse_pin);
    Schieber(int lever_pin, int motor_direction_pin, int motor_enabled_pin, int motor_pulse_pin, int upper_limit, int lower_limit);

    void begin();
    void calibrate();
    void move_to_mm(int mm);
    void move_forwards(int mm);
    void move_backwards(int mm);

  private:

    int _lever_pin;
    int _motor_direction_pin;
    int _motor_enabled_pin;
    int _motor_pulse_pin;

    bool _is_lever_pressed = false;
    bool _is_motor_enabled = false;
    bool _is_linear_calibrated = false;

    int _pos = 0;

    int _upper_limit;
    int _lower_limit;

};

#endif
