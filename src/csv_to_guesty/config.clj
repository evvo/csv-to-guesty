(ns csv-to-guesty.config
  (:require [clojure.edn :as edn])
  (:gen-class))


(defn load-config
  "Given a filename, load & return a config file"
  [filename]
  (edn/read-string (slurp filename)))

(def global-config (load-config "config.edn"))
