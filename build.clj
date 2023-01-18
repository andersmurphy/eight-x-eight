(ns build
  (:require [clojure.tools.build.api :as b]))

(defn copy-static-assets
  [_]
  (println "Copying static assets...")
  (b/delete {:path "docs"})
  (b/copy-dir {:src-dirs ["resources/public"] :target-dir "docs"}))
