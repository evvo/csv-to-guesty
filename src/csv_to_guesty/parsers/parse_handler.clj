(ns csv-to-guesty.parsers.parse-handler
  (:use [csv-to-guesty.parsers.csv-parser :as parser]
        [csv-to-guesty.column-formatter])
  (:gen-class))


(defn parse-file [filepath]
  (parser/get-formatted-data filepath))

(defn get-date-column []
  :Date)

(defn get-account-ids [data]
  (map first
    (remove
      (fn [x] (.contains x (get-date-column)))
      (first data))))

(defn get-account-prices [data accountID]
  (loop [data data iterator 0 result []]
    (if (empty? data)
      result
      (let [currentData (first data)
            iteratoryStr (keyword (str iterator))
            currentResult { :from (parse-sheet-date (get currentData (get-date-column)))
                            :to (parse-sheet-date (get currentData (get-date-column)))
                            :listingId accountID
                            :price (get currentData accountID) }]
        (recur (rest data) (inc iterator) (conj result currentResult ))
        )
      )
    )
  )

(defn prepare-data-for-api [filepath]
  (let [data (parser/get-formatted-data filepath)
        accounts (get-account-ids data)]
    (loop [accounts accounts iterator 0 result []]
      (if (empty? accounts)
        result
        (recur (rest accounts) (inc iterator) (conj result  (get-account-prices data (first accounts)) ) )
        )
      )
    )
  )
