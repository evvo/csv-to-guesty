(ns csv-to-guesty.web.authentication
  (:use [csv-to-guesty.web.users])
  (:require [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds]))
  (:gen-class))

(defn basic-authentication-wrapper [ring-handler users]
  (friend/authenticate ring-handler
                           {:workflows [(workflows/http-basic)]
                            :credential-fn (partial creds/bcrypt-credential-fn users)
                            :unauthenticated-handler #(workflows/http-basic-deny "Unauthorized" %)
                            :realm "Auth"}))
