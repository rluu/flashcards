#!/usr/bin/env bash
##############################################################################
# Vagrant bootstrap script to get this project started with:
#   - Java 8, Maven
#   - PostgreSQL
##############################################################################

##############################################################################
# Variables.

# Shared directory with the virtual machine's host operating system,
# which is mapped to the project's top-level directory.
export PROJECT_HOME=/vagrant

# PostgreSQL version.
export PGSQL_VERSION=9.5

# PostgreSQL database user that is created.
# This user is created with no password.
export DATABASE_USERNAME=flashcards_user

# PostgreSQL database that is created.
export DATABASE_NAME=flashcards_db

##############################################################################
# Install operating system packages.

echo "Installing operating system packages ..."
sudo apt-get update -y


#sudo apt-get install -y emacs23
#sudo apt-get install -y git
#sudo apt-get install -y unzip
#sudo apt-get install -y python3-pip

sudo apt-get install -y zip

##############################################################################
# Set up Java.

echo "Installing Java 8 JDK ..."
sudo apt-get install -y openjdk-8-jdk

echo "Installing Maven ..."
sudo apt-get install -y maven

##############################################################################
# Set up PostgreSQL.

echo "Installing PostgreSQL database ..."

apt-get install -y postgresql-${PGSQL_VERSION} postgresql-contrib-${PGSQL_VERSION} libpq-dev
cp ${PROJECT_HOME}/provision/pg_hba.conf /etc/postgresql/${PGSQL_VERSION}/main/
/etc/init.d/postgresql reload

# Create the PostgreSQL user and database.
#su -l postgres -c 'createuser --no-password -s -e ${DATABASE_USERNAME}'
sudo -u postgres createuser --no-password -s -e ${DATABASE_USERNAME}
createdb -U${DATABASE_USERNAME} ${DATABASE_NAME}

##############################################################################
# Setup the Bash environment.

echo "Doing bash environment setup ..."

echo "alias ls='ls --color=auto'" >> /home/ubuntu/.bash_profile
echo "alias sl='ls'" >> /home/ubuntu/.bash_profile

##############################################################################

echo "Provisioning Bash script completed."

##############################################################################
