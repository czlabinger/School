#define CUSTOM
#include "setup.h"

#include <EasyCAT.h>
EasyCAT easycat;

#include <Keypad.h>

bool send_done_received = false;

//KeyPad Setup

const byte KEYPAD_ROWS = 4;

const byte KEYPAD_COLS = 4;

//Das sind die ersten vier Pins (von links gezaehlt) vom KeyPad

byte rowPins[KEYPAD_ROWS] = { 2, 3,
                              4, 5 };

//Das sind die letzten vier Pins vom KeyPad

byte colPins[KEYPAD_COLS] = { 6, 7,
                              8, 9 };







//Belegung von KeyPad, alle Felder mit 8 sind irrelevant.

char allKeys[KEYPAD_ROWS][KEYPAD_COLS] = {

  { '1', '2', '3', '8' },

  { 'A', 'B', '8', '8' },

  { 'S', '8', '8', '8' },

  { '8', '8', '8', '8' }

};




Keypad customKeypad = Keypad(makeKeymap(allKeys),
                             rowPins, colPins, KEYPAD_ROWS, KEYPAD_COLS);





void setup() {
  easycat.Init();
  Serial.begin(9600);
}

int cubeToMove = 1;

int endPosition = 1;

void loop() {

 char pressedKey = 'S';

  if (pressedKey) {
    //if (pressedKey == '1' && isCubeThere((int)pressedKey - 48)) {
    if (pressedKey == '1'){

      cubeToMove = 1;

    //} else if (pressedKey == '2'&& isCubeThere((int)pressedKey - 48)) {
    } else if (pressedKey == '2') {

      cubeToMove = 2;

    //} else if (pressedKey == '3'&& isCubeThere((int)pressedKey - 48)) {
    } else if (pressedKey == '3') {

      cubeToMove = 3;

    //} else if (pressedKey == 'A'&& isCubeThere(cubeToMove)) {
    } else if (pressedKey == 'A') {

      endPosition = 1;

    //} else if (pressedKey == 'B'&& isCubeThere(cubeToMove)) {
    } else if (pressedKey == 'B') {

      endPosition = 2;

    // Wählt die Positon aus
    //} else if (pressedKey == 'S'&& isCubeThere(cubeToMove)) {
    } else if (pressedKey == 'S') {


      check_if_op_finished();
      //if(send_done_received) {
        Serial.println(cubeToMove);

        Serial.println(endPosition);

        send("cube"+cubeToMove,"pos"+endPosition);
      //}
      // Senden die rübergegebenen Daten 

    }

    else {

      Serial.println("Error");

      //talk();

      // Gibt bei Fehler eine  Nachricht mit "Error" über den Lautsprecher rüber
    }
  }

  easycat.MainTask();
}


void send(String input, String position) {
  if(easycat.BufferIn.Cust.update == 0x1) {
    Serial.println("Operation already send (Update = true)");
    return;
  }

  uint8_t cube;
  uint8_t pos;

  // if(input.equals("cube1")) {
  //   cube = 0x1;
  // } else if(input.equals("cube2")) {
  //   cube = 0x2;
  // } else if(input.equals("cube3")) {
  //   cube = 0x3;
  // } else {
  //   //talk();
  //   return;
  // }

  // if(position.equals("pos1")) {
  //   pos = 0x1;
  // } else if(position.equals("pos2")) {
  //   pos = 0x2;
  // } else {
  //   //talk();
  // }

  easycat.BufferIn.Cust.cube = 0x1;
  easycat.BufferIn.Cust.position = 0x1;

  // easycat.BufferIn.Cust.cube = cube;
  // easycat.BufferIn.Cust.position = pos;
  // easycat.BufferIn.Cust.update = 0x1;
}


void check_if_op_finished() {
  if(send_done_received) {
    send_done_received = false;
    easycat.BufferIn.Cust.done_received = 0x0;
  }

  if(easycat.BufferOut.Cust.done == 0x1 && send_done_received == false) {
    easycat.BufferIn.Cust.done_received = 0x1;
    send_done_received = true;
  }
}

void check_if_op_started() {
  if(easycat.BufferOut.Cust.update_received == 0x1) {
    easycat.BufferIn.Cust.update = 0x0;
  }
}

void talk() {
  //TODO
  Serial.println("Talk"); 
}