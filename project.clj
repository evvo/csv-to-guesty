(defproject csv-to-guesty "0.1.0-SNAPSHOT"
  :description "CSV upload to Guesty API"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [lein-light-nrepl "0.3.3"]
                 [dk.ative/docjure "1.11.0"]
                 [cheshire "5.7.0"]
                 [clj-http "3.4.1"]
                 [hiccup "1.0.5"]
                 [ring/ring-core "1.5.1"]
                 [ring/ring-devel "1.5.1"]
                 [ring/ring-jetty-adapter "1.5.1"]
                 [lein-ring "0.11.0"]
                 [compojure "1.5.2"]
                 [com.cemerick/friend "0.2.3"]
                 [clojure-csv/clojure-csv "2.0.1"]
                 [clj-time "0.13.0"]]
  :repl-options {:nrepl-middleware [lighttable.nrepl.handler/lighttable-ops]}
  :main ^:skip-aot csv-to-guesty.core

;;   :ring {:handler csv-to-guesty.web/app
;;          :nrepl {:start? true
;;                  :port 9998}}

  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
