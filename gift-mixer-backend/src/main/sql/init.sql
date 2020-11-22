# Initial configuration

CREATE DATABASE giftmixer;

CREATE USER 'giftmixer'@'localhost' IDENTIFIED BY '<hidden>';  # Password is hidden
GRANT ALL PRIVILEGES ON giftmixer.* TO 'giftmixer'@'localhost';
FLUSH PRIVILEGES;
