(ns aoc-2024.day2
  (:require [aoc-2024.util :as util]
            [clojure.string :as str]))

(def inputStrings (util/readAndSplitInput "res/day2/input"))
(def parsedInput (map (fn [line] (map #(Integer/parseInt %) (str/split line #" "))) inputStrings))
(def distances (map (fn [line] (map - (rest line) line)) parsedInput))

(defn checkSafeDistance
  [d]
  (cond
    (= 0 d) false
    (< 3 (abs d)) false
    :else d))

(defn oneStar []
  (count (filter int? (map (fn [line] (reduce #(cond
                                                 (false? %1) false
                                                 (and (< 0 %1)
                                                      (< 0 %2)) (checkSafeDistance %2)
                                                 (and (> 0 %1)
                                                      (> 0 %2)) (checkSafeDistance %2)
                                                 :else false)
                                              (checkSafeDistance (first line)) line)) distances))))

(defn twoStar [])
