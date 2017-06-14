(ns csv-to-guesty.parsers.csv-parser
  (:use csv-to-guesty.column-formatter
        clojure-csv.core)
  (:gen-class))

(defn get-document-data [filepath]
  (parse-csv
    (slurp filepath)))

(defn get-formatted-data [filepath]
  (for [row (rest (get-document-data filepath))
        :let [properties (first (get-document-data filepath))]]
    (get-columns properties row)))
