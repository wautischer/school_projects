#include <Arduino.h>
#include <SPI.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <Fonts/FreeMono9pt7b.h>
#include <Fonts/FreeMonoBold12pt7b.h>
#include <Fonts/FreeSans9pt7b.h>

Adafruit_SSD1306 display(128, 64);

#define PIN_TRIGGER 2
#define PIN_ECHO    3

const int SENSOR_MAX_RANGE = 400; // in cm
unsigned long duration;
unsigned int distance;

void setup() {

  pinMode(PIN_TRIGGER, OUTPUT);
  pinMode(PIN_ECHO, INPUT);
  
  delay(100);
  display.begin(SSD1306_SWITCHCAPVCC, 0x3C);
  display.clearDisplay();
  display.setTextColor(WHITE);
  display.setRotation(0);
  display.setTextWrap(false);
  display.dim(0);
}

void loop() {

  display.clearDisplay();
  display.setTextSize(0);
  display.setFont(&FreeSans9pt7b);
  
  display.setCursor(0, 40);
  display.println("Laenge: ");
  
  digitalWrite(PIN_TRIGGER, LOW);
  delayMicroseconds(2);

  digitalWrite(PIN_TRIGGER, HIGH);
  delayMicroseconds(10);

  duration = pulseIn(PIN_ECHO, HIGH);
  distance = duration/58;

  if (distance > SENSOR_MAX_RANGE || distance <= 0){
    display.setCursor(75, 40);
    display.println("ERR");
  } else {
    display.setCursor(69, 40);
    display.println(distance);
    display.setCursor(100, 40);
    display.println("cm");
  }
  display.display();
  delay(1000);
}