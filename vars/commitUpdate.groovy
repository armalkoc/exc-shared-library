#!/usr/bin/env groovy

def call() {
    withCredentials([usernamePassword(credentialsId: 'github-armalkoc', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh 'git config --global user.email armin.bootcamp@gmail.com'
        sh 'git config --global user.name "armalkoc"'
        sh 'git remote set-url origin https://$USER:$PASS@github.com/armalkoc/twn-exercise-jenkins.git'
        sh 'git add .'
        sh 'git commit -m "ci: version bump"'
        sh 'git push origin HEAD:master'
    }
}