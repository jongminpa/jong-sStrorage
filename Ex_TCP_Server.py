from socket import *
host="127.0.0.1"
port=12305

s=socket(AF_INET,SOCK_STREAM)
s.bind((host,port))
print(s.recv(1024))
s.close()