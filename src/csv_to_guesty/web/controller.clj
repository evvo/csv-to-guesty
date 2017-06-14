(ns csv-to-guesty.web.controller
  (:use [csv-to-guesty.web.file-handler]
        [csv-to-guesty.web.views]
        [csv-to-guesty.parsers.parse-handler :as file-parser]
        [csv-to-guesty.api])
  (:gen-class))


(defn upload-file-to-api [params]
  (let [file (handle-upload params)
        parsedFileData (file-parser/prepare-data-for-api (str upload-directory file))
        fileSentToAPI (update-listing-calendar parsedFileData)
        errors (remove (fn [x] (= 200 (x :status))) fileSentToAPI)]
      (do
        (delete-uploaded-file file)
        (if (empty? errors)
          (html-response (upload-success) 200)
          (html-response (error-page ((first errors) :body)) 500)))))

(defn validate-file [params]
  (if (and
        (not (empty? (get params "file")))
        (< 0 ((get params "file") :size))
        (> 500000 ((get params "file") :size))
        (validate-extension ((get params "file") :filename)))
    true
    false))

(defn hand-file-upload [params]
  (if (validate-file params)
    (upload-file-to-api params)
    (html-response (error-page "Error in the uploaded file") 500)))
