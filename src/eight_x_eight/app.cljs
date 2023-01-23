(ns eight-x-eight.app)

(def background-music
  (new js/Audio "snds/music.mp3"))

(def h js/preact.h)
(defn f [children]
  (apply h js/preact.Fragment nil children))

(def tile-map
  (let [X ["tile__floor"]
        V ["tile__floor" "tile__trap"]
        T ["tile__base"]
        _ ["tile__empty"]]
    [[_ _ X _ _ _ X _ _ _ X _ _]
     [_ _ T _ _ _ T _ _ _ T _ _]
     [X _ _ _ X _ _ _ X _ _ _ X]
     [T _ _ _ T _ _ _ T _ _ _ T]
     [_ _ X _ _ _ V _ _ _ X _ _]
     [_ _ T _ _ _ T _ _ _ T _ _]
     [X _ _ _ V _ _ _ V _ _ _ X]
     [T _ _ _ T _ _ _ T _ _ _ T]
     [_ _ X _ _ _ V _ _ _ X _ _]
     [_ _ T _ _ _ T _ _ _ T _ _]
     [X _ _ _ X _ _ _ X _ _ _ X]
     [T _ _ _ T _ _ _ T _ _ _ T]
     [_ _ X _ _ _ X _ _ _ X _ _]
     [_ _ T _ _ _ T _ _ _ T _ _]]))

(defn board-screen [_]
  (f [(h "div" #js {:class "board"}
         (f (for [y (range 0 14)
                  x (range 0 13)]
              (h "div" #js {:class "tile"}
                 (f (mapv (fn [layer]
                            (h "div" #js {:class layer})) (get-in tile-map [y x])))))))
      (h "button" #js {:onclick (fn [_]
                                  (set! background-music -loop true)
                                  (.play background-music))}
         "MUSIC")]))

(let [container (.getElementById js/document "screen-area")]
  (defn renderer [full-state]
    (js/preact.render (board-screen full-state) container)))

(renderer nil)

;; preact signals seems similar to re-frame (will it work with figwheel?)
;; macro for making preact easier
;; tap to teleport
;; css animation transition to create looping background
