pipeline {
    agent any

    stages {
        stage('GetProject') {
            steps {
                git 'https://github.com/CarthyP/petercarthypetitions.git'
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean:clean"

                sh "mvn dependency:copy-dependencies"

                sh "mvn compiler:compile"
            }
        }

        stage('Package') {
            steps {
            sh 'mvn package'
            }
        }

        stage('Exec') {
            steps {
                sh "mvn exec:java"
            }
        }
    }

    post {

            archiveArtifacts allowEmptyArchive: true,
                artifacts: '**/petercarthyspetitions*.war'
        }
    }
}