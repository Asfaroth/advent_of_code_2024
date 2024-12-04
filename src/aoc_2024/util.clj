(ns aoc-2024.util
  (:require [clojure.string :as str]))

(defn readAndSplitInput
  "Reads the file at the given path and returns the contents as list of strings splitted line by line."
  [file]
  (str/split (slurp file) #"\n"))

(defn readInput
  [file]
  (slurp file))

(defn drop-nth
  [n coll]
  (concat
   (take n coll)
   (drop (inc n) coll)))
