(ns aoc-2024.day3
  (:require [aoc-2024.util :as util]
            [clojure.string :as str]))

(def inputString (str/replace (util/readInput "res/day3/input") #"\n" "")) ;; removing \n as this influences the matching of $ (end of string/line)

(defn findAndParseMULs
  "Finds all present mul statements in the given string and returns its tuples."
  [inputString]
  (map #(str/split (str/replace % #"mul\(|\)" "") #",") (re-seq #"mul\(\d*,\d*\)" inputString)))

(defn oneStar []
  (reduce + (map #(* (Integer/parseInt (first %)) (Integer/parseInt (second %))) (findAndParseMULs inputString))))

(defn twoStar []
  (let [dontTuples (apply concat (map #(findAndParseMULs (first %)) (re-seq #"(don't.*?do[^n])|(don't.*?$)" inputString))) ;; re-seq returns re-groups -> (first) only returns the match
        diffs (map #(* (Integer/parseInt (first %)) (Integer/parseInt (second %))) dontTuples)]
    (- (oneStar) (reduce + diffs))))
