#define CUSTOM
#include "setup.h"

#include <EasyCAT.h>
EasyCAT easycat;

bool send_done_received = false;

void setup() {
  easycat.Init();
  Serial.begin(9600);
}

void loop() {
  check_if_op_finished();
  check_if_op_started();
}


void send(String input, String position) {
  if(easycat.BufferOut.Cust.update == 0x1) {
    Serial.println("Operation already send (Update = true)");
    return;
  }

  uint8_t cube;
  uint8_t pos;

  if(input.equals("cube1")) {
    cube = 0x1;
  } else if(input.equals("cube2")) {
    cube = 0x2;
  } else if(input.equals("cube3")) {
    cube = 0x3;
  } else {
    talk();
    return;
  }

  if(position.equals("pos1")) {
    pos = 0x1;
  } else if(position.equals("pos2")) {
    pos = 0x2;
  } else {
    talk();
  }

  easycat.BufferOut.Cust.cube = cube;
  easycat.BufferOut.Cust.position = pos;
  easycat.BufferOut.Cust.update = 0x1;
}

void check_if_op_finished() {
  if(send_done_received) {
    send_done_received = false;
    easycat.BufferOut.Cust.done_received = 0x0;
  }

  if(easycat.BufferIn.Cust.done == 0x1 && send_done_received == false) {
    easycat.BufferOut.Cust.done_received = 0x1;
    send_done_received = true;
  }
}

void check_if_op_started() {
  if(easycat.BufferIn.Cust.update_received == 0x1) {
    easycat.BufferOut.Cust.update = 0x0;
  }
}

void talk() {
  //TODO
  Serial.println("Talk"); 
}