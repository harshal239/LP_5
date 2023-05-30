from timeit import default_timer as timer
from dateutil import parser
import threading
import datetime
import socket
import time


def startSendingTime(slave_client):

    while True:
        slave_client.send(str(datetime.datetime.now()).encode())
        print("Recent Time sent Successfully",  end="\n\n")

        time.sleep(5)


def startReceivingTime(slave_client):

    while True:

        Synchronized_Time = parser.parse(slave_client.recv(1024).decode())

        print("Synchronous time at the client is : " + str(Synchronized_Time),end="\n\n")


def initiateSlaveClient(port=8080):
    slave_client = socket.socket()

    slave_client.connect(('127.0.0.1', port))
    
    
    print("Start Sending time to server \n")
    send_time_thread = threading.Thread(target=startSendingTime,
                                             args=(slave_client,))
    send_time_thread.start()
    

    print("Start Receiving Sync time from server \n")
    receiving_time_thread = threading.Thread(target=startReceivingTime,
                                             args=(slave_client,))
    receiving_time_thread.start()


if __name__ == "__main__":
    initiateSlaveClient(port=8080)

