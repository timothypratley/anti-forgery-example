(defproject anti-forgery-example "0.1.0-SNAPSHOT"
  :description "Demostrates the use of anti-forgery tokens"
  :url "http://github.com/timothypratley/anti-forgery-example"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler anti-forgery-example.handler/app})
