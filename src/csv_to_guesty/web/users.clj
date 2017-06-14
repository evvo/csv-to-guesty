(ns csv-to-guesty.web.users
  (:require [cemerick.friend :as friend]
            (cemerick.friend [credentials :as creds]))
  (:gen-class))


(defn format-user [user hashing-fn]
  {(user :username)
   {:username (user :username) :password (hashing-fn (user :password)) :roles (user :roles)}})

(defn get-users-from-config []
  (map #(format-user % creds/hash-bcrypt) (csv-to-guesty.config/global-config :users)))

(defn formatted-users []
  (into {} (get-users-from-config)))

(def web-users (formatted-users))
