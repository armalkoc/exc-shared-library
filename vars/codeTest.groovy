#!/usr/bin/env groovy

def call() {
    // we have to get inside app directory to be able to run test
    dir("app") {
        sh 'npm install'
        sh 'npm test'
    }
}