#!/usr/bin/env groovy

package com.example

class Docker implements Serialzable {

    def source

    Docker(pipeline) {
        this.source = pipeline

    }

    def buildDockerImage() {
        withCredentials([usernamePassword(credentialsId: 'docker-full-pip', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            sh "docker build -t 192.168.0.112:3031/docker-full-pipeline:twn-exc-app-${IMAGE_NAME} ."
            sh 'echo $PASS | docker login -u $USER --password-stdin 192.168.0.112:3031'
            sh "docker push 192.168.0.112:3031/docker-full-pipeline:twn-exc-app-${IMAGE_NAME}"
        }
    }
}