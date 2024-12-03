(ns aoc-2024.core
  (:require [aoc-2024.day1 :as day1]
            [aoc-2024.day2 :as day2]))

(defn printResults
  [first second]
  (println (str "First Star:  " first))
  (println (str "Second Star: " second)))

(defn day1 []
  (printResults (day1/oneStar) (day1/twoStar)))

(defn day2 []
  (printResults (day2/oneStar) (day2/twoStar)))

