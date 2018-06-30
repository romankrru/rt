(ns rt.core
    (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(def philosopher-cols
  [:name :country :date])

(def philosophers
  [{:name "Descartes" :country "France" :date 1596}
   {:name "Kant" :country "Prussia" :date 1724}
   {:name "Quine" :country "USA" :date 1908}])

(defn row-ui [cols m]
  [:tr {:key (:name m)} (map (fn [col] [:td {:key col} (get m col)]) cols)])

(defn table-ui [cols rel]
  [:table
   [:thead
    [:tr (map (fn [col] [:th {:key col} (name col)]) cols)]]
   [:tbody
    (map (partial row-ui cols) rel)]])


; Children and Other Props

; <MyUI name="Smith" age={72}>
;   <Child/>
;   <Child/>
; </MyUI>

(defn my-ui [{:keys [name age]}]
  [:div "Mr. " name " is " age " years old"])

(defn my-ui* [name age]
  [:div "Mr. " name " is " age " years old"])

(defn title-ul-ui [{:keys [title]} & children]
  [:section
   [:h3 title]
   [:ul children]])

(defn root []
  [:div
    [title-ul-ui {:title "People"}
     [:li {:key 1} "Smith"]
     [:li {:key 2} "Schmidt"]]
    [my-ui {:name "Smith" :age 72}]
    [my-ui* "Smith" 72]])

(defn hello-world []
  [:div
    [root]
    [table-ui philosopher-cols philosophers]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload [])
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
