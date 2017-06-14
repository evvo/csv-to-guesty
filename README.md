# csv-to-guesty

Web application written in Clojure, that allows bulk updating of property prices by dates for Guesty (using the Guesty API) using CSV file.
The uploaded CSV file must contain a single "Date" column and at least one column,
which name is a property ID (the CSV could contain more than one property ID).

The "Date" column must use the following date format: 3/23/2017 (month/day/year).

You can find an example CSV file in the doc folder.

## Installation

Build the application using leiningen:

    $ lein compile
    $ lein uberjar

Change the config in the config.edn (usually, you will only need to change the :api_key, :api_secret and the default user's credentials)

The default user is "admin" with password "secret". You can add more users using the same format.
The default port is 9998.

Make sure that the upload directory is within the resources folder and is writable.

## Usage

In order to start the application, run:

    $ java -jar csv-to-guesty-0.1.0-standalone.jar

## Examples

You can find an example CSV in the doc folder.

## License

Copyright © 2017 Evtimiy Mihaylov (evo@vaupe.com)

Distributed under the Eclipse Public License, the same as Clojure.
