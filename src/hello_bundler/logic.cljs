(ns hello-bundler.logic)

(deftype LVAR [])

(defn lvar []
  (->LVAR))

(defn lvar? [x]
  (instance? LVAR x))

(defn walk [env a]
  (if-let [a (get env a)]
    (recur env a)
    a))



(defn unify [env a b]
  (let [a' (walk env a)
        b' (walk env b)]
    (cond
      (lvar? a') (assoc env a' b')
      (lvar? b') (assoc env b' a')
      :else (when (= a' b') env ))))


(defn eq [a b]
  (keep
   (fn [env]
     (unify env a b))))


(let [a (lvar)
      b (lvar)
      c (lvar)]
  (unify {a 42
          b a
          c b}  c a))


(let [a (lvar)
      b (lvar)
      c (lvar)]
  (unify {a 42
          b a
          c b} 42  c))

(let [a (lvar)]
  (walk {a 42} a))

(let [a (lvar)]
  (type 2))


(defn disjunction [& exprs]
  (fn [xf]
    (let [fs (mapv (fn [expr]
                     (expr xf))
                   exprs)]
      (fn ([] xf)
        ([acc] (xf acc))
        ([acc itm]
         (reduce (fn [acc f]
                   (f acc itm))
                 acc fs))))))

(let [a (lvar)]
  (transduce
   (comp
    (eq a 1)
    (keep (fn [env]
            (walk env a))))
   conj
   [{}]))
