**Sigfox API Proxy**

The purpose of this project is to offer a better access to the Sigfox backend API to improve the developer experience.
This project is containing a Java implementation of the Sigfox API model and use them to proxify the original API with
exactly the same contract.

The purpose is to give you access to a developer portal containing all the documentation of the API and the associated
Models with a easy way to enter your parameter and documented results.

You can directly use the portal from our servers going on [https://sigfox-api.ingeniousthings.fr/](https://sigfox-api.ingeniousthings.fr/")
There is no registration, you just need to have a Sigfox API access to use it. For creating your Sigfox API access you
need to go to backend.sigfox.com, go to GROUP menu, Select one of your group and select API ACCESS menu. Then you can click on the
create link.

We do not store any of your information in the online accessible portal. If you want you can install your own portal by
cloning this repo and launch it on your own environment.

**IntegniousThings API**

In addition of the Sigfox API proxy we have built some usefull API to automate different Sigfox API call for simplify some more complex
task. Actually the IngeniousThings API allow operations like:
- Exporting a deviceType with all its callback configuration (for replication or backup purpose)
- Importing a deviceType from a previously exported one.
- Duplicate a deviceType with group/contract change or a new name.


__Build your own portal__


* clone this repo
* build it
    * ./gradlew build -x test
* run it
    * java -jar build/lib/*.jar

