class Schieber {

  public:

    Schieber(int lever_pin, int motor_direction_pin, );

    void begin();

    void dot();

    void dash();

  private:

    int _pin;

};