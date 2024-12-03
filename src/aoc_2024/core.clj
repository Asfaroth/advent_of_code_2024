(ns aoc-2024.core
  (:require [aoc-2024.day1 :as day1]))

(defn day1 []
  ""
  (let [oneStarResult (day1/oneStar)
        twoStarResult (day1/twoStar)]
    (println (str "First Star:  " oneStarResult))
    (println (str "Second Star: " twoStarResult))))
