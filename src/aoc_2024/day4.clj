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
        diagonals (reduce + (map
                             #(countOccurences (matrix/diagonal inputMatrix %))
                             (range (* (matrix/row-count inputMatrix) -1) (matrix/column-count inputMatrix))))
        reverseDiagonals (reduce + (map
                                    #(countOccurences (matrix/diagonal reverseInputMatrix %))
                                    (range (* (matrix/row-count reverseInputMatrix) -1) (matrix/column-count reverseInputMatrix))))]
    (println [horizontal vertical diagonals reverseDiagonals])
    (+ horizontal vertical diagonals reverseDiagonals)))

(defn twoStar []
  0)
