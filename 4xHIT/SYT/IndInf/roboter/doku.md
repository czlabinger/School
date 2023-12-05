## Arduino

### Importieren der EasyCat lib

Download von der offiziellen Seite [hier](https://www.bausano.net/images/arduino-easycat/EasyCAT.zip).
In arduino IDE: Sketch -> Include Library -> Add .ZIP Library.

//TODO: Code

### Easy Configurator

Die Dokumentation kann [hier](https://www.bausano.net/images/arduino-easycat/EasyConfigurator_UserManual.pdf) gefunden werden.

Download von der offiziellen Seite [hier](https://www.bausano.net/images/arduino-easycat/EasyConfigurator_V4_2.zip)
Extrahieren und EasyCAT_Config_GUI.exe ausfuehren.


![Easy Configurator](/home/stoffi05/Documents/School/4xHIT/SYT/IndInf/roboter/easyConf.png)


Vendor ID: Die ID des Unternehmens\
Vendor Name: Name des Unternehmens\
Product Code: Der Code der das Produkt identifiziert\
Revision: Die Version der Konfiguration\
Name: Name des Projekts\


Inputs und Outputs definieren:

* update_received: Gibt an ob das Update erhalten wurde
* done: Prozess ist fertig


* cube: Welcher Wuerfel bewegt werden soll
* position: Auf welche Position der Wuerfel bewegt werden soll
* update: Gibt an ob die gesendeten Befehle ausgefuert werden sollen
* done_received: Bestaetigung, dass der Prozess beendet wurde


Dannach 'Create files' druecken damit die benoetigten files erstellt werden.
Das .h file in den Folder des Arduino Projekts kopieren damit es als Library verwendet werden kann.

'Write EEPROM' druecken, das .bin file auswahlen. Dadurch wird die Konfiguration auf das Board geschrieben.

//TODO: .xml
