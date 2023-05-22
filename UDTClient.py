import threading
from socket import *

def receive_messages():
    while True:
        try:
            message = client.recv(1024)
            if message:
                print(message.decode('utf-8'))
            else:
                print("Connection lost")
                break
        except:
            continue

host = "192.168.100.41"
port = 12345

client = socket(AF_INET, SOCK_STREAM)
client.connect((host, port))

print("Connected to the chat server")

t = threading.Thread(target=receive_messages)
t.start()

while True:
    message = input()
    if message.lower() == "quit":
        client.close()
        break
    client.send(message.encode('utf-8'))

