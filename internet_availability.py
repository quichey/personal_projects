from datetime import datetime
import time
import socket
import os
from urllib.request import urlopen
from urllib.error import URLError, HTTPError
run = 1
while run == 1:
    socket.setdefaulttimeout( 23 )
    url = 'http://google.com/'

    n = datetime.now()
    addon = str(n) + "\n"
    printString = None

    f = open('internet.txt','a')
    try :
        response = urlopen( url )
    except (HTTPError, URLError):
        printString = "Internet Down:" + addon
    else :
        printString = "Internet Up:" + addon

    f.write(printString)
    f.close()
    print(printString)
    time.sleep(10)
