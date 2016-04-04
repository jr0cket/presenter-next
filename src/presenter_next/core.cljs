(ns presenter-next.core
  (:require
   #_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defcard first-card
  (sab/html [:div
             [:h1 "This is your first devcard! - redux"]
             [:p "This is my excellent presentation, please give your feedback"]]))

(defcard second-card
  (sab/html [:div
             [:h1 "Second devcard - tell me what you think about it"]
             [:p "Are you bored yet?"]
             ]))

(defcard vote-card
  (sab/html [:div
             [:h1 "Vote if you like the slide"]
             ]))

(defonce first-example-state (atom {:count 0}))

(defcard example-counter
  (fn [data-atom owner]
    (sab/html [:h3 "How many people like this slide: " (:count @data-atom)]))
  first-example-state)

(defcard example-incrementer
  (fn [data-atom owner]
    (sab/html [:button {:onClick (fn [] (swap! data-atom update-in [:count] inc)) } "increment"]))
  first-example-state)

(defcard example-decrementer
  (fn [data-atom owner]
    (sab/html [:button {:onClick (fn [] (swap! data-atom update-in [:count] dec)) } "decrement"]))
  first-example-state)

(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (js/React.render (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html

