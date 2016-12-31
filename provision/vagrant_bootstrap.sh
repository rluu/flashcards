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

# Python virtualenv directory.
export PYTHON_VIRTUALENV_DIR=${PROJECT_HOME}/venv

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
#sudo apt-get install -y git  # Git already comes with Ubuntu xenial

echo "Installing zip and unzip ..."
sudo apt-get install -y zip unzip

echo "Installing python3-pip ..."
sudo apt-get install -y python3-pip

# Install virtualenv and use that instead of using pyvenv or python -m venv,
# because there is a bug with it on ubuntu/trusty64 and ubuntu/xenial,
# preventing it from working.  
# In the future, we should be able to switch to using python -m venv instead
# of virtualenv.
echo "Installing virtualenv ..."
sudo pip3 install virtualenv

##############################################################################
# Set up Java.

#echo "Installing Java 8 JDK ..."
#sudo apt-get install -y openjdk-8-jdk

#echo "Installing Maven ..."
#sudo apt-get install -y maven

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

echo "Installing pgAdmin ..."
apt-get install -y pgadmin3

##############################################################################
# Creating a python3 virtualenv environment

echo "Creating the project's Python 3 virtualenv environment ..."
cd ${PROJECT_HOME}

if [ -d "${PYTHON_VIRTUALENV_DIR}" ]; then
    rm -rf ${PYTHON_VIRTUALENV_DIR}
fi
virtualenv ${PYTHON_VIRTUALENV_DIR}

# Activate the virtual environment.
source ${PYTHON_VIRTUALENV_DIR}/bin/activate

########################################
# Install other Python dependencies into the Python virtual environment.

echo "Installing the project's Python 3 packages via pip3 ..."
pip3 install -r ${PROJECT_HOME}/provision/pip_requirements.txt

##############################################################################
# Setup the Bash environment.

echo "Doing bash environment setup ..."

echo "alias ls='ls --color=auto'" | sudo -u ubuntu tee -a /home/ubuntu/.bash_profile
echo "alias sl='ls'" | sudo -u ubuntu tee -a /home/ubuntu/.bash_profile
echo "source ${PYTHON_VIRTUALENV_DIR}/bin/activate" | sudo -u ubuntu tee -a /home/ubuntu/.bash_profile

##############################################################################

echo "Provisioning Bash script completed."

##############################################################################
