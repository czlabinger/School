# Topology

Multiple clients connected to a single server.
The number of clients can become an issue -> load balanced servers with shared DB
Or server to server communication (two or more servers are connected with eachother). Servers can be added without a problem but can only be disconnected if no client is connected.
Latency may become an issue -> Can lead to complicated collisions. 
Can be reduced by avoiding long server chains and by increasing the sync time between servers
