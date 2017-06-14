(ns csv-to-guesty.column-formatter
  (:require [clj-time.format :as f])
  (:gen-class))


(def input-date-format (f/formatter "MM/dd/yyyy"))
(def output-date-format (f/formatter "yyyy-MM-dd"))

(defn format-column-headers [possible]
  (map keyword (map (fn [x] (clojure.string/replace x #"" "")) (remove empty? possible))))

(defn format-content-rows [contentRows]
  (remove empty? contentRows))

(defn get-columns [possible properties]
  (apply assoc {}
    (interleave (format-column-headers possible)
                (format-content-rows properties))))

(defn parse-sheet-date [date]
  (f/unparse output-date-format (f/parse input-date-format date)))
