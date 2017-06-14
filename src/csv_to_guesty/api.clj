(ns csv-to-guesty.api
  (:require [cheshire.core :refer :all]
            [clj-http.client :as client]
            [cheshire.core :refer :all])
  (:gen-class))

(def api-config (get csv-to-guesty.config/global-config :api))
(def api-key (api-config :api_key))
(def api-secret (api-config :api_secret))
(def api-main-url (api-config :main_url))

(defn update-api-data [request-url body]
  (client/put
    (str api-main-url request-url)
    { :basic-auth [api-key api-secret]
      :body (generate-string body)
      :content-type :json
      :conn-timeout 20000
      :throw-exceptions false
    }))

(defn update-listing-calendar [request-data]
  (for [listing request-data]
    (do
      (update-api-data "/listings/calendars" listing))))
