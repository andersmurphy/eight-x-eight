(ns eight-x-eight.app)

(def background-music
  (new js/Audio "snds/music.mp3"))

(def h js/preact.h)
(defn f [children]
  (apply h js/preact.Fragment nil children))

(defn board-screen [_]
  (f [(h "div" #js {:class "board"}
         (h "div" #js {:class "tile" :key 0}
            (h "div" #js {:class "tile__floor"})
            (h "div" #js {:class "tile__treasure"}))
         (f (for [k (range 1 (inc 64))]
              (if (even? k)
                (h "div" #js {:class "tile" :key k}
                   (h "div" #js {:class "tile__floor"})
                   (h "div" #js {:class "tile__monster"}))
                (h "div" #js {:class "tile" :key k}
                   (h "div" #js {:class "tile__wall"}))))))
      (h "button" #js {:class   "nes-btn"
                       :onclick (fn [_]
                                  (set! background-music -loop true)
                                  (.play background-music))}
         "MUSIC")]))

(let [container (.getElementById js/document "screen-area")]
  (defn renderer [full-state]
    (js/preact.render (board-screen full-state) container)))

(renderer nil)

;; preact supports svg
;; preact signals seems similar to re-frame (will it work with figwheel?)
;; need to be able to mix css classes
;; macro for macking preact easier 
