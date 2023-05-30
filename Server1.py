import time
import socket

def berkeley_algorithm(client_time, server_time):
    time_diff = server_time - client_time
    adjusted_time = server_time + (time_diff / 2)
    return adjusted_time

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_address = 'localhost'
server_port = 1234
server_socket.bind((server_address, server_port))
server_socket.listen(1)

while True:
    client_socket, client_address = server_socket.accept()
    client_time = float(client_socket.recv(1024).decode())

    server_time = time.time()
    adjusted_time = berkeley_algorithm(client_time, server_time)

    print("Server's Adjusted Time:", time.ctime(adjusted_time))

    client_socket.sendall(str(adjusted_time).encode())
    client_socket.close()
