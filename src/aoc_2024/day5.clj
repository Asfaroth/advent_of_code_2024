(ns aoc-2024.day5
  (:require [aoc-2024.util :as util]
            [clojure.string :as str]))

(def inputString (util/readInput "res/day5/input"))
(def rules (map #(map Integer/parseInt (str/split % #"\|")) (str/split (first (str/split inputString #"\n\n")) #"\n")))
(def updates (map #(map Integer/parseInt (str/split % #",")) (str/split (second (str/split inputString #"\n\n")) #"\n")))

(defn checkUpdate
  [update]
  (every? true? (map #(let [firstIndex (.indexOf update (first %))
                            secondIndex (.indexOf update (second %))]
                        (cond
                          (= -1 secondIndex) true
                          (< firstIndex secondIndex) true
                          :else false)) rules)))

(defn oneStar []
  (reduce + (filter some? (map #(if (checkUpdate %)
                                  (util/middle-value %) nil)
                               updates))))

(defn twoStar []
  0)
