(ns eight-x-eight.app
  (:require
   ["react-dom/client" :refer [createRoot]]
   [sablono.core :as sab :include-macros true]))

(defn main-template [_]
  [:div.board
   (concat
    [[:div.tile {:key 1}
      [:div.tile__floor]
      [:div.tile__treasure]]]
    (for [k (range 1 (inc 64))]
      (if (even? k)
        [:div.tile {:key k}
         [:div.tile__floor]
         [:div.tile__monster]]
        [:div.tile {:key k}
         [:div.tile__wall {:key k}]])))])

(let [container (.getElementById js/document "board-area")
      root      (createRoot container)]
  (defn renderer [full-state]
        (.render root (sab/html (main-template full-state)))))

(renderer nil)

;; TODO: get repl working (maybe just use cljc for logic

;; TODO: add sound
;; TODO: add splash/info screen
