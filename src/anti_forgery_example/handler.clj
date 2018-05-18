(ns anti-forgery-example.handler
  (:require [compojure.core :as compojure :refer [GET POST]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.anti-forgery :as af]
            [ring.util.anti-forgery :as afu]
            [hiccup.page :as page]))

(defn home [req]
  (page/html5
    [:head [:title "Hiccup HTML"]]
    [:h2 "Hiccup HTML"]
    [:pre [:code af/*anti-forgery-token*]]
    [:form {:action "/vote" :method "post"}
     [:input {:type "submit"
              :value "Vote!"}]
     (afu/anti-forgery-field)]
    [:script {:src "js/app.js"}]))

(defn vote [params]
  (println "Voted!" params)
  (page/html5
    [:h1 "You voted :)"]))

(def app-routes
  (compojure/routes
    (GET "/" req (home req))
    (GET "/token" req
      (do
        (println "AF" af/*anti-forgery-token*)
        (or (get-in req [:session :csrf-token])
            (get-in req [:session :ring.middleware.anti-forgery/anti-forgery-token])
            (get-in req [:session "__anti-forgery-token"])
            "No anti-forgery token found")))
    (POST "/vote" {:keys [params]} (vote params))
    (route/resources "/")
    (route/not-found "Not Found")))

(def app
  (wrap-defaults app-routes site-defaults))
