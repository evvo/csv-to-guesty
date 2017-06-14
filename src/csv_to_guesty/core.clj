(ns csv-to-guesty.core
  (:use [csv-to-guesty.config]
        [csv-to-guesty.web.main])
  (:gen-class))

(defn -main
  [& args]
  (println "Starting Server...")
  (start-server))
