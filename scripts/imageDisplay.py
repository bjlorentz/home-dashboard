#!/home/home/.virtualenvs/pimoroni/bin/python3

import sys,os
from PIL import Image
from inky.auto import auto

display = auto(ask_user=True, verbose=True)

image = Image.open("/home/home/output.jpg")
resizedImage = image.resize(display.resolution)

display.set_image(resizedImage)

display.show()