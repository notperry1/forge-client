pipeline {
    agent { docker 'gradle:latest' } 
    stages {
        stage('Gradle Build') {
            steps {
                sh 'gradle build'
            }
        }
    }
}
