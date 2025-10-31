#!/usr/bin/env groovy

def call() {
    // we have to enter inside app directory because thats where our package.json is located
    dir("app") {
    // update app version in the package.js file with one of the release types: major, minor or patch
    // following command updates the minor version in package.json and ensures no Git commands are executed in the background, preventing automatic commits or tags in your Jenkins Pipeline
        sh 'npm version minor --no-git-tag-version'
                       
        // after version has been changed we have to read version from package.json file and store it in the version variable
        def packageJson = readJSON file: 'package.json'
        def version = packageJson.version
                        
        // here we will define our image name based on version variable and build number variable
        env.IMAGE_NAME = "$version-$BUILD_NUMBER"
        return "${version}-${BUILD_NUMBER}"

        // alternative solution without Pipeline Utility Steps plugin:
        // def version = sh (returnStdout: true, script: "grep 'version' package.json | cut -d '\"' -f4 | tr '\\n' '\\0'")
        env.IMAGE_NAME = "$version-$BUILD_NUMBER"
    }
}