(ns aoc-2024.day5
  (:require [aoc-2024.util :as util]
            [clojure.string :as str]))

(def inputString (util/readInput "res/day5/input"))
(def rules (map #(map Integer/parseInt (str/split % #"\|")) (str/split (first (str/split inputString #"\n\n")) #"\n")))
(def updates (map #(map Integer/parseInt (str/split % #",")) (str/split (second (str/split inputString #"\n\n")) #"\n")))

(defn checkUpdate
  ([update]
   (every? true? (map #(checkUpdate update %) rules)))
  ([update rule]
   (let [firstIndex (.indexOf update (first rule))
         secondIndex (.indexOf update (second rule))]
     (cond
       (= -1 secondIndex) true
       (< firstIndex secondIndex) true
       :else false))))

(defn oneStar []
  (reduce + (filter some? (map #(if (checkUpdate %)
                                  (util/middle-value %) nil)
                               updates))))

;; Kahn's algorithm for topological sorting

(defn buildGraph [nodes rules]
  (let [nodeSet (set nodes)
        filteredRules (filter (fn [[source target]]
                                (and (nodeSet source) (nodeSet target)))
                              rules) ;; Restricting ruleset to relevant nodes
        adjacencyList (reduce (fn [graph [source target]]
                                (update graph source (fnil conj #{}) target))
                              {}
                              filteredRules)
        inDegreeInit (reduce (fn [degreeMap node]
                               (assoc degreeMap node 0))
                             {}
                             nodes)
        inDegree (reduce (fn [degreeMap [_ target]]
                           (update degreeMap target (fnil inc 0)))
                         inDegreeInit
                         filteredRules)]
    [adjacencyList inDegree]))

(defn topologicalSort [nodes rules]
  (let [[adjacencyList inDegree] (buildGraph nodes rules)
        zeroInDegreeQueue (into clojure.lang.PersistentQueue/EMPTY
                                (filter #(zero? (get inDegree % 0)) nodes))]
    (loop [queue zeroInDegreeQueue
           sortedResult []
           currentInDegree inDegree]
      (if (empty? queue)
        (if (= (count sortedResult) (count nodes))
          sortedResult
          (throw (Exception. "Cannot sort nodes: rules contain a cycle.")))
        (let [currentNode (peek queue)
              updatedQueue (pop queue)
              [newInDegree newQueue]
              (reduce (fn [[degreeMap queue] neighbor]
                        (let [updatedDegree (dec (get degreeMap neighbor))]
                          [(assoc degreeMap neighbor updatedDegree)
                           (if (zero? updatedDegree) (conj queue neighbor) queue)]))
                      [currentInDegree updatedQueue]
                      (get adjacencyList currentNode []))]
          (recur newQueue (conj sortedResult currentNode) newInDegree))))))

(defn sortByRules [nodes rules]
  (let [sortedNodes (topologicalSort nodes rules)
        nodeOrderMap (zipmap sortedNodes (range))]
    (sort-by nodeOrderMap nodes)))

(defn twoStar []
  (reduce + (filter some? (map #(if (checkUpdate %)
                                  nil (util/middle-value (sortByRules % rules)))
                               updates))))
