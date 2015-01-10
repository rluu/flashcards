# todo-webapp

### Description

This project is a webapp that features todo list functionality, built on top of Python/Django.

Creation date: 2015-01-09.

### Author(s)

Ryan Luu
ryanluu@gmail.com

### Dependencies

System-level dependencies:
- [Python >= 3.4](https://www.python.org/)
- [PostgreSQL](http://www.postgresql.org/)
- [virtualenv](https://virtualenv.pypa.io/en/latest/)

Python package dependencies (installed using pip, using requirements.txt):
- [psycopg](http://initd.org/psycopg/)
- [Django](https://www.djangoproject.com/)


### Setup and installation of environment:

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
    - Download the zip file.
    - Run the executable.  Agree to the prompt to move the application to the Applications folder.
    - Add the following to your .bashrc or .bash_profile to get the PostgreSQL tools in your path:
    ```
# Path to PostgreSQL.
export POSTGRESQL_HOME=/Applications/Postgres.app/Contents/Versions/9.4

# Prepend PostgreSQL's bin directory to the PATH.
export PATH=$POSTGRESQL_HOME/bin:$PATH
    ```

6. Start up the PostgreSQL database.  If you're on Mac OS X and it was installed via Postgres.app, then run it via the Application folder; you should see an elephant should pop up in the tool bar.

7. Clone this project's git repository, and change into the directory.
  ```
git clone https://github.com/rluu/todo-webapp.git
cd todo-webapp
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

