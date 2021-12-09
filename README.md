# CommunicationBetweenServerAndClient-
The aim of this project is to develop a simple TCP-based client server application. Your TCP client/server will communicate over the network and exchange data. The server will start in passive mode listening for a transmission from the client. The client will then start and contact the server (on a given IP address and port number). The user of the client application types in a word that will be sent to the server; the server will send back a copy of the word.

If the server is down then the client must display an error message.

If server is running, then the client will ask the user to select one of the options:

1) open mode
2) secure mode
3) quit application

In the open mode, the word is sent to the server in clear text. On the other hand, in secure mode, the word is encrypted using a shared encryption key.

Note that the client code and server code should not be on the same host. Your work should demonstrate that the client and server can communicate using a network connection.
