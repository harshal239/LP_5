import time
import socket

def berkeley_algorithm(client_time, server_time):
    time_diff = server_time - client_time
    adjusted_time = client_time + (time_diff / 2)
    return adjusted_time


client_time = time.time()
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_address = 'localhost'
server_port = 1234

client_socket.connect((server_address, server_port))
client_socket.sendall(str(client_time).encode())

server_time = float(client_socket.recv(1024).decode())

adjusted_time = berkeley_algorithm(client_time, server_time)

print("Client's Adjusted Time:", time.ctime(adjusted_time))

# Close the socket
client_socket.close()
