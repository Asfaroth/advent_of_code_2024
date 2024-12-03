(ns aoc-2024.day1
  (:require [aoc-2024.util :as util]
            [clojure.string :as str]))

(def inputStrings (util/readAndSplitInput "res/day1/input"))
(def parsedInput (map (fn [line] (map #(Integer/parseInt %) (str/split line #"   "))) inputStrings))
(def firstList (map first parsedInput))
(def secondList (map second parsedInput))

(defn oneStar []
  (reduce + (map #(abs (- %1 %2)) (sort firstList) (sort secondList))))

(defn twoStar []
  (let [frequencies (frequencies secondList)]
    (reduce + (map #(* % (get frequencies % 0)) firstList))))
