#!/usr/bin/env groovy

package com.example

class Docker implements Serializable {

    def source

    Docker(pipeline) {
        this.source = pipeline

    }

    def buildDockerImage() {
        source.withCredentials([source.usernamePassword(credentialsId: 'docker-hub-private-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            source.sh "docker build -t ${source.IMAGE_NAME} ."
            source.sh "echo '${source.PASS}' | docker login -u '${source.USER}' --password-stdin"
            source.sh "docker push ${source.IMAGE_NAME}"
        }
    }
}