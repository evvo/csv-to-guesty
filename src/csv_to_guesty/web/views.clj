(ns csv-to-guesty.web.views
  (:use [hiccup.core])
  (:gen-class))


(defn simple-response [message status]
  {:status status
   :headers {"Content-Type" "text/html"}
   :body message})

(defn main-template [content]
  (html [:html
         [:head
          [:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"}]]
         [:body
           [:div {:class "container"}
              [:div {:class "row"}
                 [:div {:class "col-md-6 col-md-offset-3"} content]]]
         ]]))

(defn html-response [html-content status]
  {:status status
   :headers {"Content-Type" "text/html"}
   :body (main-template html-content)})

(defn home-page []
  '[:form {:action "/file" :method "post" :enctype "multipart/form-data"}
    [:div {:class "form-group"}
     [:label {:for "mainFile"} "File upload"]
         [:input {:name "file" :type "file" :size "20" :id "mainFile"}]
         [:button {:type "submit" :name "submit" :value "submit" :class "btn btn-default"} "Submit" ]]])

(defn upload-success []
  [:div {:class "alert alert-success"} "File has been uploaded"])

(defn error-page [& error]
  [:div {:class "alert alert-danger"} (apply str "Error uploading the file: " error)])
