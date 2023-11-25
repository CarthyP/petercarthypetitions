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

        stage('Test') {
            steps {
                sh 'mvn test'
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
                artifacts: '**/petercarthyspetitions*.war'
            }
        }

        stage('Deploy') {
            steps {
                input 'Deploy App?'
                sh 'docker build -f Dockerfile -t petercarthyspetitions . '
                sh 'docker rm -f "pappcontainer" || true'
                sh 'docker run --name "pappcontainer" -p 9090:8080 --detach petercarthyspetitions:latest'
            }
        }
    }
}

