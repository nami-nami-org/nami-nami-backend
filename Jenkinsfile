pipeline {
    agent any

    environment {
        COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                    url: 'git@github.com:nami-nami-org/nami-nami-backend.git', 
            }
        }

        stage('Build & Deploy') {
            steps {
                sh 'docker-compose down'
                sh 'docker-compose pull'
                sh 'docker-compose up -d --build'
            }
        }
    }

    post {
        success {
            echo "Deploy completado correctamente"
        }
        failure {
            echo "Hubo un error en el deploy"
        }
    }
}
