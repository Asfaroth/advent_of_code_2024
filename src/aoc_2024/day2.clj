(ns aoc-2024.day2
  (:require [aoc-2024.util :as util]
            [clojure.string :as str]))

(def inputStrings (util/readAndSplitInput "res/day2/input"))
(def parsedInput (map (fn [line] (map #(Integer/parseInt %) (str/split line #" "))) inputStrings))

(defn checkReport
  [report]
  (let [asc? (apply < report)
        desc? (apply > report)
        close? (every? #(> 4 (Math/abs (- (first %) (second %))))
                       (zipmap report (rest report)))]
    (and (or asc? desc?) close?)))

(defn oneStar []
  (count (filter true? (map checkReport parsedInput))))

(defn twoStar []
  (let [combinations (map
                      #(concat (list %) (map (fn [index] (util/drop-nth index %)) (range (count %))))
                      parsedInput)] ;; check every combination with items left out
    (count (filter true? (map (fn [combination] (some checkReport combination)) combinations))))) ;; some aborts as soon as one combination is true
