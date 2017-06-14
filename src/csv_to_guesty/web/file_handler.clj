(ns csv-to-guesty.web.file-handler
  (:use [clojure.java.io :as io])
  (:import [org.apache.commons.io FilenameUtils])
  (:gen-class))


(def upload-directory-path (csv-to-guesty.config/global-config :upload_directory))

(defn get-file-field [request-params]
  (get request-params "file"))

(def upload-directory
  (apply str "resources" (java.io.File/separator) upload-directory-path (java.io.File/separator)))

(defn generate-file-path[filename])

(defn validate-extension [filename]
  (if (= "csv" (FilenameUtils/getExtension filename))
    true
    false))

(defn copy-template-file [tempfile filename]
  (try
      (nil? (io/copy tempfile (io/file upload-directory filename)))
      (catch Exception e (str "exception: " (.getMessage e)))))

(defn delete-uploaded-file [filename]
  (try
      (nil? (io/delete-file (str upload-directory filename)))
      (catch Exception e (str "exception: " (.getMessage e)))))

(defn upload-file [file]
  (let [filename (file :filename)
        size (file :size)
        tempfile (file :tempfile)]
      (if (copy-template-file tempfile filename)
        filename
        false)))

(defn handle-upload [request-params]
  (upload-file (get-file-field request-params)))
