(ns eight-x-eight.app
  (:require
   ["react-dom/client" :refer [createRoot]]
   [sablono.core :as sab :include-macros true]))

(def background-music
  (new js/Audio "snds/music.mp3"))

(defn board-screen [_]
  [[:div.board
   (concat
    [[:div.tile {:key 0}
      [:div.tile__floor]
      [:div.tile__treasure]]]
    (for [k (range 1 (inc 64))]
      (if (even? k)
        [:div.tile {:key k}
         [:div.tile__floor]
         [:div.tile__monster]]
        [:div.tile {:key k}
         [:div.tile__wall {:key k}]])))]
   [:button.nes-btn {:onClick #(do
                                 (set! background-music -loop true)
                                 (.play background-music))} "MUSIC"]])

(let [container (.getElementById js/document "screen-area")
      root      (createRoot container)]
  (defn renderer [full-state]
    (.render root (sab/html (board-screen full-state)))))
(renderer nil)

;; TODO: get repl working (maybe just use cljc for logic
