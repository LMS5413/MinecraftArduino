const int pinGreen = 2;
const int pinYellow = 3;
const int pinRed = 4;

const int numLeds = 3;

int ledStates[numLeds] = {LOW, LOW, LOW};
const int ledPins[numLeds] = {pinGreen, pinYellow, pinRed};

void setup() {
  for (int i = 0; i < numLeds; i++) {
    pinMode(ledPins[i], OUTPUT);
  }
  Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0) {
    String value = Serial.readString();
    value.trim();

    if (value.equals("LED_GREEN")) {
      toggleLed(0);
    } else if (value.equals("LED_YELLOW")) {
      toggleLed(1);
    } else if (value.equals("LED_RED")) {
      toggleLed(2);
    }
  }
}

void toggleLed(int ledIndex) {
  ledStates[ledIndex] = (ledStates[ledIndex] == LOW) ? HIGH : LOW;
  digitalWrite(ledPins[ledIndex], ledStates[ledIndex]);
}
