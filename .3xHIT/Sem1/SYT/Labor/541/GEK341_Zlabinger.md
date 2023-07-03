<h1>GEK531 Netzwerke in virtuellen Umgebungen</h1>

**Christof Zlabingr**
**13.10.2022**

<br />

## Welche Möglichkeiten gibt es die Kommandozeile zu öffnen? 

Über das Startmenü, taskleiste, shortcut, im systemexplorer -> rmb -> open terminal here

<br />

## Was ist ein terminal emulator und wo kommt dieser zum Einsatz?

Ein termial emulator emuliert ein termial mit einer gui.

<br />

## Beschreibe kurz die folgenden Befehle und führe mögliche Anwendungsgebiete an:

- cd: change directory: wächselt in das folgende verzeichnis. z.B.: cd Downloads/
- cat: concatenate: gibt den Ihnahlt eines files aus. z.B.: cat text.txt
- echo: gibt die dannach angegebenen arguente im termial aus. z.B.: echo Hello World!
- ls: to list files: gibt alle files und subdirectories aus. verwendung: ls
- rmdir: remove direcotory: löscht ein directory. z.B.: rmdir test
- man: manual: gibt das maual des dannach folgenden befehles aus. z.B.: man apt
- exit: schließt das Termial.
- login: muss mit root rechten ausgeführt werden und führt dazu, dass man sich mit einem User anmeldet. verwendung: sudo lolgin
- mkdir: make directory: erstellt ein dir in dem dir in dem man sich befindet. z.B.: mkdir test

<br />

## Wie kann man das Terminal nun verlassen?

Mit dem x am fensterrand, alt + f4, exit 

<br />

## Wie kann der Befehl auch durch eine Tastenkombination aufgerufen werden?

Indem man in den systemsettings einen neuen hotkey erstellt der dann einen Befehl ausführt.

<br />


## Wo liegt der Unterschied zu dem naheliegenden logout?

logout kann verwendet werden wenn man als ein user im termial angemeldet ist und sich auslogen will. 

<br />

## Was passiert wenn man mittels ssh auf einem Server verbunden ist?

Es wird eine ssh verbindung mit dem jeweiligen server aufgebaut und man kann befehle für den server schicken. 

## Wie heißt der Befehl, der einen den aktuellen Ort mitteilt?

pwd

<br />

## Was ist das Heimverzeichnis?

Das Home verzeichnis ist für jeden user ein standart ort wo folder wie z.B.: Documents, Downloads, etc. befunden. 

<br />

## Wie kann man dorthin gelangen?

cd ~

<br />

## In welchem Verzeichnis liegen die meisten Benutzer-Befehle unter Linux?

/bin/

<br />

## Wo liegt der Unterschied zu absoluten Pfaden?

Absolute Pfade gehen immer vom root verzeichnis aus während relative Pfade immer vom aktuellen Verzeichnis ausgehen. 

<br />

## Und was sind . und ..?

../ bedeuten ein dir dach oben <br />
./ bedeutet das aktuelle dir

<br />

## Wie könnte man aber nun die Ausgabe von ls -l /etc/* in eine Datei umleiten?

ls -l /etc/* >> test.log

<br />

## Wo ist dabei der Unterschied zu > und >>?

">" übschreibt die Datei wenn sie schon existiert. <br />
">>" hängt an das ende des Files an.

<br />

## Und wenn das in die eine Richtung geht, kann man auch aus einer Datei Befehle bestimmte Informationen übergeben? 

echo "$(< Halo.txt)" [1]

<br />

## Welcher Befehl bietet sich dafür an?

echo

<br />

## Was bedeutet die ~?

Home verzeichnis

<br />

## Zeige dir mit dpkg --list alle bekannte Pakete des Systems an und filtere nun nur Pakete die in der Beschreibung linux erhalten

dpkg --list && grep -wf search Linux

<br />

## Logische Verknüpfungen von Befehlen helfen uns auch bei der Abarbeitung und Verschachtelung von Abläufen. Erstelle mit dem Befehl mkdir -p ~/eins/zwei/drei die beschriebene Verzeichnisstruktur und kopiere die Datei Hallo.txt aus dem Heimatverzeichnis in das letzte der erstellten Verzeichnisse - und das aber nur dann wenn, der Befehl mkdir erfolgreich war! Wie kommen dir dabei logische Operatoren zu Hilfe?

mkdir -p ~/eins/zwei/drei && mv Hallo.txt ~/eins/zwei/drei/

<br />

## Wir arbeiten in einer sogenannten Shell, die bestimmte Eigenschaften und Funktionen mitbringt, welche ist dies standardmäßig in Xubuntu?

bash

<br />

## Wie können die aktuellen, sessionbasierten Umgebungsvariablen angezeigt werden?

export

<br />

## Was würde anders sein, wenn man zum Beispiel in die csh wechselt?

csh launched die C-shell deren syntax von C inspiriert ist. Unterscheid ist z.B.: in bash erstellt man eine variable mit "VAR=value" und in csh mit "set var=value" [2]

<br />

## Beschreibe kurz wie der Wechsel funktioniert und wo diese Funktion sehr von Nutzen sein könnte.

popd: removed standartmäßig den letzten eintrag aus dem dirs command der den verlauf der driectories anzeigt. <br />

pushd: fügt das aktuelle dir in den verlauf hinzu

<br />

## Welche andere Art der Visualisierung hilft der leichteren Administration von sehr verzweigten Verzeichnisbäumen?

tree

<br />

## Wie unterscheiden sich dabei hard von soft links ?

Hard link zeigt auf eine spezielle stelle auf der festplatte. <br />

Soft links zeigt auf einen speziellen namen. 

<br />

## Was ist ein Inode und wie kann diese Identifikation mit dem Befehl ls anzeigen?

index node: beinhaltet metadaten für die files

<br />

## Der Befehl find ist ein sehr mächtiger Befehl, wo liegt dabei der Unterschied zu locate?

locate durchsucht eine database und find sucht das ganze system

<br />

## Durchsuche das Heimatverzeichnis nach Dateien, die in den letzten fünf Minuten verändert wurden! Welche Option verwendest du? Leite dabei Fehlermeldungen (z.B. Berechtigungsmeldungen) nach /dev/null um!

find ~ -type f -mmin 5

<br />

## Welche verbreiteten Distributionen verwenden welchen Paket-Manager?

Ubuntu: apt
Debian: dpkg
Arch: pacman

<br />

##  Welche verschiedene Levels sind dabei im Einsatz, oder wo ist der Unterschied von apt und dpkg?

dpkg: kann auch locale packages installieren 
apt: installiert automatisch dependencies

<br />

## Wo bekommt man Unterstützung für einen Befehl?

man \<command> <br />
\<command> --help

<br />

## Wo liegt der Unterschied zwischen der Übergabe von einem oder zwei Bindestrichen bei der Verwendung von Optionen? (z.B. sudo -h und sudo --help)

-h könnte auch ein parameter für etwas anderes sein --help gibt genau an dass es sich um help handelt

<br />

## Welche Funktionsweise hat die $PATH-Variable?

Funktionieren wie enviromental variables unter windows

<br  />

## Wie wird sie verwendet und wie ist sie unter Linux aufgebaut?

\$PATH=path

<br />

## Wie ist $PATH unter Windows aufgebaut?

"path"

<br />

## Wie kann die $PATH Variable angepasst werden?

sudo nano /etc/environment <br />
export \$PATH=path

<br />

## Wann kommt die Anpassung zur Anwendung?

Wenn eine neue enviromental variable hinzugefüt/entfernt werden soll

<br />

## Woran erkennt man einen relativen Pfad (im Gegensatz zu einem absoluten)?

Ein relativer pfad geht immer vom aktuellen verzeichniss aus. (Ein alsuluter geht immer von / aus)

<br />

## Nenne zwei Arten, wie man das home Verzeichnis des aktuellen Benutzers ermitteln kann.

echo ~ <br />
eval echo ~$USER [3]

<br />

## Wo liegt dieses üblicherweise?

/home/\<user>/

<br />

## Ein Benutzer möchte seinen Download-Ordner archivieren und tut dies mittels cp Downloads/ backup. Warum funktioniert das nicht und was sollte er stattdessen aufrufen?

Weil es nicht wie ein folder aufgerufen wird und die -r bzw -a option fehlt <br />

cp -a /source/. /dest/[4]

<br />

## Wie kann man aus der grafischen Oberfläche zur System-Kommandozeile wechseln? Und wofür steht tty?

Ctrl + Alt + f2 <br />

teletype (terminal)[5]

<br />

## Wofür steht bash und mit welchen anderen Interpretern ist diese verwandt?

Bourne Again Shell

verwandt mit Bourne shell 

<br />

## Was ist POSIX und wie unterstützt es die Verwendung von verschiedenen Distributionen?

Portable Operating System Interface. Standarts die von der IEEE festgelegt wurden.[6]

<br />200000

## Wie kommen die Datenströme stdin stdout stderr in der Shell zum Einsatz?

stdin: Standart input <br />
stdout: Standart output <br />
srdeer: Standart error <br />

kommen für den input/output/ausgabe von errors zum einsatz

<br />

## Können diese umgeleitet werden und wenn ja wie?

ja mittels > und >>

<br />

## Man steht an und kommt nicht weiter, wie heißt noch mal dieser eine Befehl ... Welcher Befehl hilft einem bei der Suche durch alle Manpages und listet die gefundenen Begriffe übersichtlich auf?

apropos \<string>

<br />

Quellen: 

[1] https://stackoverflow.com/questions/4227994/how-do-i-use-the-lines-of-a-file-as-arguments-of-a-command

[2] https://www.computerhope.com/unix/ucsh.htm

[3] https://superuser.com/questions/484277/get-home-directory-by-username

[4] https://askubuntu.com/questions/86822/how-can-i-copy-the-contents-of-a-folder-to-another-folder-in-a-different-directo

[5] https://www.geeksforgeeks.org/tty-command-in-linux-with-examples/#:~:text=tty%20is%20short%20of%20teletype,output%20produced%20by%20the%20system.

[6] https://en.wikipedia.org/wiki/POSIX
