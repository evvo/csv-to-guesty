(ns csv-to-guesty.web.main
  (:use [ring.adapter.jetty]
        [ring.middleware.reload]
        [ring.middleware.stacktrace]
        [ring.middleware.params]
        [ring.middleware.multipart-params]
        [csv-to-guesty.web.authentication]
        [csv-to-guesty.web.users]
        [csv-to-guesty.web.routes])
  (:gen-class))


(def app-development
  (-> app-routes
      (basic-authentication-wrapper web-users)
      wrap-params
      wrap-multipart-params
      (wrap-reload)
      (wrap-stacktrace)))

(def app-production
  (-> app-routes
      (basic-authentication-wrapper web-users)
      wrap-params
      wrap-multipart-params))

(defn start-server []
  (if (= "development" (csv-to-guesty.config/global-config :environment))
    (run-jetty #'app-development {:port (csv-to-guesty.config/global-config :port) :join? false})
    (run-jetty #'app-production {:port (csv-to-guesty.config/global-config :port) :join? false})))
