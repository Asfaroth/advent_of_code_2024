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
    (+ horizontal vertical diagonals reverseDiagonals)))

(defn check3x3Matrix
  "Returns either 1 if an X-MAS structure is found or 0 if not."
  [matrix]
  (if (and
       (re-matches #"(MAS)|(SAM)" (apply str (matrix/diagonal matrix)))
       (re-matches #"(MAS)|(SAM)" (apply str (matrix/diagonal (map reverse matrix)))))
    1 0))

(defn testMatrix
  ([matrix] (testMatrix matrix 0 0 0))
  ([matrix xPos yPos occurences]
   (if (< xPos (- (matrix/column-count matrix) 3))
     (recur matrix (inc xPos) yPos (+ (check3x3Matrix (matrix/submatrix matrix xPos 3 yPos 3)) occurences))
     (if (< yPos (- (matrix/row-count matrix) 3))
       (recur matrix 0 (inc yPos) (+ (check3x3Matrix (matrix/submatrix matrix xPos 3 yPos 3)) occurences))
       (+ (check3x3Matrix (matrix/submatrix matrix xPos 3 yPos 3)) occurences)))))

(defn twoStar []
  (testMatrix inputMatrix))
