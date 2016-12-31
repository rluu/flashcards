# flashcards

### Description

This project is a webapp that features flashcard functionality for learning new subjects.

Coming features:
- User accounts
- Multiple flashcard sets
- Import and export of flashcards.

Software stack:
- [Python 3.4](https://www.python.org/) or higher.
- [Django 1.10](https://www.djangoproject.com/)

Creation date: 2015-01-09.

### Author(s)

Ryan Luu
&nbsp;&nbsp;
ryanluu@gmail.com

### Dependencies

System-level dependencies:
- [Python 3.4](https://www.python.org/) or higher.
- [PostgreSQL 8.4](http://www.postgresql.org/) or higher.
- [virtualenv](https://virtualenv.pypa.io/en/latest/)

Python package dependencies (installed using pip, using requirements.txt):
- [Django 1.10](https://www.djangoproject.com/)
- [psycopg](http://initd.org/psycopg/)

Vagrant virtual machine prerequisites:
- [VirtualBox](https://www.virtualbox.org/)
- [Vagrant](https://www.vagrantup.com/)
- [Git](https://git-scm.com/)

Setup and installation of DEV environment using Vagrant
---

#### Install [Virtual Box](https://www.virtualbox.org/wiki/Downloads)

Install [Virtual Box](https://www.virtualbox.org/wiki/Downloads) for your host platform.
This is required for installing and running Vagrant.

#### Install [Vagrant](https://releases.hashicorp.com/vagrant/)
This will get you set up with a database and runtime environment.

```
# Get the most recent package of Vagrant.
wget https://releases.hashicorp.com/vagrant/1.9.1/vagrant_1.9.1_x86_64.rpm
wget https://releases.hashicorp.com/vagrant/1.9.1/vagrant_1.9.1_SHA256SUMS
md256sum vagrant_1.9.1_x86_64.rpm
cat vagrant_1.9.1_SHA256SUMS

# Install
rpm -Uvh vagrant_1.9.1_x86_64.rpm
```

#### Navigate to the project directory and start the VM.
Starting the Vagrant VirtualBox VM for the first time should take about 4 to 5 minutes, depending on the speed of your internet connection.

```
cd ${PROJECT_HOME}
vagrant up
```

To stop the VM run the following:
```
vagrant halt
```

To destroy (delete) the VM, run the following:
```
vagrant destroy
```

#### Running the server

1) Secure shell into the virtual machine.

```
vagrant ssh
```

2) SSH-ing into the virtual machine should also source the Python3 virtualenv.
You'll know if it did this by the format of the command prompt.  Verify that the command prompt looks something like:

```
(venv)ubuntu@ubuntu-trusty-64:~$
```

If you don't see the `(venv)` in the front of the prompt, then you will need to source the script manually via: `source ./venv/bin/activate`

2) Navigate to the project django directory.

```
cd /vagrant/django
```

3) Make sure that the database migrations have been done.  (syncing the database tables with the Python class models in source code).

```
python3 manage.py migrate
```

4) If this is the very first time running the Django server with the database, then you will want to create a superuser account for the Django backend server.

```
python3 manage.py createsuperuser
```

You can enter whatever admin username you want to use (e.g. 'admin'), etc.
For now, this is the only user that allowed to make model changes (create new users, groups, events, etc.), otherwise you will only have read-only access to the REST interface.

5) Start the Django server.

```
python3 manage.py runserver
```

Available URLs:
  - REST API URL: [http://localhost:8000/api/](http://localhost:8000/api/)
  - REST API Authentication URL: [http://localhost:8000/api-auth/login/](http://localhost:8000/api-auth/login/)
  - Django admin page: [http://localhost:8000/admin/](http://localhost:8000/admin/)

6) Optionally, you can populate the database with fake data by running the following:

```
cd /vagrant/django/scripts/

./user_create.py --username="happyuser" --password="happypass" --email="happy@test.com"

./events_populate_with_fake_data.py --count=1024 --username="happyuser"

./events_delete.py --id=3 --id=28

./events_delete.py --username="happyuser" --all

./events_delete.py --all

./user_delete.py --username="happyuser"
```

### SSH Port Forwarding
To access the guest virtual machine from the host machine, you can use ssh port forwarding as follows.  
There may be a better solution, but for now, this is a work-around.

1) From the host machine, navigate to the project top-level directory.

```
cd ${PROJECT_HOME}
```

2) Run the following to forward ports.  Connections made to a port on the host machine will get forwarded to a particular port on the guest machine.

HTTP server defaults to port 8000 for the REST API and server pages.
```
ssh -f $(vagrant ssh-config | grep -v "Host " | awk '{print " -o "$1"="$2}') localhost -L 8000:127.0.0.1:8000 -N
```

Note: The format of the `ssh -L` forwarding is:
```
-L <PORT_ON_HOST_MACHINE>:<IP_ADDRESS_ON_GUEST_MACHINE>:<PORT_ON_GUEST_MACHINE>
```

3) Now you can open up a browser on the host machine and navigate to the desired URLs.  For example:

- [http://localhost:8000/](http://localhost:8000/)


Troubleshooting / Debugging
---

To drop the Django database, re-populate with the models, and re-create the superuser, and run the Django server:

```
python3 manage.py reset_db
python3 manage.py migrate
python3 manage.py createsuperuser
python3 manage.py runserver
```

Setup and installation of DEV environment without using Vagrant
---

1. Make sure Python 3.4 is installed.
  ```
python3
exit()
  ```

2. Make sure `pip` is associated to Python 3.4
  ```
pip --version
  ```

3. See if `virtualenv` is installed.  If it is, then verify that it is installed with Python 3.4  This can be done by looking at the --help output under the -p option's description text.
  ```
which virtualenv
virtualenv --help
  ```

4. If `virtualenv` is not installed, then install it using `pip` (for Python 3.4).
  ```
su
pip install virtualenv
  ```

5. Install [PostgreSQL](http://www.postgresql.org/download/).
  - On Mac OS X, using the [Postgres.app](http://postgresapp.com/):
    - Download the zip file and extract it.
    - Run the executable.  Agree to the prompt to move the application to the Applications folder.
    - Add the following to your .bashrc or .bash_profile to get the PostgreSQL tools in your path:
    ```
# Path to PostgreSQL.
export POSTGRESQL_HOME=/Applications/Postgres.app/Contents/Versions/9.4

# Prepend PostgreSQL's bin directory to the PATH.
export PATH=$POSTGRESQL_HOME/bin:$PATH
    ```

6. Start up the PostgreSQL database.  If you're on Mac OS X and it was installed via Postgres.app, then run it via the Application folder; you should see an elephant icon pop up in the tool bar.

7. Clone this project's git repository, and change into the directory.
  ```
git clone https://github.com/rluu/flashcards.git
cd flashcards
  ```

8. Run `virtualenv` within the project directory to create a virtual environment, and then activate the environment.
  ```
virtualenv --no-site-packages venv
source venv/bin/activate
  ```

9. Using `pip`, install the packages in `requirements.txt`.  This will install [Django](https://www.djangoproject.com/), [psycopg](http://initd.org/psycopg/), and other dependencies.
  ```
pip install -r requirements.txt
  ```
Note: To generate a new requirements file at any time:
  ```
pip freeze > requirements.txt
  ```

Description of project directories and settings files.
---

```
${PROJECT_HOME}/venv
```
- Contains the python3 virtualenv files.

```
${PROJECT_HOME}/django
```
- Contains the django files that were initially generated by
`django-admin startproject <PROJECT_NAME>`

```
${PROJECT_HOME}/django/flashcards/settings.py
```
- Contains the database settings used by the django application.


```
${PROJECT_HOME}/provision/vagrant_bootstrap.sh
```
- Contains the provisioning script for Vagrant.  Upon `vagrant up`, this will download and install all dependencies for the webapp.  This includes:
  - PostgreSQL database:
    - Database server download
    - Database install
    - Database creation
    - Database user creation
  - Python 3 install
  - Django install


Considerations when deploying to PRD
---

Passwords are default and unsecured for:
  - PostgreSQL database connectivity and database account password.  
    See file: `provision/pg_hba.conf` and `provision/vagrant_bootstrap.sh`

