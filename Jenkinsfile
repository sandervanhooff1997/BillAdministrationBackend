pipeline {
    agent none
    environment {
        CI = 'true'
    }
    stages {
        stage('Sonarqube') {
            agent {
                docker 'openjdk:8-jre'
            }
            environment {
                scannerHome = tool 'SonarQubeScanner'
            }
            steps {
                sh 'java -version'
                withSonarQubeEnv('sonarqube') {
                    sh "${scannerHome}/bin/sonar-scanner -X"
                }
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'node:6-alpine'
                    args '-p 3000:3000'
                }
            }
            steps {
                sh 'npm install'
            }
        }
    }
}
