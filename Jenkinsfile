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

            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage ('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true,
                artifacts: '**/petitionsapp*.jar'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker build -f Dockerfile -t myapp . '
            }
        }
    }
}

