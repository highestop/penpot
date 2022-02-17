;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at http://mozilla.org/MPL/2.0/.
;;
;; Copyright (c) UXBOX Labs SL

(ns app.common.geom.shapes.text
  (:require
   [app.common.geom.shapes.common :as gco]
   [app.common.geom.shapes.rect :as gpr]
   [app.common.geom.shapes.transforms :as gtr]))

(defn position-data->rect
  [{:keys [x y width height]}]
  {:x x
   :y (- y height)
   :width width
   :height height})

(defn position-data-bounding-box
  [{:keys [position-data] :as shape}]
  (let [points    (->> position-data
                       (mapcat (comp gpr/rect->points position-data->rect)))
        transform (gtr/transform-matrix shape)
        points    (gco/transform-points points transform)]
    (gpr/points->selrect points)))
