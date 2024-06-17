# 8.4.1 Systemsicherheit "Firewall"
**Christof Zlabinger**

## Setup

### Gateway
```bash
sudo nano /etc/netplan/01-networkmanager-all.yml
```

```yml
network:
  version: 2
  renderer: NetworkManager
  ethernets:
    enp7s0:
        dhcp: no
        addresses: [192.168.18.1/24]
        gateway4: 192.168.18.1
```

### Server

```yml
# /etc/netplan/01-networkmanager-all.yml

network:
  version: 2
  renderer: NetworkManager
  ethernets:
    enp7s0:
    dhcp: no
    addresses: [192.168.18.10/24]
    gateway4: 192.168.18.1
```

## Task 1

Auf dem Server openssh-server und apache installieren.
```bash
sudo apt install openssh-server apache2
```

### Configurieren der firewall

Bestehende einstellungen loeschen
```bash
iptables -P INPUT DROP
iptables -P OUTPUT DROP
iptables -P FORWARD DROP
```

SSH erlauben
```bash
#Inbound
iptables -A INPUT -p tcp --dport 22 -m state --state NEW,ESTABLISHED -j ACCEPT

#Outbound
iptables -A OUTPUT -p tcp --dport 22 -m state --state NEW,ESTABLISHED -j ACCEPT
```

HTTP und HTTPS
```bash
iptables -A INPUT -p tcp --dport 80 -j ACCEPT
iptables -A INPUT -p tcp --dport 443 -j ACCEPT

iptables -A OUTPUT -p tcp --dport 80 -j ACCEPT
iptables -A OUTPUT -p tcp --dport 443 -j ACCEPT
```

Erlauben von icmp
```bash
iptables -A OUTPUT -d 192.168.18.10/24 -p icmp -j ACCEPT
iptables -A INPUT -s 192.168.18.10/24 -p icmp -j ACCEPT
```

DNS erlauben
```bash
iptables -A OUTPUT -p udp --dport 53 -j ACCEPT
iptables -A OUTPUT -p tcp --dport 53 -j ACCEPT
iptables -A INPUT -p udp --dport 53 -j ACCEPT
iptables -A INPUT -p tcp --dport 53 -j ACCEPT
```

Speichern der rules

```bash
iptables-save > /home/czlabinger/rules.v4
```
Laden der rules
```bash
iptables-restore < /home/czlabinger/rules.v4
```



## Task 2


