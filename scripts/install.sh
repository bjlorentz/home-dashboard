#!/usr/bin/env sh

## Update packages
sudo apt-get update

echo "Install GIT"
sudo apt -y install git

# Ne pas installer en tant que root
echo "Install Inky project from Pimoroni github source"
git clone https://github.com/pimoroni/inky
cd inky
./install.sh

# Activation de la déttection automatique
sudo raspi-config nonint do_i2c 0
sudo raspi-config nonint do_spi 0

# Install PIP
sudo apt -y install python3-pip

# Création de l'environnemnet Python pour la gestion des images
python3 -m venv /home/home/python/home-dashboard

# Installation PIL avec PIP
/home/home/python/home-dashboard/bin/python3 -m pip install --upgrade pip
/home/home/python/home-dashboard/bin/python3 -m pip install --upgrade Pillow

# Install de chromedriver
sudo apt-get install chromium-chromedriver

exit 0;