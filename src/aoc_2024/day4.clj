(ns aoc-2024.day4
  (:require [aoc-2024.util :as util]
            [clojure.string :as str]
            [clojure.core.matrix :as matrix]))

(def inputMatrix (mapv #(str/split % #"") (util/readAndSplitInput "res/day4/input")))
(def reverseInputMatrix (map reverse inputMatrix))

(defn countOccurences
  "Counts all occurences (forward and backward!) in a given vector"
  [inputVector]
  (+ (count (re-seq #"(XMAS)" (apply str inputVector)))
     (count (re-seq #"(XMAS)" (apply str (reverse inputVector))))))

(defn oneStar []
  (let [horizontal (reduce + (map countOccurences (matrix/rows inputMatrix)))
        vertical (reduce + (map countOccurences (matrix/columns inputMatrix)))
        upperDiagonals (reduce + (map #(countOccurences (matrix/diagonal inputMatrix %)) (range (matrix/column-count inputMatrix))))
        lowerDiagonals (reduce + (map #(countOccurences (matrix/diagonal inputMatrix (* % -1))) (range 1 (matrix/row-count inputMatrix))))
        upperReverseDiagonals (reduce + (map #(countOccurences (matrix/diagonal reverseInputMatrix %)) (range (matrix/column-count reverseInputMatrix))))
        lowerReverseDiagonals (reduce + (map #(countOccurences (matrix/diagonal reverseInputMatrix (* % -1))) (range 1 (matrix/row-count reverseInputMatrix))))]
    (println [horizontal vertical upperDiagonals lowerDiagonals upperReverseDiagonals lowerReverseDiagonals])
    (+ horizontal vertical upperDiagonals lowerDiagonals upperReverseDiagonals lowerReverseDiagonals)))

(defn twoStar []
  0)
