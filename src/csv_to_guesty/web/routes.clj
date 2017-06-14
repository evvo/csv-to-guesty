(ns csv-to-guesty.web.routes
  (:use [compojure.core :as compojure]
        [compojure.route :as route]
        [csv-to-guesty.web.views]
        [csv-to-guesty.web.controller])
  (:require [compojure.handler :as handler]
            [cemerick.friend :as friend])
  (:gen-class))


(defroutes public-routes
;;   Placeholder for the public routes
  )

(defroutes protected-routes
  (GET "/" request (html-response (home-page) 200))
  (POST "/file" {params :params} (hand-file-upload params)))

(defroutes app-routes
  (compojure/wrap-routes protected-routes
                         friend/wrap-authorize #{:staff/admin})
  (compojure/routes public-routes)
  (route/not-found "NOT FOUND"))
