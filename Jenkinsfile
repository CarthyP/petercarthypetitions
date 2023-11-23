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
                sh 'docker build -f Dockerfile -t petitionsapp . '
                sh 'docker rm -f "pappcontainer" || true'
                sh 'docker run --name "pappcontainer" -p 9090:8080 --detach petitionsapp:latest'
            }
        }
    }
}

