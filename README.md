# FlashCards

### Description

This project is a webapp that features flashcard functionality for learning new subjects.

Coming features:
- User accounts
- Multiple flashcard sets
- Import and export of flashcards.

Software stack:
- [Java 8](http://openjdk.java.net/projects/jdk8/)
- [PostgreSQL](http://www.postgresql.org/)
- [Dropwizard](http://www.dropwizard.io/) Java framework:
  - [Jetty](http://www.eclipse.org/jetty/) for HTTP.
  - [Jersey](http://jersey.java.net/) for REST.
  - [Jackson](http://wiki.fasterxml.com/JacksonHome) for JSON.
  - [Metrics](http://metrics.dropwizard.io/) for metrics.
  - [JDBI](http://www.jdbi.org/) for relational database with Java.
  - [Liquibase](http://www.liquibase.org/) for database schema versioning.
  - [Freemarker](http://freemarker.sourceforge.net/) for front-end templating.

Creation date: 2016-12-28

### Author(s)

- Ryan Luu
&nbsp;&nbsp;
ryanluu@gmail.com

### Dependencies

System-level dependencies:
- [Java 8](http://openjdk.java.net/projects/jdk8/)
- [Apache Maven](https://maven.apache.org/)
- [PostgreSQL](http://www.postgresql.org/)

How to start the FlashCards application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/flashcards-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url: [http://localhost:8080/](http://localhost:8080/)

Health Check
---

To see your applications health enter url: [http://localhost:8081/healthcheck](http://localhost:8081/healthcheck)

For other admin-related information, see: [http://localhost:8081/](http://localhost:8081/)

Setup and installation of DEV environment using Vagrant
---

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

#### Build and run.

```
vagrant ssh
cd /vagrant
mvn clean install
java -jar target/flashcards-1.0-SNAPSHOT.jar server config.yml
```

#### SSH Port Forwarding
To access the guest virtual machine from the host machine, you can use ssh port forwarding as follows.  
There may be a better solution, but for now, this is a work-around.

1) From the host machine, navigate to the project top-level directory.

```
cd ${PROJECT_HOME}
```

2) Run the following to forward ports.  Connections made to a port on the host machine will get forwarded to a particular port on the guest machine.

HTTP server defaults to port 8080 for the REST API and port 8081 for admin metrics.
```
ssh -f $(vagrant ssh-config | grep -v "Host " | awk '{print " -o "$1"="$2}') localhost -L 8080:127.0.0.1:8080 -N
ssh -f $(vagrant ssh-config | grep -v "Host " | awk '{print " -o "$1"="$2}') localhost -L 8081:127.0.0.1:8081 -N
```

Note: The format of the `ssh -L` forwarding is:
```
-L <PORT_ON_HOST_MACHINE>:<IP_ADDRESS_ON_GUEST_MACHINE>:<PORT_ON_GUEST_MACHINE>
```

3) Now you can open up a browser on the host machine and navigate to the desired URLs.  For example:

- [http://localhost:8080/](http://localhost:8080/)
- [http://localhost:8081/](http://localhost:8081/)

Database Migrations With Liquibase
---

Check database status:

```
java -jar target/flashcards-1.0-SNAPSHOT.jar db status config.yml
```

Schema dump:

```
java -jar target/flashcards-1.0-SNAPSHOT.jar db dump config.yml
```

Tagging your schema:

```
java -jar target/flashcards-1.0-SNAPSHOT.jar db tag config.yml 2016-12-29-pre-user-move
```

Migrating your schema (dry-run and for real):

```
java -jar target/flashcards-1.0-SNAPSHOT.jar db migrate config.yml --dry-run

java -jar target/flashcards-1.0-SNAPSHOT.jar db migrate config.yml
```

Rolling back your schema (dry-run and for real):

```
java -jar target/flashcards-1.0-SNAPSHOT.jar db rollback config.yml --tag 2016-12-29-pre-user-move --dry-run

java -jar target/flashcards-1.0-SNAPSHOT.jar db rollback config.yml --tag 2016-12-29-pre-user-move
```

Testing Migrations: 

To verify that a set of pending change sets can be fully rolled back, use
the `db test` command, which will migrate forward, roll back to the original
state, then migrate forward again:

Note: Do not run this in production!!!
```
java -jar target/flashcards-1.0-SNAPSHOT.jar db test config.yml
```

Preparing a rollback script before they have been applied:

```
java -jar target/flashcards-1.0-SNAPSHOT.jar db prepare-rollback config.yml
```

Considerations when deploying to PRD
---

Passwords are default and unsecured for:
  - Application ADMIN account password.  See file: `migrations.xml`
  - PostgreSQL database connectivity and database account password.  
    See file: `provision/pg_hba.conf` and `provision/vagrant_bootstrap.sh`


Troubleshooting / Debugging
---

Old Stuff: Setup and installation of environment
---

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

