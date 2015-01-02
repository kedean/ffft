(ns clj-fft.fft-test
  (:require [clojure.test :refer :all]
            [clj-fft.fft :refer :all])
  (:use clj-fft.complex)
  (:import clj_fft.complex.Complex)
  (:import java.lang.Math))

(defn round [n places]
  (/
    (Math/round
      (* n places))
    places))

(def error-tolerance 0.0001)

(deftest fft-test-1d
  (testing "Kevin needs to implement tests!."
  (let [
    expected-out
    (list
      (Complex. 10 0)
      (Complex. -2 2)
      (Complex. -2 0)
      (Complex. -2 -2)
      )
      actual-out
      (fft (list 1 2 3 4))
      ]
      (doall (map
        (fn [exp act]
                (let [diff (complex-sub exp act)]
                (do
                  (assert
                    (< (real diff) error-tolerance))
                    (assert
                      (< (imag diff) error-tolerance)))))
                      expected-out actual-out))
                      )))

(deftest fft-test-3x3
  (testing "Kevin needs to implement tests!."
    (let [
        expected-out
        (list
          [(Complex. 45.0 0)         (Complex. -4.5 2.59807621)  (Complex. -4.5 -2.59807621)]
          [(Complex. -13.5 7.79422863)    (Complex. 0.0 0)           (Complex. 0.0 0)]
          [(Complex. -13.5 -7.79422863)   (Complex. 0.0 0)           (Complex. 0.0 0)]
        )
        actual-out
        (fft (list [1 2 3] [4 5 6] [7 8 9]))
      ]
      (doall (map
        (fn [exp-level-1 act-level-1]
          (doall
          (map
            (fn [exp act]
              (let [diff (complex-sub exp act)]
                (do
                  (assert
                    (< (real diff) error-tolerance))
                  (assert
                    (< (imag diff) error-tolerance)))))
            exp-level-1 act-level-1
            )))
        expected-out actual-out))
      )))

(deftest ifft-test-1d
  (testing "Kevin needs to implement tests!."
  (let [
      original-list
      (list 1 2 3)
      actual-out
      (ifft (fft original-list))
      ]
      (doall (map
        (fn [exp act]
                (let [diff (complex-sub exp act)]
                (do
                  (assert
                    (< (real diff) error-tolerance))
                    (assert
                      (< (imag diff) error-tolerance)))))
                      original-list actual-out))
                      )))

(deftest fft-complex-input
  (testing "fft should take complex inputs as well."
    (fft (list (Complex. 1 0) (Complex. 2 0) (Complex. 3 1)))
  ))

(deftest fft-real-input
  (testing "fft should take real inputs."
    (fft (list 1 2 3))
  ))

(deftest ifft-complex-input
  (testing "ifft should take complex inputs as well."
    (ifft (list (Complex. 1 0) (Complex. 2 0) (Complex. 3 1)))
  ))

(deftest ifft-real-input
  (testing "ifft should take real inputs."
    (ifft (list 1 2 3))
  ))