<h1>GEK531 Netzwerke in virtuellen Umgebungen</h1>

**Christof Zlabingr**
**28.09.2022**

<br />
<br />

<h2>Welche drei gängigen Konfigurationsarten gibt es bei der Netzwerkkonfiguration von virtuellen Maschinen?<br />
Wann verwende ich welche Netzwerkkonfiguration?<bt />
Welche Auswirkungen auf die an die VM vergebene IP-Adresse hat der jeweilige Modus?</h2><br /> 

<u>Bridged:</u> Greift direkt auf die Netzwerkkarte des Hosts zu. Bekommt vom DHCP-Server des Netzes eine IP. Wird fürs testen verwendet wenn man vollen zugriff haben will.

<u>NAT:</u> Erstellt ein eigenes Network für die VM, kann von außen nur über, einen in der NAT-Tabelle festgelegten, Port erreicht werden. Bekommt die IP vom Hyperviser der einen virtuellen DHCP-Server hat. Wird verwendet wenn man nur bei bestimmten Ports auf das gast os zugreifen will. 

<u>Host-Only:</u> Nur der host kann mit dem gast OS komunizieren. Bekommt die IP vom Hyperviser. Wird verwendet wenn man einen abgesicherten bereich haben will.

<br />
<br />

<h2>Wie können externe Netzwerkknoten auf die Gast-Instanz zugreifen?</h2>

Andere Netzwerkknoten können über bridged oder über NAT mit einem, vorher festgelegten port, das Gast OS pingen

<br />
<br />

<h2>sudo apt update; sudo apt upgrade. Was geschieht bei diesen zwei Befehlen?</h2>
<u>sudo apt update:</u> Refreshed den cache der verfügbaren updates.

<u>sudo apt upgrade:</u> Installiert die updates tatsächlich.

<br />
<br />

<h2>Was muss das Host-System starten, damit eine automatische IP-Vergabe in der Gast-Instanz zustande kommen kann? Wie kann man das auch manuell lösen?</h2>

Der Hyperviser setzt einen virtuellen DHCP-Server auf. Man kann dies auch manuell machen indem man 

<br />
<br />

<h2>Wo werden die virtuellen Netzwerkdevices verwaltet?</h2>

Im host system wird die virtuelle Netzwerkkarte auch angezeigt und kann so verwaltet werden.

<br />
<br />

<h2>Wie kann ein bestimmtes Netzwerkinterface bei der Bridged Variante ausgewählt werden?</h2>

Indem über einen bestimmten Port auf das Gerät zugreift.