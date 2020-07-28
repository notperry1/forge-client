pipeline {
  agent {
    docker 'gradle:latest'
  }
  stages {
    stage('Gradle Build') {
      steps {
        sh '''./gradlew build
'''
      }
    }

  }
  post {
    always {
      archiveArtifacts(artifacts: 'build/libs/**/*.jar', fingerprint: true)
    }

  }
}