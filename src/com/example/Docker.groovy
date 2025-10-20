#!/usr/bin/env groovy

package com.example

class Docker implements Serializable {

    def source

    Docker(pipeline) {
        this.source = pipeline

    }

    def buildDockerImage() {
        source.withCredentials([source.usernamePassword(credentialsId: 'docker-full-pip', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            source.sh "docker build -t 192.168.0.112:3031/docker-full-pipeline:twn-exc-app-${IMAGE_NAME} ."
            source.sh "echo '${source.PASS}' | docker login -u '${source.USER}' --password-stdin 192.168.0.112:3031"
            source.sh "docker push 192.168.0.112:3031/docker-full-pipeline:twn-exc-app-${IMAGE_NAME}"
        }
    }
}