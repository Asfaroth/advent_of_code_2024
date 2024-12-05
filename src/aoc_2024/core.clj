(ns aoc-2024.core
  (:require [aoc-2024.day1 :as day1]
            [aoc-2024.day2 :as day2]
            [aoc-2024.day3 :as day3]
            [aoc-2024.day4 :as day4]
            [aoc-2024.day5 :as day5]))

(defn printResults
  [first second]
  (println (str "First Star:  " first))
  (println (str "Second Star: " second)))

(defn day1 []
  (printResults (day1/oneStar) (day1/twoStar)))

(defn day2 []
  (printResults (day2/oneStar) (day2/twoStar)))

(defn day3 []
  (printResults (day3/oneStar) (day3/twoStar)))

(defn day4 []
  (printResults (day4/oneStar) (day4/twoStar)))

(defn day5 []
  (printResults (day5/oneStar) (day5/twoStar)))
